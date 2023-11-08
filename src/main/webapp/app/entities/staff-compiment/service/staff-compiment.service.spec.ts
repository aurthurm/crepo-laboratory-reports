import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IStaffCompiment } from '../staff-compiment.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../staff-compiment.test-samples';

import { StaffCompimentService } from './staff-compiment.service';

const requireRestSample: IStaffCompiment = {
  ...sampleWithRequiredData,
};

describe('StaffCompiment Service', () => {
  let service: StaffCompimentService;
  let httpMock: HttpTestingController;
  let expectedResult: IStaffCompiment | IStaffCompiment[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(StaffCompimentService);
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

    it('should create a StaffCompiment', () => {
      const staffCompiment = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(staffCompiment).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a StaffCompiment', () => {
      const staffCompiment = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(staffCompiment).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a StaffCompiment', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of StaffCompiment', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a StaffCompiment', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addStaffCompimentToCollectionIfMissing', () => {
      it('should add a StaffCompiment to an empty array', () => {
        const staffCompiment: IStaffCompiment = sampleWithRequiredData;
        expectedResult = service.addStaffCompimentToCollectionIfMissing([], staffCompiment);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(staffCompiment);
      });

      it('should not add a StaffCompiment to an array that contains it', () => {
        const staffCompiment: IStaffCompiment = sampleWithRequiredData;
        const staffCompimentCollection: IStaffCompiment[] = [
          {
            ...staffCompiment,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addStaffCompimentToCollectionIfMissing(staffCompimentCollection, staffCompiment);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a StaffCompiment to an array that doesn't contain it", () => {
        const staffCompiment: IStaffCompiment = sampleWithRequiredData;
        const staffCompimentCollection: IStaffCompiment[] = [sampleWithPartialData];
        expectedResult = service.addStaffCompimentToCollectionIfMissing(staffCompimentCollection, staffCompiment);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(staffCompiment);
      });

      it('should add only unique StaffCompiment to an array', () => {
        const staffCompimentArray: IStaffCompiment[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const staffCompimentCollection: IStaffCompiment[] = [sampleWithRequiredData];
        expectedResult = service.addStaffCompimentToCollectionIfMissing(staffCompimentCollection, ...staffCompimentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const staffCompiment: IStaffCompiment = sampleWithRequiredData;
        const staffCompiment2: IStaffCompiment = sampleWithPartialData;
        expectedResult = service.addStaffCompimentToCollectionIfMissing([], staffCompiment, staffCompiment2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(staffCompiment);
        expect(expectedResult).toContain(staffCompiment2);
      });

      it('should accept null and undefined values', () => {
        const staffCompiment: IStaffCompiment = sampleWithRequiredData;
        expectedResult = service.addStaffCompimentToCollectionIfMissing([], null, staffCompiment, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(staffCompiment);
      });

      it('should return initial array if no StaffCompiment is added', () => {
        const staffCompimentCollection: IStaffCompiment[] = [sampleWithRequiredData];
        expectedResult = service.addStaffCompimentToCollectionIfMissing(staffCompimentCollection, undefined, null);
        expect(expectedResult).toEqual(staffCompimentCollection);
      });
    });

    describe('compareStaffCompiment', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareStaffCompiment(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareStaffCompiment(entity1, entity2);
        const compareResult2 = service.compareStaffCompiment(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareStaffCompiment(entity1, entity2);
        const compareResult2 = service.compareStaffCompiment(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareStaffCompiment(entity1, entity2);
        const compareResult2 = service.compareStaffCompiment(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
