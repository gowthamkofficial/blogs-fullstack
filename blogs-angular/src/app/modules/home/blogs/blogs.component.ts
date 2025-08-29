import { Component, OnInit } from '@angular/core';
import { HomeService } from '../home.service';
import { LoaderService } from '../../../core/service/loader.service';
import { ToasterService } from '../../../core/service/toaster.service';
import { BlogResponseDto } from '../../../core/dto/BlogDto';
import { PaginationDTO } from '../../../core/dto/paginationDto';
import { ApiResponse } from '../../../core/response/ApiResponse';
import { PagedResponse } from '../../../core/response/PagedResponse';
import { delay } from 'rxjs';

@Component({
  selector: 'app-blogs',
  standalone: false,
  templateUrl: './blogs.component.html',
  styleUrls: ['./blogs.component.css'],
})
export class BlogsComponent implements OnInit {
  blogList: BlogResponseDto[] = [];
  paginationDto: PaginationDTO = new PaginationDTO();
  lastPage: boolean = false; // track if backend says it's the last page
  loading = false;

  constructor(
    private homeService: HomeService,
    private loader: LoaderService,
    private toaster: ToasterService
  ) {}

  ngOnInit(): void {
    this.paginationDto.page = 0;
    this.paginationDto.size = 10;
    this.getAllBlogs();
  }

  getAllBlogs(): void {
    if (this.loading || this.lastPage) return;

    this.loading = true;
    this.loader.open();

    this.homeService.getAllblogs(this.paginationDto).pipe(delay(500)).subscribe({
      next: (res: ApiResponse<PagedResponse<BlogResponseDto>>) => {
        if (res?.data?.content) {
          this.blogList = [...this.blogList, ...res.data.content];
          this.lastPage = res.data.last; // backend flag
          this.paginationDto.page++;
        }
        this.loader.close();
        this.loading = false;
      },
      error: () => {
        this.toaster.error('Failed to load blogs');
        this.loader.close();
        this.loading = false;
      },
    });
  }

  onScroll(event): void {
    this.getAllBlogs();
  }

}
