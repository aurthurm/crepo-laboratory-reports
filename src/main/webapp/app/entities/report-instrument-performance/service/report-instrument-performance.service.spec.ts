import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IReportInstrumentPerformance } from '../report-instrument-performance.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../report-instrument-performance.test-samples';

import { ReportInstrumentPerformanceService } from './report-instrument-performance.service';

const requireRestSample: IReportInstrumentPerformance = {
  ...sampleWithRequiredData,
};

describe('ReportInstrumentPerformance Service', () => {
  let service: ReportInstrumentPerformanceService;
  let httpMock: HttpTestingController;
  let expectedResult: IReportInstrumentPerformance | IReportInstrumentPerformance[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ReportInstrumentPerformanceService);
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

    it('should create a ReportInstrumentPerformance', () => {
      const reportInstrumentPerformance = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(reportInstrumentPerformance).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ReportInstrumentPerformance', () => {
      const reportInstrumentPerformance = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(reportInstrumentPerformance).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ReportInstrumentPerformance', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ReportInstrumentPerformance', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ReportInstrumentPerformance', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addReportInstrumentPerformanceToCollectionIfMissing', () => {
      it('should add a ReportInstrumentPerformance to an empty array', () => {
        const reportInstrumentPerformance: IReportInstrumentPerformance = sampleWithRequiredData;
        expectedResult = service.addReportInstrumentPerformanceToCollectionIfMissing([], reportInstrumentPerformance);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportInstrumentPerformance);
      });

      it('should not add a ReportInstrumentPerformance to an array that contains it', () => {
        const reportInstrumentPerformance: IReportInstrumentPerformance = sampleWithRequiredData;
        const reportInstrumentPerformanceCollection: IReportInstrumentPerformance[] = [
          {
            ...reportInstrumentPerformance,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addReportInstrumentPerformanceToCollectionIfMissing(
          reportInstrumentPerformanceCollection,
          reportInstrumentPerformance,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ReportInstrumentPerformance to an array that doesn't contain it", () => {
        const reportInstrumentPerformance: IReportInstrumentPerformance = sampleWithRequiredData;
        const reportInstrumentPerformanceCollection: IReportInstrumentPerformance[] = [sampleWithPartialData];
        expectedResult = service.addReportInstrumentPerformanceToCollectionIfMissing(
          reportInstrumentPerformanceCollection,
          reportInstrumentPerformance,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportInstrumentPerformance);
      });

      it('should add only unique ReportInstrumentPerformance to an array', () => {
        const reportInstrumentPerformanceArray: IReportInstrumentPerformance[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const reportInstrumentPerformanceCollection: IReportInstrumentPerformance[] = [sampleWithRequiredData];
        expectedResult = service.addReportInstrumentPerformanceToCollectionIfMissing(
          reportInstrumentPerformanceCollection,
          ...reportInstrumentPerformanceArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const reportInstrumentPerformance: IReportInstrumentPerformance = sampleWithRequiredData;
        const reportInstrumentPerformance2: IReportInstrumentPerformance = sampleWithPartialData;
        expectedResult = service.addReportInstrumentPerformanceToCollectionIfMissing(
          [],
          reportInstrumentPerformance,
          reportInstrumentPerformance2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportInstrumentPerformance);
        expect(expectedResult).toContain(reportInstrumentPerformance2);
      });

      it('should accept null and undefined values', () => {
        const reportInstrumentPerformance: IReportInstrumentPerformance = sampleWithRequiredData;
        expectedResult = service.addReportInstrumentPerformanceToCollectionIfMissing([], null, reportInstrumentPerformance, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportInstrumentPerformance);
      });

      it('should return initial array if no ReportInstrumentPerformance is added', () => {
        const reportInstrumentPerformanceCollection: IReportInstrumentPerformance[] = [sampleWithRequiredData];
        expectedResult = service.addReportInstrumentPerformanceToCollectionIfMissing(
          reportInstrumentPerformanceCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(reportInstrumentPerformanceCollection);
      });
    });

    describe('compareReportInstrumentPerformance', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareReportInstrumentPerformance(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareReportInstrumentPerformance(entity1, entity2);
        const compareResult2 = service.compareReportInstrumentPerformance(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareReportInstrumentPerformance(entity1, entity2);
        const compareResult2 = service.compareReportInstrumentPerformance(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareReportInstrumentPerformance(entity1, entity2);
        const compareResult2 = service.compareReportInstrumentPerformance(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
