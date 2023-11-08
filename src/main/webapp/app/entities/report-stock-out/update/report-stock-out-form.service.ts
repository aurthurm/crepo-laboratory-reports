import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IReportStockOut, NewReportStockOut } from '../report-stock-out.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReportStockOut for edit and NewReportStockOutFormGroupInput for create.
 */
type ReportStockOutFormGroupInput = IReportStockOut | PartialWithRequiredKeyOf<NewReportStockOut>;

type ReportStockOutFormDefaults = Pick<NewReportStockOut, 'id'>;

type ReportStockOutFormGroupContent = {
  id: FormControl<IReportStockOut['id'] | NewReportStockOut['id']>;
  laboratoryId: FormControl<IReportStockOut['laboratoryId']>;
  laboratory: FormControl<IReportStockOut['laboratory']>;
  stockItemId: FormControl<IReportStockOut['stockItemId']>;
  stockItem: FormControl<IReportStockOut['stockItem']>;
  departmentId: FormControl<IReportStockOut['departmentId']>;
  department: FormControl<IReportStockOut['department']>;
  available: FormControl<IReportStockOut['available']>;
  comment: FormControl<IReportStockOut['comment']>;
  reportingPeriodId: FormControl<IReportStockOut['reportingPeriodId']>;
};

export type ReportStockOutFormGroup = FormGroup<ReportStockOutFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReportStockOutFormService {
  createReportStockOutFormGroup(reportStockOut: ReportStockOutFormGroupInput = { id: null }): ReportStockOutFormGroup {
    const reportStockOutRawValue = {
      ...this.getFormDefaults(),
      ...reportStockOut,
    };
    return new FormGroup<ReportStockOutFormGroupContent>({
      id: new FormControl(
        { value: reportStockOutRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(reportStockOutRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(reportStockOutRawValue.laboratory),
      stockItemId: new FormControl(reportStockOutRawValue.stockItemId, {
        validators: [Validators.required],
      }),
      stockItem: new FormControl(reportStockOutRawValue.stockItem),
      departmentId: new FormControl(reportStockOutRawValue.departmentId),
      department: new FormControl(reportStockOutRawValue.department),
      available: new FormControl(reportStockOutRawValue.available),
      comment: new FormControl(reportStockOutRawValue.comment),
      reportingPeriodId: new FormControl(reportStockOutRawValue.reportingPeriodId, {
        validators: [Validators.required],
      }),
    });
  }

  getReportStockOut(form: ReportStockOutFormGroup): IReportStockOut | NewReportStockOut {
    return form.getRawValue() as IReportStockOut | NewReportStockOut;
  }

  resetForm(form: ReportStockOutFormGroup, reportStockOut: ReportStockOutFormGroupInput): void {
    const reportStockOutRawValue = { ...this.getFormDefaults(), ...reportStockOut };
    form.reset(
      {
        ...reportStockOutRawValue,
        id: { value: reportStockOutRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReportStockOutFormDefaults {
    return {
      id: null,
    };
  }
}
