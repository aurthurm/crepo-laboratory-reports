import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ILaboratoryInstrument } from '../laboratory-instrument.model';
import { LaboratoryInstrumentService } from '../service/laboratory-instrument.service';
import { LaboratoryInstrumentFormService, LaboratoryInstrumentFormGroup } from './laboratory-instrument-form.service';

@Component({
  standalone: true,
  selector: 'jhi-laboratory-instrument-update',
  templateUrl: './laboratory-instrument-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class LaboratoryInstrumentUpdateComponent implements OnInit {
  isSaving = false;
  laboratoryInstrument: ILaboratoryInstrument | null = null;

  editForm: LaboratoryInstrumentFormGroup = this.laboratoryInstrumentFormService.createLaboratoryInstrumentFormGroup();

  constructor(
    protected laboratoryInstrumentService: LaboratoryInstrumentService,
    protected laboratoryInstrumentFormService: LaboratoryInstrumentFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ laboratoryInstrument }) => {
      this.laboratoryInstrument = laboratoryInstrument;
      if (laboratoryInstrument) {
        this.updateForm(laboratoryInstrument);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const laboratoryInstrument = this.laboratoryInstrumentFormService.getLaboratoryInstrument(this.editForm);
    if (laboratoryInstrument.id !== null) {
      this.subscribeToSaveResponse(this.laboratoryInstrumentService.update(laboratoryInstrument));
    } else {
      this.subscribeToSaveResponse(this.laboratoryInstrumentService.create(laboratoryInstrument));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILaboratoryInstrument>>): void {
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

  protected updateForm(laboratoryInstrument: ILaboratoryInstrument): void {
    this.laboratoryInstrument = laboratoryInstrument;
    this.laboratoryInstrumentFormService.resetForm(this.editForm, laboratoryInstrument);
  }
}
