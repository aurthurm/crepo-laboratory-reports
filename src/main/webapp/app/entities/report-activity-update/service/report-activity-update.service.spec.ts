import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IReportActivityUpdate } from '../report-activity-update.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../report-activity-update.test-samples';

import { ReportActivityUpdateService } from './report-activity-update.service';

const requireRestSample: IReportActivityUpdate = {
  ...sampleWithRequiredData,
};

describe('ReportActivityUpdate Service', () => {
  let service: ReportActivityUpdateService;
  let httpMock: HttpTestingController;
  let expectedResult: IReportActivityUpdate | IReportActivityUpdate[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ReportActivityUpdateService);
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

    it('should create a ReportActivityUpdate', () => {
      const reportActivityUpdate = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(reportActivityUpdate).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ReportActivityUpdate', () => {
      const reportActivityUpdate = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(reportActivityUpdate).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ReportActivityUpdate', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ReportActivityUpdate', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ReportActivityUpdate', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addReportActivityUpdateToCollectionIfMissing', () => {
      it('should add a ReportActivityUpdate to an empty array', () => {
        const reportActivityUpdate: IReportActivityUpdate = sampleWithRequiredData;
        expectedResult = service.addReportActivityUpdateToCollectionIfMissing([], reportActivityUpdate);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportActivityUpdate);
      });

      it('should not add a ReportActivityUpdate to an array that contains it', () => {
        const reportActivityUpdate: IReportActivityUpdate = sampleWithRequiredData;
        const reportActivityUpdateCollection: IReportActivityUpdate[] = [
          {
            ...reportActivityUpdate,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addReportActivityUpdateToCollectionIfMissing(reportActivityUpdateCollection, reportActivityUpdate);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ReportActivityUpdate to an array that doesn't contain it", () => {
        const reportActivityUpdate: IReportActivityUpdate = sampleWithRequiredData;
        const reportActivityUpdateCollection: IReportActivityUpdate[] = [sampleWithPartialData];
        expectedResult = service.addReportActivityUpdateToCollectionIfMissing(reportActivityUpdateCollection, reportActivityUpdate);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportActivityUpdate);
      });

      it('should add only unique ReportActivityUpdate to an array', () => {
        const reportActivityUpdateArray: IReportActivityUpdate[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const reportActivityUpdateCollection: IReportActivityUpdate[] = [sampleWithRequiredData];
        expectedResult = service.addReportActivityUpdateToCollectionIfMissing(reportActivityUpdateCollection, ...reportActivityUpdateArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const reportActivityUpdate: IReportActivityUpdate = sampleWithRequiredData;
        const reportActivityUpdate2: IReportActivityUpdate = sampleWithPartialData;
        expectedResult = service.addReportActivityUpdateToCollectionIfMissing([], reportActivityUpdate, reportActivityUpdate2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportActivityUpdate);
        expect(expectedResult).toContain(reportActivityUpdate2);
      });

      it('should accept null and undefined values', () => {
        const reportActivityUpdate: IReportActivityUpdate = sampleWithRequiredData;
        expectedResult = service.addReportActivityUpdateToCollectionIfMissing([], null, reportActivityUpdate, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportActivityUpdate);
      });

      it('should return initial array if no ReportActivityUpdate is added', () => {
        const reportActivityUpdateCollection: IReportActivityUpdate[] = [sampleWithRequiredData];
        expectedResult = service.addReportActivityUpdateToCollectionIfMissing(reportActivityUpdateCollection, undefined, null);
        expect(expectedResult).toEqual(reportActivityUpdateCollection);
      });
    });

    describe('compareReportActivityUpdate', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareReportActivityUpdate(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareReportActivityUpdate(entity1, entity2);
        const compareResult2 = service.compareReportActivityUpdate(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareReportActivityUpdate(entity1, entity2);
        const compareResult2 = service.compareReportActivityUpdate(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareReportActivityUpdate(entity1, entity2);
        const compareResult2 = service.compareReportActivityUpdate(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
