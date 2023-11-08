import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ILaboratoryStock } from '../laboratory-stock.model';
import { LaboratoryStockService } from '../service/laboratory-stock.service';
import { LaboratoryStockFormService, LaboratoryStockFormGroup } from './laboratory-stock-form.service';

@Component({
  standalone: true,
  selector: 'jhi-laboratory-stock-update',
  templateUrl: './laboratory-stock-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class LaboratoryStockUpdateComponent implements OnInit {
  isSaving = false;
  laboratoryStock: ILaboratoryStock | null = null;

  editForm: LaboratoryStockFormGroup = this.laboratoryStockFormService.createLaboratoryStockFormGroup();

  constructor(
    protected laboratoryStockService: LaboratoryStockService,
    protected laboratoryStockFormService: LaboratoryStockFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ laboratoryStock }) => {
      this.laboratoryStock = laboratoryStock;
      if (laboratoryStock) {
        this.updateForm(laboratoryStock);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const laboratoryStock = this.laboratoryStockFormService.getLaboratoryStock(this.editForm);
    if (laboratoryStock.id !== null) {
      this.subscribeToSaveResponse(this.laboratoryStockService.update(laboratoryStock));
    } else {
      this.subscribeToSaveResponse(this.laboratoryStockService.create(laboratoryStock));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILaboratoryStock>>): void {
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

  protected updateForm(laboratoryStock: ILaboratoryStock): void {
    this.laboratoryStock = laboratoryStock;
    this.laboratoryStockFormService.resetForm(this.editForm, laboratoryStock);
  }
}
