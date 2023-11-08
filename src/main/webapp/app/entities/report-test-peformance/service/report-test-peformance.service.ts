import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IReportTestPeformance, NewReportTestPeformance } from '../report-test-peformance.model';

export type PartialUpdateReportTestPeformance = Partial<IReportTestPeformance> & Pick<IReportTestPeformance, 'id'>;

export type EntityResponseType = HttpResponse<IReportTestPeformance>;
export type EntityArrayResponseType = HttpResponse<IReportTestPeformance[]>;

@Injectable({ providedIn: 'root' })
export class ReportTestPeformanceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/report-test-peformances');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(reportTestPeformance: NewReportTestPeformance): Observable<EntityResponseType> {
    return this.http.post<IReportTestPeformance>(this.resourceUrl, reportTestPeformance, { observe: 'response' });
  }

  update(reportTestPeformance: IReportTestPeformance): Observable<EntityResponseType> {
    return this.http.put<IReportTestPeformance>(
      `${this.resourceUrl}/${this.getReportTestPeformanceIdentifier(reportTestPeformance)}`,
      reportTestPeformance,
      { observe: 'response' },
    );
  }

  partialUpdate(reportTestPeformance: PartialUpdateReportTestPeformance): Observable<EntityResponseType> {
    return this.http.patch<IReportTestPeformance>(
      `${this.resourceUrl}/${this.getReportTestPeformanceIdentifier(reportTestPeformance)}`,
      reportTestPeformance,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReportTestPeformance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReportTestPeformance[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getReportTestPeformanceIdentifier(reportTestPeformance: Pick<IReportTestPeformance, 'id'>): number {
    return reportTestPeformance.id;
  }

  compareReportTestPeformance(o1: Pick<IReportTestPeformance, 'id'> | null, o2: Pick<IReportTestPeformance, 'id'> | null): boolean {
    return o1 && o2 ? this.getReportTestPeformanceIdentifier(o1) === this.getReportTestPeformanceIdentifier(o2) : o1 === o2;
  }

  addReportTestPeformanceToCollectionIfMissing<Type extends Pick<IReportTestPeformance, 'id'>>(
    reportTestPeformanceCollection: Type[],
    ...reportTestPeformancesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const reportTestPeformances: Type[] = reportTestPeformancesToCheck.filter(isPresent);
    if (reportTestPeformances.length > 0) {
      const reportTestPeformanceCollectionIdentifiers = reportTestPeformanceCollection.map(
        reportTestPeformanceItem => this.getReportTestPeformanceIdentifier(reportTestPeformanceItem)!,
      );
      const reportTestPeformancesToAdd = reportTestPeformances.filter(reportTestPeformanceItem => {
        const reportTestPeformanceIdentifier = this.getReportTestPeformanceIdentifier(reportTestPeformanceItem);
        if (reportTestPeformanceCollectionIdentifiers.includes(reportTestPeformanceIdentifier)) {
          return false;
        }
        reportTestPeformanceCollectionIdentifiers.push(reportTestPeformanceIdentifier);
        return true;
      });
      return [...reportTestPeformancesToAdd, ...reportTestPeformanceCollection];
    }
    return reportTestPeformanceCollection;
  }
}
