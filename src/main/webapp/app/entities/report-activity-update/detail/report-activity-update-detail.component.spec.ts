import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ReportActivityUpdateDetailComponent } from './report-activity-update-detail.component';

describe('ReportActivityUpdate Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportActivityUpdateDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ReportActivityUpdateDetailComponent,
              resolve: { reportActivityUpdate: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ReportActivityUpdateDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load reportActivityUpdate on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ReportActivityUpdateDetailComponent);

      // THEN
      expect(instance.reportActivityUpdate).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
