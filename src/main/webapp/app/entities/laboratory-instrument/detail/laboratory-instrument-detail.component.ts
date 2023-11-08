import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ILaboratoryInstrument } from '../laboratory-instrument.model';

@Component({
  standalone: true,
  selector: 'jhi-laboratory-instrument-detail',
  templateUrl: './laboratory-instrument-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class LaboratoryInstrumentDetailComponent {
  @Input() laboratoryInstrument: ILaboratoryInstrument | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
