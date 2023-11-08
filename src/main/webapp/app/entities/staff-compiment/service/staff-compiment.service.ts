import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStaffCompiment, NewStaffCompiment } from '../staff-compiment.model';

export type PartialUpdateStaffCompiment = Partial<IStaffCompiment> & Pick<IStaffCompiment, 'id'>;

export type EntityResponseType = HttpResponse<IStaffCompiment>;
export type EntityArrayResponseType = HttpResponse<IStaffCompiment[]>;

@Injectable({ providedIn: 'root' })
export class StaffCompimentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/staff-compiments');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(staffCompiment: NewStaffCompiment): Observable<EntityResponseType> {
    return this.http.post<IStaffCompiment>(this.resourceUrl, staffCompiment, { observe: 'response' });
  }

  update(staffCompiment: IStaffCompiment): Observable<EntityResponseType> {
    return this.http.put<IStaffCompiment>(`${this.resourceUrl}/${this.getStaffCompimentIdentifier(staffCompiment)}`, staffCompiment, {
      observe: 'response',
    });
  }

  partialUpdate(staffCompiment: PartialUpdateStaffCompiment): Observable<EntityResponseType> {
    return this.http.patch<IStaffCompiment>(`${this.resourceUrl}/${this.getStaffCompimentIdentifier(staffCompiment)}`, staffCompiment, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStaffCompiment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStaffCompiment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getStaffCompimentIdentifier(staffCompiment: Pick<IStaffCompiment, 'id'>): number {
    return staffCompiment.id;
  }

  compareStaffCompiment(o1: Pick<IStaffCompiment, 'id'> | null, o2: Pick<IStaffCompiment, 'id'> | null): boolean {
    return o1 && o2 ? this.getStaffCompimentIdentifier(o1) === this.getStaffCompimentIdentifier(o2) : o1 === o2;
  }

  addStaffCompimentToCollectionIfMissing<Type extends Pick<IStaffCompiment, 'id'>>(
    staffCompimentCollection: Type[],
    ...staffCompimentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const staffCompiments: Type[] = staffCompimentsToCheck.filter(isPresent);
    if (staffCompiments.length > 0) {
      const staffCompimentCollectionIdentifiers = staffCompimentCollection.map(
        staffCompimentItem => this.getStaffCompimentIdentifier(staffCompimentItem)!,
      );
      const staffCompimentsToAdd = staffCompiments.filter(staffCompimentItem => {
        const staffCompimentIdentifier = this.getStaffCompimentIdentifier(staffCompimentItem);
        if (staffCompimentCollectionIdentifiers.includes(staffCompimentIdentifier)) {
          return false;
        }
        staffCompimentCollectionIdentifiers.push(staffCompimentIdentifier);
        return true;
      });
      return [...staffCompimentsToAdd, ...staffCompimentCollection];
    }
    return staffCompimentCollection;
  }
}
