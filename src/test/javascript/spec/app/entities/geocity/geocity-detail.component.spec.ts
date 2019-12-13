import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GeoLocJhTestModule } from '../../../test.module';
import { GeocityDetailComponent } from 'app/entities/geocity/geocity-detail.component';
import { Geocity } from 'app/shared/model/geocity.model';

describe('Component Tests', () => {
  describe('Geocity Management Detail Component', () => {
    let comp: GeocityDetailComponent;
    let fixture: ComponentFixture<GeocityDetailComponent>;
    const route = ({ data: of({ geocity: new Geocity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeoLocJhTestModule],
        declarations: [GeocityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GeocityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeocityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geocity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
