import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import moment from 'moment';
import { FieldType, showValidationMessage } from '../../../core/helper/errorMessage.helper';
import { LoaderService } from '../../../core/service/loader.service';
import { ToasterService } from '../../../core/service/toaster.service';
import { MyBlogsService } from '../my-blogs.service';

@Component({
  selector: 'app-update',
  standalone: false,
  templateUrl: './update.component.html',
  styleUrl: './update.component.css',
})
export class UpdateComponent {

  blogForm!: FormGroup;
  blogId!: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private loader: LoaderService,
    private toaster: ToasterService,
    private blogService: MyBlogsService
  ) {}

  ngOnInit(): void {
    this.blogId = +this.route.snapshot.paramMap.get('id')!;
    this.initBlogForm();
    this.loadBlogData();
  }

  initBlogForm() {
    this.blogForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      category: new FormControl('', [Validators.required]),
      publishedOn: new FormControl('', [Validators.required]),
      content: new FormControl('', [Validators.required, Validators.minLength(1000)]),
    });
  }

  markAsTouched(){
    this.blogForm.get("content").markAsTouched();
    }

  loadBlogData() {
    this.loader.open();
    this.blogService.getBlogById(this.blogId).subscribe({
      next: (res) => {
        this.loader.close();
        if (res?.data) {
          const blog = res.data;
          this.blogForm.patchValue({
            title: blog.title,
            category: blog.category,
            publishedOn: moment(blog.publishedOn).toDate(),
            content: blog.content
          });
        }
      },
      error: (err) => {
        this.loader.close();
        this.toaster.error(err?.message || 'Failed to load blog');
      }
    });
  }

  showError(controlName: string, label: string, type: FieldType) {
    const control = this.blogForm.get(controlName);
    return showValidationMessage(control, label, type);
  }

  get form(){
    return this.blogForm.controls
  }

  submit() {
    if (this.blogForm.invalid) {
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
      publishedOn: moment(this.blogForm.value.publishedOn).format('YYYY-MM-DD'),
      content: this.blogForm.value.content // ensure it's string from editor
    };

    this.loader.open();
    this.blogService.updateBlog(this.blogId, blogRequest).subscribe({
      next: (res) => {
        this.loader.close();
        this.toaster.success('Blog updated successfully!');
        this.router.navigate(['/my-blogs']);
      },
      error: (err) => {
        this.loader.close();
        this.toaster.error(err?.message || 'Something went wrong');
      }
    });
  }
}