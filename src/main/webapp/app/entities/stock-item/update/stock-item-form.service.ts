import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStockItem, NewStockItem } from '../stock-item.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStockItem for edit and NewStockItemFormGroupInput for create.
 */
type StockItemFormGroupInput = IStockItem | PartialWithRequiredKeyOf<NewStockItem>;

type StockItemFormDefaults = Pick<NewStockItem, 'id'>;

type StockItemFormGroupContent = {
  id: FormControl<IStockItem['id'] | NewStockItem['id']>;
  name: FormControl<IStockItem['name']>;
  description: FormControl<IStockItem['description']>;
};

export type StockItemFormGroup = FormGroup<StockItemFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StockItemFormService {
  createStockItemFormGroup(stockItem: StockItemFormGroupInput = { id: null }): StockItemFormGroup {
    const stockItemRawValue = {
      ...this.getFormDefaults(),
      ...stockItem,
    };
    return new FormGroup<StockItemFormGroupContent>({
      id: new FormControl(
        { value: stockItemRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(stockItemRawValue.name, {
        validators: [Validators.required],
      }),
      description: new FormControl(stockItemRawValue.description),
    });
  }

  getStockItem(form: StockItemFormGroup): IStockItem | NewStockItem {
    return form.getRawValue() as IStockItem | NewStockItem;
  }

  resetForm(form: StockItemFormGroup, stockItem: StockItemFormGroupInput): void {
    const stockItemRawValue = { ...this.getFormDefaults(), ...stockItem };
    form.reset(
      {
        ...stockItemRawValue,
        id: { value: stockItemRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): StockItemFormDefaults {
    return {
      id: null,
    };
  }
}
