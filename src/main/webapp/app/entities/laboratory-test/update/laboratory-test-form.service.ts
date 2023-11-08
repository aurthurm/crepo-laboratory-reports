import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ILaboratoryTest, NewLaboratoryTest } from '../laboratory-test.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILaboratoryTest for edit and NewLaboratoryTestFormGroupInput for create.
 */
type LaboratoryTestFormGroupInput = ILaboratoryTest | PartialWithRequiredKeyOf<NewLaboratoryTest>;

type LaboratoryTestFormDefaults = Pick<NewLaboratoryTest, 'id'>;

type LaboratoryTestFormGroupContent = {
  id: FormControl<ILaboratoryTest['id'] | NewLaboratoryTest['id']>;
  laboratoryId: FormControl<ILaboratoryTest['laboratoryId']>;
  laboratory: FormControl<ILaboratoryTest['laboratory']>;
  testId: FormControl<ILaboratoryTest['testId']>;
  test: FormControl<ILaboratoryTest['test']>;
  departmentId: FormControl<ILaboratoryTest['departmentId']>;
  department: FormControl<ILaboratoryTest['department']>;
};

export type LaboratoryTestFormGroup = FormGroup<LaboratoryTestFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LaboratoryTestFormService {
  createLaboratoryTestFormGroup(laboratoryTest: LaboratoryTestFormGroupInput = { id: null }): LaboratoryTestFormGroup {
    const laboratoryTestRawValue = {
      ...this.getFormDefaults(),
      ...laboratoryTest,
    };
    return new FormGroup<LaboratoryTestFormGroupContent>({
      id: new FormControl(
        { value: laboratoryTestRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(laboratoryTestRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(laboratoryTestRawValue.laboratory),
      testId: new FormControl(laboratoryTestRawValue.testId, {
        validators: [Validators.required],
      }),
      test: new FormControl(laboratoryTestRawValue.test),
      departmentId: new FormControl(laboratoryTestRawValue.departmentId),
      department: new FormControl(laboratoryTestRawValue.department),
    });
  }

  getLaboratoryTest(form: LaboratoryTestFormGroup): ILaboratoryTest | NewLaboratoryTest {
    return form.getRawValue() as ILaboratoryTest | NewLaboratoryTest;
  }

  resetForm(form: LaboratoryTestFormGroup, laboratoryTest: LaboratoryTestFormGroupInput): void {
    const laboratoryTestRawValue = { ...this.getFormDefaults(), ...laboratoryTest };
    form.reset(
      {
        ...laboratoryTestRawValue,
        id: { value: laboratoryTestRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): LaboratoryTestFormDefaults {
    return {
      id: null,
    };
  }
}
