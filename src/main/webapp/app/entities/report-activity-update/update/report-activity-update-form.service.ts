import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IReportActivityUpdate, NewReportActivityUpdate } from '../report-activity-update.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReportActivityUpdate for edit and NewReportActivityUpdateFormGroupInput for create.
 */
type ReportActivityUpdateFormGroupInput = IReportActivityUpdate | PartialWithRequiredKeyOf<NewReportActivityUpdate>;

type ReportActivityUpdateFormDefaults = Pick<NewReportActivityUpdate, 'id'>;

type ReportActivityUpdateFormGroupContent = {
  id: FormControl<IReportActivityUpdate['id'] | NewReportActivityUpdate['id']>;
  laboratoryId: FormControl<IReportActivityUpdate['laboratoryId']>;
  laboratory: FormControl<IReportActivityUpdate['laboratory']>;
  activity: FormControl<IReportActivityUpdate['activity']>;
  activityDetails: FormControl<IReportActivityUpdate['activityDetails']>;
  outcomes: FormControl<IReportActivityUpdate['outcomes']>;
  comments: FormControl<IReportActivityUpdate['comments']>;
  reportingPeriodId: FormControl<IReportActivityUpdate['reportingPeriodId']>;
};

export type ReportActivityUpdateFormGroup = FormGroup<ReportActivityUpdateFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReportActivityUpdateFormService {
  createReportActivityUpdateFormGroup(
    reportActivityUpdate: ReportActivityUpdateFormGroupInput = { id: null },
  ): ReportActivityUpdateFormGroup {
    const reportActivityUpdateRawValue = {
      ...this.getFormDefaults(),
      ...reportActivityUpdate,
    };
    return new FormGroup<ReportActivityUpdateFormGroupContent>({
      id: new FormControl(
        { value: reportActivityUpdateRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(reportActivityUpdateRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(reportActivityUpdateRawValue.laboratory),
      activity: new FormControl(reportActivityUpdateRawValue.activity),
      activityDetails: new FormControl(reportActivityUpdateRawValue.activityDetails),
      outcomes: new FormControl(reportActivityUpdateRawValue.outcomes),
      comments: new FormControl(reportActivityUpdateRawValue.comments),
      reportingPeriodId: new FormControl(reportActivityUpdateRawValue.reportingPeriodId, {
        validators: [Validators.required],
      }),
    });
  }

  getReportActivityUpdate(form: ReportActivityUpdateFormGroup): IReportActivityUpdate | NewReportActivityUpdate {
    return form.getRawValue() as IReportActivityUpdate | NewReportActivityUpdate;
  }

  resetForm(form: ReportActivityUpdateFormGroup, reportActivityUpdate: ReportActivityUpdateFormGroupInput): void {
    const reportActivityUpdateRawValue = { ...this.getFormDefaults(), ...reportActivityUpdate };
    form.reset(
      {
        ...reportActivityUpdateRawValue,
        id: { value: reportActivityUpdateRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReportActivityUpdateFormDefaults {
    return {
      id: null,
    };
  }
}
