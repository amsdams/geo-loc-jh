import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GeoLocJhSharedModule } from 'app/shared/shared.module';
import { GeocityComponent } from './geocity.component';
import { GeocityDetailComponent } from './geocity-detail.component';
import { GeocityUpdateComponent } from './geocity-update.component';
import { GeocityDeleteDialogComponent } from './geocity-delete-dialog.component';
import { geocityRoute } from './geocity.route';

@NgModule({
  imports: [GeoLocJhSharedModule, RouterModule.forChild(geocityRoute)],
  declarations: [GeocityComponent, GeocityDetailComponent, GeocityUpdateComponent, GeocityDeleteDialogComponent],
  entryComponents: [GeocityDeleteDialogComponent]
})
export class GeoLocJhGeocityModule {}
