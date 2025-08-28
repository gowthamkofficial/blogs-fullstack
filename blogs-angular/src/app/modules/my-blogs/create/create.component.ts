import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FieldType, showValidationMessage } from '../../../core/helper/errorMessage.helper';
import { LoaderService } from '../../../core/service/loader.service';
import { ToasterService } from '../../../core/service/toaster.service';
import { MyBlogsService } from '../my-blogs.service';
import { Router } from '@angular/router';
import moment from 'moment';

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
      content: new FormControl('', [Validators.required, Validators.minLength(1000)]),
    });
  }

  showError(controlName: string, label: string, type: FieldType) {
    const control = this.blogForm.get(controlName);
    return showValidationMessage(control, label, type);
  }

  get form() {
    return this.blogForm.controls;
  }


  markAsTouched(){
  this.blogForm.get("content").markAsTouched();
  }



  submit() {
    if (this.blogForm.invalid) {
      // Mark all fields as touched to trigger validation messages
      this.blogForm.markAllAsTouched();
      return;
    }
  


    
    const storeUser: string | null = sessionStorage.getItem('SESSION_USER');
    const userData = storeUser ? JSON.parse(storeUser) : null;
  
    if (!userData) {
      this.toaster.error('User not logged in.');
      return;
    }
  
    const blogRequest = {
      userId: userData.userId,              
      title: this.blogForm.value.title, 
      category: this.blogForm.value.category,
      publishedOn: moment(this.blogForm.value.publishedOn).format("YYYY-MM-DD"),
      content: this.blogForm.value.content
    };
  console.log(blogRequest);
  
    this.loader.open();
    this.blogService.createBlog(blogRequest).subscribe({
      next: (res) => {
        this.loader.close();
        this.toaster.success('Blog created successfully!');
        this.router.navigate(['/my-blogs']);
      },
      error: (err) => {
        this.loader.close();
        console.log(err);
        
        this.toaster.error(err?.message || 'Something went wrong');
      }
    });
  }
  

}
