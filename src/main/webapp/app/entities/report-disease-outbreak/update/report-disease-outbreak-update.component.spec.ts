import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ReportDiseaseOutbreakService } from '../service/report-disease-outbreak.service';
import { IReportDiseaseOutbreak } from '../report-disease-outbreak.model';
import { ReportDiseaseOutbreakFormService } from './report-disease-outbreak-form.service';

import { ReportDiseaseOutbreakUpdateComponent } from './report-disease-outbreak-update.component';

describe('ReportDiseaseOutbreak Management Update Component', () => {
  let comp: ReportDiseaseOutbreakUpdateComponent;
  let fixture: ComponentFixture<ReportDiseaseOutbreakUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reportDiseaseOutbreakFormService: ReportDiseaseOutbreakFormService;
  let reportDiseaseOutbreakService: ReportDiseaseOutbreakService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ReportDiseaseOutbreakUpdateComponent],
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
      .overrideTemplate(ReportDiseaseOutbreakUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReportDiseaseOutbreakUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reportDiseaseOutbreakFormService = TestBed.inject(ReportDiseaseOutbreakFormService);
    reportDiseaseOutbreakService = TestBed.inject(ReportDiseaseOutbreakService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const reportDiseaseOutbreak: IReportDiseaseOutbreak = { id: 456 };

      activatedRoute.data = of({ reportDiseaseOutbreak });
      comp.ngOnInit();

      expect(comp.reportDiseaseOutbreak).toEqual(reportDiseaseOutbreak);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportDiseaseOutbreak>>();
      const reportDiseaseOutbreak = { id: 123 };
      jest.spyOn(reportDiseaseOutbreakFormService, 'getReportDiseaseOutbreak').mockReturnValue(reportDiseaseOutbreak);
      jest.spyOn(reportDiseaseOutbreakService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportDiseaseOutbreak });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportDiseaseOutbreak }));
      saveSubject.complete();

      // THEN
      expect(reportDiseaseOutbreakFormService.getReportDiseaseOutbreak).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(reportDiseaseOutbreakService.update).toHaveBeenCalledWith(expect.objectContaining(reportDiseaseOutbreak));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportDiseaseOutbreak>>();
      const reportDiseaseOutbreak = { id: 123 };
      jest.spyOn(reportDiseaseOutbreakFormService, 'getReportDiseaseOutbreak').mockReturnValue({ id: null });
      jest.spyOn(reportDiseaseOutbreakService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportDiseaseOutbreak: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportDiseaseOutbreak }));
      saveSubject.complete();

      // THEN
      expect(reportDiseaseOutbreakFormService.getReportDiseaseOutbreak).toHaveBeenCalled();
      expect(reportDiseaseOutbreakService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportDiseaseOutbreak>>();
      const reportDiseaseOutbreak = { id: 123 };
      jest.spyOn(reportDiseaseOutbreakService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportDiseaseOutbreak });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reportDiseaseOutbreakService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
