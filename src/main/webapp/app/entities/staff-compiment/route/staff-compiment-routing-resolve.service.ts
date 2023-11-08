import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStaffCompiment } from '../staff-compiment.model';
import { StaffCompimentService } from '../service/staff-compiment.service';

export const staffCompimentResolve = (route: ActivatedRouteSnapshot): Observable<null | IStaffCompiment> => {
  const id = route.params['id'];
  if (id) {
    return inject(StaffCompimentService)
      .find(id)
      .pipe(
        mergeMap((staffCompiment: HttpResponse<IStaffCompiment>) => {
          if (staffCompiment.body) {
            return of(staffCompiment.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default staffCompimentResolve;
