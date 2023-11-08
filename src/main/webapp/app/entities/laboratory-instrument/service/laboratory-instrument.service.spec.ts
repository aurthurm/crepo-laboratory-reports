import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILaboratoryInstrument } from '../laboratory-instrument.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../laboratory-instrument.test-samples';

import { LaboratoryInstrumentService } from './laboratory-instrument.service';

const requireRestSample: ILaboratoryInstrument = {
  ...sampleWithRequiredData,
};

describe('LaboratoryInstrument Service', () => {
  let service: LaboratoryInstrumentService;
  let httpMock: HttpTestingController;
  let expectedResult: ILaboratoryInstrument | ILaboratoryInstrument[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LaboratoryInstrumentService);
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

    it('should create a LaboratoryInstrument', () => {
      const laboratoryInstrument = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(laboratoryInstrument).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a LaboratoryInstrument', () => {
      const laboratoryInstrument = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(laboratoryInstrument).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a LaboratoryInstrument', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of LaboratoryInstrument', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a LaboratoryInstrument', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addLaboratoryInstrumentToCollectionIfMissing', () => {
      it('should add a LaboratoryInstrument to an empty array', () => {
        const laboratoryInstrument: ILaboratoryInstrument = sampleWithRequiredData;
        expectedResult = service.addLaboratoryInstrumentToCollectionIfMissing([], laboratoryInstrument);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(laboratoryInstrument);
      });

      it('should not add a LaboratoryInstrument to an array that contains it', () => {
        const laboratoryInstrument: ILaboratoryInstrument = sampleWithRequiredData;
        const laboratoryInstrumentCollection: ILaboratoryInstrument[] = [
          {
            ...laboratoryInstrument,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addLaboratoryInstrumentToCollectionIfMissing(laboratoryInstrumentCollection, laboratoryInstrument);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a LaboratoryInstrument to an array that doesn't contain it", () => {
        const laboratoryInstrument: ILaboratoryInstrument = sampleWithRequiredData;
        const laboratoryInstrumentCollection: ILaboratoryInstrument[] = [sampleWithPartialData];
        expectedResult = service.addLaboratoryInstrumentToCollectionIfMissing(laboratoryInstrumentCollection, laboratoryInstrument);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(laboratoryInstrument);
      });

      it('should add only unique LaboratoryInstrument to an array', () => {
        const laboratoryInstrumentArray: ILaboratoryInstrument[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const laboratoryInstrumentCollection: ILaboratoryInstrument[] = [sampleWithRequiredData];
        expectedResult = service.addLaboratoryInstrumentToCollectionIfMissing(laboratoryInstrumentCollection, ...laboratoryInstrumentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const laboratoryInstrument: ILaboratoryInstrument = sampleWithRequiredData;
        const laboratoryInstrument2: ILaboratoryInstrument = sampleWithPartialData;
        expectedResult = service.addLaboratoryInstrumentToCollectionIfMissing([], laboratoryInstrument, laboratoryInstrument2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(laboratoryInstrument);
        expect(expectedResult).toContain(laboratoryInstrument2);
      });

      it('should accept null and undefined values', () => {
        const laboratoryInstrument: ILaboratoryInstrument = sampleWithRequiredData;
        expectedResult = service.addLaboratoryInstrumentToCollectionIfMissing([], null, laboratoryInstrument, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(laboratoryInstrument);
      });

      it('should return initial array if no LaboratoryInstrument is added', () => {
        const laboratoryInstrumentCollection: ILaboratoryInstrument[] = [sampleWithRequiredData];
        expectedResult = service.addLaboratoryInstrumentToCollectionIfMissing(laboratoryInstrumentCollection, undefined, null);
        expect(expectedResult).toEqual(laboratoryInstrumentCollection);
      });
    });

    describe('compareLaboratoryInstrument', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareLaboratoryInstrument(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareLaboratoryInstrument(entity1, entity2);
        const compareResult2 = service.compareLaboratoryInstrument(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareLaboratoryInstrument(entity1, entity2);
        const compareResult2 = service.compareLaboratoryInstrument(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareLaboratoryInstrument(entity1, entity2);
        const compareResult2 = service.compareLaboratoryInstrument(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
