import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { LaboratoryInstrumentComponent } from './list/laboratory-instrument.component';
import { LaboratoryInstrumentDetailComponent } from './detail/laboratory-instrument-detail.component';
import { LaboratoryInstrumentUpdateComponent } from './update/laboratory-instrument-update.component';
import LaboratoryInstrumentResolve from './route/laboratory-instrument-routing-resolve.service';

const laboratoryInstrumentRoute: Routes = [
  {
    path: '',
    component: LaboratoryInstrumentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LaboratoryInstrumentDetailComponent,
    resolve: {
      laboratoryInstrument: LaboratoryInstrumentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LaboratoryInstrumentUpdateComponent,
    resolve: {
      laboratoryInstrument: LaboratoryInstrumentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LaboratoryInstrumentUpdateComponent,
    resolve: {
      laboratoryInstrument: LaboratoryInstrumentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default laboratoryInstrumentRoute;
