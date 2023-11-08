import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IReportStockOut } from '../report-stock-out.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../report-stock-out.test-samples';

import { ReportStockOutService } from './report-stock-out.service';

const requireRestSample: IReportStockOut = {
  ...sampleWithRequiredData,
};

describe('ReportStockOut Service', () => {
  let service: ReportStockOutService;
  let httpMock: HttpTestingController;
  let expectedResult: IReportStockOut | IReportStockOut[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ReportStockOutService);
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

    it('should create a ReportStockOut', () => {
      const reportStockOut = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(reportStockOut).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ReportStockOut', () => {
      const reportStockOut = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(reportStockOut).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ReportStockOut', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ReportStockOut', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ReportStockOut', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addReportStockOutToCollectionIfMissing', () => {
      it('should add a ReportStockOut to an empty array', () => {
        const reportStockOut: IReportStockOut = sampleWithRequiredData;
        expectedResult = service.addReportStockOutToCollectionIfMissing([], reportStockOut);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportStockOut);
      });

      it('should not add a ReportStockOut to an array that contains it', () => {
        const reportStockOut: IReportStockOut = sampleWithRequiredData;
        const reportStockOutCollection: IReportStockOut[] = [
          {
            ...reportStockOut,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addReportStockOutToCollectionIfMissing(reportStockOutCollection, reportStockOut);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ReportStockOut to an array that doesn't contain it", () => {
        const reportStockOut: IReportStockOut = sampleWithRequiredData;
        const reportStockOutCollection: IReportStockOut[] = [sampleWithPartialData];
        expectedResult = service.addReportStockOutToCollectionIfMissing(reportStockOutCollection, reportStockOut);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportStockOut);
      });

      it('should add only unique ReportStockOut to an array', () => {
        const reportStockOutArray: IReportStockOut[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const reportStockOutCollection: IReportStockOut[] = [sampleWithRequiredData];
        expectedResult = service.addReportStockOutToCollectionIfMissing(reportStockOutCollection, ...reportStockOutArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const reportStockOut: IReportStockOut = sampleWithRequiredData;
        const reportStockOut2: IReportStockOut = sampleWithPartialData;
        expectedResult = service.addReportStockOutToCollectionIfMissing([], reportStockOut, reportStockOut2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportStockOut);
        expect(expectedResult).toContain(reportStockOut2);
      });

      it('should accept null and undefined values', () => {
        const reportStockOut: IReportStockOut = sampleWithRequiredData;
        expectedResult = service.addReportStockOutToCollectionIfMissing([], null, reportStockOut, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportStockOut);
      });

      it('should return initial array if no ReportStockOut is added', () => {
        const reportStockOutCollection: IReportStockOut[] = [sampleWithRequiredData];
        expectedResult = service.addReportStockOutToCollectionIfMissing(reportStockOutCollection, undefined, null);
        expect(expectedResult).toEqual(reportStockOutCollection);
      });
    });

    describe('compareReportStockOut', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareReportStockOut(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareReportStockOut(entity1, entity2);
        const compareResult2 = service.compareReportStockOut(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareReportStockOut(entity1, entity2);
        const compareResult2 = service.compareReportStockOut(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareReportStockOut(entity1, entity2);
        const compareResult2 = service.compareReportStockOut(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
