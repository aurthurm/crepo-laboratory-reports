import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILaboratoryStock } from '../laboratory-stock.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../laboratory-stock.test-samples';

import { LaboratoryStockService } from './laboratory-stock.service';

const requireRestSample: ILaboratoryStock = {
  ...sampleWithRequiredData,
};

describe('LaboratoryStock Service', () => {
  let service: LaboratoryStockService;
  let httpMock: HttpTestingController;
  let expectedResult: ILaboratoryStock | ILaboratoryStock[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LaboratoryStockService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a LaboratoryStock', () => {
      const laboratoryStock = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(laboratoryStock).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a LaboratoryStock', () => {
      const laboratoryStock = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(laboratoryStock).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a LaboratoryStock', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of LaboratoryStock', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a LaboratoryStock', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addLaboratoryStockToCollectionIfMissing', () => {
      it('should add a LaboratoryStock to an empty array', () => {
        const laboratoryStock: ILaboratoryStock = sampleWithRequiredData;
        expectedResult = service.addLaboratoryStockToCollectionIfMissing([], laboratoryStock);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(laboratoryStock);
      });

      it('should not add a LaboratoryStock to an array that contains it', () => {
        const laboratoryStock: ILaboratoryStock = sampleWithRequiredData;
        const laboratoryStockCollection: ILaboratoryStock[] = [
          {
            ...laboratoryStock,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addLaboratoryStockToCollectionIfMissing(laboratoryStockCollection, laboratoryStock);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a LaboratoryStock to an array that doesn't contain it", () => {
        const laboratoryStock: ILaboratoryStock = sampleWithRequiredData;
        const laboratoryStockCollection: ILaboratoryStock[] = [sampleWithPartialData];
        expectedResult = service.addLaboratoryStockToCollectionIfMissing(laboratoryStockCollection, laboratoryStock);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(laboratoryStock);
      });

      it('should add only unique LaboratoryStock to an array', () => {
        const laboratoryStockArray: ILaboratoryStock[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const laboratoryStockCollection: ILaboratoryStock[] = [sampleWithRequiredData];
        expectedResult = service.addLaboratoryStockToCollectionIfMissing(laboratoryStockCollection, ...laboratoryStockArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const laboratoryStock: ILaboratoryStock = sampleWithRequiredData;
        const laboratoryStock2: ILaboratoryStock = sampleWithPartialData;
        expectedResult = service.addLaboratoryStockToCollectionIfMissing([], laboratoryStock, laboratoryStock2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(laboratoryStock);
        expect(expectedResult).toContain(laboratoryStock2);
      });

      it('should accept null and undefined values', () => {
        const laboratoryStock: ILaboratoryStock = sampleWithRequiredData;
        expectedResult = service.addLaboratoryStockToCollectionIfMissing([], null, laboratoryStock, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(laboratoryStock);
      });

      it('should return initial array if no LaboratoryStock is added', () => {
        const laboratoryStockCollection: ILaboratoryStock[] = [sampleWithRequiredData];
        expectedResult = service.addLaboratoryStockToCollectionIfMissing(laboratoryStockCollection, undefined, null);
        expect(expectedResult).toEqual(laboratoryStockCollection);
      });
    });

    describe('compareLaboratoryStock', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareLaboratoryStock(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareLaboratoryStock(entity1, entity2);
        const compareResult2 = service.compareLaboratoryStock(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareLaboratoryStock(entity1, entity2);
        const compareResult2 = service.compareLaboratoryStock(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareLaboratoryStock(entity1, entity2);
        const compareResult2 = service.compareLaboratoryStock(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
