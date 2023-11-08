import { ILaboratoryStock, NewLaboratoryStock } from './laboratory-stock.model';

export const sampleWithRequiredData: ILaboratoryStock = {
  id: 19199,
  laboratoryId: 'thunderbolt the absent',
  stockItemId: 'gah keenly',
};

export const sampleWithPartialData: ILaboratoryStock = {
  id: 19456,
  laboratoryId: 'opt',
  stockItemId: 'absentmindedly fritter ouch',
  departmentId: 'deliberately',
};

export const sampleWithFullData: ILaboratoryStock = {
  id: 4232,
  laboratoryId: 'save restfully double',
  laboratory: 'barring opposite alert',
  stockItemId: 'dresser',
  stockItem: 'polymerise',
  departmentId: 'unlike',
  department: 'offense blah orderly',
};

export const sampleWithNewData: NewLaboratoryStock = {
  laboratoryId: 'hence',
  stockItemId: 'mammoth',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
