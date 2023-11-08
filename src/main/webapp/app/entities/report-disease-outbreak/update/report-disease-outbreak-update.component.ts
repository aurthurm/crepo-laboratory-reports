import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { Outbreak } from 'app/entities/enumerations/outbreak.model';
import { IReportDiseaseOutbreak } from '../report-disease-outbreak.model';
import { ReportDiseaseOutbreakService } from '../service/report-disease-outbreak.service';
import { ReportDiseaseOutbreakFormService, ReportDiseaseOutbreakFormGroup } from './report-disease-outbreak-form.service';

@Component({
  standalone: true,
  selector: 'jhi-report-disease-outbreak-update',
  templateUrl: './report-disease-outbreak-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReportDiseaseOutbreakUpdateComponent implements OnInit {
  isSaving = false;
  reportDiseaseOutbreak: IReportDiseaseOutbreak | null = null;
  outbreakValues = Object.keys(Outbreak);

  editForm: ReportDiseaseOutbreakFormGroup = this.reportDiseaseOutbreakFormService.createReportDiseaseOutbreakFormGroup();

  constructor(
    protected reportDiseaseOutbreakService: ReportDiseaseOutbreakService,
    protected reportDiseaseOutbreakFormService: ReportDiseaseOutbreakFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportDiseaseOutbreak }) => {
      this.reportDiseaseOutbreak = reportDiseaseOutbreak;
      if (reportDiseaseOutbreak) {
        this.updateForm(reportDiseaseOutbreak);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportDiseaseOutbreak = this.reportDiseaseOutbreakFormService.getReportDiseaseOutbreak(this.editForm);
    if (reportDiseaseOutbreak.id !== null) {
      this.subscribeToSaveResponse(this.reportDiseaseOutbreakService.update(reportDiseaseOutbreak));
    } else {
      this.subscribeToSaveResponse(this.reportDiseaseOutbreakService.create(reportDiseaseOutbreak));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportDiseaseOutbreak>>): void {
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

  protected updateForm(reportDiseaseOutbreak: IReportDiseaseOutbreak): void {
    this.reportDiseaseOutbreak = reportDiseaseOutbreak;
    this.reportDiseaseOutbreakFormService.resetForm(this.editForm, reportDiseaseOutbreak);
  }
}
