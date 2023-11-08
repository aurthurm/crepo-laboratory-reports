import { IReportTestPeformance, NewReportTestPeformance } from './report-test-peformance.model';

export const sampleWithRequiredData: IReportTestPeformance = {
  id: 1579,
  laboratoryId: 'given mortally',
  sampleTypeId: 'meter',
  testId: 'afford dependent remarkable',
  instrumentId: 'upliftingly complete whose',
  reportingPeriodId: 'yuck although',
};

export const sampleWithPartialData: IReportTestPeformance = {
  id: 21211,
  laboratoryId: 'aw foolishly but',
  departmentId: 'forenenst',
  department: 'exemplary plus rectangular',
  sampleTypeId: 'review',
  testId: 'swoosh thirsty',
  test: 'holster longingly',
  numberDispatched: 'metronome',
  numberRejected: 'rudely',
  instrumentId: 'hungrily throughout',
  criticalResults: 'YES',
  criticalComment: 'how mud',
  reportingPeriodId: 'cashier rudiment because',
};

export const sampleWithFullData: IReportTestPeformance = {
  id: 2566,
  laboratoryId: 'natter',
  laboratory: 'rebellion finally',
  departmentId: 'ray woot ick',
  department: 'fast',
  sampleTypeId: 'because er',
  sampleType: 'before externalize',
  testId: 'out so of',
  test: 'who yahoo why',
  turnAroundTime: 'geranium jubilantly merrily',
  numberTested: 'gossip for although',
  numberDispatched: 'aw modulo',
  numberRejected: 'for alongside questionably',
  instrumentId: 'without disappoint',
  instrument: 'ouch slop understated',
  criticalResults: 'NO',
  numberCritical: 'mortified dynamo',
  criticalComment: 'till',
  reportingPeriodId: 'er annually',
};

export const sampleWithNewData: NewReportTestPeformance = {
  laboratoryId: 'what inability ack',
  sampleTypeId: 'versus',
  testId: 'external',
  instrumentId: 'aha mindless',
  reportingPeriodId: 'top successfully caucus',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
