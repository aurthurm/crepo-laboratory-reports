import { IReportingPeriod, NewReportingPeriod } from './reporting-period.model';

export const sampleWithRequiredData: IReportingPeriod = {
  id: 19885,
  month: 'rectangle gently',
  year: 7240,
};

export const sampleWithPartialData: IReportingPeriod = {
  id: 1197,
  day: 30313,
  week: 6762,
  month: 'geez skull if',
  year: 3777,
};

export const sampleWithFullData: IReportingPeriod = {
  id: 30083,
  day: 27897,
  week: 22661,
  month: 'suddenly unbearably',
  year: 31584,
};

export const sampleWithNewData: NewReportingPeriod = {
  month: 'hm',
  year: 9158,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
