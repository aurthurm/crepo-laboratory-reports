import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILaboratoryInstrument, NewLaboratoryInstrument } from '../laboratory-instrument.model';

export type PartialUpdateLaboratoryInstrument = Partial<ILaboratoryInstrument> & Pick<ILaboratoryInstrument, 'id'>;

export type EntityResponseType = HttpResponse<ILaboratoryInstrument>;
export type EntityArrayResponseType = HttpResponse<ILaboratoryInstrument[]>;

@Injectable({ providedIn: 'root' })
export class LaboratoryInstrumentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/laboratory-instruments');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(laboratoryInstrument: NewLaboratoryInstrument): Observable<EntityResponseType> {
    return this.http.post<ILaboratoryInstrument>(this.resourceUrl, laboratoryInstrument, { observe: 'response' });
  }

  update(laboratoryInstrument: ILaboratoryInstrument): Observable<EntityResponseType> {
    return this.http.put<ILaboratoryInstrument>(
      `${this.resourceUrl}/${this.getLaboratoryInstrumentIdentifier(laboratoryInstrument)}`,
      laboratoryInstrument,
      { observe: 'response' },
    );
  }

  partialUpdate(laboratoryInstrument: PartialUpdateLaboratoryInstrument): Observable<EntityResponseType> {
    return this.http.patch<ILaboratoryInstrument>(
      `${this.resourceUrl}/${this.getLaboratoryInstrumentIdentifier(laboratoryInstrument)}`,
      laboratoryInstrument,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILaboratoryInstrument>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILaboratoryInstrument[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLaboratoryInstrumentIdentifier(laboratoryInstrument: Pick<ILaboratoryInstrument, 'id'>): number {
    return laboratoryInstrument.id;
  }

  compareLaboratoryInstrument(o1: Pick<ILaboratoryInstrument, 'id'> | null, o2: Pick<ILaboratoryInstrument, 'id'> | null): boolean {
    return o1 && o2 ? this.getLaboratoryInstrumentIdentifier(o1) === this.getLaboratoryInstrumentIdentifier(o2) : o1 === o2;
  }

  addLaboratoryInstrumentToCollectionIfMissing<Type extends Pick<ILaboratoryInstrument, 'id'>>(
    laboratoryInstrumentCollection: Type[],
    ...laboratoryInstrumentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const laboratoryInstruments: Type[] = laboratoryInstrumentsToCheck.filter(isPresent);
    if (laboratoryInstruments.length > 0) {
      const laboratoryInstrumentCollectionIdentifiers = laboratoryInstrumentCollection.map(
        laboratoryInstrumentItem => this.getLaboratoryInstrumentIdentifier(laboratoryInstrumentItem)!,
      );
      const laboratoryInstrumentsToAdd = laboratoryInstruments.filter(laboratoryInstrumentItem => {
        const laboratoryInstrumentIdentifier = this.getLaboratoryInstrumentIdentifier(laboratoryInstrumentItem);
        if (laboratoryInstrumentCollectionIdentifiers.includes(laboratoryInstrumentIdentifier)) {
          return false;
        }
        laboratoryInstrumentCollectionIdentifiers.push(laboratoryInstrumentIdentifier);
        return true;
      });
      return [...laboratoryInstrumentsToAdd, ...laboratoryInstrumentCollection];
    }
    return laboratoryInstrumentCollection;
  }
}
