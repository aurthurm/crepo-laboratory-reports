import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ReportStockOutService } from '../service/report-stock-out.service';
import { IReportStockOut } from '../report-stock-out.model';
import { ReportStockOutFormService } from './report-stock-out-form.service';

import { ReportStockOutUpdateComponent } from './report-stock-out-update.component';

describe('ReportStockOut Management Update Component', () => {
  let comp: ReportStockOutUpdateComponent;
  let fixture: ComponentFixture<ReportStockOutUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reportStockOutFormService: ReportStockOutFormService;
  let reportStockOutService: ReportStockOutService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ReportStockOutUpdateComponent],
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
      .overrideTemplate(ReportStockOutUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReportStockOutUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reportStockOutFormService = TestBed.inject(ReportStockOutFormService);
    reportStockOutService = TestBed.inject(ReportStockOutService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const reportStockOut: IReportStockOut = { id: 456 };

      activatedRoute.data = of({ reportStockOut });
      comp.ngOnInit();

      expect(comp.reportStockOut).toEqual(reportStockOut);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportStockOut>>();
      const reportStockOut = { id: 123 };
      jest.spyOn(reportStockOutFormService, 'getReportStockOut').mockReturnValue(reportStockOut);
      jest.spyOn(reportStockOutService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportStockOut });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportStockOut }));
      saveSubject.complete();

      // THEN
      expect(reportStockOutFormService.getReportStockOut).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(reportStockOutService.update).toHaveBeenCalledWith(expect.objectContaining(reportStockOut));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportStockOut>>();
      const reportStockOut = { id: 123 };
      jest.spyOn(reportStockOutFormService, 'getReportStockOut').mockReturnValue({ id: null });
      jest.spyOn(reportStockOutService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportStockOut: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportStockOut }));
      saveSubject.complete();

      // THEN
      expect(reportStockOutFormService.getReportStockOut).toHaveBeenCalled();
      expect(reportStockOutService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportStockOut>>();
      const reportStockOut = { id: 123 };
      jest.spyOn(reportStockOutService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportStockOut });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reportStockOutService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
