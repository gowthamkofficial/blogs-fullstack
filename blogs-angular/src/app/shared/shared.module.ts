import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComponentsModule } from './components/components.module';
import { NgxSuneditorModule } from 'ngx-suneditor';
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MaterialModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    ComponentsModule,
    NgxSuneditorModule 
  ],
  exports: [
    MaterialModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    ComponentsModule,
    NgxSuneditorModule 
  ],
})
export class SharedModule {}
