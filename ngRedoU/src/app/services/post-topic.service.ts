import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { PostTopic } from '../models/post-topic';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostTopicService {
  private baseUrl = environment.baseUrl;

    constructor(private http: HttpClient, private authSvc: AuthService) { }

  getPostTopicById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostTopic>(this.baseUrl + "posttopic/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostTopicSvc get by Id");
      })
    );
  }

  getPostTopicByName(topicName: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostTopic>(this.baseUrl + "posttopic/topicname/" + topicName).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostTopicSvc get by Name");
      })
    );
  }

  getAllPostTopics() {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<PostTopic[]>(this.baseUrl + "posttopic/all").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In PostTopicSvc get All");
      })
    );
  }

  createPostTopic(postTopic: PostTopic) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/posttopic/create", postTopic, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In PostTopicSvc create PostTopic");
        })
      );
  }

  updatePostTopic(postTopic: PostTopic) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<PostTopic>(
        this.baseUrl + "api/posttopic/update/" +
        postTopic.id, postTopic,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In PostTopicSvc update PostTopic");
        })
      );
  }

  deletePostTopic(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/posttopic/delete/" + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In PostTopicSvc delete PostTopic");
        })
      );
  }

}
