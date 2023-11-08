import { IStaffCompiment, NewStaffCompiment } from './staff-compiment.model';

export const sampleWithRequiredData: IStaffCompiment = {
  id: 28304,
  laboratoryId: 'growing',
};

export const sampleWithPartialData: IStaffCompiment = {
  id: 24829,
  laboratoryId: 'uh-huh',
  departmentId: 'frantically lest choker',
  scientistAvailable: 29170,
  scientistRequired: 6024,
  microscopitsAvailable: 16712,
  microscopitsRequired: 14365,
  labTechsRequired: 32107,
};

export const sampleWithFullData: IStaffCompiment = {
  id: 26433,
  laboratoryId: 'sweaty until pish',
  laboratory: 'protrude yippee crazy',
  departmentId: 'jungle gosh',
  department: 'tag courageously smoothly',
  scientistAvailable: 2044,
  scientistRequired: 29778,
  microscopitsAvailable: 4081,
  microscopitsRequired: 17250,
  labTechsAvailable: 11520,
  labTechsRequired: 24148,
  generalHandsAvailable: 10899,
  generalHandsRequired: 30566,
};

export const sampleWithNewData: NewStaffCompiment = {
  laboratoryId: 'materialise',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
