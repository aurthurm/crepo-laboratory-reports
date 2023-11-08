import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IReportingPeriod, NewReportingPeriod } from '../reporting-period.model';

export type PartialUpdateReportingPeriod = Partial<IReportingPeriod> & Pick<IReportingPeriod, 'id'>;

export type EntityResponseType = HttpResponse<IReportingPeriod>;
export type EntityArrayResponseType = HttpResponse<IReportingPeriod[]>;

@Injectable({ providedIn: 'root' })
export class ReportingPeriodService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/reporting-periods');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(reportingPeriod: NewReportingPeriod): Observable<EntityResponseType> {
    return this.http.post<IReportingPeriod>(this.resourceUrl, reportingPeriod, { observe: 'response' });
  }

  update(reportingPeriod: IReportingPeriod): Observable<EntityResponseType> {
    return this.http.put<IReportingPeriod>(`${this.resourceUrl}/${this.getReportingPeriodIdentifier(reportingPeriod)}`, reportingPeriod, {
      observe: 'response',
    });
  }

  partialUpdate(reportingPeriod: PartialUpdateReportingPeriod): Observable<EntityResponseType> {
    return this.http.patch<IReportingPeriod>(`${this.resourceUrl}/${this.getReportingPeriodIdentifier(reportingPeriod)}`, reportingPeriod, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReportingPeriod>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReportingPeriod[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getReportingPeriodIdentifier(reportingPeriod: Pick<IReportingPeriod, 'id'>): number {
    return reportingPeriod.id;
  }

  compareReportingPeriod(o1: Pick<IReportingPeriod, 'id'> | null, o2: Pick<IReportingPeriod, 'id'> | null): boolean {
    return o1 && o2 ? this.getReportingPeriodIdentifier(o1) === this.getReportingPeriodIdentifier(o2) : o1 === o2;
  }

  addReportingPeriodToCollectionIfMissing<Type extends Pick<IReportingPeriod, 'id'>>(
    reportingPeriodCollection: Type[],
    ...reportingPeriodsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const reportingPeriods: Type[] = reportingPeriodsToCheck.filter(isPresent);
    if (reportingPeriods.length > 0) {
      const reportingPeriodCollectionIdentifiers = reportingPeriodCollection.map(
        reportingPeriodItem => this.getReportingPeriodIdentifier(reportingPeriodItem)!,
      );
      const reportingPeriodsToAdd = reportingPeriods.filter(reportingPeriodItem => {
        const reportingPeriodIdentifier = this.getReportingPeriodIdentifier(reportingPeriodItem);
        if (reportingPeriodCollectionIdentifiers.includes(reportingPeriodIdentifier)) {
          return false;
        }
        reportingPeriodCollectionIdentifiers.push(reportingPeriodIdentifier);
        return true;
      });
      return [...reportingPeriodsToAdd, ...reportingPeriodCollection];
    }
    return reportingPeriodCollection;
  }
}
