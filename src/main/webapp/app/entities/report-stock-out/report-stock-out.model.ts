import { StockAvailability } from 'app/entities/enumerations/stock-availability.model';

export interface IReportStockOut {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  stockItemId?: string | null;
  stockItem?: string | null;
  departmentId?: string | null;
  department?: string | null;
  available?: keyof typeof StockAvailability | null;
  comment?: string | null;
  reportingPeriodId?: string | null;
}

export type NewReportStockOut = Omit<IReportStockOut, 'id'> & { id: null };
