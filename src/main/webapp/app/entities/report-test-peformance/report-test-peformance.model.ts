import { CriticalResult } from 'app/entities/enumerations/critical-result.model';

export interface IReportTestPeformance {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  departmentId?: string | null;
  department?: string | null;
  sampleTypeId?: string | null;
  sampleType?: string | null;
  testId?: string | null;
  test?: string | null;
  turnAroundTime?: string | null;
  numberTested?: string | null;
  numberDispatched?: string | null;
  numberRejected?: string | null;
  instrumentId?: string | null;
  instrument?: string | null;
  criticalResults?: keyof typeof CriticalResult | null;
  numberCritical?: string | null;
  criticalComment?: string | null;
  reportingPeriodId?: string | null;
}

export type NewReportTestPeformance = Omit<IReportTestPeformance, 'id'> & { id: null };
