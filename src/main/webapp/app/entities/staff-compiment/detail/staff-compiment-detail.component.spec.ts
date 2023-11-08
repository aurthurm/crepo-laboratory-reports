import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { StaffCompimentDetailComponent } from './staff-compiment-detail.component';

describe('StaffCompiment Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StaffCompimentDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: StaffCompimentDetailComponent,
              resolve: { staffCompiment: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(StaffCompimentDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load staffCompiment on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', StaffCompimentDetailComponent);

      // THEN
      expect(instance.staffCompiment).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
