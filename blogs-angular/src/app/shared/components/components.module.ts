import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainLayoutComponent } from './main-layout/main-layout.component';
import { AuthLayoutComponent } from './auth-layout/auth-layout.component';
import { BlogCardComponent } from './blog-card/blog-card.component';
import { LoaderComponent } from './loader/loader.component';

@NgModule({
  declarations: [
    LoaderComponent
  ],
  imports: [
    CommonModule,
    BlogCardComponent,
  ],
  exports: [BlogCardComponent,LoaderComponent],
})
export class ComponentsModule {}
