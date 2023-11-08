import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ReportActivityUpdateService } from '../service/report-activity-update.service';
import { IReportActivityUpdate } from '../report-activity-update.model';
import { ReportActivityUpdateFormService } from './report-activity-update-form.service';

import { ReportActivityUpdateUpdateComponent } from './report-activity-update-update.component';

describe('ReportActivityUpdate Management Update Component', () => {
  let comp: ReportActivityUpdateUpdateComponent;
  let fixture: ComponentFixture<ReportActivityUpdateUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reportActivityUpdateFormService: ReportActivityUpdateFormService;
  let reportActivityUpdateService: ReportActivityUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ReportActivityUpdateUpdateComponent],
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
      .overrideTemplate(ReportActivityUpdateUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReportActivityUpdateUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reportActivityUpdateFormService = TestBed.inject(ReportActivityUpdateFormService);
    reportActivityUpdateService = TestBed.inject(ReportActivityUpdateService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const reportActivityUpdate: IReportActivityUpdate = { id: 456 };

      activatedRoute.data = of({ reportActivityUpdate });
      comp.ngOnInit();

      expect(comp.reportActivityUpdate).toEqual(reportActivityUpdate);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportActivityUpdate>>();
      const reportActivityUpdate = { id: 123 };
      jest.spyOn(reportActivityUpdateFormService, 'getReportActivityUpdate').mockReturnValue(reportActivityUpdate);
      jest.spyOn(reportActivityUpdateService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportActivityUpdate });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportActivityUpdate }));
      saveSubject.complete();

      // THEN
      expect(reportActivityUpdateFormService.getReportActivityUpdate).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(reportActivityUpdateService.update).toHaveBeenCalledWith(expect.objectContaining(reportActivityUpdate));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportActivityUpdate>>();
      const reportActivityUpdate = { id: 123 };
      jest.spyOn(reportActivityUpdateFormService, 'getReportActivityUpdate').mockReturnValue({ id: null });
      jest.spyOn(reportActivityUpdateService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportActivityUpdate: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportActivityUpdate }));
      saveSubject.complete();

      // THEN
      expect(reportActivityUpdateFormService.getReportActivityUpdate).toHaveBeenCalled();
      expect(reportActivityUpdateService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportActivityUpdate>>();
      const reportActivityUpdate = { id: 123 };
      jest.spyOn(reportActivityUpdateService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportActivityUpdate });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reportActivityUpdateService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
