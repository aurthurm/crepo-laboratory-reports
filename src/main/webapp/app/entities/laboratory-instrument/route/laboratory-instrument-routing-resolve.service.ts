import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ILaboratoryInstrument } from '../laboratory-instrument.model';
import { LaboratoryInstrumentService } from '../service/laboratory-instrument.service';

export const laboratoryInstrumentResolve = (route: ActivatedRouteSnapshot): Observable<null | ILaboratoryInstrument> => {
  const id = route.params['id'];
  if (id) {
    return inject(LaboratoryInstrumentService)
      .find(id)
      .pipe(
        mergeMap((laboratoryInstrument: HttpResponse<ILaboratoryInstrument>) => {
          if (laboratoryInstrument.body) {
            return of(laboratoryInstrument.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default laboratoryInstrumentResolve;
