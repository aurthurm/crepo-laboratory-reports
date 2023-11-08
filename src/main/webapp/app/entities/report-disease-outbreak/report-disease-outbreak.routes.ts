import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReportDiseaseOutbreakComponent } from './list/report-disease-outbreak.component';
import { ReportDiseaseOutbreakDetailComponent } from './detail/report-disease-outbreak-detail.component';
import { ReportDiseaseOutbreakUpdateComponent } from './update/report-disease-outbreak-update.component';
import ReportDiseaseOutbreakResolve from './route/report-disease-outbreak-routing-resolve.service';

const reportDiseaseOutbreakRoute: Routes = [
  {
    path: '',
    component: ReportDiseaseOutbreakComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportDiseaseOutbreakDetailComponent,
    resolve: {
      reportDiseaseOutbreak: ReportDiseaseOutbreakResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportDiseaseOutbreakUpdateComponent,
    resolve: {
      reportDiseaseOutbreak: ReportDiseaseOutbreakResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportDiseaseOutbreakUpdateComponent,
    resolve: {
      reportDiseaseOutbreak: ReportDiseaseOutbreakResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default reportDiseaseOutbreakRoute;
