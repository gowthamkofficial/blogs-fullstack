import { Injectable } from '@angular/core';
import { ApiService } from '../../core/service/api.service';
import { PaginationDTO } from '../../core/dto/paginationDto';
import { Observable } from 'rxjs';
import { PagedResponse } from '../../core/response/PagedResponse';
import { BlogResponseDto } from '../../core/dto/BlogDto';
import { ApiEndpoints } from '../../core/enums/ApiEndpoints';
import { HttpParams } from '@angular/common/http';
import { checkNull } from '../../core/helper/checknull';
import { ApiResponse } from '../../core/response/ApiResponse';

@Injectable({
  providedIn: 'root',
})
export class MyBlogsService {
  constructor(private apiService: ApiService) {}

  getAllBlogsByUser(
    userId: number,
    paginationDto: PaginationDTO
  ): Observable<ApiResponse<PagedResponse<BlogResponseDto>>> {
    return this.apiService.get(
      `${ApiEndpoints.GET_BLOGS_BY_USERID}/${userId}`,
      paginationDto
    );
  }
}
