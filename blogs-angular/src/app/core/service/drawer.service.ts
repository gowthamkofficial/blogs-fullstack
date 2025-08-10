import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DrawerService {
  private currentDrawer: BehaviorSubject<string | null> = new BehaviorSubject<
    string | null
  >(null);

  public drawer$: Observable<string | null> = this.currentDrawer.asObservable();

  constructor() {}

  openDrawer(componentName: string) {
    this.currentDrawer.next(componentName);
  }

  closeDrawer() {
    this.currentDrawer.next(null);
  }
}
