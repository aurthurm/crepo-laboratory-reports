import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../report-test-peformance.test-samples';

import { ReportTestPeformanceFormService } from './report-test-peformance-form.service';

describe('ReportTestPeformance Form Service', () => {
  let service: ReportTestPeformanceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportTestPeformanceFormService);
  });

  describe('Service methods', () => {
    describe('createReportTestPeformanceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReportTestPeformanceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
            sampleTypeId: expect.any(Object),
            sampleType: expect.any(Object),
            testId: expect.any(Object),
            test: expect.any(Object),
            turnAroundTime: expect.any(Object),
            numberTested: expect.any(Object),
            numberDispatched: expect.any(Object),
            numberRejected: expect.any(Object),
            instrumentId: expect.any(Object),
            instrument: expect.any(Object),
            criticalResults: expect.any(Object),
            numberCritical: expect.any(Object),
            criticalComment: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });

      it('passing IReportTestPeformance should create a new form with FormGroup', () => {
        const formGroup = service.createReportTestPeformanceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
            sampleTypeId: expect.any(Object),
            sampleType: expect.any(Object),
            testId: expect.any(Object),
            test: expect.any(Object),
            turnAroundTime: expect.any(Object),
            numberTested: expect.any(Object),
            numberDispatched: expect.any(Object),
            numberRejected: expect.any(Object),
            instrumentId: expect.any(Object),
            instrument: expect.any(Object),
            criticalResults: expect.any(Object),
            numberCritical: expect.any(Object),
            criticalComment: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });
    });

    describe('getReportTestPeformance', () => {
      it('should return NewReportTestPeformance for default ReportTestPeformance initial value', () => {
        const formGroup = service.createReportTestPeformanceFormGroup(sampleWithNewData);

        const reportTestPeformance = service.getReportTestPeformance(formGroup) as any;

        expect(reportTestPeformance).toMatchObject(sampleWithNewData);
      });

      it('should return NewReportTestPeformance for empty ReportTestPeformance initial value', () => {
        const formGroup = service.createReportTestPeformanceFormGroup();

        const reportTestPeformance = service.getReportTestPeformance(formGroup) as any;

        expect(reportTestPeformance).toMatchObject({});
      });

      it('should return IReportTestPeformance', () => {
        const formGroup = service.createReportTestPeformanceFormGroup(sampleWithRequiredData);

        const reportTestPeformance = service.getReportTestPeformance(formGroup) as any;

        expect(reportTestPeformance).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReportTestPeformance should not enable id FormControl', () => {
        const formGroup = service.createReportTestPeformanceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReportTestPeformance should disable id FormControl', () => {
        const formGroup = service.createReportTestPeformanceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
