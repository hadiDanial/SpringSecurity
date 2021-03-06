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
import { DoublePasswordComponent } from './components/shared/inputs/forms/double-password/double-password.component';
import { matchingPasswordsValidator } from './components/shared/inputs/forms/double-password/matchingPasswordsValidator';
import { UploadFileComponent } from './components/shared/inputs/generic/upload-file/upload-file.component';
import { DownloadFileComponent } from './components/shared/inputs/generic/download-file/download-file.component'
import { DragAndDropComponent } from './components/drag-and-drop/drag-and-drop.component';
import { LogoutComponent } from './components/user/logout/logout.component';
import { UserProfileComponent } from './components/user/user-profile/user-profile.component';
import { SearchComponent } from './components/general/search/search.component';
import { ProfilePictureComponent } from './components/user/profile-picture/profile-picture.component';
import { PostComponent } from './components/posts/post/post.component';
import { PostPreviewComponent } from './components/posts/post-preview/post-preview.component';
import { CreatePostComponent } from './components/posts/create-post/create-post.component';
import { EditPostComponent } from './components/posts/edit-post/edit-post.component';
import { CommentComponent } from './components/comments/comment/comment.component';
import { CommentPreviewComponent } from './components/comments/comment-preview/comment-preview.component';
import { AddCommentComponent } from './components/comments/add-comment/add-comment.component';
import { PostsComponent } from './components/posts/posts/posts.component';
import { PostPreviewPipe } from './pipes/postPreviewPipe/post-preview.pipe';
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
    DoublePasswordComponent,
    UploadFileComponent,
    DownloadFileComponent,
    DragAndDropComponent,
    LogoutComponent,
    UserProfileComponent,
    SearchComponent,
    ProfilePictureComponent,
    PostComponent,
    PostPreviewComponent,
    CreatePostComponent,
    EditPostComponent,
    CommentComponent,
    CommentPreviewComponent,
    AddCommentComponent,
    PostsComponent,
    PostPreviewPipe
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
