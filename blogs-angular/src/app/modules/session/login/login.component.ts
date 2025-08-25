import { Component } from '@angular/core';
import { ToasterService } from '../../../core/service/toaster.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  standalone: false,
})
export class LoginComponent {

  constructor(
    private toaster : ToasterService
  ){

    
  }


  showToaster(){
    this.toaster.success("Toaster is working")
  }
}
