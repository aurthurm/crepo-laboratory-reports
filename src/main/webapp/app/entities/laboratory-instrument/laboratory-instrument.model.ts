export interface ILaboratoryInstrument {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  instrumentId?: string | null;
  instrument?: string | null;
  departmentId?: string | null;
  department?: string | null;
}

export type NewLaboratoryInstrument = Omit<ILaboratoryInstrument, 'id'> & { id: null };
