import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { UserSignupComponent } from './components/user/user-signup/user-signup.component';
import { LoggedOutGuard } from './guards/logged-out.guard';
import { LoginGuard } from './guards/login.guard';
import { NavigationComponent } from './components/shared/navigation/navigation.component';
import { LogoutComponent } from './components/user/logout/logout.component';
import { SearchComponent } from './components/general/search/search.component';
import { CreatePostComponent } from './components/posts/create-post/create-post.component';
import { PostsComponent } from './components/posts/posts/posts.component';
import { PostComponent } from './components/posts/post/post.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'logout',
    component: LogoutComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'login',
    component: UserLoginComponent,
    canActivate: [LoggedOutGuard]
  },
  {
    path: 'signup',
    component: UserSignupComponent,
    canActivate: [LoggedOutGuard]
  },
  {
    path: 'posts',
    children: [{
      path: 'new-post',
      component: CreatePostComponent,
      canActivate: [LoginGuard]
    },
    {
      path: 'get-all-posts',
      component: PostsComponent
    },
    {
      path: ':id',
      component: PostComponent,
      children: [
        {
          path: '**',
          redirectTo: 'post',
        }
      ]
    }]
  },
  {
    path: 'search',
    component: SearchComponent
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
