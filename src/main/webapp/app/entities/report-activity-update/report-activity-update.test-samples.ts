import { IReportActivityUpdate, NewReportActivityUpdate } from './report-activity-update.model';

export const sampleWithRequiredData: IReportActivityUpdate = {
  id: 24736,
  laboratoryId: 'notable',
  reportingPeriodId: 'lonely authorized',
};

export const sampleWithPartialData: IReportActivityUpdate = {
  id: 19847,
  laboratoryId: 'unlike',
  laboratory: 'enliven',
  activity: 'how fly flicker',
  outcomes: 'since coolly whereas',
  reportingPeriodId: 'wannabe',
};

export const sampleWithFullData: IReportActivityUpdate = {
  id: 23550,
  laboratoryId: 'boo pish absent',
  laboratory: 'vitro mushy armadillo',
  activity: 'truly',
  activityDetails: 'ew square',
  outcomes: 'wedding till',
  comments: 'plait yowza boohoo',
  reportingPeriodId: 'whereas till',
};

export const sampleWithNewData: NewReportActivityUpdate = {
  laboratoryId: 'best unnaturally insist',
  reportingPeriodId: 'jeweller whereas',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
