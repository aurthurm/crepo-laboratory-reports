import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CriticalResult } from 'app/entities/enumerations/critical-result.model';
import { IReportTestPeformance } from '../report-test-peformance.model';
import { ReportTestPeformanceService } from '../service/report-test-peformance.service';
import { ReportTestPeformanceFormService, ReportTestPeformanceFormGroup } from './report-test-peformance-form.service';

@Component({
  standalone: true,
  selector: 'jhi-report-test-peformance-update',
  templateUrl: './report-test-peformance-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReportTestPeformanceUpdateComponent implements OnInit {
  isSaving = false;
  reportTestPeformance: IReportTestPeformance | null = null;
  criticalResultValues = Object.keys(CriticalResult);

  editForm: ReportTestPeformanceFormGroup = this.reportTestPeformanceFormService.createReportTestPeformanceFormGroup();

  constructor(
    protected reportTestPeformanceService: ReportTestPeformanceService,
    protected reportTestPeformanceFormService: ReportTestPeformanceFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportTestPeformance }) => {
      this.reportTestPeformance = reportTestPeformance;
      if (reportTestPeformance) {
        this.updateForm(reportTestPeformance);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportTestPeformance = this.reportTestPeformanceFormService.getReportTestPeformance(this.editForm);
    if (reportTestPeformance.id !== null) {
      this.subscribeToSaveResponse(this.reportTestPeformanceService.update(reportTestPeformance));
    } else {
      this.subscribeToSaveResponse(this.reportTestPeformanceService.create(reportTestPeformance));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportTestPeformance>>): void {
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

  protected updateForm(reportTestPeformance: IReportTestPeformance): void {
    this.reportTestPeformance = reportTestPeformance;
    this.reportTestPeformanceFormService.resetForm(this.editForm, reportTestPeformance);
  }
}
