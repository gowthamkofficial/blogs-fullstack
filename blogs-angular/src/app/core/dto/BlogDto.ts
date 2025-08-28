import { UserResponseDto } from './UserDto';

export class BlogResponseDto {
  blogId!: number;
  title!: string;
  category!: string;
  publishedOn!: string;
  content!: string;
  createdOn!: string;
  updatedOn!: string;
  user: UserResponseDto;
}


export interface BlogRequestDto {
  userId: number;        
  title: string;         
  category: string;      
  publishedOn: string;   
  content: string;
}
