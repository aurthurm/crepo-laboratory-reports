import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStockItem } from '../stock-item.model';
import { StockItemService } from '../service/stock-item.service';

export const stockItemResolve = (route: ActivatedRouteSnapshot): Observable<null | IStockItem> => {
  const id = route.params['id'];
  if (id) {
    return inject(StockItemService)
      .find(id)
      .pipe(
        mergeMap((stockItem: HttpResponse<IStockItem>) => {
          if (stockItem.body) {
            return of(stockItem.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default stockItemResolve;
