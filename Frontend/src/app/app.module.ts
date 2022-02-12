import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { HomeComponent } from './pages/home/home.component';
import { AdminModule } from './components/admin/admin.module';
import { ManagerModule } from './components/manager/manager.module';
import { RouterModule } from '@angular/router';
import { MarkdownPipe } from './pipes/markdownPipe/markdown.pipe';
import { httpInterceptorProviders } from './interceptors';
import { CommonModule } from '@angular/common';  
import { materialImports } from './materialIndex';
import { inputImports } from './components/shared/sharedImports';
import { NavigationComponent } from './components/shared/navigation/navigation.component';
import { ContentComponent } from './components/shared/content/content.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { UserSignupComponent } from './components/user/user-signup/user-signup.component';
import { PasswordComponent } from './components/shared/inputs/forms/password/password.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MarkdownPipe,
    NavigationComponent,
    ContentComponent,
    UserLoginComponent,
    inputImports,
    UserSignupComponent,
    PasswordComponent,
    
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    DragDropModule,
    AdminModule,
    ManagerModule,
    materialImports,
    
  ],
  exports: [
    RouterModule,
    materialImports,  
  ],
  providers: [
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
