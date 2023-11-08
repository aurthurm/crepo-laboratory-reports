import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { StaffCompimentComponent } from './list/staff-compiment.component';
import { StaffCompimentDetailComponent } from './detail/staff-compiment-detail.component';
import { StaffCompimentUpdateComponent } from './update/staff-compiment-update.component';
import StaffCompimentResolve from './route/staff-compiment-routing-resolve.service';

const staffCompimentRoute: Routes = [
  {
    path: '',
    component: StaffCompimentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StaffCompimentDetailComponent,
    resolve: {
      staffCompiment: StaffCompimentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StaffCompimentUpdateComponent,
    resolve: {
      staffCompiment: StaffCompimentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StaffCompimentUpdateComponent,
    resolve: {
      staffCompiment: StaffCompimentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default staffCompimentRoute;
