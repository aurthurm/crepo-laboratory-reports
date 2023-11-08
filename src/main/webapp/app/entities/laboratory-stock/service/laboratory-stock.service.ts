import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILaboratoryStock, NewLaboratoryStock } from '../laboratory-stock.model';

export type PartialUpdateLaboratoryStock = Partial<ILaboratoryStock> & Pick<ILaboratoryStock, 'id'>;

export type EntityResponseType = HttpResponse<ILaboratoryStock>;
export type EntityArrayResponseType = HttpResponse<ILaboratoryStock[]>;

@Injectable({ providedIn: 'root' })
export class LaboratoryStockService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/laboratory-stocks');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(laboratoryStock: NewLaboratoryStock): Observable<EntityResponseType> {
    return this.http.post<ILaboratoryStock>(this.resourceUrl, laboratoryStock, { observe: 'response' });
  }

  update(laboratoryStock: ILaboratoryStock): Observable<EntityResponseType> {
    return this.http.put<ILaboratoryStock>(`${this.resourceUrl}/${this.getLaboratoryStockIdentifier(laboratoryStock)}`, laboratoryStock, {
      observe: 'response',
    });
  }

  partialUpdate(laboratoryStock: PartialUpdateLaboratoryStock): Observable<EntityResponseType> {
    return this.http.patch<ILaboratoryStock>(`${this.resourceUrl}/${this.getLaboratoryStockIdentifier(laboratoryStock)}`, laboratoryStock, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILaboratoryStock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILaboratoryStock[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLaboratoryStockIdentifier(laboratoryStock: Pick<ILaboratoryStock, 'id'>): number {
    return laboratoryStock.id;
  }

  compareLaboratoryStock(o1: Pick<ILaboratoryStock, 'id'> | null, o2: Pick<ILaboratoryStock, 'id'> | null): boolean {
    return o1 && o2 ? this.getLaboratoryStockIdentifier(o1) === this.getLaboratoryStockIdentifier(o2) : o1 === o2;
  }

  addLaboratoryStockToCollectionIfMissing<Type extends Pick<ILaboratoryStock, 'id'>>(
    laboratoryStockCollection: Type[],
    ...laboratoryStocksToCheck: (Type | null | undefined)[]
  ): Type[] {
    const laboratoryStocks: Type[] = laboratoryStocksToCheck.filter(isPresent);
    if (laboratoryStocks.length > 0) {
      const laboratoryStockCollectionIdentifiers = laboratoryStockCollection.map(
        laboratoryStockItem => this.getLaboratoryStockIdentifier(laboratoryStockItem)!,
      );
      const laboratoryStocksToAdd = laboratoryStocks.filter(laboratoryStockItem => {
        const laboratoryStockIdentifier = this.getLaboratoryStockIdentifier(laboratoryStockItem);
        if (laboratoryStockCollectionIdentifiers.includes(laboratoryStockIdentifier)) {
          return false;
        }
        laboratoryStockCollectionIdentifiers.push(laboratoryStockIdentifier);
        return true;
      });
      return [...laboratoryStocksToAdd, ...laboratoryStockCollection];
    }
    return laboratoryStockCollection;
  }
}
