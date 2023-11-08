import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IReportingPeriod } from '../reporting-period.model';
import { ReportingPeriodService } from '../service/reporting-period.service';

@Component({
  standalone: true,
  templateUrl: './reporting-period-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ReportingPeriodDeleteDialogComponent {
  reportingPeriod?: IReportingPeriod;

  constructor(
    protected reportingPeriodService: ReportingPeriodService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reportingPeriodService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
