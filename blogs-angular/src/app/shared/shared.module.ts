import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MaterialModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  exports: [MaterialModule, CommonModule, ReactiveFormsModule, FormsModule],
})
export class SharedModule {}
