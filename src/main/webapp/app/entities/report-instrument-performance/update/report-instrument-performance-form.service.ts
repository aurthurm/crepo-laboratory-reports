import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IReportInstrumentPerformance, NewReportInstrumentPerformance } from '../report-instrument-performance.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReportInstrumentPerformance for edit and NewReportInstrumentPerformanceFormGroupInput for create.
 */
type ReportInstrumentPerformanceFormGroupInput = IReportInstrumentPerformance | PartialWithRequiredKeyOf<NewReportInstrumentPerformance>;

type ReportInstrumentPerformanceFormDefaults = Pick<NewReportInstrumentPerformance, 'id'>;

type ReportInstrumentPerformanceFormGroupContent = {
  id: FormControl<IReportInstrumentPerformance['id'] | NewReportInstrumentPerformance['id']>;
  laboratoryId: FormControl<IReportInstrumentPerformance['laboratoryId']>;
  laboratory: FormControl<IReportInstrumentPerformance['laboratory']>;
  departmentId: FormControl<IReportInstrumentPerformance['departmentId']>;
  department: FormControl<IReportInstrumentPerformance['department']>;
  instrumentId: FormControl<IReportInstrumentPerformance['instrumentId']>;
  instrument: FormControl<IReportInstrumentPerformance['instrument']>;
  status: FormControl<IReportInstrumentPerformance['status']>;
  uptime: FormControl<IReportInstrumentPerformance['uptime']>;
  downtime: FormControl<IReportInstrumentPerformance['downtime']>;
  serviceStatus: FormControl<IReportInstrumentPerformance['serviceStatus']>;
  caliberationStatus: FormControl<IReportInstrumentPerformance['caliberationStatus']>;
  functionality: FormControl<IReportInstrumentPerformance['functionality']>;
  comment: FormControl<IReportInstrumentPerformance['comment']>;
  reportingPeriodId: FormControl<IReportInstrumentPerformance['reportingPeriodId']>;
};

export type ReportInstrumentPerformanceFormGroup = FormGroup<ReportInstrumentPerformanceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReportInstrumentPerformanceFormService {
  createReportInstrumentPerformanceFormGroup(
    reportInstrumentPerformance: ReportInstrumentPerformanceFormGroupInput = { id: null },
  ): ReportInstrumentPerformanceFormGroup {
    const reportInstrumentPerformanceRawValue = {
      ...this.getFormDefaults(),
      ...reportInstrumentPerformance,
    };
    return new FormGroup<ReportInstrumentPerformanceFormGroupContent>({
      id: new FormControl(
        { value: reportInstrumentPerformanceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(reportInstrumentPerformanceRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(reportInstrumentPerformanceRawValue.laboratory),
      departmentId: new FormControl(reportInstrumentPerformanceRawValue.departmentId),
      department: new FormControl(reportInstrumentPerformanceRawValue.department),
      instrumentId: new FormControl(reportInstrumentPerformanceRawValue.instrumentId, {
        validators: [Validators.required],
      }),
      instrument: new FormControl(reportInstrumentPerformanceRawValue.instrument),
      status: new FormControl(reportInstrumentPerformanceRawValue.status),
      uptime: new FormControl(reportInstrumentPerformanceRawValue.uptime),
      downtime: new FormControl(reportInstrumentPerformanceRawValue.downtime),
      serviceStatus: new FormControl(reportInstrumentPerformanceRawValue.serviceStatus),
      caliberationStatus: new FormControl(reportInstrumentPerformanceRawValue.caliberationStatus),
      functionality: new FormControl(reportInstrumentPerformanceRawValue.functionality),
      comment: new FormControl(reportInstrumentPerformanceRawValue.comment),
      reportingPeriodId: new FormControl(reportInstrumentPerformanceRawValue.reportingPeriodId, {
        validators: [Validators.required],
      }),
    });
  }

  getReportInstrumentPerformance(
    form: ReportInstrumentPerformanceFormGroup,
  ): IReportInstrumentPerformance | NewReportInstrumentPerformance {
    return form.getRawValue() as IReportInstrumentPerformance | NewReportInstrumentPerformance;
  }

  resetForm(form: ReportInstrumentPerformanceFormGroup, reportInstrumentPerformance: ReportInstrumentPerformanceFormGroupInput): void {
    const reportInstrumentPerformanceRawValue = { ...this.getFormDefaults(), ...reportInstrumentPerformance };
    form.reset(
      {
        ...reportInstrumentPerformanceRawValue,
        id: { value: reportInstrumentPerformanceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReportInstrumentPerformanceFormDefaults {
    return {
      id: null,
    };
  }
}
