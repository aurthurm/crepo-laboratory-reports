import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ILaboratoryTest } from '../laboratory-test.model';
import { LaboratoryTestService } from '../service/laboratory-test.service';

@Component({
  standalone: true,
  templateUrl: './laboratory-test-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class LaboratoryTestDeleteDialogComponent {
  laboratoryTest?: ILaboratoryTest;

  constructor(
    protected laboratoryTestService: LaboratoryTestService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.laboratoryTestService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
