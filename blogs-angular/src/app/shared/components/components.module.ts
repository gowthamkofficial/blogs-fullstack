import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainLayoutComponent } from './main-layout/main-layout.component';
import { AuthLayoutComponent } from './auth-layout/auth-layout.component';

@NgModule({
  declarations: [],
  imports: [CommonModule, MainLayoutComponent, AuthLayoutComponent],
})
export class ComponentsModule {}
