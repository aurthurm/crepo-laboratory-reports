import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'reporting-period',
        data: { pageTitle: 'cRepoExtensioApp.reportingPeriod.home.title' },
        loadChildren: () => import('./reporting-period/reporting-period.routes'),
      },
      {
        path: 'laboratory-test',
        data: { pageTitle: 'cRepoExtensioApp.laboratoryTest.home.title' },
        loadChildren: () => import('./laboratory-test/laboratory-test.routes'),
      },
      {
        path: 'report-test-peformance',
        data: { pageTitle: 'cRepoExtensioApp.reportTestPeformance.home.title' },
        loadChildren: () => import('./report-test-peformance/report-test-peformance.routes'),
      },
      {
        path: 'laboratory-instrument',
        data: { pageTitle: 'cRepoExtensioApp.laboratoryInstrument.home.title' },
        loadChildren: () => import('./laboratory-instrument/laboratory-instrument.routes'),
      },
      {
        path: 'report-instrument-performance',
        data: { pageTitle: 'cRepoExtensioApp.reportInstrumentPerformance.home.title' },
        loadChildren: () => import('./report-instrument-performance/report-instrument-performance.routes'),
      },
      {
        path: 'staff-compiment',
        data: { pageTitle: 'cRepoExtensioApp.staffCompiment.home.title' },
        loadChildren: () => import('./staff-compiment/staff-compiment.routes'),
      },
      {
        path: 'stock-item',
        data: { pageTitle: 'cRepoExtensioApp.stockItem.home.title' },
        loadChildren: () => import('./stock-item/stock-item.routes'),
      },
      {
        path: 'laboratory-stock',
        data: { pageTitle: 'cRepoExtensioApp.laboratoryStock.home.title' },
        loadChildren: () => import('./laboratory-stock/laboratory-stock.routes'),
      },
      {
        path: 'report-stock-out',
        data: { pageTitle: 'cRepoExtensioApp.reportStockOut.home.title' },
        loadChildren: () => import('./report-stock-out/report-stock-out.routes'),
      },
      {
        path: 'report-activity-update',
        data: { pageTitle: 'cRepoExtensioApp.reportActivityUpdate.home.title' },
        loadChildren: () => import('./report-activity-update/report-activity-update.routes'),
      },
      {
        path: 'report-disease-outbreak',
        data: { pageTitle: 'cRepoExtensioApp.reportDiseaseOutbreak.home.title' },
        loadChildren: () => import('./report-disease-outbreak/report-disease-outbreak.routes'),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
