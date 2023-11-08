import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IReportDiseaseOutbreak } from '../report-disease-outbreak.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../report-disease-outbreak.test-samples';

import { ReportDiseaseOutbreakService } from './report-disease-outbreak.service';

const requireRestSample: IReportDiseaseOutbreak = {
  ...sampleWithRequiredData,
};

describe('ReportDiseaseOutbreak Service', () => {
  let service: ReportDiseaseOutbreakService;
  let httpMock: HttpTestingController;
  let expectedResult: IReportDiseaseOutbreak | IReportDiseaseOutbreak[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ReportDiseaseOutbreakService);
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

    it('should create a ReportDiseaseOutbreak', () => {
      const reportDiseaseOutbreak = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(reportDiseaseOutbreak).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ReportDiseaseOutbreak', () => {
      const reportDiseaseOutbreak = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(reportDiseaseOutbreak).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ReportDiseaseOutbreak', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ReportDiseaseOutbreak', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ReportDiseaseOutbreak', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addReportDiseaseOutbreakToCollectionIfMissing', () => {
      it('should add a ReportDiseaseOutbreak to an empty array', () => {
        const reportDiseaseOutbreak: IReportDiseaseOutbreak = sampleWithRequiredData;
        expectedResult = service.addReportDiseaseOutbreakToCollectionIfMissing([], reportDiseaseOutbreak);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportDiseaseOutbreak);
      });

      it('should not add a ReportDiseaseOutbreak to an array that contains it', () => {
        const reportDiseaseOutbreak: IReportDiseaseOutbreak = sampleWithRequiredData;
        const reportDiseaseOutbreakCollection: IReportDiseaseOutbreak[] = [
          {
            ...reportDiseaseOutbreak,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addReportDiseaseOutbreakToCollectionIfMissing(reportDiseaseOutbreakCollection, reportDiseaseOutbreak);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ReportDiseaseOutbreak to an array that doesn't contain it", () => {
        const reportDiseaseOutbreak: IReportDiseaseOutbreak = sampleWithRequiredData;
        const reportDiseaseOutbreakCollection: IReportDiseaseOutbreak[] = [sampleWithPartialData];
        expectedResult = service.addReportDiseaseOutbreakToCollectionIfMissing(reportDiseaseOutbreakCollection, reportDiseaseOutbreak);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportDiseaseOutbreak);
      });

      it('should add only unique ReportDiseaseOutbreak to an array', () => {
        const reportDiseaseOutbreakArray: IReportDiseaseOutbreak[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const reportDiseaseOutbreakCollection: IReportDiseaseOutbreak[] = [sampleWithRequiredData];
        expectedResult = service.addReportDiseaseOutbreakToCollectionIfMissing(
          reportDiseaseOutbreakCollection,
          ...reportDiseaseOutbreakArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const reportDiseaseOutbreak: IReportDiseaseOutbreak = sampleWithRequiredData;
        const reportDiseaseOutbreak2: IReportDiseaseOutbreak = sampleWithPartialData;
        expectedResult = service.addReportDiseaseOutbreakToCollectionIfMissing([], reportDiseaseOutbreak, reportDiseaseOutbreak2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportDiseaseOutbreak);
        expect(expectedResult).toContain(reportDiseaseOutbreak2);
      });

      it('should accept null and undefined values', () => {
        const reportDiseaseOutbreak: IReportDiseaseOutbreak = sampleWithRequiredData;
        expectedResult = service.addReportDiseaseOutbreakToCollectionIfMissing([], null, reportDiseaseOutbreak, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportDiseaseOutbreak);
      });

      it('should return initial array if no ReportDiseaseOutbreak is added', () => {
        const reportDiseaseOutbreakCollection: IReportDiseaseOutbreak[] = [sampleWithRequiredData];
        expectedResult = service.addReportDiseaseOutbreakToCollectionIfMissing(reportDiseaseOutbreakCollection, undefined, null);
        expect(expectedResult).toEqual(reportDiseaseOutbreakCollection);
      });
    });

    describe('compareReportDiseaseOutbreak', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareReportDiseaseOutbreak(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareReportDiseaseOutbreak(entity1, entity2);
        const compareResult2 = service.compareReportDiseaseOutbreak(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareReportDiseaseOutbreak(entity1, entity2);
        const compareResult2 = service.compareReportDiseaseOutbreak(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareReportDiseaseOutbreak(entity1, entity2);
        const compareResult2 = service.compareReportDiseaseOutbreak(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
