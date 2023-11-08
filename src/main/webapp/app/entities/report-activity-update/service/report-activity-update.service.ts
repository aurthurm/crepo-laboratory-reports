import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IReportActivityUpdate, NewReportActivityUpdate } from '../report-activity-update.model';

export type PartialUpdateReportActivityUpdate = Partial<IReportActivityUpdate> & Pick<IReportActivityUpdate, 'id'>;

export type EntityResponseType = HttpResponse<IReportActivityUpdate>;
export type EntityArrayResponseType = HttpResponse<IReportActivityUpdate[]>;

@Injectable({ providedIn: 'root' })
export class ReportActivityUpdateService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/report-activity-updates');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(reportActivityUpdate: NewReportActivityUpdate): Observable<EntityResponseType> {
    return this.http.post<IReportActivityUpdate>(this.resourceUrl, reportActivityUpdate, { observe: 'response' });
  }

  update(reportActivityUpdate: IReportActivityUpdate): Observable<EntityResponseType> {
    return this.http.put<IReportActivityUpdate>(
      `${this.resourceUrl}/${this.getReportActivityUpdateIdentifier(reportActivityUpdate)}`,
      reportActivityUpdate,
      { observe: 'response' },
    );
  }

  partialUpdate(reportActivityUpdate: PartialUpdateReportActivityUpdate): Observable<EntityResponseType> {
    return this.http.patch<IReportActivityUpdate>(
      `${this.resourceUrl}/${this.getReportActivityUpdateIdentifier(reportActivityUpdate)}`,
      reportActivityUpdate,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReportActivityUpdate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReportActivityUpdate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getReportActivityUpdateIdentifier(reportActivityUpdate: Pick<IReportActivityUpdate, 'id'>): number {
    return reportActivityUpdate.id;
  }

  compareReportActivityUpdate(o1: Pick<IReportActivityUpdate, 'id'> | null, o2: Pick<IReportActivityUpdate, 'id'> | null): boolean {
    return o1 && o2 ? this.getReportActivityUpdateIdentifier(o1) === this.getReportActivityUpdateIdentifier(o2) : o1 === o2;
  }

  addReportActivityUpdateToCollectionIfMissing<Type extends Pick<IReportActivityUpdate, 'id'>>(
    reportActivityUpdateCollection: Type[],
    ...reportActivityUpdatesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const reportActivityUpdates: Type[] = reportActivityUpdatesToCheck.filter(isPresent);
    if (reportActivityUpdates.length > 0) {
      const reportActivityUpdateCollectionIdentifiers = reportActivityUpdateCollection.map(
        reportActivityUpdateItem => this.getReportActivityUpdateIdentifier(reportActivityUpdateItem)!,
      );
      const reportActivityUpdatesToAdd = reportActivityUpdates.filter(reportActivityUpdateItem => {
        const reportActivityUpdateIdentifier = this.getReportActivityUpdateIdentifier(reportActivityUpdateItem);
        if (reportActivityUpdateCollectionIdentifiers.includes(reportActivityUpdateIdentifier)) {
          return false;
        }
        reportActivityUpdateCollectionIdentifiers.push(reportActivityUpdateIdentifier);
        return true;
      });
      return [...reportActivityUpdatesToAdd, ...reportActivityUpdateCollection];
    }
    return reportActivityUpdateCollection;
  }
}
