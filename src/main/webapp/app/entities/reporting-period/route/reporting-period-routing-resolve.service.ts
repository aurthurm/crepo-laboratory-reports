import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReportingPeriod } from '../reporting-period.model';
import { ReportingPeriodService } from '../service/reporting-period.service';

export const reportingPeriodResolve = (route: ActivatedRouteSnapshot): Observable<null | IReportingPeriod> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReportingPeriodService)
      .find(id)
      .pipe(
        mergeMap((reportingPeriod: HttpResponse<IReportingPeriod>) => {
          if (reportingPeriod.body) {
            return of(reportingPeriod.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default reportingPeriodResolve;
