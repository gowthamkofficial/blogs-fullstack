import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { CommonModule } from '@angular/common';
import { HomeService } from '../home.service';
import { LoaderService } from '../../../core/service/loader.service';
import { ToasterService } from '../../../core/service/toaster.service';
import { ActivatedRoute } from '@angular/router';
import { UserResponseDto } from '../../../core/dto/UserDto';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ApiResponse } from '../../../core/response/ApiResponse';
import { DrawerService } from '../../../core/service/drawer.service';
import { checkNull } from '../../../core/helper/checknull';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrl: './comments.component.css',
  imports: [CommonModule, SharedModule],
})
export class CommentsComponent implements OnInit, OnChanges {
  blogId: number;
  userId: number;
  commentForm: FormGroup;
  comments: any[] = [];

  constructor(
    private homeService: HomeService,
    private loader: LoaderService,
    private toaster: ToasterService,
    private activatedRoute: ActivatedRoute,
    private drawerService: DrawerService
  ) {
    const storeUser: string | null = sessionStorage.getItem('SESSION_USER');
    const userData: UserResponseDto = storeUser ? JSON.parse(storeUser) : null;
    this.userId = userData?.userId ?? 0;
    this.drawerService.currentBlog$.subscribe((res) => {
      this.blogId = res;
      this.initCommentForm();
      this.getAllComments();
    });
  }

  ngOnChanges(changes: SimpleChanges): void {}

  ngOnInit(): void {}

  initCommentForm() {
    this.commentForm = new FormGroup({
      userId: new FormControl(this.userId, [Validators.required]),
      blogId: new FormControl(this.blogId, [Validators.required]),
      commentText: new FormControl('', [
        Validators.required,
        Validators.minLength(1),
      ]),
    });
  }

  getAllComments() {
    this.loader.open();
    this.homeService.getAllCommentsByBlogId(this.blogId).subscribe({
      next: (res: ApiResponse<any[]>) => {
        this.loader.close();
        this.comments = res.data || [];
      },
      error: () => {
        this.loader.close();
        this.toaster.error('Error while fetching comments.');
      },
    });
  }

  createComment() {
    if (this.commentForm.invalid) {
      this.toaster.warning('Please enter a comment.');
      return;
    }

    this.loader.open();
    this.homeService.addComment(this.commentForm.value).subscribe({
      next: (res: ApiResponse<any>) => {
        this.loader.close();

        this.toaster.success('Comment added.');
        this.commentForm.reset({
          userId: this.userId,
          blogId: this.blogId,
          commentText: '',
        });
        this.commentForm.get("commentText").markAsUntouched()
        this.commentForm.get("commentText").updateValueAndValidity();
        this.getAllComments();
      },
      error: () => {
        this.loader.close();
        this.toaster.error('Error while adding comment.');
      },
    });
  }

  closeDrawer() {
    this.drawerService.closeDrawer(); 
  }

  getImageURI(uri: string) {
    return checkNull(uri) ? environment.apiURL + uri : 'assets/images/user.png';
  }
  
}
