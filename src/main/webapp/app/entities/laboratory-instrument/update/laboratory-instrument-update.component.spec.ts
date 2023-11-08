import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { LaboratoryInstrumentService } from '../service/laboratory-instrument.service';
import { ILaboratoryInstrument } from '../laboratory-instrument.model';
import { LaboratoryInstrumentFormService } from './laboratory-instrument-form.service';

import { LaboratoryInstrumentUpdateComponent } from './laboratory-instrument-update.component';

describe('LaboratoryInstrument Management Update Component', () => {
  let comp: LaboratoryInstrumentUpdateComponent;
  let fixture: ComponentFixture<LaboratoryInstrumentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let laboratoryInstrumentFormService: LaboratoryInstrumentFormService;
  let laboratoryInstrumentService: LaboratoryInstrumentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), LaboratoryInstrumentUpdateComponent],
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
      .overrideTemplate(LaboratoryInstrumentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LaboratoryInstrumentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    laboratoryInstrumentFormService = TestBed.inject(LaboratoryInstrumentFormService);
    laboratoryInstrumentService = TestBed.inject(LaboratoryInstrumentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const laboratoryInstrument: ILaboratoryInstrument = { id: 456 };

      activatedRoute.data = of({ laboratoryInstrument });
      comp.ngOnInit();

      expect(comp.laboratoryInstrument).toEqual(laboratoryInstrument);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryInstrument>>();
      const laboratoryInstrument = { id: 123 };
      jest.spyOn(laboratoryInstrumentFormService, 'getLaboratoryInstrument').mockReturnValue(laboratoryInstrument);
      jest.spyOn(laboratoryInstrumentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryInstrument });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: laboratoryInstrument }));
      saveSubject.complete();

      // THEN
      expect(laboratoryInstrumentFormService.getLaboratoryInstrument).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(laboratoryInstrumentService.update).toHaveBeenCalledWith(expect.objectContaining(laboratoryInstrument));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryInstrument>>();
      const laboratoryInstrument = { id: 123 };
      jest.spyOn(laboratoryInstrumentFormService, 'getLaboratoryInstrument').mockReturnValue({ id: null });
      jest.spyOn(laboratoryInstrumentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryInstrument: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: laboratoryInstrument }));
      saveSubject.complete();

      // THEN
      expect(laboratoryInstrumentFormService.getLaboratoryInstrument).toHaveBeenCalled();
      expect(laboratoryInstrumentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryInstrument>>();
      const laboratoryInstrument = { id: 123 };
      jest.spyOn(laboratoryInstrumentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryInstrument });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(laboratoryInstrumentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
