import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { PostReply } from '../models/post-reply';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostReplyReplyService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getPostReplyById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostReply>(this.baseUrl + 'postreply/id/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In PostReplySvc get by Id');
      })
    );
  }

  getPostReplyByUserId(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostReply[]>(this.baseUrl + 'postreply/userid/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In PostReplySvc get by UserId');
      })
    );
  }

  getPostReplyByUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostReply[]>(this.baseUrl + 'postreply/username/' + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In PostReplySvc get by Username');
      })
    );
  }

  getPostReplyByOriginalPostId(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostReply[]>(this.baseUrl + 'postreply/originalpostid/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In PostReplySvc get by OriginalPostID');
      })
    );
  }

  getPostReplyByOriginalPostUserId(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostReply[]>(this.baseUrl + 'postreply/originalpostuserid/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In PostReplySvc get by OriginalPostUserID');
      })
    );
  }

  getPostReplyByOriginalPostUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostReply[]>(this.baseUrl + 'postreply/originalpostusername/' + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In PostReplySvc get by OriginalPostUsername');
      })
    );
  }

  createPostReply(postReply: PostReply) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + 'api/postreply/create', postReply, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In PostRelySvc create PostReply');
        })
      );
  }

  createPostReplyTOPostReply(postReply: PostReply, originalPostReplyID: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + 'api/postreply/createReplyToReply/' + originalPostReplyID, postReply, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In PostRelySvc create PostReply');
        })
      );
  }

  markPostReplyRead(postReplyID: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<PostReply>(this.baseUrl + 'api/postreply/setread/' + postReplyID, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In PostRelySvc mark Post Reply as Read');
        })
      );
  }


  updatePostReply(postReply: PostReply) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<PostReply>(
        this.baseUrl + 'api/postreply/update/' +
        postReply.id, postReply,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In PostRelySvc update PostReply');
        })
      );
  }

  deletePostReply(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + 'api/postreply/delete/' + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In PostRelySvc delete PostReply');
        })
      );
  }

}
