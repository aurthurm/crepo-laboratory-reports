import { Outbreak } from 'app/entities/enumerations/outbreak.model';

export interface IReportDiseaseOutbreak {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  outbreak?: keyof typeof Outbreak | null;
  disease?: string | null;
  comment?: string | null;
  reportingPeriodId?: string | null;
}

export type NewReportDiseaseOutbreak = Omit<IReportDiseaseOutbreak, 'id'> & { id: null };
