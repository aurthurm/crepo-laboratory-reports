import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReportTestPeformance } from '../report-test-peformance.model';
import { ReportTestPeformanceService } from '../service/report-test-peformance.service';

export const reportTestPeformanceResolve = (route: ActivatedRouteSnapshot): Observable<null | IReportTestPeformance> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReportTestPeformanceService)
      .find(id)
      .pipe(
        mergeMap((reportTestPeformance: HttpResponse<IReportTestPeformance>) => {
          if (reportTestPeformance.body) {
            return of(reportTestPeformance.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default reportTestPeformanceResolve;
