export interface IStockItem {
  id: number;
  name?: string | null;
  description?: string | null;
}

export type NewStockItem = Omit<IStockItem, 'id'> & { id: null };
