import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IReportStockOut, NewReportStockOut } from '../report-stock-out.model';

export type PartialUpdateReportStockOut = Partial<IReportStockOut> & Pick<IReportStockOut, 'id'>;

export type EntityResponseType = HttpResponse<IReportStockOut>;
export type EntityArrayResponseType = HttpResponse<IReportStockOut[]>;

@Injectable({ providedIn: 'root' })
export class ReportStockOutService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/report-stock-outs');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(reportStockOut: NewReportStockOut): Observable<EntityResponseType> {
    return this.http.post<IReportStockOut>(this.resourceUrl, reportStockOut, { observe: 'response' });
  }

  update(reportStockOut: IReportStockOut): Observable<EntityResponseType> {
    return this.http.put<IReportStockOut>(`${this.resourceUrl}/${this.getReportStockOutIdentifier(reportStockOut)}`, reportStockOut, {
      observe: 'response',
    });
  }

  partialUpdate(reportStockOut: PartialUpdateReportStockOut): Observable<EntityResponseType> {
    return this.http.patch<IReportStockOut>(`${this.resourceUrl}/${this.getReportStockOutIdentifier(reportStockOut)}`, reportStockOut, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReportStockOut>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReportStockOut[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getReportStockOutIdentifier(reportStockOut: Pick<IReportStockOut, 'id'>): number {
    return reportStockOut.id;
  }

  compareReportStockOut(o1: Pick<IReportStockOut, 'id'> | null, o2: Pick<IReportStockOut, 'id'> | null): boolean {
    return o1 && o2 ? this.getReportStockOutIdentifier(o1) === this.getReportStockOutIdentifier(o2) : o1 === o2;
  }

  addReportStockOutToCollectionIfMissing<Type extends Pick<IReportStockOut, 'id'>>(
    reportStockOutCollection: Type[],
    ...reportStockOutsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const reportStockOuts: Type[] = reportStockOutsToCheck.filter(isPresent);
    if (reportStockOuts.length > 0) {
      const reportStockOutCollectionIdentifiers = reportStockOutCollection.map(
        reportStockOutItem => this.getReportStockOutIdentifier(reportStockOutItem)!,
      );
      const reportStockOutsToAdd = reportStockOuts.filter(reportStockOutItem => {
        const reportStockOutIdentifier = this.getReportStockOutIdentifier(reportStockOutItem);
        if (reportStockOutCollectionIdentifiers.includes(reportStockOutIdentifier)) {
          return false;
        }
        reportStockOutCollectionIdentifiers.push(reportStockOutIdentifier);
        return true;
      });
      return [...reportStockOutsToAdd, ...reportStockOutCollection];
    }
    return reportStockOutCollection;
  }
}
