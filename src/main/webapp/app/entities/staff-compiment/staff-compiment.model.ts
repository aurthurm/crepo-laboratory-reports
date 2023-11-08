export interface IStaffCompiment {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  departmentId?: string | null;
  department?: string | null;
  scientistAvailable?: number | null;
  scientistRequired?: number | null;
  microscopitsAvailable?: number | null;
  microscopitsRequired?: number | null;
  labTechsAvailable?: number | null;
  labTechsRequired?: number | null;
  generalHandsAvailable?: number | null;
  generalHandsRequired?: number | null;
}

export type NewStaffCompiment = Omit<IStaffCompiment, 'id'> & { id: null };
