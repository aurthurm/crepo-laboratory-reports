export interface IReportingPeriod {
  id: number;
  day?: number | null;
  week?: number | null;
  month?: string | null;
  year?: number | null;
}

export type NewReportingPeriod = Omit<IReportingPeriod, 'id'> & { id: null };
