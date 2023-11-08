import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILaboratoryTest, NewLaboratoryTest } from '../laboratory-test.model';

export type PartialUpdateLaboratoryTest = Partial<ILaboratoryTest> & Pick<ILaboratoryTest, 'id'>;

export type EntityResponseType = HttpResponse<ILaboratoryTest>;
export type EntityArrayResponseType = HttpResponse<ILaboratoryTest[]>;

@Injectable({ providedIn: 'root' })
export class LaboratoryTestService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/laboratory-tests');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(laboratoryTest: NewLaboratoryTest): Observable<EntityResponseType> {
    return this.http.post<ILaboratoryTest>(this.resourceUrl, laboratoryTest, { observe: 'response' });
  }

  update(laboratoryTest: ILaboratoryTest): Observable<EntityResponseType> {
    return this.http.put<ILaboratoryTest>(`${this.resourceUrl}/${this.getLaboratoryTestIdentifier(laboratoryTest)}`, laboratoryTest, {
      observe: 'response',
    });
  }

  partialUpdate(laboratoryTest: PartialUpdateLaboratoryTest): Observable<EntityResponseType> {
    return this.http.patch<ILaboratoryTest>(`${this.resourceUrl}/${this.getLaboratoryTestIdentifier(laboratoryTest)}`, laboratoryTest, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILaboratoryTest>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILaboratoryTest[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLaboratoryTestIdentifier(laboratoryTest: Pick<ILaboratoryTest, 'id'>): number {
    return laboratoryTest.id;
  }

  compareLaboratoryTest(o1: Pick<ILaboratoryTest, 'id'> | null, o2: Pick<ILaboratoryTest, 'id'> | null): boolean {
    return o1 && o2 ? this.getLaboratoryTestIdentifier(o1) === this.getLaboratoryTestIdentifier(o2) : o1 === o2;
  }

  addLaboratoryTestToCollectionIfMissing<Type extends Pick<ILaboratoryTest, 'id'>>(
    laboratoryTestCollection: Type[],
    ...laboratoryTestsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const laboratoryTests: Type[] = laboratoryTestsToCheck.filter(isPresent);
    if (laboratoryTests.length > 0) {
      const laboratoryTestCollectionIdentifiers = laboratoryTestCollection.map(
        laboratoryTestItem => this.getLaboratoryTestIdentifier(laboratoryTestItem)!,
      );
      const laboratoryTestsToAdd = laboratoryTests.filter(laboratoryTestItem => {
        const laboratoryTestIdentifier = this.getLaboratoryTestIdentifier(laboratoryTestItem);
        if (laboratoryTestCollectionIdentifiers.includes(laboratoryTestIdentifier)) {
          return false;
        }
        laboratoryTestCollectionIdentifiers.push(laboratoryTestIdentifier);
        return true;
      });
      return [...laboratoryTestsToAdd, ...laboratoryTestCollection];
    }
    return laboratoryTestCollection;
  }
}
