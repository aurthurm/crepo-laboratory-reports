export interface IReportActivityUpdate {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  activity?: string | null;
  activityDetails?: string | null;
  outcomes?: string | null;
  comments?: string | null;
  reportingPeriodId?: string | null;
}

export type NewReportActivityUpdate = Omit<IReportActivityUpdate, 'id'> & { id: null };
