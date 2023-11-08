import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ILaboratoryTest } from '../laboratory-test.model';
import { LaboratoryTestService } from '../service/laboratory-test.service';
import { LaboratoryTestFormService, LaboratoryTestFormGroup } from './laboratory-test-form.service';

@Component({
  standalone: true,
  selector: 'jhi-laboratory-test-update',
  templateUrl: './laboratory-test-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class LaboratoryTestUpdateComponent implements OnInit {
  isSaving = false;
  laboratoryTest: ILaboratoryTest | null = null;

  editForm: LaboratoryTestFormGroup = this.laboratoryTestFormService.createLaboratoryTestFormGroup();

  constructor(
    protected laboratoryTestService: LaboratoryTestService,
    protected laboratoryTestFormService: LaboratoryTestFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ laboratoryTest }) => {
      this.laboratoryTest = laboratoryTest;
      if (laboratoryTest) {
        this.updateForm(laboratoryTest);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const laboratoryTest = this.laboratoryTestFormService.getLaboratoryTest(this.editForm);
    if (laboratoryTest.id !== null) {
      this.subscribeToSaveResponse(this.laboratoryTestService.update(laboratoryTest));
    } else {
      this.subscribeToSaveResponse(this.laboratoryTestService.create(laboratoryTest));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILaboratoryTest>>): void {
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

  protected updateForm(laboratoryTest: ILaboratoryTest): void {
    this.laboratoryTest = laboratoryTest;
    this.laboratoryTestFormService.resetForm(this.editForm, laboratoryTest);
  }
}
