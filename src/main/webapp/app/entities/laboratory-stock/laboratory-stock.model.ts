export interface ILaboratoryStock {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  stockItemId?: string | null;
  stockItem?: string | null;
  departmentId?: string | null;
  department?: string | null;
}

export type NewLaboratoryStock = Omit<ILaboratoryStock, 'id'> & { id: null };
