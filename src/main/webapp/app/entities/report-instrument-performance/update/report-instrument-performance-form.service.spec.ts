import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../report-instrument-performance.test-samples';

import { ReportInstrumentPerformanceFormService } from './report-instrument-performance-form.service';

describe('ReportInstrumentPerformance Form Service', () => {
  let service: ReportInstrumentPerformanceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportInstrumentPerformanceFormService);
  });

  describe('Service methods', () => {
    describe('createReportInstrumentPerformanceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReportInstrumentPerformanceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
            instrumentId: expect.any(Object),
            instrument: expect.any(Object),
            status: expect.any(Object),
            uptime: expect.any(Object),
            downtime: expect.any(Object),
            serviceStatus: expect.any(Object),
            caliberationStatus: expect.any(Object),
            functionality: expect.any(Object),
            comment: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });

      it('passing IReportInstrumentPerformance should create a new form with FormGroup', () => {
        const formGroup = service.createReportInstrumentPerformanceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
            instrumentId: expect.any(Object),
            instrument: expect.any(Object),
            status: expect.any(Object),
            uptime: expect.any(Object),
            downtime: expect.any(Object),
            serviceStatus: expect.any(Object),
            caliberationStatus: expect.any(Object),
            functionality: expect.any(Object),
            comment: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });
    });

    describe('getReportInstrumentPerformance', () => {
      it('should return NewReportInstrumentPerformance for default ReportInstrumentPerformance initial value', () => {
        const formGroup = service.createReportInstrumentPerformanceFormGroup(sampleWithNewData);

        const reportInstrumentPerformance = service.getReportInstrumentPerformance(formGroup) as any;

        expect(reportInstrumentPerformance).toMatchObject(sampleWithNewData);
      });

      it('should return NewReportInstrumentPerformance for empty ReportInstrumentPerformance initial value', () => {
        const formGroup = service.createReportInstrumentPerformanceFormGroup();

        const reportInstrumentPerformance = service.getReportInstrumentPerformance(formGroup) as any;

        expect(reportInstrumentPerformance).toMatchObject({});
      });

      it('should return IReportInstrumentPerformance', () => {
        const formGroup = service.createReportInstrumentPerformanceFormGroup(sampleWithRequiredData);

        const reportInstrumentPerformance = service.getReportInstrumentPerformance(formGroup) as any;

        expect(reportInstrumentPerformance).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReportInstrumentPerformance should not enable id FormControl', () => {
        const formGroup = service.createReportInstrumentPerformanceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReportInstrumentPerformance should disable id FormControl', () => {
        const formGroup = service.createReportInstrumentPerformanceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
