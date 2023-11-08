import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ILaboratoryInstrument, NewLaboratoryInstrument } from '../laboratory-instrument.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILaboratoryInstrument for edit and NewLaboratoryInstrumentFormGroupInput for create.
 */
type LaboratoryInstrumentFormGroupInput = ILaboratoryInstrument | PartialWithRequiredKeyOf<NewLaboratoryInstrument>;

type LaboratoryInstrumentFormDefaults = Pick<NewLaboratoryInstrument, 'id'>;

type LaboratoryInstrumentFormGroupContent = {
  id: FormControl<ILaboratoryInstrument['id'] | NewLaboratoryInstrument['id']>;
  laboratoryId: FormControl<ILaboratoryInstrument['laboratoryId']>;
  laboratory: FormControl<ILaboratoryInstrument['laboratory']>;
  instrumentId: FormControl<ILaboratoryInstrument['instrumentId']>;
  instrument: FormControl<ILaboratoryInstrument['instrument']>;
  departmentId: FormControl<ILaboratoryInstrument['departmentId']>;
  department: FormControl<ILaboratoryInstrument['department']>;
};

export type LaboratoryInstrumentFormGroup = FormGroup<LaboratoryInstrumentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LaboratoryInstrumentFormService {
  createLaboratoryInstrumentFormGroup(
    laboratoryInstrument: LaboratoryInstrumentFormGroupInput = { id: null },
  ): LaboratoryInstrumentFormGroup {
    const laboratoryInstrumentRawValue = {
      ...this.getFormDefaults(),
      ...laboratoryInstrument,
    };
    return new FormGroup<LaboratoryInstrumentFormGroupContent>({
      id: new FormControl(
        { value: laboratoryInstrumentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(laboratoryInstrumentRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(laboratoryInstrumentRawValue.laboratory),
      instrumentId: new FormControl(laboratoryInstrumentRawValue.instrumentId, {
        validators: [Validators.required],
      }),
      instrument: new FormControl(laboratoryInstrumentRawValue.instrument),
      departmentId: new FormControl(laboratoryInstrumentRawValue.departmentId),
      department: new FormControl(laboratoryInstrumentRawValue.department),
    });
  }

  getLaboratoryInstrument(form: LaboratoryInstrumentFormGroup): ILaboratoryInstrument | NewLaboratoryInstrument {
    return form.getRawValue() as ILaboratoryInstrument | NewLaboratoryInstrument;
  }

  resetForm(form: LaboratoryInstrumentFormGroup, laboratoryInstrument: LaboratoryInstrumentFormGroupInput): void {
    const laboratoryInstrumentRawValue = { ...this.getFormDefaults(), ...laboratoryInstrument };
    form.reset(
      {
        ...laboratoryInstrumentRawValue,
        id: { value: laboratoryInstrumentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): LaboratoryInstrumentFormDefaults {
    return {
      id: null,
    };
  }
}
