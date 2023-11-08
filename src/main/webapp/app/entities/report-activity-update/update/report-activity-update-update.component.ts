import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IReportActivityUpdate } from '../report-activity-update.model';
import { ReportActivityUpdateService } from '../service/report-activity-update.service';
import { ReportActivityUpdateFormService, ReportActivityUpdateFormGroup } from './report-activity-update-form.service';

@Component({
  standalone: true,
  selector: 'jhi-report-activity-update-update',
  templateUrl: './report-activity-update-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReportActivityUpdateUpdateComponent implements OnInit {
  isSaving = false;
  reportActivityUpdate: IReportActivityUpdate | null = null;

  editForm: ReportActivityUpdateFormGroup = this.reportActivityUpdateFormService.createReportActivityUpdateFormGroup();

  constructor(
    protected reportActivityUpdateService: ReportActivityUpdateService,
    protected reportActivityUpdateFormService: ReportActivityUpdateFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportActivityUpdate }) => {
      this.reportActivityUpdate = reportActivityUpdate;
      if (reportActivityUpdate) {
        this.updateForm(reportActivityUpdate);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportActivityUpdate = this.reportActivityUpdateFormService.getReportActivityUpdate(this.editForm);
    if (reportActivityUpdate.id !== null) {
      this.subscribeToSaveResponse(this.reportActivityUpdateService.update(reportActivityUpdate));
    } else {
      this.subscribeToSaveResponse(this.reportActivityUpdateService.create(reportActivityUpdate));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportActivityUpdate>>): void {
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

  protected updateForm(reportActivityUpdate: IReportActivityUpdate): void {
    this.reportActivityUpdate = reportActivityUpdate;
    this.reportActivityUpdateFormService.resetForm(this.editForm, reportActivityUpdate);
  }
}
