import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../laboratory-instrument.test-samples';

import { LaboratoryInstrumentFormService } from './laboratory-instrument-form.service';

describe('LaboratoryInstrument Form Service', () => {
  let service: LaboratoryInstrumentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LaboratoryInstrumentFormService);
  });

  describe('Service methods', () => {
    describe('createLaboratoryInstrumentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLaboratoryInstrumentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            instrumentId: expect.any(Object),
            instrument: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
          }),
        );
      });

      it('passing ILaboratoryInstrument should create a new form with FormGroup', () => {
        const formGroup = service.createLaboratoryInstrumentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            instrumentId: expect.any(Object),
            instrument: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
          }),
        );
      });
    });

    describe('getLaboratoryInstrument', () => {
      it('should return NewLaboratoryInstrument for default LaboratoryInstrument initial value', () => {
        const formGroup = service.createLaboratoryInstrumentFormGroup(sampleWithNewData);

        const laboratoryInstrument = service.getLaboratoryInstrument(formGroup) as any;

        expect(laboratoryInstrument).toMatchObject(sampleWithNewData);
      });

      it('should return NewLaboratoryInstrument for empty LaboratoryInstrument initial value', () => {
        const formGroup = service.createLaboratoryInstrumentFormGroup();

        const laboratoryInstrument = service.getLaboratoryInstrument(formGroup) as any;

        expect(laboratoryInstrument).toMatchObject({});
      });

      it('should return ILaboratoryInstrument', () => {
        const formGroup = service.createLaboratoryInstrumentFormGroup(sampleWithRequiredData);

        const laboratoryInstrument = service.getLaboratoryInstrument(formGroup) as any;

        expect(laboratoryInstrument).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILaboratoryInstrument should not enable id FormControl', () => {
        const formGroup = service.createLaboratoryInstrumentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLaboratoryInstrument should disable id FormControl', () => {
        const formGroup = service.createLaboratoryInstrumentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
