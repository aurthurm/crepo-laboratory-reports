import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../report-stock-out.test-samples';

import { ReportStockOutFormService } from './report-stock-out-form.service';

describe('ReportStockOut Form Service', () => {
  let service: ReportStockOutFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportStockOutFormService);
  });

  describe('Service methods', () => {
    describe('createReportStockOutFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReportStockOutFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            stockItemId: expect.any(Object),
            stockItem: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
            available: expect.any(Object),
            comment: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });

      it('passing IReportStockOut should create a new form with FormGroup', () => {
        const formGroup = service.createReportStockOutFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            stockItemId: expect.any(Object),
            stockItem: expect.any(Object),
            departmentId: expect.any(Object),
            department: expect.any(Object),
            available: expect.any(Object),
            comment: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });
    });

    describe('getReportStockOut', () => {
      it('should return NewReportStockOut for default ReportStockOut initial value', () => {
        const formGroup = service.createReportStockOutFormGroup(sampleWithNewData);

        const reportStockOut = service.getReportStockOut(formGroup) as any;

        expect(reportStockOut).toMatchObject(sampleWithNewData);
      });

      it('should return NewReportStockOut for empty ReportStockOut initial value', () => {
        const formGroup = service.createReportStockOutFormGroup();

        const reportStockOut = service.getReportStockOut(formGroup) as any;

        expect(reportStockOut).toMatchObject({});
      });

      it('should return IReportStockOut', () => {
        const formGroup = service.createReportStockOutFormGroup(sampleWithRequiredData);

        const reportStockOut = service.getReportStockOut(formGroup) as any;

        expect(reportStockOut).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReportStockOut should not enable id FormControl', () => {
        const formGroup = service.createReportStockOutFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReportStockOut should disable id FormControl', () => {
        const formGroup = service.createReportStockOutFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
