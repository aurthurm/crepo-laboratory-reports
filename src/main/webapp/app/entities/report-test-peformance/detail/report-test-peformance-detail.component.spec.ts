import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ReportTestPeformanceDetailComponent } from './report-test-peformance-detail.component';

describe('ReportTestPeformance Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportTestPeformanceDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ReportTestPeformanceDetailComponent,
              resolve: { reportTestPeformance: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ReportTestPeformanceDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load reportTestPeformance on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ReportTestPeformanceDetailComponent);

      // THEN
      expect(instance.reportTestPeformance).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
