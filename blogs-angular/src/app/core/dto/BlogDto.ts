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
likesCount: any;
  likedUsers: any;
}

export interface BlogRequestDto {
  userId: number;
  title: string;
  category: string;
  publishedOn: string;
  content: string;
}

export class CommentRequestDto {
  userId: number;
  blogId: number;
  commentText: string;
}

export class CommentResponseDto {
  id: number;
  commentText: string;
  user: UserResponseDto;
  createdOn: Date;
}
