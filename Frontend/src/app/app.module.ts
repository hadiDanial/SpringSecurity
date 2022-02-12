import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { HomeComponent } from './pages/home/home.component';
import { UserModule } from './components/user/user.module';
import { SharedModule } from './components/shared/shared.module';
import { AdminModule } from './components/admin/admin.module';
import { ManagerModule } from './components/manager/manager.module';
import { RouterModule } from '@angular/router';
import { MarkdownPipe } from './pipes/markdownPipe/markdown.pipe';
import { httpInterceptorProviders } from './interceptors';
import { CommonModule } from '@angular/common';  
import { materialImports } from './materialIndex';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MarkdownPipe,
    
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    DragDropModule,
    SharedModule,
    UserModule,
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
