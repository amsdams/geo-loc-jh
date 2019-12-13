import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GeoLocJhTestModule } from '../../../test.module';
import { GeocityDeleteDialogComponent } from 'app/entities/geocity/geocity-delete-dialog.component';
import { GeocityService } from 'app/entities/geocity/geocity.service';

describe('Component Tests', () => {
  describe('Geocity Management Delete Component', () => {
    let comp: GeocityDeleteDialogComponent;
    let fixture: ComponentFixture<GeocityDeleteDialogComponent>;
    let service: GeocityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeoLocJhTestModule],
        declarations: [GeocityDeleteDialogComponent]
      })
        .overrideTemplate(GeocityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeocityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeocityService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
