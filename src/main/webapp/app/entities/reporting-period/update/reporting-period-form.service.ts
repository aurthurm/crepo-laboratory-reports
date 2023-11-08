import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IReportingPeriod, NewReportingPeriod } from '../reporting-period.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReportingPeriod for edit and NewReportingPeriodFormGroupInput for create.
 */
type ReportingPeriodFormGroupInput = IReportingPeriod | PartialWithRequiredKeyOf<NewReportingPeriod>;

type ReportingPeriodFormDefaults = Pick<NewReportingPeriod, 'id'>;

type ReportingPeriodFormGroupContent = {
  id: FormControl<IReportingPeriod['id'] | NewReportingPeriod['id']>;
  day: FormControl<IReportingPeriod['day']>;
  week: FormControl<IReportingPeriod['week']>;
  month: FormControl<IReportingPeriod['month']>;
  year: FormControl<IReportingPeriod['year']>;
};

export type ReportingPeriodFormGroup = FormGroup<ReportingPeriodFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReportingPeriodFormService {
  createReportingPeriodFormGroup(reportingPeriod: ReportingPeriodFormGroupInput = { id: null }): ReportingPeriodFormGroup {
    const reportingPeriodRawValue = {
      ...this.getFormDefaults(),
      ...reportingPeriod,
    };
    return new FormGroup<ReportingPeriodFormGroupContent>({
      id: new FormControl(
        { value: reportingPeriodRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      day: new FormControl(reportingPeriodRawValue.day),
      week: new FormControl(reportingPeriodRawValue.week),
      month: new FormControl(reportingPeriodRawValue.month, {
        validators: [Validators.required],
      }),
      year: new FormControl(reportingPeriodRawValue.year, {
        validators: [Validators.required],
      }),
    });
  }

  getReportingPeriod(form: ReportingPeriodFormGroup): IReportingPeriod | NewReportingPeriod {
    return form.getRawValue() as IReportingPeriod | NewReportingPeriod;
  }

  resetForm(form: ReportingPeriodFormGroup, reportingPeriod: ReportingPeriodFormGroupInput): void {
    const reportingPeriodRawValue = { ...this.getFormDefaults(), ...reportingPeriod };
    form.reset(
      {
        ...reportingPeriodRawValue,
        id: { value: reportingPeriodRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReportingPeriodFormDefaults {
    return {
      id: null,
    };
  }
}
