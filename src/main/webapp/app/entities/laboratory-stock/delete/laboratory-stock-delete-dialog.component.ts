import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ILaboratoryStock } from '../laboratory-stock.model';
import { LaboratoryStockService } from '../service/laboratory-stock.service';

@Component({
  standalone: true,
  templateUrl: './laboratory-stock-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class LaboratoryStockDeleteDialogComponent {
  laboratoryStock?: ILaboratoryStock;

  constructor(
    protected laboratoryStockService: LaboratoryStockService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.laboratoryStockService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
