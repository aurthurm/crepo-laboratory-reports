import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { LaboratoryTestService } from '../service/laboratory-test.service';
import { ILaboratoryTest } from '../laboratory-test.model';
import { LaboratoryTestFormService } from './laboratory-test-form.service';

import { LaboratoryTestUpdateComponent } from './laboratory-test-update.component';

describe('LaboratoryTest Management Update Component', () => {
  let comp: LaboratoryTestUpdateComponent;
  let fixture: ComponentFixture<LaboratoryTestUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let laboratoryTestFormService: LaboratoryTestFormService;
  let laboratoryTestService: LaboratoryTestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), LaboratoryTestUpdateComponent],
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
      .overrideTemplate(LaboratoryTestUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LaboratoryTestUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    laboratoryTestFormService = TestBed.inject(LaboratoryTestFormService);
    laboratoryTestService = TestBed.inject(LaboratoryTestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const laboratoryTest: ILaboratoryTest = { id: 456 };

      activatedRoute.data = of({ laboratoryTest });
      comp.ngOnInit();

      expect(comp.laboratoryTest).toEqual(laboratoryTest);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryTest>>();
      const laboratoryTest = { id: 123 };
      jest.spyOn(laboratoryTestFormService, 'getLaboratoryTest').mockReturnValue(laboratoryTest);
      jest.spyOn(laboratoryTestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryTest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: laboratoryTest }));
      saveSubject.complete();

      // THEN
      expect(laboratoryTestFormService.getLaboratoryTest).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(laboratoryTestService.update).toHaveBeenCalledWith(expect.objectContaining(laboratoryTest));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryTest>>();
      const laboratoryTest = { id: 123 };
      jest.spyOn(laboratoryTestFormService, 'getLaboratoryTest').mockReturnValue({ id: null });
      jest.spyOn(laboratoryTestService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryTest: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: laboratoryTest }));
      saveSubject.complete();

      // THEN
      expect(laboratoryTestFormService.getLaboratoryTest).toHaveBeenCalled();
      expect(laboratoryTestService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryTest>>();
      const laboratoryTest = { id: 123 };
      jest.spyOn(laboratoryTestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryTest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(laboratoryTestService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
