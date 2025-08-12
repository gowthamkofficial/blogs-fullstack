import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './list/list.component';
import { ViewComponent } from './view/view.component';
import { CreateComponent } from './create/create.component';
import { UpdateComponent } from './update/update.component';

const routes: Routes = [
  { path: 'my-blogs', redirectTo: '', pathMatch: 'full' },
{
  path:"",
  component:ListComponent
},
  {
    path: 'view/:id',
    component: ViewComponent,
  },
  {
    path: 'create',
    component: CreateComponent,
  },
  {
    path: 'update',
    component: UpdateComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MyBlogsRoutingModule {}
