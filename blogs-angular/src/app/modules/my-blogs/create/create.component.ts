import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-create',
  standalone: false,
  templateUrl: './create.component.html',
  styleUrl: './create.component.css',
})
export class CreateComponent {
  createForm = new FormGroup({
    content: new FormControl(''),
  });
}
