import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReportStockOutComponent } from './list/report-stock-out.component';
import { ReportStockOutDetailComponent } from './detail/report-stock-out-detail.component';
import { ReportStockOutUpdateComponent } from './update/report-stock-out-update.component';
import ReportStockOutResolve from './route/report-stock-out-routing-resolve.service';

const reportStockOutRoute: Routes = [
  {
    path: '',
    component: ReportStockOutComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportStockOutDetailComponent,
    resolve: {
      reportStockOut: ReportStockOutResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportStockOutUpdateComponent,
    resolve: {
      reportStockOut: ReportStockOutResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportStockOutUpdateComponent,
    resolve: {
      reportStockOut: ReportStockOutResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default reportStockOutRoute;
