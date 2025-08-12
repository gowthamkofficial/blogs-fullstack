import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-list',
  standalone: false,
  templateUrl: './list.component.html',
  styleUrl: './list.component.css',
})
export class ListComponent {
  blogs = [
    {
      blogId: 1,
      title: 'Understanding JavaScript Closures',
      category: 'JavaScript',
      createdOn: '2025-08-01',
      publishedOn: '2025-08-03',
      status: 'Published',
    },
    {
      blogId: 2,
      title: 'Spring Boot CRUD Operations with MySQL',
      category: 'Java',
      createdOn: '2025-08-02',
      publishedOn: '2025-08-04',
      status: 'Published',
    },
    {
      blogId: 3,
      title: 'CSS Grid vs Flexbox: When to Use What',
      category: 'CSS',
      createdOn: '2025-08-04',
      publishedOn: '2025-08-06',
      status: 'Published',
    },
    {
      blogId: 4,
      title: 'Angular Signals: The Future of Reactivity',
      category: 'Angular',
      createdOn: '2025-08-05',
      publishedOn: null,
      status: 'Draft',
    },
    {
      blogId: 5,
      title: 'Getting Started with Tailwind CSS',
      category: 'CSS',
      createdOn: '2025-08-06',
      publishedOn: '2025-08-08',
      status: 'Published',
    },
    {
      blogId: 6,
      title: 'A Beginner’s Guide to React Hooks',
      category: 'React',
      createdOn: '2025-08-07',
      publishedOn: '2025-08-09',
      status: 'Published',
    },
    {
      blogId: 7,
      title: 'Node.js Streams Explained',
      category: 'Node.js',
      createdOn: '2025-08-08',
      publishedOn: null,
      status: 'Draft',
    },
    {
      blogId: 8,
      title: 'Database Indexing Strategies',
      category: 'Database',
      createdOn: '2025-08-09',
      publishedOn: '2025-08-11',
      status: 'Published',
    },
    {
      blogId: 9,
      title: 'Mastering TypeScript Generics',
      category: 'TypeScript',
      createdOn: '2025-08-10',
      publishedOn: null,
      status: 'Draft',
    },
    {
      blogId: 10,
      title: 'REST vs GraphQL: Which to Choose?',
      category: 'API',
      createdOn: '2025-08-11',
      publishedOn: '2025-08-12',
      status: 'Published',
    },
  ];

  displayedColumns: string[] = [
    'blogId',
    'title',
    'category',
    'createdOn',
    'publishedOn',
    'status',
  ];
  dataSource = new MatTableDataSource(this.blogs);
  constructor() {
    this.dataSource = new MatTableDataSource(this.blogs);
  }
}
