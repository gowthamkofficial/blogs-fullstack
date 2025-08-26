import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FieldType, showValidationMessage } from '../../../core/helper/errorMessage.helper';
import { LoaderService } from '../../../core/service/loader.service';
import { ToasterService } from '../../../core/service/toaster.service';
import { MyBlogsService } from '../my-blogs.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create',
  standalone: false,
  templateUrl: './create.component.html',
  styleUrl: './create.component.css',
})
export class CreateComponent implements OnInit{
 protected blogForm!: FormGroup;

  constructor(
    private toaster: ToasterService,
    private loader: LoaderService,
    private blogService: MyBlogsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initBlogForm();
  }

  initBlogForm() {
    this.blogForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      category: new FormControl('', [Validators.required]),
      publishedOn: new FormControl('', [Validators.required]),
      content: new FormControl('', [Validators.required, Validators.minLength(50)]),
    });
  }

  showError(controlName: string, label: string, type: FieldType) {
    const control = this.blogForm.get(controlName);
    return showValidationMessage(control, label, type);
  }

  get form() {
    return this.blogForm.controls;
  }

}
