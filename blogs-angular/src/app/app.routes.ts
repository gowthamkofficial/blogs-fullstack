import { Routes } from '@angular/router';
import { AuthLayoutComponent } from './shared/components/auth-layout/auth-layout.component';
import { MainLayoutComponent } from './shared/components/main-layout/main-layout.component';
import { authenticatedGuard } from './core/gaurd/authenticated.guard';
import { authGaurd } from './core/gaurd/auth.guard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'session',
    pathMatch: 'full',
  },
  {
    path: '',
    component: AuthLayoutComponent,
    canActivate:[authenticatedGuard],
    children: [
      {
        path: 'session',
        loadChildren: () =>
          import('./modules/session/session.module').then(
            (m) => m.SessionModule
          ),
      },
    ],
  },

  {
    path: '',
    component: MainLayoutComponent,
    canActivate:[authGaurd],
    children: [
      {
        path: 'blogs',
        loadChildren: () =>
          import('./modules/home/home.module').then((m) => m.HomeModule),
      },
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./modules/dashboard/dashboard.module').then(
            (m) => m.DashboardModule
          ),
      },
      {
        path: 'my-blogs',
        loadChildren: () =>
          import('./modules/my-blogs/my-blogs.module').then(
            (m) => m.MyBlogsModule
          ),
      },
      {
        path: 'my-profile',
        loadChildren: () =>
          import('./modules/my-profile/my-profile.module').then(
            (m) => m.MyProfileModule
          ),
      },
    ],
  },
];
