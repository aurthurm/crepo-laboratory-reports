import { InstrumentFunctionality } from 'app/entities/enumerations/instrument-functionality.model';

export interface IReportInstrumentPerformance {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  departmentId?: string | null;
  department?: string | null;
  instrumentId?: string | null;
  instrument?: string | null;
  status?: string | null;
  uptime?: string | null;
  downtime?: string | null;
  serviceStatus?: string | null;
  caliberationStatus?: string | null;
  functionality?: keyof typeof InstrumentFunctionality | null;
  comment?: string | null;
  reportingPeriodId?: string | null;
}

export type NewReportInstrumentPerformance = Omit<IReportInstrumentPerformance, 'id'> & { id: null };
