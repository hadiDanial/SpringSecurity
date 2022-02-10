import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
   // outlet: "mainRouter"
  },
  {
    path: 'login',
    component: UserLoginComponent,
   // outlet: "mainRouter"
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
