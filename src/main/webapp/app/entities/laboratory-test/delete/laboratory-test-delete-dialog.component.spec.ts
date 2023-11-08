jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { LaboratoryTestService } from '../service/laboratory-test.service';

import { LaboratoryTestDeleteDialogComponent } from './laboratory-test-delete-dialog.component';

describe('LaboratoryTest Management Delete Component', () => {
  let comp: LaboratoryTestDeleteDialogComponent;
  let fixture: ComponentFixture<LaboratoryTestDeleteDialogComponent>;
  let service: LaboratoryTestService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, LaboratoryTestDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(LaboratoryTestDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(LaboratoryTestDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(LaboratoryTestService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
