import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IReportActivityUpdate } from '../report-activity-update.model';

@Component({
  standalone: true,
  selector: 'jhi-report-activity-update-detail',
  templateUrl: './report-activity-update-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ReportActivityUpdateDetailComponent {
  @Input() reportActivityUpdate: IReportActivityUpdate | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
