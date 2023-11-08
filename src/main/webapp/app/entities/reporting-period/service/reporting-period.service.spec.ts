import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IReportingPeriod } from '../reporting-period.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../reporting-period.test-samples';

import { ReportingPeriodService } from './reporting-period.service';

const requireRestSample: IReportingPeriod = {
  ...sampleWithRequiredData,
};

describe('ReportingPeriod Service', () => {
  let service: ReportingPeriodService;
  let httpMock: HttpTestingController;
  let expectedResult: IReportingPeriod | IReportingPeriod[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ReportingPeriodService);
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

    it('should create a ReportingPeriod', () => {
      const reportingPeriod = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(reportingPeriod).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ReportingPeriod', () => {
      const reportingPeriod = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(reportingPeriod).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ReportingPeriod', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ReportingPeriod', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ReportingPeriod', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addReportingPeriodToCollectionIfMissing', () => {
      it('should add a ReportingPeriod to an empty array', () => {
        const reportingPeriod: IReportingPeriod = sampleWithRequiredData;
        expectedResult = service.addReportingPeriodToCollectionIfMissing([], reportingPeriod);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportingPeriod);
      });

      it('should not add a ReportingPeriod to an array that contains it', () => {
        const reportingPeriod: IReportingPeriod = sampleWithRequiredData;
        const reportingPeriodCollection: IReportingPeriod[] = [
          {
            ...reportingPeriod,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addReportingPeriodToCollectionIfMissing(reportingPeriodCollection, reportingPeriod);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ReportingPeriod to an array that doesn't contain it", () => {
        const reportingPeriod: IReportingPeriod = sampleWithRequiredData;
        const reportingPeriodCollection: IReportingPeriod[] = [sampleWithPartialData];
        expectedResult = service.addReportingPeriodToCollectionIfMissing(reportingPeriodCollection, reportingPeriod);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportingPeriod);
      });

      it('should add only unique ReportingPeriod to an array', () => {
        const reportingPeriodArray: IReportingPeriod[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const reportingPeriodCollection: IReportingPeriod[] = [sampleWithRequiredData];
        expectedResult = service.addReportingPeriodToCollectionIfMissing(reportingPeriodCollection, ...reportingPeriodArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const reportingPeriod: IReportingPeriod = sampleWithRequiredData;
        const reportingPeriod2: IReportingPeriod = sampleWithPartialData;
        expectedResult = service.addReportingPeriodToCollectionIfMissing([], reportingPeriod, reportingPeriod2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportingPeriod);
        expect(expectedResult).toContain(reportingPeriod2);
      });

      it('should accept null and undefined values', () => {
        const reportingPeriod: IReportingPeriod = sampleWithRequiredData;
        expectedResult = service.addReportingPeriodToCollectionIfMissing([], null, reportingPeriod, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportingPeriod);
      });

      it('should return initial array if no ReportingPeriod is added', () => {
        const reportingPeriodCollection: IReportingPeriod[] = [sampleWithRequiredData];
        expectedResult = service.addReportingPeriodToCollectionIfMissing(reportingPeriodCollection, undefined, null);
        expect(expectedResult).toEqual(reportingPeriodCollection);
      });
    });

    describe('compareReportingPeriod', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareReportingPeriod(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareReportingPeriod(entity1, entity2);
        const compareResult2 = service.compareReportingPeriod(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareReportingPeriod(entity1, entity2);
        const compareResult2 = service.compareReportingPeriod(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareReportingPeriod(entity1, entity2);
        const compareResult2 = service.compareReportingPeriod(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
