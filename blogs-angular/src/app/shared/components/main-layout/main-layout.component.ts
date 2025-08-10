import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../../shared.module';
import { CommentsComponent } from '../../../modules/home/comments/comments.component';
import { DrawerService } from '../../../core/service/drawer.service';
import { MatDrawer } from '@angular/material/sidenav';
import { checkNull } from '../../../core/helper/checknull';

@Component({
  selector: 'app-main-layout',
  imports: [CommonModule, RouterModule, SharedModule, CommentsComponent],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css',
})
export class MainLayoutComponent {
  @ViewChild('rightDrawer') rightDrawer!: MatDrawer;
  constructor(public drawerService: DrawerService) {
    this.drawerService.drawer$.subscribe({
      next: (comp: String | null) => {
        if (checkNull(comp)) {
          this.rightDrawer.open();
        }
      },
    });
  }
}
