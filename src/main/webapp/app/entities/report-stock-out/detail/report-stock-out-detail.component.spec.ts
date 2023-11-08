import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ReportStockOutDetailComponent } from './report-stock-out-detail.component';

describe('ReportStockOut Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportStockOutDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ReportStockOutDetailComponent,
              resolve: { reportStockOut: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ReportStockOutDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load reportStockOut on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ReportStockOutDetailComponent);

      // THEN
      expect(instance.reportStockOut).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
