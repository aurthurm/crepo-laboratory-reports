import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IReportInstrumentPerformance } from '../report-instrument-performance.model';

@Component({
  standalone: true,
  selector: 'jhi-report-instrument-performance-detail',
  templateUrl: './report-instrument-performance-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ReportInstrumentPerformanceDetailComponent {
  @Input() reportInstrumentPerformance: IReportInstrumentPerformance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
