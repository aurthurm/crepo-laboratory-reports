import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ReportDiseaseOutbreakService } from '../service/report-disease-outbreak.service';

import { ReportDiseaseOutbreakComponent } from './report-disease-outbreak.component';
import SpyInstance = jest.SpyInstance;

describe('ReportDiseaseOutbreak Management Component', () => {
  let comp: ReportDiseaseOutbreakComponent;
  let fixture: ComponentFixture<ReportDiseaseOutbreakComponent>;
  let service: ReportDiseaseOutbreakService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'report-disease-outbreak', component: ReportDiseaseOutbreakComponent }]),
        HttpClientTestingModule,
        ReportDiseaseOutbreakComponent,
      ],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(ReportDiseaseOutbreakComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReportDiseaseOutbreakComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ReportDiseaseOutbreakService);
    routerNavigateSpy = jest.spyOn(comp.router, 'navigate');

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        }),
      ),
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.reportDiseaseOutbreaks?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to reportDiseaseOutbreakService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getReportDiseaseOutbreakIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getReportDiseaseOutbreakIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });

  it('should load a page', () => {
    // WHEN
    comp.navigateToPage(1);

    // THEN
    expect(routerNavigateSpy).toHaveBeenCalled();
  });

  it('should calculate the sort attribute for an id', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({ sort: ['id,desc'] }));
  });

  it('should calculate the sort attribute for a non-id attribute', () => {
    // GIVEN
    comp.predicate = 'name';

    // WHEN
    comp.navigateToWithComponentValues();

    // THEN
    expect(routerNavigateSpy).toHaveBeenLastCalledWith(
      expect.anything(),
      expect.objectContaining({
        queryParams: expect.objectContaining({
          sort: ['name,asc'],
        }),
      }),
    );
  });
});
