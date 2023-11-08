import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReportingPeriodComponent } from './list/reporting-period.component';
import { ReportingPeriodDetailComponent } from './detail/reporting-period-detail.component';
import { ReportingPeriodUpdateComponent } from './update/reporting-period-update.component';
import ReportingPeriodResolve from './route/reporting-period-routing-resolve.service';

const reportingPeriodRoute: Routes = [
  {
    path: '',
    component: ReportingPeriodComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportingPeriodDetailComponent,
    resolve: {
      reportingPeriod: ReportingPeriodResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportingPeriodUpdateComponent,
    resolve: {
      reportingPeriod: ReportingPeriodResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportingPeriodUpdateComponent,
    resolve: {
      reportingPeriod: ReportingPeriodResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default reportingPeriodRoute;
