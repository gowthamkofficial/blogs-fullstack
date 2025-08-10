import { Component } from '@angular/core';
import { DrawerService } from '../../../core/service/drawer.service';

@Component({
  selector: 'app-blogs-view',
  standalone: false,
  templateUrl: './blogs-view.component.html',
  styleUrl: './blogs-view.component.css',
})
export class BlogsViewComponent {
  constructor(private drawerService: DrawerService) {}

  openCommentsDrawer() {
    this.drawerService.openDrawer('comments');
  }
}
