import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGeocity } from 'app/shared/model/geocity.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { GeocityService } from './geocity.service';
import { GeocityDeleteDialogComponent } from './geocity-delete-dialog.component';

@Component({
  selector: 'jhi-geocity',
  templateUrl: './geocity.component.html'
})
export class GeocityComponent implements OnInit, OnDestroy {
  geocities: IGeocity[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;
  currentSearch: string;

  constructor(
    protected geocityService: GeocityService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute
  ) {
    this.geocities = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll() {
    if (this.currentSearch) {
      this.geocityService
        .search({
          query: this.currentSearch,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.sort()
        })
        .subscribe((res: HttpResponse<IGeocity[]>) => this.paginateGeocities(res.body, res.headers));
      return;
    }
    this.geocityService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IGeocity[]>) => this.paginateGeocities(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.geocities = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  clear() {
    this.geocities = [];
    this.links = {
      last: 0
    };
    this.page = 0;
    this.predicate = 'id';
    this.reverse = true;
    this.currentSearch = '';
    this.loadAll();
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.geocities = [];
    this.links = {
      last: 0
    };
    this.page = 0;
    this.predicate = '_score';
    this.reverse = false;
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInGeocities();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGeocity) {
    return item.id;
  }

  registerChangeInGeocities() {
    this.eventSubscriber = this.eventManager.subscribe('geocityListModification', () => this.reset());
  }

  delete(geocity: IGeocity) {
    const modalRef = this.modalService.open(GeocityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.geocity = geocity;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateGeocities(data: IGeocity[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.geocities.push(data[i]);
    }
  }
}
