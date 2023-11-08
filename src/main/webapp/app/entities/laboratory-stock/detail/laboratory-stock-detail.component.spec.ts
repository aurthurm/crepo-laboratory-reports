import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { LaboratoryStockDetailComponent } from './laboratory-stock-detail.component';

describe('LaboratoryStock Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LaboratoryStockDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: LaboratoryStockDetailComponent,
              resolve: { laboratoryStock: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(LaboratoryStockDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load laboratoryStock on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', LaboratoryStockDetailComponent);

      // THEN
      expect(instance.laboratoryStock).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
