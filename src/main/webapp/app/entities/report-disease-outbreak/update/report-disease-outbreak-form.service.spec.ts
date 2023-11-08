import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../report-disease-outbreak.test-samples';

import { ReportDiseaseOutbreakFormService } from './report-disease-outbreak-form.service';

describe('ReportDiseaseOutbreak Form Service', () => {
  let service: ReportDiseaseOutbreakFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportDiseaseOutbreakFormService);
  });

  describe('Service methods', () => {
    describe('createReportDiseaseOutbreakFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReportDiseaseOutbreakFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            outbreak: expect.any(Object),
            disease: expect.any(Object),
            comment: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });

      it('passing IReportDiseaseOutbreak should create a new form with FormGroup', () => {
        const formGroup = service.createReportDiseaseOutbreakFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            laboratoryId: expect.any(Object),
            laboratory: expect.any(Object),
            outbreak: expect.any(Object),
            disease: expect.any(Object),
            comment: expect.any(Object),
            reportingPeriodId: expect.any(Object),
          }),
        );
      });
    });

    describe('getReportDiseaseOutbreak', () => {
      it('should return NewReportDiseaseOutbreak for default ReportDiseaseOutbreak initial value', () => {
        const formGroup = service.createReportDiseaseOutbreakFormGroup(sampleWithNewData);

        const reportDiseaseOutbreak = service.getReportDiseaseOutbreak(formGroup) as any;

        expect(reportDiseaseOutbreak).toMatchObject(sampleWithNewData);
      });

      it('should return NewReportDiseaseOutbreak for empty ReportDiseaseOutbreak initial value', () => {
        const formGroup = service.createReportDiseaseOutbreakFormGroup();

        const reportDiseaseOutbreak = service.getReportDiseaseOutbreak(formGroup) as any;

        expect(reportDiseaseOutbreak).toMatchObject({});
      });

      it('should return IReportDiseaseOutbreak', () => {
        const formGroup = service.createReportDiseaseOutbreakFormGroup(sampleWithRequiredData);

        const reportDiseaseOutbreak = service.getReportDiseaseOutbreak(formGroup) as any;

        expect(reportDiseaseOutbreak).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReportDiseaseOutbreak should not enable id FormControl', () => {
        const formGroup = service.createReportDiseaseOutbreakFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReportDiseaseOutbreak should disable id FormControl', () => {
        const formGroup = service.createReportDiseaseOutbreakFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
