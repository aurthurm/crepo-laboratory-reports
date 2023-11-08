import { IReportInstrumentPerformance, NewReportInstrumentPerformance } from './report-instrument-performance.model';

export const sampleWithRequiredData: IReportInstrumentPerformance = {
  id: 26910,
  laboratoryId: 'playfully',
  instrumentId: 'finally',
  reportingPeriodId: 'when',
};

export const sampleWithPartialData: IReportInstrumentPerformance = {
  id: 21405,
  laboratoryId: 'begonia eek',
  department: 'rant provided',
  instrumentId: 'truly suspiciously river',
  instrument: 'legalise',
  uptime: 'oh tar silently',
  downtime: 'fearful',
  functionality: 'YES',
  reportingPeriodId: 'until minimize',
};

export const sampleWithFullData: IReportInstrumentPerformance = {
  id: 16288,
  laboratoryId: 'knee',
  laboratory: 'angrily minus',
  departmentId: 'genuflect after',
  department: 'collapse per anenst',
  instrumentId: 'scrawny with',
  instrument: 'through',
  status: 'repair',
  uptime: 'wedge vivaciously drat',
  downtime: 'disgusting',
  serviceStatus: 'pfft',
  caliberationStatus: 'uh-huh nicely reluctantly',
  functionality: 'NO',
  comment: 'quickly',
  reportingPeriodId: 'active',
};

export const sampleWithNewData: NewReportInstrumentPerformance = {
  laboratoryId: 'kill whose amidst',
  instrumentId: 'push pot jar',
  reportingPeriodId: 'ironclad roughly aha',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
