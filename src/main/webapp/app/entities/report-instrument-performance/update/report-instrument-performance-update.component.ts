import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { InstrumentFunctionality } from 'app/entities/enumerations/instrument-functionality.model';
import { IReportInstrumentPerformance } from '../report-instrument-performance.model';
import { ReportInstrumentPerformanceService } from '../service/report-instrument-performance.service';
import { ReportInstrumentPerformanceFormService, ReportInstrumentPerformanceFormGroup } from './report-instrument-performance-form.service';

@Component({
  standalone: true,
  selector: 'jhi-report-instrument-performance-update',
  templateUrl: './report-instrument-performance-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReportInstrumentPerformanceUpdateComponent implements OnInit {
  isSaving = false;
  reportInstrumentPerformance: IReportInstrumentPerformance | null = null;
  instrumentFunctionalityValues = Object.keys(InstrumentFunctionality);

  editForm: ReportInstrumentPerformanceFormGroup = this.reportInstrumentPerformanceFormService.createReportInstrumentPerformanceFormGroup();

  constructor(
    protected reportInstrumentPerformanceService: ReportInstrumentPerformanceService,
    protected reportInstrumentPerformanceFormService: ReportInstrumentPerformanceFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportInstrumentPerformance }) => {
      this.reportInstrumentPerformance = reportInstrumentPerformance;
      if (reportInstrumentPerformance) {
        this.updateForm(reportInstrumentPerformance);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportInstrumentPerformance = this.reportInstrumentPerformanceFormService.getReportInstrumentPerformance(this.editForm);
    if (reportInstrumentPerformance.id !== null) {
      this.subscribeToSaveResponse(this.reportInstrumentPerformanceService.update(reportInstrumentPerformance));
    } else {
      this.subscribeToSaveResponse(this.reportInstrumentPerformanceService.create(reportInstrumentPerformance));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportInstrumentPerformance>>): void {
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

  protected updateForm(reportInstrumentPerformance: IReportInstrumentPerformance): void {
    this.reportInstrumentPerformance = reportInstrumentPerformance;
    this.reportInstrumentPerformanceFormService.resetForm(this.editForm, reportInstrumentPerformance);
  }
}
