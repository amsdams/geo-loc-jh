import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GeoLocJhTestModule } from '../../../test.module';
import { GeocityUpdateComponent } from 'app/entities/geocity/geocity-update.component';
import { GeocityService } from 'app/entities/geocity/geocity.service';
import { Geocity } from 'app/shared/model/geocity.model';

describe('Component Tests', () => {
  describe('Geocity Management Update Component', () => {
    let comp: GeocityUpdateComponent;
    let fixture: ComponentFixture<GeocityUpdateComponent>;
    let service: GeocityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeoLocJhTestModule],
        declarations: [GeocityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GeocityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeocityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeocityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Geocity(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Geocity();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
