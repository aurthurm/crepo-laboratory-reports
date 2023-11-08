import { IStockItem, NewStockItem } from './stock-item.model';

export const sampleWithRequiredData: IStockItem = {
  id: 22774,
  name: 'deafening huzzah',
};

export const sampleWithPartialData: IStockItem = {
  id: 20695,
  name: 'for modulo yahoo',
  description: 'geez bustle gosh',
};

export const sampleWithFullData: IStockItem = {
  id: 13062,
  name: 'maybe',
  description: 'drat carefully',
};

export const sampleWithNewData: NewStockItem = {
  name: 'feline belch golden',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
