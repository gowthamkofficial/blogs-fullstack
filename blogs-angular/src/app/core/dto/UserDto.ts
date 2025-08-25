export class UserRequestDto {
  firstName: string;
  lastName: string;
  mobileNumber: string;
  emailAddress: string;
  state: string;
  district: string;
  address: string;
  pincode: string;
}

export class UserRegisterRequestDto extends UserRequestDto {
  password: string;
}

export class UserResponseDto {
  firstName: string;
  lastName: string;
  mobileNumber: string;
  emailAddress: string;
  state: string;
  district: string;
  address: string;
  pincode: string;
  profileImageUrl: string;
}

export class UserLoginRequestDto {
  email: string;
  password: string;
}

