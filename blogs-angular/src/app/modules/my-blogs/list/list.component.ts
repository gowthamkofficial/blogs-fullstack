import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { PaginationDTO } from '../../../core/dto/paginationDto';
import { UserResponseDto } from '../../../core/dto/UserDto';
import { checkNull } from '../../../core/helper/checknull';
import { MyBlogsService } from '../my-blogs.service';
import { LoaderService } from '../../../core/service/loader.service';
import { ToasterService } from '../../../core/service/toaster.service';
import { BlogResponseDto } from '../../../core/dto/BlogDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  standalone: false,
  templateUrl: './list.component.html',
  styleUrl: './list.component.css',
})
export class ListComponent implements OnInit {
  displayedColumns: string[] = [
    'blogId',
    'title',
    'category',
    'createdOn',
    'publishedOn',
    'actions',
  ];

  paginationDto = new PaginationDTO();
  dataSource: MatTableDataSource<BlogResponseDto> =
    new MatTableDataSource<BlogResponseDto>();
  userData: UserResponseDto;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private blogService: MyBlogsService,
    private loader: LoaderService,
    private toaster: ToasterService,
    private router: Router
  ) {
    const storedUser: string = sessionStorage.getItem('SESSION_USER');
    this.userData = checkNull(storedUser) ? JSON.parse(storedUser) : null;
  }

  ngOnInit(): void {
    this.getAllBlogs();
  }

  getAllBlogs() {
    this.loader.open();
    this.blogService
      .getAllBlogsByUser(this.userData.userId, this.paginationDto)
      .subscribe({
        next: (res) => {
          this.dataSource = new MatTableDataSource(res.data?.content ?? []);
          // this.dataSource.paginator = this.paginator;
          // this.dataSource.sort = this.sort;
          this.paginationDto.totalElements = res.data.totalElements ?? 0;
          this.paginationDto.totalPages = res.data.totalPages ?? 0;
          this.loader.close();
          console.log(res, 'this is the response');
        },
        error: () => {
          this.dataSource = new MatTableDataSource([]);
          this.paginationDto.totalElements = 0;
          this.paginationDto.totalPages = 0;
          this.loader.close();
        },
      });
  }

  onPageChange(event: PageEvent) {
    this.paginationDto.page = event.pageIndex;
    this.paginationDto.size = event.pageSize;
    this.getAllBlogs();
  }


   onSortChange(sortState: Sort) {
    this.paginationDto.sortBy = sortState.active;
    this.paginationDto.sortDir = sortState.direction || 'asc'; 
    this.getAllBlogs();
  }


  onView(blog: BlogResponseDto) {
    this.router.navigate([`/my-blogs/view/${blog.blogId}`], { state: blog });
  }

  onEdit(blog: BlogResponseDto) {
    console.log(blog);

    this.router.navigate([`/my-blogs/update/${blog.blogId}`], { state: blog });
  }
}
