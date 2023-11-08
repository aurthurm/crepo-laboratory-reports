import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ReportTestPeformanceService } from '../service/report-test-peformance.service';
import { IReportTestPeformance } from '../report-test-peformance.model';
import { ReportTestPeformanceFormService } from './report-test-peformance-form.service';

import { ReportTestPeformanceUpdateComponent } from './report-test-peformance-update.component';

describe('ReportTestPeformance Management Update Component', () => {
  let comp: ReportTestPeformanceUpdateComponent;
  let fixture: ComponentFixture<ReportTestPeformanceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reportTestPeformanceFormService: ReportTestPeformanceFormService;
  let reportTestPeformanceService: ReportTestPeformanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ReportTestPeformanceUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ReportTestPeformanceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReportTestPeformanceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reportTestPeformanceFormService = TestBed.inject(ReportTestPeformanceFormService);
    reportTestPeformanceService = TestBed.inject(ReportTestPeformanceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const reportTestPeformance: IReportTestPeformance = { id: 456 };

      activatedRoute.data = of({ reportTestPeformance });
      comp.ngOnInit();

      expect(comp.reportTestPeformance).toEqual(reportTestPeformance);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportTestPeformance>>();
      const reportTestPeformance = { id: 123 };
      jest.spyOn(reportTestPeformanceFormService, 'getReportTestPeformance').mockReturnValue(reportTestPeformance);
      jest.spyOn(reportTestPeformanceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportTestPeformance });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportTestPeformance }));
      saveSubject.complete();

      // THEN
      expect(reportTestPeformanceFormService.getReportTestPeformance).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(reportTestPeformanceService.update).toHaveBeenCalledWith(expect.objectContaining(reportTestPeformance));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportTestPeformance>>();
      const reportTestPeformance = { id: 123 };
      jest.spyOn(reportTestPeformanceFormService, 'getReportTestPeformance').mockReturnValue({ id: null });
      jest.spyOn(reportTestPeformanceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportTestPeformance: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportTestPeformance }));
      saveSubject.complete();

      // THEN
      expect(reportTestPeformanceFormService.getReportTestPeformance).toHaveBeenCalled();
      expect(reportTestPeformanceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportTestPeformance>>();
      const reportTestPeformance = { id: 123 };
      jest.spyOn(reportTestPeformanceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportTestPeformance });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reportTestPeformanceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
