import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainLayoutComponent } from './main-layout/main-layout.component';
import { AuthLayoutComponent } from './auth-layout/auth-layout.component';
import { BlogCardComponent } from './blog-card/blog-card.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BlogCardComponent,
  ],
  exports: [BlogCardComponent],
})
export class ComponentsModule {}
