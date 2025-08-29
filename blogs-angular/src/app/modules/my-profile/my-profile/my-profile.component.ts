import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../core/service/api.service';
import { LoaderService } from '../../../core/service/loader.service';
import { ToasterService } from '../../../core/service/toaster.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserRequestDto, UserResponseDto } from '../../../core/dto/UserDto';
import { checkNull } from '../../../core/helper/checknull';
import { MyProfileService } from '../my-profile.service';
import { ApiResponse } from '../../../core/response/ApiResponse';
import { environment } from '../../../../environments/environment.development';

@Component({
  selector: 'app-my-profile',
  standalone: false,
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.css',
})
export class MyProfileComponent implements OnInit {
  profileForm: FormGroup;
  userData: any;
  editMode: boolean = false;
  profileImageFile: File | null = null;
  profileImagePreview: string | ArrayBuffer | null = null;
  constructor(
    private profileService: MyProfileService,
    private loader: LoaderService,
    private toaster: ToasterService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    this.getUserDetails();
  }

  getUserDetails() {
    const storeUser: string = sessionStorage.getItem('SESSION_USER');
    const userData: UserResponseDto = checkNull(storeUser)
      ? JSON.parse(storeUser)
      : null;

    if (checkNull(userData?.userId)) {
      this.loader.open();

      this.profileService.getUserById(userData.userId).subscribe({
        next: (res: ApiResponse<UserResponseDto>) => {
          this.userData = res.data ?? null;

          if (this.userData) {
            this.profileForm.patchValue({
              firstName: this.userData.firstName,
              lastName: this.userData.lastName,
              mobileNumber: this.userData.mobileNumber,
              emailAddress: this.userData.emailAddress,
              state: this.userData.state,
              district: this.userData.district,
              pincode: this.userData.pincode,
              address: this.userData.address,
              profileImageUrl: this.userData.profileImageUrl,
              userId: this.userData.userId,
            });
          }

          this.loader.close();
        },
        error: (err) => {
          this.loader.close();
          this.toaster.error(err?.error.message ?? 'Something went wrong!');
        },
      });
    }
  }

  enableEdit() {
    this.editMode = true;
  }

  cancelEdit() {
    this.editMode = false;

    if (this.userData) {
      this.profileForm.patchValue({
        firstName: this.userData.firstName,
        lastName: this.userData.lastName,
        mobileNumber: this.userData.mobileNumber,
        emailAddress: this.userData.emailAddress,
        state: this.userData.state,
        district: this.userData.district,
        pincode: this.userData.pincode,
        address: this.userData.address,
        profileImageUrl: this.userData.profileImageUrl,
        userId: this.userData.userId,
      });
    }
  }

  initializeForm() {
    this.profileForm = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      mobileNumber: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[0-9]{10}$/),
      ]),
      emailAddress: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      state: new FormControl('', [Validators.required]),
      district: new FormControl('', [Validators.required]),
      pincode: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[0-9]{6}$/),
      ]),
      address: new FormControl('', [Validators.required]),
      profileImageUrl: new FormControl(''),
      userId: new FormControl(''),
    });
  }

  get form() {
    return this.profileForm.controls;
  }

  updateProfile() {
    if (this.profileForm.invalid) {
      this.profileForm.markAllAsTouched();
      this.toaster.error('Please fix the errors in the form before updating.');
      return;
    }

    const userId = this.profileForm.get('userId')?.value;
    const dto: UserRequestDto = {
      firstName: this.profileForm.get('firstName')?.value,
      lastName: this.profileForm.get('lastName')?.value,
      mobileNumber: this.profileForm.get('mobileNumber')?.value,
      emailAddress: this.profileForm.get('emailAddress')?.value,
      state: this.profileForm.get('state')?.value,
      district: this.profileForm.get('district')?.value,
      address: this.profileForm.get('address')?.value,
      pincode: this.profileForm.get('pincode')?.value,
    };

    this.profileService.updateProfile(userId, dto).subscribe({
      next: (res) => {
        this.toaster.success('Profile updated successfully!');
        this.loader.close();
        this.editMode = false;
        this.userData = { ...this.userData, ...dto };
      },
      error: (err) => {
        this.loader.close();
        this.toaster.error(err?.error?.message ?? 'Failed to update profile.');
      },
    });
  }

  // Profile Image Update

 onFileSelected(event: Event) {
  const file = (event.target as HTMLInputElement).files?.[0];
  if (file) {
    // ✅ Validate file type
    if (!file.type.startsWith('image/')) {
      this.toaster.warning(`Only image files are allowed!`);

      return;
    }

    const maxSizeInMB = 1;
    if (file.size > maxSizeInMB * 1024 * 1024) {
      this.toaster.warning(`File size must be less than ${maxSizeInMB} MB`);
      return;
    }

    this.profileImageFile = file;

    const reader = new FileReader();
    reader.onload = () => {
      this.profileImagePreview = reader.result;
    };
    reader.readAsDataURL(file);
  }
}


  uploadProfileImage() {
    if (!this.profileImageFile) {
      this.toaster.error('Please select an image first!');
      return;
    }

    const userId = this.profileForm.get('userId')?.value;
    const formData = new FormData();
    formData.append('file', this.profileImageFile);

    this.loader.open();
    this.profileService.uploadProfile(userId, formData).subscribe({
      next: (res: any) => {
        this.toaster.success('Profile image updated successfully!');
        this.loader.close();

        if (res?.data?.fileUrl) {
          this.profileForm.patchValue({ profileImageUrl: res.data.fileUrl });
          this.profileImagePreview = '';
          this.profileImageFile = null;
        }
      },
      error: (err) => {
        this.loader.close();
        this.toaster.error(err?.error?.message ?? 'Failed to upload image');
      },
    });
  }

  getProfileImageUrl(): string {
    // Preview takes priority
    if (this.profileImagePreview) {
      return this.profileImagePreview as string;
    }

    const url = this.profileForm.get('profileImageUrl')?.value;
    console.log(url, 'let me check the url');

    if (url) {
      // Check if url already starts with http or https
      if (/^https?:\/\//i.test(url)) {
        return url;
      }
      // Otherwise, prepend environment.apiURL
      return `${environment.apiURL}${url}`;
    }

    // Default placeholder
    return 'assets/images/user.png';
  }
}
