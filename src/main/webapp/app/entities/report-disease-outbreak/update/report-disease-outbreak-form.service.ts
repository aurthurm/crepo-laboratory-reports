import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IReportDiseaseOutbreak, NewReportDiseaseOutbreak } from '../report-disease-outbreak.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReportDiseaseOutbreak for edit and NewReportDiseaseOutbreakFormGroupInput for create.
 */
type ReportDiseaseOutbreakFormGroupInput = IReportDiseaseOutbreak | PartialWithRequiredKeyOf<NewReportDiseaseOutbreak>;

type ReportDiseaseOutbreakFormDefaults = Pick<NewReportDiseaseOutbreak, 'id'>;

type ReportDiseaseOutbreakFormGroupContent = {
  id: FormControl<IReportDiseaseOutbreak['id'] | NewReportDiseaseOutbreak['id']>;
  laboratoryId: FormControl<IReportDiseaseOutbreak['laboratoryId']>;
  laboratory: FormControl<IReportDiseaseOutbreak['laboratory']>;
  outbreak: FormControl<IReportDiseaseOutbreak['outbreak']>;
  disease: FormControl<IReportDiseaseOutbreak['disease']>;
  comment: FormControl<IReportDiseaseOutbreak['comment']>;
  reportingPeriodId: FormControl<IReportDiseaseOutbreak['reportingPeriodId']>;
};

export type ReportDiseaseOutbreakFormGroup = FormGroup<ReportDiseaseOutbreakFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReportDiseaseOutbreakFormService {
  createReportDiseaseOutbreakFormGroup(
    reportDiseaseOutbreak: ReportDiseaseOutbreakFormGroupInput = { id: null },
  ): ReportDiseaseOutbreakFormGroup {
    const reportDiseaseOutbreakRawValue = {
      ...this.getFormDefaults(),
      ...reportDiseaseOutbreak,
    };
    return new FormGroup<ReportDiseaseOutbreakFormGroupContent>({
      id: new FormControl(
        { value: reportDiseaseOutbreakRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(reportDiseaseOutbreakRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(reportDiseaseOutbreakRawValue.laboratory),
      outbreak: new FormControl(reportDiseaseOutbreakRawValue.outbreak),
      disease: new FormControl(reportDiseaseOutbreakRawValue.disease),
      comment: new FormControl(reportDiseaseOutbreakRawValue.comment),
      reportingPeriodId: new FormControl(reportDiseaseOutbreakRawValue.reportingPeriodId, {
        validators: [Validators.required],
      }),
    });
  }

  getReportDiseaseOutbreak(form: ReportDiseaseOutbreakFormGroup): IReportDiseaseOutbreak | NewReportDiseaseOutbreak {
    return form.getRawValue() as IReportDiseaseOutbreak | NewReportDiseaseOutbreak;
  }

  resetForm(form: ReportDiseaseOutbreakFormGroup, reportDiseaseOutbreak: ReportDiseaseOutbreakFormGroupInput): void {
    const reportDiseaseOutbreakRawValue = { ...this.getFormDefaults(), ...reportDiseaseOutbreak };
    form.reset(
      {
        ...reportDiseaseOutbreakRawValue,
        id: { value: reportDiseaseOutbreakRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReportDiseaseOutbreakFormDefaults {
    return {
      id: null,
    };
  }
}
