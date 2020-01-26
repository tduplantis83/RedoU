import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Post } from '../models/post';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getPostById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Post>(this.baseUrl + "post/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostSvc get by Id");
      })
    );
  }

  getPostByUserId(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Post[]>(this.baseUrl + "post/userid/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostSvc get by UserId");
      })
    );
  }

  getPostByUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Post[]>(this.baseUrl + "post/username/" + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostSvc get by Username");
      })
    );
  }

  getPostByPostTopic(postTopic: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Post[]>(this.baseUrl + "post/posttopic/" + postTopic).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostSvc get by Post Topic");
      })
    );
  }

  getPostByTitleLike(title: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Post[]>(this.baseUrl + "post/title/" + title).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostSvc get by Title");
      })
    );
  }

  getPostByContentLike(content: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Post[]>(this.baseUrl + "post/content/" + content).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostSvc get by Content");
      })
    );
  }

  createPost(post: Post) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/post/create", post, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In PostSvc create Post");
        })
      );
  }

  updatePost(post: Post) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<Post>(
        this.baseUrl + "api/post/update/" +
        post.id, post,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In PostSvc update Post");
        })
      );
  }

  deletePost(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/post/delete/" + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In PostSvc delete Post");
        })
      );
  }

}
