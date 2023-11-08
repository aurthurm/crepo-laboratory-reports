import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { StockItemDetailComponent } from './stock-item-detail.component';

describe('StockItem Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StockItemDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: StockItemDetailComponent,
              resolve: { stockItem: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(StockItemDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load stockItem on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', StockItemDetailComponent);

      // THEN
      expect(instance.stockItem).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
