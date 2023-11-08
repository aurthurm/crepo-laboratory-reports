import { IReportStockOut, NewReportStockOut } from './report-stock-out.model';

export const sampleWithRequiredData: IReportStockOut = {
  id: 2781,
  laboratoryId: 'flint ah',
  stockItemId: 'times endear',
  reportingPeriodId: 'lest',
};

export const sampleWithPartialData: IReportStockOut = {
  id: 5560,
  laboratoryId: 'update among',
  stockItemId: 'doubling demineralize avalanche',
  department: 'far-flung boo',
  comment: 'rethinking',
  reportingPeriodId: 'soon reassuringly',
};

export const sampleWithFullData: IReportStockOut = {
  id: 21353,
  laboratoryId: 'tightly rebut competent',
  laboratory: 'relay',
  stockItemId: 'yum phooey',
  stockItem: 'inasmuch miskey',
  departmentId: 'yesterday',
  department: 'though',
  available: 'NOT_AVAILABLE',
  comment: 'thin',
  reportingPeriodId: 'whereas',
};

export const sampleWithNewData: NewReportStockOut = {
  laboratoryId: 'cave majestically controversy',
  stockItemId: 'famously',
  reportingPeriodId: 'asterisk why except',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
