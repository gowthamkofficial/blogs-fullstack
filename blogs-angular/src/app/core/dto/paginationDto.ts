export class PaginationDTO {
  page: number = 0;
  size: number = 10;
  sortBy: string = 'publishedOn';
  sortDir: string = 'desc';
  totalPages: number;
  totalElements: number;
  last:boolean=false;
}
