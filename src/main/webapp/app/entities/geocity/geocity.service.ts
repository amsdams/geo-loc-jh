import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeocity } from 'app/shared/model/geocity.model';

type EntityResponseType = HttpResponse<IGeocity>;
type EntityArrayResponseType = HttpResponse<IGeocity[]>;

@Injectable({ providedIn: 'root' })
export class GeocityService {
  public resourceUrl = SERVER_API_URL + 'api/geocities';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/geocities';

  constructor(protected http: HttpClient) {}

  create(geocity: IGeocity): Observable<EntityResponseType> {
    return this.http.post<IGeocity>(this.resourceUrl, geocity, { observe: 'response' });
  }

  update(geocity: IGeocity): Observable<EntityResponseType> {
    return this.http.put<IGeocity>(this.resourceUrl, geocity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeocity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeocity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeocity[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
