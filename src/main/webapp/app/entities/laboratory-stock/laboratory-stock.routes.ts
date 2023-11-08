import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { LaboratoryStockComponent } from './list/laboratory-stock.component';
import { LaboratoryStockDetailComponent } from './detail/laboratory-stock-detail.component';
import { LaboratoryStockUpdateComponent } from './update/laboratory-stock-update.component';
import LaboratoryStockResolve from './route/laboratory-stock-routing-resolve.service';

const laboratoryStockRoute: Routes = [
  {
    path: '',
    component: LaboratoryStockComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LaboratoryStockDetailComponent,
    resolve: {
      laboratoryStock: LaboratoryStockResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LaboratoryStockUpdateComponent,
    resolve: {
      laboratoryStock: LaboratoryStockResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LaboratoryStockUpdateComponent,
    resolve: {
      laboratoryStock: LaboratoryStockResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default laboratoryStockRoute;
