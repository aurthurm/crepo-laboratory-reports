import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IReportDiseaseOutbreak, NewReportDiseaseOutbreak } from '../report-disease-outbreak.model';

export type PartialUpdateReportDiseaseOutbreak = Partial<IReportDiseaseOutbreak> & Pick<IReportDiseaseOutbreak, 'id'>;

export type EntityResponseType = HttpResponse<IReportDiseaseOutbreak>;
export type EntityArrayResponseType = HttpResponse<IReportDiseaseOutbreak[]>;

@Injectable({ providedIn: 'root' })
export class ReportDiseaseOutbreakService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/report-disease-outbreaks');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(reportDiseaseOutbreak: NewReportDiseaseOutbreak): Observable<EntityResponseType> {
    return this.http.post<IReportDiseaseOutbreak>(this.resourceUrl, reportDiseaseOutbreak, { observe: 'response' });
  }

  update(reportDiseaseOutbreak: IReportDiseaseOutbreak): Observable<EntityResponseType> {
    return this.http.put<IReportDiseaseOutbreak>(
      `${this.resourceUrl}/${this.getReportDiseaseOutbreakIdentifier(reportDiseaseOutbreak)}`,
      reportDiseaseOutbreak,
      { observe: 'response' },
    );
  }

  partialUpdate(reportDiseaseOutbreak: PartialUpdateReportDiseaseOutbreak): Observable<EntityResponseType> {
    return this.http.patch<IReportDiseaseOutbreak>(
      `${this.resourceUrl}/${this.getReportDiseaseOutbreakIdentifier(reportDiseaseOutbreak)}`,
      reportDiseaseOutbreak,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReportDiseaseOutbreak>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReportDiseaseOutbreak[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getReportDiseaseOutbreakIdentifier(reportDiseaseOutbreak: Pick<IReportDiseaseOutbreak, 'id'>): number {
    return reportDiseaseOutbreak.id;
  }

  compareReportDiseaseOutbreak(o1: Pick<IReportDiseaseOutbreak, 'id'> | null, o2: Pick<IReportDiseaseOutbreak, 'id'> | null): boolean {
    return o1 && o2 ? this.getReportDiseaseOutbreakIdentifier(o1) === this.getReportDiseaseOutbreakIdentifier(o2) : o1 === o2;
  }

  addReportDiseaseOutbreakToCollectionIfMissing<Type extends Pick<IReportDiseaseOutbreak, 'id'>>(
    reportDiseaseOutbreakCollection: Type[],
    ...reportDiseaseOutbreaksToCheck: (Type | null | undefined)[]
  ): Type[] {
    const reportDiseaseOutbreaks: Type[] = reportDiseaseOutbreaksToCheck.filter(isPresent);
    if (reportDiseaseOutbreaks.length > 0) {
      const reportDiseaseOutbreakCollectionIdentifiers = reportDiseaseOutbreakCollection.map(
        reportDiseaseOutbreakItem => this.getReportDiseaseOutbreakIdentifier(reportDiseaseOutbreakItem)!,
      );
      const reportDiseaseOutbreaksToAdd = reportDiseaseOutbreaks.filter(reportDiseaseOutbreakItem => {
        const reportDiseaseOutbreakIdentifier = this.getReportDiseaseOutbreakIdentifier(reportDiseaseOutbreakItem);
        if (reportDiseaseOutbreakCollectionIdentifiers.includes(reportDiseaseOutbreakIdentifier)) {
          return false;
        }
        reportDiseaseOutbreakCollectionIdentifiers.push(reportDiseaseOutbreakIdentifier);
        return true;
      });
      return [...reportDiseaseOutbreaksToAdd, ...reportDiseaseOutbreakCollection];
    }
    return reportDiseaseOutbreakCollection;
  }
}
