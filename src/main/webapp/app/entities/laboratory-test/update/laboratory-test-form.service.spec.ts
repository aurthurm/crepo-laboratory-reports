import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../laboratory-test.test-samples';

import { LaboratoryTestFormService } from './laboratory-test-form.service';

describe('LaboratoryTest Form Service', () => {
  let service: LaboratoryTestFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LaboratoryTestFormService);
  });

  describe('Service methods', () => {
    describe('createLaboratoryTestFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLaboratoryTestFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            testId: expect.any(Object),
            test: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
          }),
        );
      });

      it('passing ILaboratoryTest should create a new form with FormGroup', () => {
        const formGroup = service.createLaboratoryTestFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            testId: expect.any(Object),
            test: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
          }),
        );
      });
    });

    describe('getLaboratoryTest', () => {
      it('should return NewLaboratoryTest for default LaboratoryTest initial value', () => {
        const formGroup = service.createLaboratoryTestFormGroup(sampleWithNewData);

        const laboratoryTest = service.getLaboratoryTest(formGroup) as any;

        expect(laboratoryTest).toMatchObject(sampleWithNewData);
      });

      it('should return NewLaboratoryTest for empty LaboratoryTest initial value', () => {
        const formGroup = service.createLaboratoryTestFormGroup();

        const laboratoryTest = service.getLaboratoryTest(formGroup) as any;

        expect(laboratoryTest).toMatchObject({});
      });

      it('should return ILaboratoryTest', () => {
        const formGroup = service.createLaboratoryTestFormGroup(sampleWithRequiredData);

        const laboratoryTest = service.getLaboratoryTest(formGroup) as any;

        expect(laboratoryTest).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILaboratoryTest should not enable id FormControl', () => {
        const formGroup = service.createLaboratoryTestFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLaboratoryTest should disable id FormControl', () => {
        const formGroup = service.createLaboratoryTestFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
