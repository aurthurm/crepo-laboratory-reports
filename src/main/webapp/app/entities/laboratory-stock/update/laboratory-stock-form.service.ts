import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ILaboratoryStock, NewLaboratoryStock } from '../laboratory-stock.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILaboratoryStock for edit and NewLaboratoryStockFormGroupInput for create.
 */
type LaboratoryStockFormGroupInput = ILaboratoryStock | PartialWithRequiredKeyOf<NewLaboratoryStock>;

type LaboratoryStockFormDefaults = Pick<NewLaboratoryStock, 'id'>;

type LaboratoryStockFormGroupContent = {
  id: FormControl<ILaboratoryStock['id'] | NewLaboratoryStock['id']>;
  laboratoryId: FormControl<ILaboratoryStock['laboratoryId']>;
  laboratory: FormControl<ILaboratoryStock['laboratory']>;
  stockItemId: FormControl<ILaboratoryStock['stockItemId']>;
  stockItem: FormControl<ILaboratoryStock['stockItem']>;
  departmentId: FormControl<ILaboratoryStock['departmentId']>;
  department: FormControl<ILaboratoryStock['department']>;
};

export type LaboratoryStockFormGroup = FormGroup<LaboratoryStockFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LaboratoryStockFormService {
  createLaboratoryStockFormGroup(laboratoryStock: LaboratoryStockFormGroupInput = { id: null }): LaboratoryStockFormGroup {
    const laboratoryStockRawValue = {
      ...this.getFormDefaults(),
      ...laboratoryStock,
    };
    return new FormGroup<LaboratoryStockFormGroupContent>({
      id: new FormControl(
        { value: laboratoryStockRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      laboratoryId: new FormControl(laboratoryStockRawValue.laboratoryId, {
        validators: [Validators.required],
      }),
      laboratory: new FormControl(laboratoryStockRawValue.laboratory),
      stockItemId: new FormControl(laboratoryStockRawValue.stockItemId, {
        validators: [Validators.required],
      }),
      stockItem: new FormControl(laboratoryStockRawValue.stockItem),
      departmentId: new FormControl(laboratoryStockRawValue.departmentId),
      department: new FormControl(laboratoryStockRawValue.department),
    });
  }

  getLaboratoryStock(form: LaboratoryStockFormGroup): ILaboratoryStock | NewLaboratoryStock {
    return form.getRawValue() as ILaboratoryStock | NewLaboratoryStock;
  }

  resetForm(form: LaboratoryStockFormGroup, laboratoryStock: LaboratoryStockFormGroupInput): void {
    const laboratoryStockRawValue = { ...this.getFormDefaults(), ...laboratoryStock };
    form.reset(
      {
        ...laboratoryStockRawValue,
        id: { value: laboratoryStockRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): LaboratoryStockFormDefaults {
    return {
      id: null,
    };
  }
}
