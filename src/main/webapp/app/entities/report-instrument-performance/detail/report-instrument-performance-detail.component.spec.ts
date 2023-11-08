import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ReportInstrumentPerformanceDetailComponent } from './report-instrument-performance-detail.component';

describe('ReportInstrumentPerformance Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportInstrumentPerformanceDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ReportInstrumentPerformanceDetailComponent,
              resolve: { reportInstrumentPerformance: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ReportInstrumentPerformanceDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load reportInstrumentPerformance on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ReportInstrumentPerformanceDetailComponent);

      // THEN
      expect(instance.reportInstrumentPerformance).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
