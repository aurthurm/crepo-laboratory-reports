import { ILaboratoryInstrument, NewLaboratoryInstrument } from './laboratory-instrument.model';

export const sampleWithRequiredData: ILaboratoryInstrument = {
  id: 15583,
  laboratoryId: 'duh inwardly instructive',
  instrumentId: 'gah',
};

export const sampleWithPartialData: ILaboratoryInstrument = {
  id: 25887,
  laboratoryId: 'restfully er that',
  laboratory: 'why adhere',
  instrumentId: 'diligent pace optimistically',
};

export const sampleWithFullData: ILaboratoryInstrument = {
  id: 28379,
  laboratoryId: 'circa prickly bluff',
  laboratory: 'ranch eek judgementally',
  instrumentId: 'blab a the',
  instrument: 'athwart candid',
  departmentId: 'change about guilty',
  department: 'scarily near next',
};

export const sampleWithNewData: NewLaboratoryInstrument = {
  laboratoryId: 'single under stingy',
  instrumentId: 'supposing south',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
