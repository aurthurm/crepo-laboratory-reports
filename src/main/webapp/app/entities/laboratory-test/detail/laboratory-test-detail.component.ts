import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ILaboratoryTest } from '../laboratory-test.model';

@Component({
  standalone: true,
  selector: 'jhi-laboratory-test-detail',
  templateUrl: './laboratory-test-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class LaboratoryTestDetailComponent {
  @Input() laboratoryTest: ILaboratoryTest | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
