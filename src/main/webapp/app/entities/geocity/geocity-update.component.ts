import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGeocity, Geocity } from 'app/shared/model/geocity.model';
import { GeocityService } from './geocity.service';

@Component({
  selector: 'jhi-geocity-update',
  templateUrl: './geocity-update.component.html'
})
export class GeocityUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    asciiname: [],
    alternatenames: [],
    location: [],
    lat: [],
    lon: [],
    featureclass: [],
    featuretype: [],
    countrycode: [],
    cc2: [],
    admin1code: [],
    admin2code: [],
    admin3code: [],
    admin4code: [],
    population: [],
    elevation: [],
    dem: []
  });

  constructor(protected geocityService: GeocityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ geocity }) => {
      this.updateForm(geocity);
    });
  }

  updateForm(geocity: IGeocity) {
    this.editForm.patchValue({
      id: geocity.id,
      name: geocity.name,
      asciiname: geocity.asciiname,
      alternatenames: geocity.alternatenames,
      location: geocity.location,
      lat: geocity.lat,
      lon: geocity.lon,
      featureclass: geocity.featureclass,
      featuretype: geocity.featuretype,
      countrycode: geocity.countrycode,
      cc2: geocity.cc2,
      admin1code: geocity.admin1code,
      admin2code: geocity.admin2code,
      admin3code: geocity.admin3code,
      admin4code: geocity.admin4code,
      population: geocity.population,
      elevation: geocity.elevation,
      dem: geocity.dem
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const geocity = this.createFromForm();
    if (geocity.id !== undefined) {
      this.subscribeToSaveResponse(this.geocityService.update(geocity));
    } else {
      this.subscribeToSaveResponse(this.geocityService.create(geocity));
    }
  }

  private createFromForm(): IGeocity {
    return {
      ...new Geocity(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      asciiname: this.editForm.get(['asciiname']).value,
      alternatenames: this.editForm.get(['alternatenames']).value,
      location: this.editForm.get(['location']).value,
      lat: this.editForm.get(['lat']).value,
      lon: this.editForm.get(['lon']).value,
      featureclass: this.editForm.get(['featureclass']).value,
      featuretype: this.editForm.get(['featuretype']).value,
      countrycode: this.editForm.get(['countrycode']).value,
      cc2: this.editForm.get(['cc2']).value,
      admin1code: this.editForm.get(['admin1code']).value,
      admin2code: this.editForm.get(['admin2code']).value,
      admin3code: this.editForm.get(['admin3code']).value,
      admin4code: this.editForm.get(['admin4code']).value,
      population: this.editForm.get(['population']).value,
      elevation: this.editForm.get(['elevation']).value,
      dem: this.editForm.get(['dem']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeocity>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
