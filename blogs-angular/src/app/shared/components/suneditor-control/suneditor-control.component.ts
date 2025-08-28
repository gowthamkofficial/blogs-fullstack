import { Component, forwardRef, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, NG_VALUE_ACCESSOR, ControlValueAccessor } from '@angular/forms';
import { NgxSuneditorModule } from 'ngx-suneditor';
import SunEditor from 'suneditor/src/lib/core';

@Component({
  selector: 'app-suneditor-control',
  standalone: true,
  imports: [CommonModule, NgxSuneditorModule, FormsModule, ReactiveFormsModule],
  template: `
    <ngx-suneditor
      [(ngModel)]="value"
      [options]="options"
      (ngModelChange)="onEditorChange($event)">
    </ngx-suneditor>
  `,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => SuneditorControlComponent),
      multi: true
    }
  ]
})
export class SuneditorControlComponent implements ControlValueAccessor, OnInit {
  @Input() options: any;

  value: string = '';

  onChange: any = () => {};
  onTouched: any = () => {};

  ngOnInit(): void {
    if (!this.options) {
      this.options = {
        width: '100%',
        mode: 'classic',
        height: '250px',
        buttonList: [['undo', 'redo', 'bold', 'italic', 'link', 'image']],
        charCounter: true,
      };
    }
  }

  onEditorChange(content: string) {
    this.value = content;
    this.onChange(content); // propagate change to form control
  }

  // ControlValueAccessor
  writeValue(value: any): void {
    this.value = value || '';
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState?(isDisabled: boolean): void {
    // optional: handle editor disabled state
  }
}
