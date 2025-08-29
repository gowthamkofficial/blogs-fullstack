import { Component, OnInit } from '@angular/core';
import { DrawerService } from '../../../core/service/drawer.service';
import { ActivatedRoute } from '@angular/router';
import { HomeService } from '../home.service';
import { ToasterService } from '../../../core/service/toaster.service';
import { LoaderService } from '../../../core/service/loader.service';
import { UserResponseDto } from '../../../core/dto/UserDto';
import { BlogResponseDto } from '../../../core/dto/BlogDto';
import { delay } from 'rxjs';
import { ApiResponse } from '../../../core/response/ApiResponse';
import { environment } from '../../../../environments/environment.development';
import { checkNull } from '../../../core/helper/checknull';

@Component({
  selector: 'app-blogs-view',
  standalone: false,
  templateUrl: './blogs-view.component.html',
  styleUrl: './blogs-view.component.css',
})
export class BlogsViewComponent implements OnInit {
  blogId: number;
  userId: number;
  blog: BlogResponseDto | null = null;

  constructor(
    private drawerService: DrawerService,
    private activatedRoute: ActivatedRoute,
    private homeService: HomeService,
    private toaster: ToasterService,
    private loader: LoaderService
  ) {
    const storeUser: string | null = sessionStorage.getItem('SESSION_USER');
    const userData: UserResponseDto = storeUser ? JSON.parse(storeUser) : null;
    this.userId = userData?.userId ?? 0;

    this.activatedRoute.params.subscribe((res) => {
      console.log(res);

      this.blogId = res['id']; // ensure number
      this.drawerService.setBlogId(this.blogId);
    });
  }

  ngOnInit(): void {
    this.getBlogById();
  }

  openCommentsDrawer(): void {
    this.drawerService.openDrawer('comments');
  }

  getBlogById(): void {
    this.loader.open();
    this.homeService
      .getBlogById(this.blogId)
      .pipe(delay(500))
      .subscribe({
        next: (res: ApiResponse<BlogResponseDto>) => {
          this.blog = res.data;
          this.loader.close();
        },
        error: (err) => {
          console.error('Error fetching blog:', err);
          this.toaster.error('Something went wrong while fetching blog');
          this.loader.close();
        },
      });
  }

  getImageURI(uri: string): string {
    return checkNull(uri) ? environment.apiURL + uri : 'assets/images/user.png';
  }

  likeBlog() {
    this.loader.open();
    this.homeService
      .addLikeByBlogIdUserId(this.userId, this.blogId)
      .pipe(delay(500))
      .subscribe({
        next: (res: ApiResponse<any>) => {
          this.loader.close();
          // Add current user to likedUsers array
          this.blog.likedUsers.push(this.userId);
          this.blog.likesCount++;
          this.toaster.success('You liked this blog.');
        },
        error: () => {
          this.loader.close();
          this.toaster.error('Unable to like blog.');
        },
      });
  }

  unlikeBlog() {
    this.loader.open();
    this.homeService
      .unlikeBlog(this.userId, this.blogId)
      .pipe(delay(500))
      .subscribe({
        next: (res: ApiResponse<any>) => {
          this.loader.close();
          // Remove current user from likedUsers array
          this.blog.likedUsers = this.blog.likedUsers.filter(
            (id) => id !== this.userId
          );
          this.blog.likesCount--;
          this.toaster.info('You unliked this blog.');
        },
        error: () => {
          this.loader.close();
          this.toaster.error('Unable to unlike blog.');
        },
      });
  }
}
