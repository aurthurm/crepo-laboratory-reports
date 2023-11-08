export interface ILaboratoryTest {
  id: number;
  laboratoryId?: string | null;
  laboratory?: string | null;
  testId?: string | null;
  test?: string | null;
  departmentId?: string | null;
  department?: string | null;
}

export type NewLaboratoryTest = Omit<ILaboratoryTest, 'id'> & { id: null };
