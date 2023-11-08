import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ReportInstrumentPerformanceService } from '../service/report-instrument-performance.service';
import { IReportInstrumentPerformance } from '../report-instrument-performance.model';
import { ReportInstrumentPerformanceFormService } from './report-instrument-performance-form.service';

import { ReportInstrumentPerformanceUpdateComponent } from './report-instrument-performance-update.component';

describe('ReportInstrumentPerformance Management Update Component', () => {
  let comp: ReportInstrumentPerformanceUpdateComponent;
  let fixture: ComponentFixture<ReportInstrumentPerformanceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reportInstrumentPerformanceFormService: ReportInstrumentPerformanceFormService;
  let reportInstrumentPerformanceService: ReportInstrumentPerformanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ReportInstrumentPerformanceUpdateComponent],
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
      .overrideTemplate(ReportInstrumentPerformanceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReportInstrumentPerformanceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reportInstrumentPerformanceFormService = TestBed.inject(ReportInstrumentPerformanceFormService);
    reportInstrumentPerformanceService = TestBed.inject(ReportInstrumentPerformanceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const reportInstrumentPerformance: IReportInstrumentPerformance = { id: 456 };

      activatedRoute.data = of({ reportInstrumentPerformance });
      comp.ngOnInit();

      expect(comp.reportInstrumentPerformance).toEqual(reportInstrumentPerformance);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportInstrumentPerformance>>();
      const reportInstrumentPerformance = { id: 123 };
      jest.spyOn(reportInstrumentPerformanceFormService, 'getReportInstrumentPerformance').mockReturnValue(reportInstrumentPerformance);
      jest.spyOn(reportInstrumentPerformanceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportInstrumentPerformance });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportInstrumentPerformance }));
      saveSubject.complete();

      // THEN
      expect(reportInstrumentPerformanceFormService.getReportInstrumentPerformance).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(reportInstrumentPerformanceService.update).toHaveBeenCalledWith(expect.objectContaining(reportInstrumentPerformance));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportInstrumentPerformance>>();
      const reportInstrumentPerformance = { id: 123 };
      jest.spyOn(reportInstrumentPerformanceFormService, 'getReportInstrumentPerformance').mockReturnValue({ id: null });
      jest.spyOn(reportInstrumentPerformanceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportInstrumentPerformance: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportInstrumentPerformance }));
      saveSubject.complete();

      // THEN
      expect(reportInstrumentPerformanceFormService.getReportInstrumentPerformance).toHaveBeenCalled();
      expect(reportInstrumentPerformanceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportInstrumentPerformance>>();
      const reportInstrumentPerformance = { id: 123 };
      jest.spyOn(reportInstrumentPerformanceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportInstrumentPerformance });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reportInstrumentPerformanceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
