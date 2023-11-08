import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStaffCompiment, NewStaffCompiment } from '../staff-compiment.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStaffCompiment for edit and NewStaffCompimentFormGroupInput for create.
 */
type StaffCompimentFormGroupInput = IStaffCompiment | PartialWithRequiredKeyOf<NewStaffCompiment>;

type StaffCompimentFormDefaults = Pick<NewStaffCompiment, 'id'>;

type StaffCompimentFormGroupContent = {
  id: FormControl<IStaffCompiment['id'] | NewStaffCompiment['id']>;
  laboratoryId: FormControl<IStaffCompiment['laboratoryId']>;
  laboratory: FormControl<IStaffCompiment['laboratory']>;
  departmentId: FormControl<IStaffCompiment['departmentId']>;
  department: FormControl<IStaffCompiment['department']>;
  scientistAvailable: FormControl<IStaffCompiment['scientistAvailable']>;
  scientistRequired: FormControl<IStaffCompiment['scientistRequired']>;
  microscopitsAvailable: FormControl<IStaffCompiment['microscopitsAvailable']>;
  microscopitsRequired: FormControl<IStaffCompiment['microscopitsRequired']>;
  labTechsAvailable: FormControl<IStaffCompiment['labTechsAvailable']>;
  labTechsRequired: FormControl<IStaffCompiment['labTechsRequired']>;
  generalHandsAvailable: FormControl<IStaffCompiment['generalHandsAvailable']>;
  generalHandsRequired: FormControl<IStaffCompiment['generalHandsRequired']>;
};

export type StaffCompimentFormGroup = FormGroup<StaffCompimentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StaffCompimentFormService {
  createStaffCompimentFormGroup(staffCompiment: StaffCompimentFormGroupInput = { id: null }): StaffCompimentFormGroup {
    const staffCompimentRawValue = {
      ...this.getFormDefaults(),
      ...staffCompiment,
    };
    return new FormGroup<StaffCompimentFormGroupContent>({
      id: new FormControl(
        { value: staffCompimentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(staffCompimentRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(staffCompimentRawValue.laboratory),
      departmentId: new FormControl(staffCompimentRawValue.departmentId),
      department: new FormControl(staffCompimentRawValue.department),
      scientistAvailable: new FormControl(staffCompimentRawValue.scientistAvailable),
      scientistRequired: new FormControl(staffCompimentRawValue.scientistRequired),
      microscopitsAvailable: new FormControl(staffCompimentRawValue.microscopitsAvailable),
      microscopitsRequired: new FormControl(staffCompimentRawValue.microscopitsRequired),
      labTechsAvailable: new FormControl(staffCompimentRawValue.labTechsAvailable),
      labTechsRequired: new FormControl(staffCompimentRawValue.labTechsRequired),
      generalHandsAvailable: new FormControl(staffCompimentRawValue.generalHandsAvailable),
      generalHandsRequired: new FormControl(staffCompimentRawValue.generalHandsRequired),
    });
  }

  getStaffCompiment(form: StaffCompimentFormGroup): IStaffCompiment | NewStaffCompiment {
    return form.getRawValue() as IStaffCompiment | NewStaffCompiment;
  }

  resetForm(form: StaffCompimentFormGroup, staffCompiment: StaffCompimentFormGroupInput): void {
    const staffCompimentRawValue = { ...this.getFormDefaults(), ...staffCompiment };
    form.reset(
      {
        ...staffCompimentRawValue,
        id: { value: staffCompimentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): StaffCompimentFormDefaults {
    return {
      id: null,
    };
  }
}
