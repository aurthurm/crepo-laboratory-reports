import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IReportActivityUpdate } from '../report-activity-update.model';
import { ReportActivityUpdateService } from '../service/report-activity-update.service';

@Component({
  standalone: true,
  templateUrl: './report-activity-update-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ReportActivityUpdateDeleteDialogComponent {
  reportActivityUpdate?: IReportActivityUpdate;

  constructor(
    protected reportActivityUpdateService: ReportActivityUpdateService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reportActivityUpdateService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
