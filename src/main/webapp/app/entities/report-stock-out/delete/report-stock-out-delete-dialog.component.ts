import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IReportStockOut } from '../report-stock-out.model';
import { ReportStockOutService } from '../service/report-stock-out.service';

@Component({
  standalone: true,
  templateUrl: './report-stock-out-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ReportStockOutDeleteDialogComponent {
  reportStockOut?: IReportStockOut;

  constructor(
    protected reportStockOutService: ReportStockOutService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reportStockOutService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
