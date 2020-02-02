import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getUserById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User>(this.baseUrl + 'users/id/' + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserSvc get by Id');
      })
    );
  }

  getUserByUsernameEXACT(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User>(this.baseUrl + 'users/username/exact/' + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserSvc get by Username EXACT');
      })
    );
  }

  getUserByUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User[]>(this.baseUrl + 'users/username/' + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserSvc get by Username');
      })
    );
  }

  getUserByEmail(email: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User[]>(this.baseUrl + 'users/email/' + email).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserSvc get by Email');
      })
    );
  }

  getUserByEnabled(enabled: boolean) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User[]>(this.baseUrl + 'api/users/enabled/' + enabled, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserSvc get by Enabled');
      })
    );
  }

  getUserByRole(role: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User[]>(this.baseUrl + 'api/users/role/' + role, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserSvc get by Role');
      })
    );
  }

  getAllUsers() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User[]>(this.baseUrl + 'api/users/all', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserSvc get All');
      })
    );
  }

  getLoggedInUser() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User>(this.baseUrl + 'api/users/getloggedinuser', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('In UserSvc get Logged In User');
      })
    );
  }

  createUser(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + 'users/create/', user, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserSvc create User');
        })
      );
  }

  updateUser(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<User>(
        this.baseUrl + 'api/users/update/' +
        user.id, user,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserSvc update User');
        })
      );
  }

  disableUser(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<User>(
        this.baseUrl + 'api/users/disable/' +
        id,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserSvc disable User');
        })
      );
  }

  enableUser(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<User>(
        this.baseUrl + 'api/users/enable/' +
        id,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserSvc enable User');
        })
      );
  }

  deleteUser(userId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + 'api/users/delete/' + userId, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('In UserSvc delete User');
        })
      );
  }

}
