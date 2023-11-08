import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReportDiseaseOutbreak } from '../report-disease-outbreak.model';
import { ReportDiseaseOutbreakService } from '../service/report-disease-outbreak.service';

export const reportDiseaseOutbreakResolve = (route: ActivatedRouteSnapshot): Observable<null | IReportDiseaseOutbreak> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReportDiseaseOutbreakService)
      .find(id)
      .pipe(
        mergeMap((reportDiseaseOutbreak: HttpResponse<IReportDiseaseOutbreak>) => {
          if (reportDiseaseOutbreak.body) {
            return of(reportDiseaseOutbreak.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default reportDiseaseOutbreakResolve;
