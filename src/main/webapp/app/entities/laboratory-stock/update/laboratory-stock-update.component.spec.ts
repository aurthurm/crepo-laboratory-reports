import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { LaboratoryStockService } from '../service/laboratory-stock.service';
import { ILaboratoryStock } from '../laboratory-stock.model';
import { LaboratoryStockFormService } from './laboratory-stock-form.service';

import { LaboratoryStockUpdateComponent } from './laboratory-stock-update.component';

describe('LaboratoryStock Management Update Component', () => {
  let comp: LaboratoryStockUpdateComponent;
  let fixture: ComponentFixture<LaboratoryStockUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let laboratoryStockFormService: LaboratoryStockFormService;
  let laboratoryStockService: LaboratoryStockService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), LaboratoryStockUpdateComponent],
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
      .overrideTemplate(LaboratoryStockUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LaboratoryStockUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    laboratoryStockFormService = TestBed.inject(LaboratoryStockFormService);
    laboratoryStockService = TestBed.inject(LaboratoryStockService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const laboratoryStock: ILaboratoryStock = { id: 456 };

      activatedRoute.data = of({ laboratoryStock });
      comp.ngOnInit();

      expect(comp.laboratoryStock).toEqual(laboratoryStock);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryStock>>();
      const laboratoryStock = { id: 123 };
      jest.spyOn(laboratoryStockFormService, 'getLaboratoryStock').mockReturnValue(laboratoryStock);
      jest.spyOn(laboratoryStockService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryStock });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: laboratoryStock }));
      saveSubject.complete();

      // THEN
      expect(laboratoryStockFormService.getLaboratoryStock).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(laboratoryStockService.update).toHaveBeenCalledWith(expect.objectContaining(laboratoryStock));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryStock>>();
      const laboratoryStock = { id: 123 };
      jest.spyOn(laboratoryStockFormService, 'getLaboratoryStock').mockReturnValue({ id: null });
      jest.spyOn(laboratoryStockService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryStock: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: laboratoryStock }));
      saveSubject.complete();

      // THEN
      expect(laboratoryStockFormService.getLaboratoryStock).toHaveBeenCalled();
      expect(laboratoryStockService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILaboratoryStock>>();
      const laboratoryStock = { id: 123 };
      jest.spyOn(laboratoryStockService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ laboratoryStock });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(laboratoryStockService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
