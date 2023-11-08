import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { StockAvailability } from 'app/entities/enumerations/stock-availability.model';
import { IReportStockOut } from '../report-stock-out.model';
import { ReportStockOutService } from '../service/report-stock-out.service';
import { ReportStockOutFormService, ReportStockOutFormGroup } from './report-stock-out-form.service';

@Component({
  standalone: true,
  selector: 'jhi-report-stock-out-update',
  templateUrl: './report-stock-out-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReportStockOutUpdateComponent implements OnInit {
  isSaving = false;
  reportStockOut: IReportStockOut | null = null;
  stockAvailabilityValues = Object.keys(StockAvailability);

  editForm: ReportStockOutFormGroup = this.reportStockOutFormService.createReportStockOutFormGroup();

  constructor(
    protected reportStockOutService: ReportStockOutService,
    protected reportStockOutFormService: ReportStockOutFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportStockOut }) => {
      this.reportStockOut = reportStockOut;
      if (reportStockOut) {
        this.updateForm(reportStockOut);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportStockOut = this.reportStockOutFormService.getReportStockOut(this.editForm);
    if (reportStockOut.id !== null) {
      this.subscribeToSaveResponse(this.reportStockOutService.update(reportStockOut));
    } else {
      this.subscribeToSaveResponse(this.reportStockOutService.create(reportStockOut));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportStockOut>>): void {
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

  protected updateForm(reportStockOut: IReportStockOut): void {
    this.reportStockOut = reportStockOut;
    this.reportStockOutFormService.resetForm(this.editForm, reportStockOut);
  }
}
