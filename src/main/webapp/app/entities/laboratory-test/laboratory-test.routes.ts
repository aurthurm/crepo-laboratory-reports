import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { LaboratoryTestComponent } from './list/laboratory-test.component';
import { LaboratoryTestDetailComponent } from './detail/laboratory-test-detail.component';
import { LaboratoryTestUpdateComponent } from './update/laboratory-test-update.component';
import LaboratoryTestResolve from './route/laboratory-test-routing-resolve.service';

const laboratoryTestRoute: Routes = [
  {
    path: '',
    component: LaboratoryTestComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LaboratoryTestDetailComponent,
    resolve: {
      laboratoryTest: LaboratoryTestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LaboratoryTestUpdateComponent,
    resolve: {
      laboratoryTest: LaboratoryTestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LaboratoryTestUpdateComponent,
    resolve: {
      laboratoryTest: LaboratoryTestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default laboratoryTestRoute;
