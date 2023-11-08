import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReportInstrumentPerformanceComponent } from './list/report-instrument-performance.component';
import { ReportInstrumentPerformanceDetailComponent } from './detail/report-instrument-performance-detail.component';
import { ReportInstrumentPerformanceUpdateComponent } from './update/report-instrument-performance-update.component';
import ReportInstrumentPerformanceResolve from './route/report-instrument-performance-routing-resolve.service';

const reportInstrumentPerformanceRoute: Routes = [
  {
    path: '',
    component: ReportInstrumentPerformanceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportInstrumentPerformanceDetailComponent,
    resolve: {
      reportInstrumentPerformance: ReportInstrumentPerformanceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportInstrumentPerformanceUpdateComponent,
    resolve: {
      reportInstrumentPerformance: ReportInstrumentPerformanceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportInstrumentPerformanceUpdateComponent,
    resolve: {
      reportInstrumentPerformance: ReportInstrumentPerformanceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default reportInstrumentPerformanceRoute;
