import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { LaboratoryTestDetailComponent } from './laboratory-test-detail.component';

describe('LaboratoryTest Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LaboratoryTestDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: LaboratoryTestDetailComponent,
              resolve: { laboratoryTest: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(LaboratoryTestDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load laboratoryTest on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', LaboratoryTestDetailComponent);

      // THEN
      expect(instance.laboratoryTest).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
