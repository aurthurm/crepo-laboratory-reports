import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IReportInstrumentPerformance } from '../report-instrument-performance.model';
import { ReportInstrumentPerformanceService } from '../service/report-instrument-performance.service';

@Component({
  standalone: true,
  templateUrl: './report-instrument-performance-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ReportInstrumentPerformanceDeleteDialogComponent {
  reportInstrumentPerformance?: IReportInstrumentPerformance;

  constructor(
    protected reportInstrumentPerformanceService: ReportInstrumentPerformanceService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reportInstrumentPerformanceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
