import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { StaffCompimentService } from '../service/staff-compiment.service';
import { IStaffCompiment } from '../staff-compiment.model';
import { StaffCompimentFormService } from './staff-compiment-form.service';

import { StaffCompimentUpdateComponent } from './staff-compiment-update.component';

describe('StaffCompiment Management Update Component', () => {
  let comp: StaffCompimentUpdateComponent;
  let fixture: ComponentFixture<StaffCompimentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let staffCompimentFormService: StaffCompimentFormService;
  let staffCompimentService: StaffCompimentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), StaffCompimentUpdateComponent],
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
      .overrideTemplate(StaffCompimentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StaffCompimentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    staffCompimentFormService = TestBed.inject(StaffCompimentFormService);
    staffCompimentService = TestBed.inject(StaffCompimentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const staffCompiment: IStaffCompiment = { id: 456 };

      activatedRoute.data = of({ staffCompiment });
      comp.ngOnInit();

      expect(comp.staffCompiment).toEqual(staffCompiment);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStaffCompiment>>();
      const staffCompiment = { id: 123 };
      jest.spyOn(staffCompimentFormService, 'getStaffCompiment').mockReturnValue(staffCompiment);
      jest.spyOn(staffCompimentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ staffCompiment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: staffCompiment }));
      saveSubject.complete();

      // THEN
      expect(staffCompimentFormService.getStaffCompiment).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(staffCompimentService.update).toHaveBeenCalledWith(expect.objectContaining(staffCompiment));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStaffCompiment>>();
      const staffCompiment = { id: 123 };
      jest.spyOn(staffCompimentFormService, 'getStaffCompiment').mockReturnValue({ id: null });
      jest.spyOn(staffCompimentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ staffCompiment: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: staffCompiment }));
      saveSubject.complete();

      // THEN
      expect(staffCompimentFormService.getStaffCompiment).toHaveBeenCalled();
      expect(staffCompimentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStaffCompiment>>();
      const staffCompiment = { id: 123 };
      jest.spyOn(staffCompimentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ staffCompiment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(staffCompimentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
