import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IReportDiseaseOutbreak } from '../report-disease-outbreak.model';

@Component({
  standalone: true,
  selector: 'jhi-report-disease-outbreak-detail',
  templateUrl: './report-disease-outbreak-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ReportDiseaseOutbreakDetailComponent {
  @Input() reportDiseaseOutbreak: IReportDiseaseOutbreak | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
