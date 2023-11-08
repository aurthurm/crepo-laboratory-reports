import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReportActivityUpdateComponent } from './list/report-activity-update.component';
import { ReportActivityUpdateDetailComponent } from './detail/report-activity-update-detail.component';
import { ReportActivityUpdateUpdateComponent } from './update/report-activity-update-update.component';
import ReportActivityUpdateResolve from './route/report-activity-update-routing-resolve.service';

const reportActivityUpdateRoute: Routes = [
  {
    path: '',
    component: ReportActivityUpdateComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportActivityUpdateDetailComponent,
    resolve: {
      reportActivityUpdate: ReportActivityUpdateResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportActivityUpdateUpdateComponent,
    resolve: {
      reportActivityUpdate: ReportActivityUpdateResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportActivityUpdateUpdateComponent,
    resolve: {
      reportActivityUpdate: ReportActivityUpdateResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default reportActivityUpdateRoute;
