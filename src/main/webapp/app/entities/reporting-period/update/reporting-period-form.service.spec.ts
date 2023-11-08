import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../reporting-period.test-samples';

import { ReportingPeriodFormService } from './reporting-period-form.service';

describe('ReportingPeriod Form Service', () => {
  let service: ReportingPeriodFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportingPeriodFormService);
  });

  describe('Service methods', () => {
    describe('createReportingPeriodFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReportingPeriodFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            day: expect.any(Object),
            week: expect.any(Object),
            month: expect.any(Object),
            year: expect.any(Object),
          }),
        );
      });

      it('passing IReportingPeriod should create a new form with FormGroup', () => {
        const formGroup = service.createReportingPeriodFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            day: expect.any(Object),
            week: expect.any(Object),
            month: expect.any(Object),
            year: expect.any(Object),
          }),
        );
      });
    });

    describe('getReportingPeriod', () => {
      it('should return NewReportingPeriod for default ReportingPeriod initial value', () => {
        const formGroup = service.createReportingPeriodFormGroup(sampleWithNewData);

        const reportingPeriod = service.getReportingPeriod(formGroup) as any;

        expect(reportingPeriod).toMatchObject(sampleWithNewData);
      });

      it('should return NewReportingPeriod for empty ReportingPeriod initial value', () => {
        const formGroup = service.createReportingPeriodFormGroup();

        const reportingPeriod = service.getReportingPeriod(formGroup) as any;

        expect(reportingPeriod).toMatchObject({});
      });

      it('should return IReportingPeriod', () => {
        const formGroup = service.createReportingPeriodFormGroup(sampleWithRequiredData);

        const reportingPeriod = service.getReportingPeriod(formGroup) as any;

        expect(reportingPeriod).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReportingPeriod should not enable id FormControl', () => {
        const formGroup = service.createReportingPeriodFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReportingPeriod should disable id FormControl', () => {
        const formGroup = service.createReportingPeriodFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
