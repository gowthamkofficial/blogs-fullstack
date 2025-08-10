import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { BlogsComponent } from './blogs/blogs.component';
import { BlogsViewComponent } from './blogs-view/blogs-view.component';
import { CommentsComponent } from './comments/comments.component';

@NgModule({
  declarations: [BlogsComponent, BlogsViewComponent],
  imports: [CommonModule, HomeRoutingModule, SharedModule],
})
export class HomeModule {}
