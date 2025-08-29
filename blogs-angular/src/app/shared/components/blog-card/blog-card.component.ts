import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { RouterModule } from '@angular/router';
import { BlogResponseDto } from '../../../core/dto/BlogDto';
import { checkNull } from '../../../core/helper/checknull';
import { environment } from '../../../../environments/environment.development';

@Component({
  selector: 'blog-card',
  imports: [CommonModule, MaterialModule, RouterModule],
  templateUrl: './blog-card.component.html',
  styleUrl: './blog-card.component.css',
})
export class BlogCardComponent implements OnChanges {
  @Input() blog: BlogResponseDto;

  ngOnChanges(changes: SimpleChanges): void {}

  getImageURI(uri: string) {
    return checkNull(uri) ? environment.apiURL + uri : 'assets/images/user.png';
  }
}
