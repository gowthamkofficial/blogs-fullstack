import { Component } from '@angular/core';
import { DrawerService } from '../../../core/service/drawer.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrl: './view.component.css',
  standalone:false
})
export class ViewComponent {
  constructor(private drawerService: DrawerService) {}

  openCommentsDrawer() {
    this.drawerService.openDrawer('comments');
  }
}
