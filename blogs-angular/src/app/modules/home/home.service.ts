import { Injectable } from '@angular/core';
import { ApiService } from '../../core/service/api.service';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private apiService : ApiService) { }



  getAllblogs(){
    
  }
}
