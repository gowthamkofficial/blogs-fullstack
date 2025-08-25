import { Injectable } from '@angular/core';
import { ApiService } from '../../core/service/api.service';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../core/response/ApiResponse';
import { UserRequestDto, UserResponseDto } from '../../core/dto/UserDto';
import { ApiEndpoints } from '../../core/enums/ApiEndpoints';

@Injectable({
  providedIn: 'root',
})
export class MyProfileService {
  constructor(private apiService: ApiService) {}

  getUserById(userId: number): Observable<ApiResponse<UserResponseDto>> {
    return this.apiService.get(`${ApiEndpoints.GET_USER_BYUSERID}/${userId}`);
  }

  updateProfile(userId:number,dto:UserRequestDto){
    return this.apiService.put(`${ApiEndpoints.UPDATE_PROFILE_DETAILS_BY_USERID}/${userId}`,dto)
  }


  uploadProfile(userId,formData){
    return this.apiService.post(`${ApiEndpoints.PROFILE_UPLOAD_BY_USERID}/${userId}`, formData)
  }
}
