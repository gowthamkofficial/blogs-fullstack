import { Component, OnInit } from '@angular/core';
import { ToasterService } from '../../../core/service/toaster.service';
import { LoaderService } from '../../../core/service/loader.service';
import { SessionService } from '../session.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { showValidationMessage } from '../../../core/helper/errorMessage.helper';
import { UserResponseDto } from '../../../core/dto/UserDto';
import { delay } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  standalone: false,
})
export class LoginComponent implements OnInit {
  protected loginForm: FormGroup;

  constructor(
    private toaster: ToasterService,
    private loader: LoaderService,
    private sessionService: SessionService,
    private router : Router
  ) {}

  ngOnInit(): void {
    this.initialLoginForm();
  }

  initialLoginForm() {
    this.loginForm = new FormGroup({
      email: new FormControl('gowtham.k@technogenesis.in', [Validators.required, Validators.email]),
      password: new FormControl('12345678', [Validators.required]),
    });
  }

  showToaster() {
    this.toaster.success('Toaster is working');
  }

  showError(controlName, label, type) {
    const control = this.loginForm.get(controlName);
    return showValidationMessage(control, label, type);
  }

  get form() {
    return this.loginForm.controls;
  }

  login() {
    this.loginForm.markAllAsTouched()
    if (this.loginForm.invalid) {
      this.toaster.error('Kindly fill all required fields');
      return;
    }

    this.loader.open();

    const { email, password } = this.loginForm.value;

    this.sessionService.login({ email, password }).pipe(delay(1500)).subscribe({
      next: (res) => {
        this.loader.close();
        this.toaster.success('Login successful');
        this.storeUserInSession(res?.data).then(()=>{
          this.router.navigate(["/blogs/list"])
        })
      },
      error: (err) => {
        this.loader.close();
        this.toaster.error(err?.error?.message || 'Login failed');
      },
    });
  }

  async storeUserInSession(dto:UserResponseDto){
   await sessionStorage.setItem("SESSION_USER",JSON.stringify(dto));
  await  sessionStorage.setItem("LOGGED_IN",'true');
  }


}
