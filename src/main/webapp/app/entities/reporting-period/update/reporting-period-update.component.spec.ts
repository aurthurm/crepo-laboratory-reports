import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ReportingPeriodService } from '../service/reporting-period.service';
import { IReportingPeriod } from '../reporting-period.model';
import { ReportingPeriodFormService } from './reporting-period-form.service';

import { ReportingPeriodUpdateComponent } from './reporting-period-update.component';

describe('ReportingPeriod Management Update Component', () => {
  let comp: ReportingPeriodUpdateComponent;
  let fixture: ComponentFixture<ReportingPeriodUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reportingPeriodFormService: ReportingPeriodFormService;
  let reportingPeriodService: ReportingPeriodService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ReportingPeriodUpdateComponent],
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
      .overrideTemplate(ReportingPeriodUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReportingPeriodUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reportingPeriodFormService = TestBed.inject(ReportingPeriodFormService);
    reportingPeriodService = TestBed.inject(ReportingPeriodService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const reportingPeriod: IReportingPeriod = { id: 456 };

      activatedRoute.data = of({ reportingPeriod });
      comp.ngOnInit();

      expect(comp.reportingPeriod).toEqual(reportingPeriod);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportingPeriod>>();
      const reportingPeriod = { id: 123 };
      jest.spyOn(reportingPeriodFormService, 'getReportingPeriod').mockReturnValue(reportingPeriod);
      jest.spyOn(reportingPeriodService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportingPeriod });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportingPeriod }));
      saveSubject.complete();

      // THEN
      expect(reportingPeriodFormService.getReportingPeriod).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(reportingPeriodService.update).toHaveBeenCalledWith(expect.objectContaining(reportingPeriod));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportingPeriod>>();
      const reportingPeriod = { id: 123 };
      jest.spyOn(reportingPeriodFormService, 'getReportingPeriod').mockReturnValue({ id: null });
      jest.spyOn(reportingPeriodService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportingPeriod: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportingPeriod }));
      saveSubject.complete();

      // THEN
      expect(reportingPeriodFormService.getReportingPeriod).toHaveBeenCalled();
      expect(reportingPeriodService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportingPeriod>>();
      const reportingPeriod = { id: 123 };
      jest.spyOn(reportingPeriodService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportingPeriod });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reportingPeriodService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
