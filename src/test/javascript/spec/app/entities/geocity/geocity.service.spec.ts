import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { GeocityService } from 'app/entities/geocity/geocity.service';
import { IGeocity, Geocity } from 'app/shared/model/geocity.model';

describe('Service Tests', () => {
  describe('Geocity Service', () => {
    let injector: TestBed;
    let service: GeocityService;
    let httpMock: HttpTestingController;
    let elemDefault: IGeocity;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(GeocityService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Geocity(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Geocity', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Geocity(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Geocity', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            asciiname: 'BBBBBB',
            alternatenames: 'BBBBBB',
            location: 1,
            lat: 1,
            lon: 1,
            featureclass: 'BBBBBB',
            featuretype: 'BBBBBB',
            countrycode: 'BBBBBB',
            cc2: 'BBBBBB',
            admin1code: 'BBBBBB',
            admin2code: 'BBBBBB',
            admin3code: 'BBBBBB',
            admin4code: 'BBBBBB',
            population: 1,
            elevation: 1,
            dem: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Geocity', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            asciiname: 'BBBBBB',
            alternatenames: 'BBBBBB',
            location: 1,
            lat: 1,
            lon: 1,
            featureclass: 'BBBBBB',
            featuretype: 'BBBBBB',
            countrycode: 'BBBBBB',
            cc2: 'BBBBBB',
            admin1code: 'BBBBBB',
            admin2code: 'BBBBBB',
            admin3code: 'BBBBBB',
            admin4code: 'BBBBBB',
            population: 1,
            elevation: 1,
            dem: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Geocity', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
