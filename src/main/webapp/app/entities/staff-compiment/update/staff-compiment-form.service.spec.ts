import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../staff-compiment.test-samples';

import { StaffCompimentFormService } from './staff-compiment-form.service';

describe('StaffCompiment Form Service', () => {
  let service: StaffCompimentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StaffCompimentFormService);
  });

  describe('Service methods', () => {
    describe('createStaffCompimentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createStaffCompimentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
            scientistAvailable: expect.any(Object),
            scientistRequired: expect.any(Object),
            microscopitsAvailable: expect.any(Object),
            microscopitsRequired: expect.any(Object),
            labTechsAvailable: expect.any(Object),
            labTechsRequired: expect.any(Object),
            generalHandsAvailable: expect.any(Object),
            generalHandsRequired: expect.any(Object),
          }),
        );
      });

      it('passing IStaffCompiment should create a new form with FormGroup', () => {
        const formGroup = service.createStaffCompimentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
            scientistAvailable: expect.any(Object),
            scientistRequired: expect.any(Object),
            microscopitsAvailable: expect.any(Object),
            microscopitsRequired: expect.any(Object),
            labTechsAvailable: expect.any(Object),
            labTechsRequired: expect.any(Object),
            generalHandsAvailable: expect.any(Object),
            generalHandsRequired: expect.any(Object),
          }),
        );
      });
    });

    describe('getStaffCompiment', () => {
      it('should return NewStaffCompiment for default StaffCompiment initial value', () => {
        const formGroup = service.createStaffCompimentFormGroup(sampleWithNewData);

        const staffCompiment = service.getStaffCompiment(formGroup) as any;

        expect(staffCompiment).toMatchObject(sampleWithNewData);
      });

      it('should return NewStaffCompiment for empty StaffCompiment initial value', () => {
        const formGroup = service.createStaffCompimentFormGroup();

        const staffCompiment = service.getStaffCompiment(formGroup) as any;

        expect(staffCompiment).toMatchObject({});
      });

      it('should return IStaffCompiment', () => {
        const formGroup = service.createStaffCompimentFormGroup(sampleWithRequiredData);

        const staffCompiment = service.getStaffCompiment(formGroup) as any;

        expect(staffCompiment).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IStaffCompiment should not enable id FormControl', () => {
        const formGroup = service.createStaffCompimentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewStaffCompiment should disable id FormControl', () => {
        const formGroup = service.createStaffCompimentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
