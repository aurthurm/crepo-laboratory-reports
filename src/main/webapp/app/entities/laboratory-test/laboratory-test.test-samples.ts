import { ILaboratoryTest, NewLaboratoryTest } from './laboratory-test.model';

export const sampleWithRequiredData: ILaboratoryTest = {
  id: 26641,
  laboratoryId: 'playfully psst',
  testId: 'mockingly',
};

export const sampleWithPartialData: ILaboratoryTest = {
  id: 7473,
  laboratoryId: 'photo ridge',
  laboratory: 'because wince',
  testId: 'ignite',
  departmentId: 'loft athwart gently',
};

export const sampleWithFullData: ILaboratoryTest = {
  id: 6267,
  laboratoryId: 'gah quicker by',
  laboratory: 'pretty shrilly councilman',
  testId: 'fiercely furthermore pish',
  test: 'plush small',
  departmentId: 'disburse',
  department: 'midst brr gosh',
};

export const sampleWithNewData: NewLaboratoryTest = {
  laboratoryId: 'psst closely quirkily',
  testId: 'vice smudge',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
