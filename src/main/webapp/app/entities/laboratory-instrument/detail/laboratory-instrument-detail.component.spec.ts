import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { LaboratoryInstrumentDetailComponent } from './laboratory-instrument-detail.component';

describe('LaboratoryInstrument Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LaboratoryInstrumentDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: LaboratoryInstrumentDetailComponent,
              resolve: { laboratoryInstrument: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(LaboratoryInstrumentDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load laboratoryInstrument on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', LaboratoryInstrumentDetailComponent);

      // THEN
      expect(instance.laboratoryInstrument).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
