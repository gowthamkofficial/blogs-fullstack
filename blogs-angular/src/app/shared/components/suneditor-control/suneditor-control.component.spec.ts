import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuneditorControlComponent } from './suneditor-control.component';

describe('SuneditorControlComponent', () => {
  let component: SuneditorControlComponent;
  let fixture: ComponentFixture<SuneditorControlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuneditorControlComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuneditorControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
