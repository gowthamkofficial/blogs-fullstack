import { Injectable } from '@angular/core';
import { ApiService } from '../../core/service/api.service';
import { PaginationDTO } from '../../core/dto/paginationDto';
import { ApiEndpoints } from '../../core/enums/ApiEndpoints';
import { ApiResponse } from '../../core/response/ApiResponse';
import { Observable } from 'rxjs';
import { BlogResponseDto, CommentRequestDto } from '../../core/dto/BlogDto';
import { PagedResponse } from '../../core/response/PagedResponse';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  constructor(private apiService: ApiService) {}

  getAllblogs(
    pagination: PaginationDTO
  ): Observable<ApiResponse<PagedResponse<BlogResponseDto>>> {
    return this.apiService.get(`${ApiEndpoints.GET_ALL_BLOGS}`, pagination);
  }

  getBlogById(blogId: number): Observable<ApiResponse<BlogResponseDto>> {
    return this.apiService.get(`${ApiEndpoints.GET_BLOG_BY_BLOGID}/${blogId}`);
  }

  getAllCommentsByBlogId(blogId: number): Observable<ApiResponse<any>> {
    return this.apiService.get(`${ApiEndpoints.GET_ALL_COMMENTS}/${blogId}`);
  }

  getAllLikesByBlogId(blogId: number): Observable<ApiResponse<any>> {
    return this.apiService.get(
      `${ApiEndpoints.GET_ALL_LIKES_BY_BLOGID}/${blogId}`
    );
  }

  addLikeByBlogIdUserId(
    userId: number,
    blogId: number
  ): Observable<ApiResponse<any>> {
    return this.apiService.post(
      `${ApiEndpoints.ADD_LIKE_BY_USERID_AND_BLOGID}/${userId}/${blogId}`
    );
  }

  addComment(
    commentRequestDto: CommentRequestDto
  ): Observable<ApiResponse<any>> {
    return this.apiService.post(
      `${ApiEndpoints.ADD_COMMENT}`,
      commentRequestDto
    );
  }

  unlikeBlog(userId: number, blogId: number): Observable<ApiResponse<any>> {
    return this.apiService.delete(
      `${ApiEndpoints.UNLIKE_BY_USERID_BLOGID}/${userId}/${blogId}`
    );
  }
}
