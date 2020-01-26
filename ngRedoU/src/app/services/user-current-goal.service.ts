import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { UserCurrentGoal } from '../models/user-current-goal';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserCurrentGoalService {

  private baseUrl = environment.baseUrl;

    constructor(private http: HttpClient, private authSvc: AuthService) { }

  getUserCurrentGoalById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<UserCurrentGoal>(this.baseUrl + "api/usercurrentgoal/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In UserCurrentGoalSvc get by Id");
      })
    );
  }

  getUserCurrentGoalByUserId(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<UserCurrentGoal[]>(this.baseUrl + "api/usercurrentgoal/userid/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In UserCurrentGoalSvc get by UserId");
      })
    );
  }

  getUserCurrentGoalByUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<UserCurrentGoal[]>(this.baseUrl + "api/usercurrentgoal/username/" + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In UserCurrentGoalSvc get by Username");
      })
    );
  }

  createUserCurrentGoal(userCurrentGoal: UserCurrentGoal) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/usercurrentgoal/create", userCurrentGoal, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In UserCurrentGoalSvc create UserCurrentGoal");
        })
      );
  }

  updateUserCurrentGoal(userCurrentGoal: UserCurrentGoal) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<UserCurrentGoal>(
        this.baseUrl + "api/usercurrentgoal/update/" +
        userCurrentGoal.id, userCurrentGoal,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In UserCurrentGoalSvc update UserCurrentGoal");
        })
      );
  }

  deleteUserCurrentGoal(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/usercurrentgoal/delete/" + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In UserCurrentGoalSvc delete UserCurrentGoal");
        })
      );
  }

}
