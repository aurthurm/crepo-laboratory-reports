import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IReportingPeriod } from '../reporting-period.model';
import { ReportingPeriodService } from '../service/reporting-period.service';
import { ReportingPeriodFormService, ReportingPeriodFormGroup } from './reporting-period-form.service';

@Component({
  standalone: true,
  selector: 'jhi-reporting-period-update',
  templateUrl: './reporting-period-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReportingPeriodUpdateComponent implements OnInit {
  isSaving = false;
  reportingPeriod: IReportingPeriod | null = null;

  editForm: ReportingPeriodFormGroup = this.reportingPeriodFormService.createReportingPeriodFormGroup();

  constructor(
    protected reportingPeriodService: ReportingPeriodService,
    protected reportingPeriodFormService: ReportingPeriodFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportingPeriod }) => {
      this.reportingPeriod = reportingPeriod;
      if (reportingPeriod) {
        this.updateForm(reportingPeriod);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportingPeriod = this.reportingPeriodFormService.getReportingPeriod(this.editForm);
    if (reportingPeriod.id !== null) {
      this.subscribeToSaveResponse(this.reportingPeriodService.update(reportingPeriod));
    } else {
      this.subscribeToSaveResponse(this.reportingPeriodService.create(reportingPeriod));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportingPeriod>>): void {
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

  protected updateForm(reportingPeriod: IReportingPeriod): void {
    this.reportingPeriod = reportingPeriod;
    this.reportingPeriodFormService.resetForm(this.editForm, reportingPeriod);
  }
}
