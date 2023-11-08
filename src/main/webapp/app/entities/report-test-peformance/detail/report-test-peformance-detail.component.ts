import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IReportTestPeformance } from '../report-test-peformance.model';

@Component({
  standalone: true,
  selector: 'jhi-report-test-peformance-detail',
  templateUrl: './report-test-peformance-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ReportTestPeformanceDetailComponent {
  @Input() reportTestPeformance: IReportTestPeformance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
