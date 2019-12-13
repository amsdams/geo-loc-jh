import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeocity } from 'app/shared/model/geocity.model';
import { GeocityService } from './geocity.service';

@Component({
  templateUrl: './geocity-delete-dialog.component.html'
})
export class GeocityDeleteDialogComponent {
  geocity: IGeocity;

  constructor(protected geocityService: GeocityService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.geocityService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'geocityListModification',
        content: 'Deleted an geocity'
      });
      this.activeModal.dismiss(true);
    });
  }
}
