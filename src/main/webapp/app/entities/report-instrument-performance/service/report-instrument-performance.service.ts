import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IReportInstrumentPerformance, NewReportInstrumentPerformance } from '../report-instrument-performance.model';

export type PartialUpdateReportInstrumentPerformance = Partial<IReportInstrumentPerformance> & Pick<IReportInstrumentPerformance, 'id'>;

export type EntityResponseType = HttpResponse<IReportInstrumentPerformance>;
export type EntityArrayResponseType = HttpResponse<IReportInstrumentPerformance[]>;

@Injectable({ providedIn: 'root' })
export class ReportInstrumentPerformanceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/report-instrument-performances');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(reportInstrumentPerformance: NewReportInstrumentPerformance): Observable<EntityResponseType> {
    return this.http.post<IReportInstrumentPerformance>(this.resourceUrl, reportInstrumentPerformance, { observe: 'response' });
  }

  update(reportInstrumentPerformance: IReportInstrumentPerformance): Observable<EntityResponseType> {
    return this.http.put<IReportInstrumentPerformance>(
      `${this.resourceUrl}/${this.getReportInstrumentPerformanceIdentifier(reportInstrumentPerformance)}`,
      reportInstrumentPerformance,
      { observe: 'response' },
    );
  }

  partialUpdate(reportInstrumentPerformance: PartialUpdateReportInstrumentPerformance): Observable<EntityResponseType> {
    return this.http.patch<IReportInstrumentPerformance>(
      `${this.resourceUrl}/${this.getReportInstrumentPerformanceIdentifier(reportInstrumentPerformance)}`,
      reportInstrumentPerformance,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReportInstrumentPerformance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReportInstrumentPerformance[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getReportInstrumentPerformanceIdentifier(reportInstrumentPerformance: Pick<IReportInstrumentPerformance, 'id'>): number {
    return reportInstrumentPerformance.id;
  }

  compareReportInstrumentPerformance(
    o1: Pick<IReportInstrumentPerformance, 'id'> | null,
    o2: Pick<IReportInstrumentPerformance, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getReportInstrumentPerformanceIdentifier(o1) === this.getReportInstrumentPerformanceIdentifier(o2) : o1 === o2;
  }

  addReportInstrumentPerformanceToCollectionIfMissing<Type extends Pick<IReportInstrumentPerformance, 'id'>>(
    reportInstrumentPerformanceCollection: Type[],
    ...reportInstrumentPerformancesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const reportInstrumentPerformances: Type[] = reportInstrumentPerformancesToCheck.filter(isPresent);
    if (reportInstrumentPerformances.length > 0) {
      const reportInstrumentPerformanceCollectionIdentifiers = reportInstrumentPerformanceCollection.map(
        reportInstrumentPerformanceItem => this.getReportInstrumentPerformanceIdentifier(reportInstrumentPerformanceItem)!,
      );
      const reportInstrumentPerformancesToAdd = reportInstrumentPerformances.filter(reportInstrumentPerformanceItem => {
        const reportInstrumentPerformanceIdentifier = this.getReportInstrumentPerformanceIdentifier(reportInstrumentPerformanceItem);
        if (reportInstrumentPerformanceCollectionIdentifiers.includes(reportInstrumentPerformanceIdentifier)) {
          return false;
        }
        reportInstrumentPerformanceCollectionIdentifiers.push(reportInstrumentPerformanceIdentifier);
        return true;
      });
      return [...reportInstrumentPerformancesToAdd, ...reportInstrumentPerformanceCollection];
    }
    return reportInstrumentPerformanceCollection;
  }
}
