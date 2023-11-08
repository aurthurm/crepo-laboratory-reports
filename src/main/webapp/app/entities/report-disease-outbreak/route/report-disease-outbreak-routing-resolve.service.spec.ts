import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IReportDiseaseOutbreak } from '../report-disease-outbreak.model';
import { ReportDiseaseOutbreakService } from '../service/report-disease-outbreak.service';

import reportDiseaseOutbreakResolve from './report-disease-outbreak-routing-resolve.service';

describe('ReportDiseaseOutbreak routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: ReportDiseaseOutbreakService;
  let resultReportDiseaseOutbreak: IReportDiseaseOutbreak | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    service = TestBed.inject(ReportDiseaseOutbreakService);
    resultReportDiseaseOutbreak = undefined;
  });

  describe('resolve', () => {
    it('should return IReportDiseaseOutbreak returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        reportDiseaseOutbreakResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultReportDiseaseOutbreak = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultReportDiseaseOutbreak).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        reportDiseaseOutbreakResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultReportDiseaseOutbreak = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultReportDiseaseOutbreak).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IReportDiseaseOutbreak>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        reportDiseaseOutbreakResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultReportDiseaseOutbreak = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultReportDiseaseOutbreak).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
