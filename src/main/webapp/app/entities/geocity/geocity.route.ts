import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Geocity } from 'app/shared/model/geocity.model';
import { GeocityService } from './geocity.service';
import { GeocityComponent } from './geocity.component';
import { GeocityDetailComponent } from './geocity-detail.component';
import { GeocityUpdateComponent } from './geocity-update.component';
import { IGeocity } from 'app/shared/model/geocity.model';

@Injectable({ providedIn: 'root' })
export class GeocityResolve implements Resolve<IGeocity> {
  constructor(private service: GeocityService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeocity> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((geocity: HttpResponse<Geocity>) => geocity.body));
    }
    return of(new Geocity());
  }
}

export const geocityRoute: Routes = [
  {
    path: '',
    component: GeocityComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Geocities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GeocityDetailComponent,
    resolve: {
      geocity: GeocityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Geocities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GeocityUpdateComponent,
    resolve: {
      geocity: GeocityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Geocities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GeocityUpdateComponent,
    resolve: {
      geocity: GeocityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Geocities'
    },
    canActivate: [UserRouteAccessService]
  }
];
