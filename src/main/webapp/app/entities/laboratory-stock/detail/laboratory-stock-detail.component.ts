import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ILaboratoryStock } from '../laboratory-stock.model';

@Component({
  standalone: true,
  selector: 'jhi-laboratory-stock-detail',
  templateUrl: './laboratory-stock-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class LaboratoryStockDetailComponent {
  @Input() laboratoryStock: ILaboratoryStock | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
