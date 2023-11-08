import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ILaboratoryInstrument } from '../laboratory-instrument.model';
import { LaboratoryInstrumentService } from '../service/laboratory-instrument.service';

@Component({
  standalone: true,
  templateUrl: './laboratory-instrument-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class LaboratoryInstrumentDeleteDialogComponent {
  laboratoryInstrument?: ILaboratoryInstrument;

  constructor(
    protected laboratoryInstrumentService: LaboratoryInstrumentService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.laboratoryInstrumentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
