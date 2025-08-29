import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComponentsModule } from './components/components.module';
import { NgxSuneditorModule } from 'ngx-suneditor';
import { InfiniteScrollDirective } from 'ngx-infinite-scroll';
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MaterialModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    ComponentsModule,
    NgxSuneditorModule ,
    InfiniteScrollDirective  
  ],
  exports: [
    MaterialModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    ComponentsModule,
    NgxSuneditorModule ,
    InfiniteScrollDirective 
  ],
})
export class SharedModule {}
