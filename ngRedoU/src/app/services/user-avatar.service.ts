import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { UserAvatar } from '../models/user-avatar';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAvatarService {

  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getUserAvatarById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<UserAvatar>(this.baseUrl + 'api/useravatar/id/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserAvatarSvc get by Id');
      })
    );
  }

  getUserAvatarByUserId(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<UserAvatar[]>(this.baseUrl + 'api/useravatar/userid/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserAvatarSvc get by UserId');
      })
    );
  }

  getUserAvatarByUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<UserAvatar[]>(this.baseUrl + 'api/useravatar/username/' + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserAvatarSvc get by Username');
      })
    );
  }

  createUserAvatar(userID: number, avatarGroupID: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + 'api/useravatar/create/' + avatarGroupID + '/' + userID, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserAvatarSvc create UserAvatar');
        })
      );
  }

  updateUserAvatar(avatarGroupID: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<UserAvatar[]>(
        this.baseUrl + 'api/useravatar/update/' +
        avatarGroupID,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserAvatarSvc update UserAvatar');
        })
      );
  }

  updateCURRENTUserAvatar(bodyType: string, userID: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<UserAvatar>(
        this.baseUrl + 'api/useravatar/updatecurrent/' +
        bodyType + '/' + userID,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserAvatarSvc update UserAvatar');
        })
      );
  }

  deleteUserAvatar(userId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + 'api/useravatar/delete/' + userId, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserAvatarSvc delete UserAvatar');
        })
      );
  }
}
