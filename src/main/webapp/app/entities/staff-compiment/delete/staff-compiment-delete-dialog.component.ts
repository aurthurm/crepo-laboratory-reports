import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IStaffCompiment } from '../staff-compiment.model';
import { StaffCompimentService } from '../service/staff-compiment.service';

@Component({
  standalone: true,
  templateUrl: './staff-compiment-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class StaffCompimentDeleteDialogComponent {
  staffCompiment?: IStaffCompiment;

  constructor(
    protected staffCompimentService: StaffCompimentService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.staffCompimentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
