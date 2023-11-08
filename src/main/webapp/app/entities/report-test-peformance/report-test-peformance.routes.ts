import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReportTestPeformanceComponent } from './list/report-test-peformance.component';
import { ReportTestPeformanceDetailComponent } from './detail/report-test-peformance-detail.component';
import { ReportTestPeformanceUpdateComponent } from './update/report-test-peformance-update.component';
import ReportTestPeformanceResolve from './route/report-test-peformance-routing-resolve.service';

const reportTestPeformanceRoute: Routes = [
  {
    path: '',
    component: ReportTestPeformanceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportTestPeformanceDetailComponent,
    resolve: {
      reportTestPeformance: ReportTestPeformanceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportTestPeformanceUpdateComponent,
    resolve: {
      reportTestPeformance: ReportTestPeformanceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportTestPeformanceUpdateComponent,
    resolve: {
      reportTestPeformance: ReportTestPeformanceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default reportTestPeformanceRoute;
