import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ILaboratoryTest } from '../laboratory-test.model';
import { LaboratoryTestService } from '../service/laboratory-test.service';

export const laboratoryTestResolve = (route: ActivatedRouteSnapshot): Observable<null | ILaboratoryTest> => {
  const id = route.params['id'];
  if (id) {
    return inject(LaboratoryTestService)
      .find(id)
      .pipe(
        mergeMap((laboratoryTest: HttpResponse<ILaboratoryTest>) => {
          if (laboratoryTest.body) {
            return of(laboratoryTest.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default laboratoryTestResolve;
