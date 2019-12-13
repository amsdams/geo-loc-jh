import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { GeoLocJhSharedModule } from 'app/shared/shared.module';
import { GeoLocJhCoreModule } from 'app/core/core.module';
import { GeoLocJhAppRoutingModule } from './app-routing.module';
import { GeoLocJhHomeModule } from './home/home.module';
import { GeoLocJhEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    GeoLocJhSharedModule,
    GeoLocJhCoreModule,
    GeoLocJhHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    GeoLocJhEntityModule,
    GeoLocJhAppRoutingModule
  ],
  declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [JhiMainComponent]
})
export class GeoLocJhAppModule {}
