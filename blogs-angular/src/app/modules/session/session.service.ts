import { Injectable } from '@angular/core';
import { ApiService } from '../../core/service/api.service';
import {
  UserLoginRequestDto,
  UserRegisterRequestDto,
  UserResponseDto,
} from '../../core/dto/UserDto';
import { ApiEndpoints } from '../../core/enums/ApiEndpoints';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../core/response/ApiResponse';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  constructor(private apiService: ApiService) {}

  login(dto: UserLoginRequestDto): Observable<ApiResponse<UserResponseDto>> {
    return this.apiService.post(`${ApiEndpoints.LOGIN}`, dto);
  }

  register(
    dto: UserRegisterRequestDto
  ): Observable<ApiResponse<UserResponseDto>> {
    return this.apiService.post(`${ApiEndpoints.REGISTER}`, dto);
  }
}
