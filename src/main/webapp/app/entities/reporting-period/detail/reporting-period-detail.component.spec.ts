import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ReportingPeriodDetailComponent } from './reporting-period-detail.component';

describe('ReportingPeriod Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportingPeriodDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ReportingPeriodDetailComponent,
              resolve: { reportingPeriod: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ReportingPeriodDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load reportingPeriod on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ReportingPeriodDetailComponent);

      // THEN
      expect(instance.reportingPeriod).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
