import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReportInstrumentPerformance } from '../report-instrument-performance.model';
import { ReportInstrumentPerformanceService } from '../service/report-instrument-performance.service';

export const reportInstrumentPerformanceResolve = (route: ActivatedRouteSnapshot): Observable<null | IReportInstrumentPerformance> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReportInstrumentPerformanceService)
      .find(id)
      .pipe(
        mergeMap((reportInstrumentPerformance: HttpResponse<IReportInstrumentPerformance>) => {
          if (reportInstrumentPerformance.body) {
            return of(reportInstrumentPerformance.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default reportInstrumentPerformanceResolve;
