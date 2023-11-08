import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILaboratoryTest } from '../laboratory-test.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../laboratory-test.test-samples';

import { LaboratoryTestService } from './laboratory-test.service';

const requireRestSample: ILaboratoryTest = {
  ...sampleWithRequiredData,
};

describe('LaboratoryTest Service', () => {
  let service: LaboratoryTestService;
  let httpMock: HttpTestingController;
  let expectedResult: ILaboratoryTest | ILaboratoryTest[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LaboratoryTestService);
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

    it('should create a LaboratoryTest', () => {
      const laboratoryTest = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(laboratoryTest).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a LaboratoryTest', () => {
      const laboratoryTest = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(laboratoryTest).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a LaboratoryTest', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of LaboratoryTest', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a LaboratoryTest', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addLaboratoryTestToCollectionIfMissing', () => {
      it('should add a LaboratoryTest to an empty array', () => {
        const laboratoryTest: ILaboratoryTest = sampleWithRequiredData;
        expectedResult = service.addLaboratoryTestToCollectionIfMissing([], laboratoryTest);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(laboratoryTest);
      });

      it('should not add a LaboratoryTest to an array that contains it', () => {
        const laboratoryTest: ILaboratoryTest = sampleWithRequiredData;
        const laboratoryTestCollection: ILaboratoryTest[] = [
          {
            ...laboratoryTest,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addLaboratoryTestToCollectionIfMissing(laboratoryTestCollection, laboratoryTest);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a LaboratoryTest to an array that doesn't contain it", () => {
        const laboratoryTest: ILaboratoryTest = sampleWithRequiredData;
        const laboratoryTestCollection: ILaboratoryTest[] = [sampleWithPartialData];
        expectedResult = service.addLaboratoryTestToCollectionIfMissing(laboratoryTestCollection, laboratoryTest);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(laboratoryTest);
      });

      it('should add only unique LaboratoryTest to an array', () => {
        const laboratoryTestArray: ILaboratoryTest[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const laboratoryTestCollection: ILaboratoryTest[] = [sampleWithRequiredData];
        expectedResult = service.addLaboratoryTestToCollectionIfMissing(laboratoryTestCollection, ...laboratoryTestArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const laboratoryTest: ILaboratoryTest = sampleWithRequiredData;
        const laboratoryTest2: ILaboratoryTest = sampleWithPartialData;
        expectedResult = service.addLaboratoryTestToCollectionIfMissing([], laboratoryTest, laboratoryTest2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(laboratoryTest);
        expect(expectedResult).toContain(laboratoryTest2);
      });

      it('should accept null and undefined values', () => {
        const laboratoryTest: ILaboratoryTest = sampleWithRequiredData;
        expectedResult = service.addLaboratoryTestToCollectionIfMissing([], null, laboratoryTest, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(laboratoryTest);
      });

      it('should return initial array if no LaboratoryTest is added', () => {
        const laboratoryTestCollection: ILaboratoryTest[] = [sampleWithRequiredData];
        expectedResult = service.addLaboratoryTestToCollectionIfMissing(laboratoryTestCollection, undefined, null);
        expect(expectedResult).toEqual(laboratoryTestCollection);
      });
    });

    describe('compareLaboratoryTest', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareLaboratoryTest(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareLaboratoryTest(entity1, entity2);
        const compareResult2 = service.compareLaboratoryTest(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareLaboratoryTest(entity1, entity2);
        const compareResult2 = service.compareLaboratoryTest(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareLaboratoryTest(entity1, entity2);
        const compareResult2 = service.compareLaboratoryTest(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
