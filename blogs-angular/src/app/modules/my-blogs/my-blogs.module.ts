import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyBlogsRoutingModule } from './my-blogs-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { ListComponent } from './list/list.component';
import { ViewComponent } from './view/view.component';
import { UpdateComponent } from './update/update.component';
import { CreateComponent } from './create/create.component';
import { NgxSuneditorModule } from 'ngx-suneditor';

@NgModule({
  declarations: [ListComponent,ViewComponent,UpdateComponent,CreateComponent],
  imports: [CommonModule, MyBlogsRoutingModule, SharedModule,NgxSuneditorModule ],
})
export class MyBlogsModule {}
