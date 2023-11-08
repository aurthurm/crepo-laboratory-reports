import { IReportDiseaseOutbreak, NewReportDiseaseOutbreak } from './report-disease-outbreak.model';

export const sampleWithRequiredData: IReportDiseaseOutbreak = {
  id: 997,
  laboratoryId: 'edit',
  reportingPeriodId: 'whose',
};

export const sampleWithPartialData: IReportDiseaseOutbreak = {
  id: 26009,
  laboratoryId: 'nocturnal unnecessarily deliberately',
  outbreak: 'YES',
  disease: 'if yum',
  comment: 'donation although yum',
  reportingPeriodId: 'woot powerless rotten',
};

export const sampleWithFullData: IReportDiseaseOutbreak = {
  id: 13946,
  laboratoryId: 'marry underneath minus',
  laboratory: 'twitter',
  outbreak: 'YES',
  disease: 'rations gosh pfft',
  comment: 'jellybeans urgently case',
  reportingPeriodId: 'curiously smart drat',
};

export const sampleWithNewData: NewReportDiseaseOutbreak = {
  laboratoryId: 'likely',
  reportingPeriodId: 'questionably',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
