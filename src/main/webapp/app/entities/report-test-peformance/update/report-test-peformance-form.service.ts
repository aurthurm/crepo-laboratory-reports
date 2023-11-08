import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IReportTestPeformance, NewReportTestPeformance } from '../report-test-peformance.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReportTestPeformance for edit and NewReportTestPeformanceFormGroupInput for create.
 */
type ReportTestPeformanceFormGroupInput = IReportTestPeformance | PartialWithRequiredKeyOf<NewReportTestPeformance>;

type ReportTestPeformanceFormDefaults = Pick<NewReportTestPeformance, 'id'>;

type ReportTestPeformanceFormGroupContent = {
  id: FormControl<IReportTestPeformance['id'] | NewReportTestPeformance['id']>;
  laboratoryId: FormControl<IReportTestPeformance['laboratoryId']>;
  laboratory: FormControl<IReportTestPeformance['laboratory']>;
  departmentId: FormControl<IReportTestPeformance['departmentId']>;
  department: FormControl<IReportTestPeformance['department']>;
  sampleTypeId: FormControl<IReportTestPeformance['sampleTypeId']>;
  sampleType: FormControl<IReportTestPeformance['sampleType']>;
  testId: FormControl<IReportTestPeformance['testId']>;
  test: FormControl<IReportTestPeformance['test']>;
  turnAroundTime: FormControl<IReportTestPeformance['turnAroundTime']>;
  numberTested: FormControl<IReportTestPeformance['numberTested']>;
  numberDispatched: FormControl<IReportTestPeformance['numberDispatched']>;
  numberRejected: FormControl<IReportTestPeformance['numberRejected']>;
  instrumentId: FormControl<IReportTestPeformance['instrumentId']>;
  instrument: FormControl<IReportTestPeformance['instrument']>;
  criticalResults: FormControl<IReportTestPeformance['criticalResults']>;
  numberCritical: FormControl<IReportTestPeformance['numberCritical']>;
  criticalComment: FormControl<IReportTestPeformance['criticalComment']>;
  reportingPeriodId: FormControl<IReportTestPeformance['reportingPeriodId']>;
};

export type ReportTestPeformanceFormGroup = FormGroup<ReportTestPeformanceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReportTestPeformanceFormService {
  createReportTestPeformanceFormGroup(
    reportTestPeformance: ReportTestPeformanceFormGroupInput = { id: null },
  ): ReportTestPeformanceFormGroup {
    const reportTestPeformanceRawValue = {
      ...this.getFormDefaults(),
      ...reportTestPeformance,
    };
    return new FormGroup<ReportTestPeformanceFormGroupContent>({
      id: new FormControl(
        { value: reportTestPeformanceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(reportTestPeformanceRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(reportTestPeformanceRawValue.laboratory),
      departmentId: new FormControl(reportTestPeformanceRawValue.departmentId),
      department: new FormControl(reportTestPeformanceRawValue.department),
      sampleTypeId: new FormControl(reportTestPeformanceRawValue.sampleTypeId, {
        validators: [Validators.required],
      }),
      sampleType: new FormControl(reportTestPeformanceRawValue.sampleType),
      testId: new FormControl(reportTestPeformanceRawValue.testId, {
        validators: [Validators.required],
      }),
      test: new FormControl(reportTestPeformanceRawValue.test),
      turnAroundTime: new FormControl(reportTestPeformanceRawValue.turnAroundTime),
      numberTested: new FormControl(reportTestPeformanceRawValue.numberTested),
      numberDispatched: new FormControl(reportTestPeformanceRawValue.numberDispatched),
      numberRejected: new FormControl(reportTestPeformanceRawValue.numberRejected),
      instrumentId: new FormControl(reportTestPeformanceRawValue.instrumentId, {
        validators: [Validators.required],
      }),
      instrument: new FormControl(reportTestPeformanceRawValue.instrument),
      criticalResults: new FormControl(reportTestPeformanceRawValue.criticalResults),
      numberCritical: new FormControl(reportTestPeformanceRawValue.numberCritical),
      criticalComment: new FormControl(reportTestPeformanceRawValue.criticalComment),
      reportingPeriodId: new FormControl(reportTestPeformanceRawValue.reportingPeriodId, {
        validators: [Validators.required],
      }),
    });
  }

  getReportTestPeformance(form: ReportTestPeformanceFormGroup): IReportTestPeformance | NewReportTestPeformance {
    return form.getRawValue() as IReportTestPeformance | NewReportTestPeformance;
  }

  resetForm(form: ReportTestPeformanceFormGroup, reportTestPeformance: ReportTestPeformanceFormGroupInput): void {
    const reportTestPeformanceRawValue = { ...this.getFormDefaults(), ...reportTestPeformance };
    form.reset(
      {
        ...reportTestPeformanceRawValue,
        id: { value: reportTestPeformanceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReportTestPeformanceFormDefaults {
    return {
      id: null,
    };
  }
}
