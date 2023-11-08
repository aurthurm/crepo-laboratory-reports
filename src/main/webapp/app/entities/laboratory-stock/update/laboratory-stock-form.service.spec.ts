import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../laboratory-stock.test-samples';

import { LaboratoryStockFormService } from './laboratory-stock-form.service';

describe('LaboratoryStock Form Service', () => {
  let service: LaboratoryStockFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LaboratoryStockFormService);
  });

  describe('Service methods', () => {
    describe('createLaboratoryStockFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLaboratoryStockFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            stockItemId: expect.any(Object),
            stockItem: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
          }),
        );
      });

      it('passing ILaboratoryStock should create a new form with FormGroup', () => {
        const formGroup = service.createLaboratoryStockFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            stockItemId: expect.any(Object),
            stockItem: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
          }),
        );
      });
    });

    describe('getLaboratoryStock', () => {
      it('should return NewLaboratoryStock for default LaboratoryStock initial value', () => {
        const formGroup = service.createLaboratoryStockFormGroup(sampleWithNewData);

        const laboratoryStock = service.getLaboratoryStock(formGroup) as any;

        expect(laboratoryStock).toMatchObject(sampleWithNewData);
      });

      it('should return NewLaboratoryStock for empty LaboratoryStock initial value', () => {
        const formGroup = service.createLaboratoryStockFormGroup();

        const laboratoryStock = service.getLaboratoryStock(formGroup) as any;

        expect(laboratoryStock).toMatchObject({});
      });

      it('should return ILaboratoryStock', () => {
        const formGroup = service.createLaboratoryStockFormGroup(sampleWithRequiredData);

        const laboratoryStock = service.getLaboratoryStock(formGroup) as any;

        expect(laboratoryStock).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILaboratoryStock should not enable id FormControl', () => {
        const formGroup = service.createLaboratoryStockFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLaboratoryStock should disable id FormControl', () => {
        const formGroup = service.createLaboratoryStockFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
