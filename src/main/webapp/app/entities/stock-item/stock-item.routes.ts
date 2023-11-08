import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { StockItemComponent } from './list/stock-item.component';
import { StockItemDetailComponent } from './detail/stock-item-detail.component';
import { StockItemUpdateComponent } from './update/stock-item-update.component';
import StockItemResolve from './route/stock-item-routing-resolve.service';

const stockItemRoute: Routes = [
  {
    path: '',
    component: StockItemComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StockItemDetailComponent,
    resolve: {
      stockItem: StockItemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StockItemUpdateComponent,
    resolve: {
      stockItem: StockItemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StockItemUpdateComponent,
    resolve: {
      stockItem: StockItemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default stockItemRoute;
