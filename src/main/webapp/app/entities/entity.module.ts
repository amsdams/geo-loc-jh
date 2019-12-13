import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'geocity',
        loadChildren: () => import('./geocity/geocity.module').then(m => m.GeoLocJhGeocityModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class GeoLocJhEntityModule {}
