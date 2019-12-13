import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeocity } from 'app/shared/model/geocity.model';

@Component({
  selector: 'jhi-geocity-detail',
  templateUrl: './geocity-detail.component.html'
})
export class GeocityDetailComponent implements OnInit {
  geocity: IGeocity;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ geocity }) => {
      this.geocity = geocity;
    });
  }

  previousState() {
    window.history.back();
  }
}
