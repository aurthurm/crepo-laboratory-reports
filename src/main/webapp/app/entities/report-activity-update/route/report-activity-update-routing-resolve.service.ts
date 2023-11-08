import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReportActivityUpdate } from '../report-activity-update.model';
import { ReportActivityUpdateService } from '../service/report-activity-update.service';

export const reportActivityUpdateResolve = (route: ActivatedRouteSnapshot): Observable<null | IReportActivityUpdate> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReportActivityUpdateService)
      .find(id)
      .pipe(
        mergeMap((reportActivityUpdate: HttpResponse<IReportActivityUpdate>) => {
          if (reportActivityUpdate.body) {
            return of(reportActivityUpdate.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default reportActivityUpdateResolve;
