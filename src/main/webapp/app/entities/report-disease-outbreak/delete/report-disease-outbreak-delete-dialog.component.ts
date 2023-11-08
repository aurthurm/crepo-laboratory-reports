import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IReportDiseaseOutbreak } from '../report-disease-outbreak.model';
import { ReportDiseaseOutbreakService } from '../service/report-disease-outbreak.service';

@Component({
  standalone: true,
  templateUrl: './report-disease-outbreak-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ReportDiseaseOutbreakDeleteDialogComponent {
  reportDiseaseOutbreak?: IReportDiseaseOutbreak;

  constructor(
    protected reportDiseaseOutbreakService: ReportDiseaseOutbreakService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reportDiseaseOutbreakService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
