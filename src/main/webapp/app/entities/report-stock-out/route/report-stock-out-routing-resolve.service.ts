import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReportStockOut } from '../report-stock-out.model';
import { ReportStockOutService } from '../service/report-stock-out.service';

export const reportStockOutResolve = (route: ActivatedRouteSnapshot): Observable<null | IReportStockOut> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReportStockOutService)
      .find(id)
      .pipe(
        mergeMap((reportStockOut: HttpResponse<IReportStockOut>) => {
          if (reportStockOut.body) {
            return of(reportStockOut.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default reportStockOutResolve;
