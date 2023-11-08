import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../report-activity-update.test-samples';

import { ReportActivityUpdateFormService } from './report-activity-update-form.service';

describe('ReportActivityUpdate Form Service', () => {
  let service: ReportActivityUpdateFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportActivityUpdateFormService);
  });

  describe('Service methods', () => {
    describe('createReportActivityUpdateFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReportActivityUpdateFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            activity: expect.any(Object),
            activityDetails: expect.any(Object),
            outcomes: expect.any(Object),
            comments: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });

      it('passing IReportActivityUpdate should create a new form with FormGroup', () => {
        const formGroup = service.createReportActivityUpdateFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            activity: expect.any(Object),
            activityDetails: expect.any(Object),
            outcomes: expect.any(Object),
            comments: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });
    });

    describe('getReportActivityUpdate', () => {
      it('should return NewReportActivityUpdate for default ReportActivityUpdate initial value', () => {
        const formGroup = service.createReportActivityUpdateFormGroup(sampleWithNewData);

        const reportActivityUpdate = service.getReportActivityUpdate(formGroup) as any;

        expect(reportActivityUpdate).toMatchObject(sampleWithNewData);
      });

      it('should return NewReportActivityUpdate for empty ReportActivityUpdate initial value', () => {
        const formGroup = service.createReportActivityUpdateFormGroup();

        const reportActivityUpdate = service.getReportActivityUpdate(formGroup) as any;

        expect(reportActivityUpdate).toMatchObject({});
      });

      it('should return IReportActivityUpdate', () => {
        const formGroup = service.createReportActivityUpdateFormGroup(sampleWithRequiredData);

        const reportActivityUpdate = service.getReportActivityUpdate(formGroup) as any;

        expect(reportActivityUpdate).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReportActivityUpdate should not enable id FormControl', () => {
        const formGroup = service.createReportActivityUpdateFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReportActivityUpdate should disable id FormControl', () => {
        const formGroup = service.createReportActivityUpdateFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
