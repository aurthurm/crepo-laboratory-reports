import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IStaffCompiment } from '../staff-compiment.model';
import { StaffCompimentService } from '../service/staff-compiment.service';
import { StaffCompimentFormService, StaffCompimentFormGroup } from './staff-compiment-form.service';

@Component({
  standalone: true,
  selector: 'jhi-staff-compiment-update',
  templateUrl: './staff-compiment-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class StaffCompimentUpdateComponent implements OnInit {
  isSaving = false;
  staffCompiment: IStaffCompiment | null = null;

  editForm: StaffCompimentFormGroup = this.staffCompimentFormService.createStaffCompimentFormGroup();

  constructor(
    protected staffCompimentService: StaffCompimentService,
    protected staffCompimentFormService: StaffCompimentFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ staffCompiment }) => {
      this.staffCompiment = staffCompiment;
      if (staffCompiment) {
        this.updateForm(staffCompiment);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const staffCompiment = this.staffCompimentFormService.getStaffCompiment(this.editForm);
    if (staffCompiment.id !== null) {
      this.subscribeToSaveResponse(this.staffCompimentService.update(staffCompiment));
    } else {
      this.subscribeToSaveResponse(this.staffCompimentService.create(staffCompiment));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStaffCompiment>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(staffCompiment: IStaffCompiment): void {
    this.staffCompiment = staffCompiment;
    this.staffCompimentFormService.resetForm(this.editForm, staffCompiment);
  }
}
