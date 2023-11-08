import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IReportTestPeformance } from '../report-test-peformance.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../report-test-peformance.test-samples';

import { ReportTestPeformanceService } from './report-test-peformance.service';

const requireRestSample: IReportTestPeformance = {
  ...sampleWithRequiredData,
};

describe('ReportTestPeformance Service', () => {
  let service: ReportTestPeformanceService;
  let httpMock: HttpTestingController;
  let expectedResult: IReportTestPeformance | IReportTestPeformance[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ReportTestPeformanceService);
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

    it('should create a ReportTestPeformance', () => {
      const reportTestPeformance = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(reportTestPeformance).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ReportTestPeformance', () => {
      const reportTestPeformance = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(reportTestPeformance).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ReportTestPeformance', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ReportTestPeformance', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ReportTestPeformance', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addReportTestPeformanceToCollectionIfMissing', () => {
      it('should add a ReportTestPeformance to an empty array', () => {
        const reportTestPeformance: IReportTestPeformance = sampleWithRequiredData;
        expectedResult = service.addReportTestPeformanceToCollectionIfMissing([], reportTestPeformance);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportTestPeformance);
      });

      it('should not add a ReportTestPeformance to an array that contains it', () => {
        const reportTestPeformance: IReportTestPeformance = sampleWithRequiredData;
        const reportTestPeformanceCollection: IReportTestPeformance[] = [
          {
            ...reportTestPeformance,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addReportTestPeformanceToCollectionIfMissing(reportTestPeformanceCollection, reportTestPeformance);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ReportTestPeformance to an array that doesn't contain it", () => {
        const reportTestPeformance: IReportTestPeformance = sampleWithRequiredData;
        const reportTestPeformanceCollection: IReportTestPeformance[] = [sampleWithPartialData];
        expectedResult = service.addReportTestPeformanceToCollectionIfMissing(reportTestPeformanceCollection, reportTestPeformance);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportTestPeformance);
      });

      it('should add only unique ReportTestPeformance to an array', () => {
        const reportTestPeformanceArray: IReportTestPeformance[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const reportTestPeformanceCollection: IReportTestPeformance[] = [sampleWithRequiredData];
        expectedResult = service.addReportTestPeformanceToCollectionIfMissing(reportTestPeformanceCollection, ...reportTestPeformanceArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const reportTestPeformance: IReportTestPeformance = sampleWithRequiredData;
        const reportTestPeformance2: IReportTestPeformance = sampleWithPartialData;
        expectedResult = service.addReportTestPeformanceToCollectionIfMissing([], reportTestPeformance, reportTestPeformance2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportTestPeformance);
        expect(expectedResult).toContain(reportTestPeformance2);
      });

      it('should accept null and undefined values', () => {
        const reportTestPeformance: IReportTestPeformance = sampleWithRequiredData;
        expectedResult = service.addReportTestPeformanceToCollectionIfMissing([], null, reportTestPeformance, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportTestPeformance);
      });

      it('should return initial array if no ReportTestPeformance is added', () => {
        const reportTestPeformanceCollection: IReportTestPeformance[] = [sampleWithRequiredData];
        expectedResult = service.addReportTestPeformanceToCollectionIfMissing(reportTestPeformanceCollection, undefined, null);
        expect(expectedResult).toEqual(reportTestPeformanceCollection);
      });
    });

    describe('compareReportTestPeformance', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareReportTestPeformance(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareReportTestPeformance(entity1, entity2);
        const compareResult2 = service.compareReportTestPeformance(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareReportTestPeformance(entity1, entity2);
        const compareResult2 = service.compareReportTestPeformance(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareReportTestPeformance(entity1, entity2);
        const compareResult2 = service.compareReportTestPeformance(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
