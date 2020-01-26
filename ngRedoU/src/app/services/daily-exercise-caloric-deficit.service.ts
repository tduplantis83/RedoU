import { AuthService } from "./auth.service";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { tap, catchError } from "rxjs/operators";
import { throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { User } from '../models/user';
import { DailyExerciseCaloricDeficit } from '../models/daily-exercise-caloric-deficit';

@Injectable({
  providedIn: 'root'
})
export class DailyExerciseCaloricDeficitService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getDailyExerciseCaloricDeficitById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<DailyExerciseCaloricDeficit>(this.baseUrl + "/api/dailyexercisecaloricdeficit/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In DailyExerciseCaloricDeficitSvc get by Id");
      })
    );
  }

  getDailyExerciseCaloricDeficitByUserId(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<DailyExerciseCaloricDeficit[]>(this.baseUrl + "/api/dailyexercisecaloricdeficit/userid/" + user.id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In DailyExerciseCaloricDeficitSvc get by UserId");
      })
    );
  }

  getDailyExerciseCaloricDeficitByUsername(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<DailyExerciseCaloricDeficit[]>(this.baseUrl + "/api/dailyexercisecaloricdeficit/username/" + user.userName).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In DailyExerciseCaloricDeficitSvc get by Username");
      })
    );
  }

  createDailyExerciseCaloricDeficit(dailyExerciseCaloricDeficit: DailyExerciseCaloricDeficit) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/dailyexercisecaloricdeficit/create", dailyExerciseCaloricDeficit, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In DailyExerciseCaloricDeficitSvc create DailyExerciseCaloricDeficit");
        })
      );
  }

  updateDailyExerciseCaloricDeficit(dailyExerciseCaloricDeficit: DailyExerciseCaloricDeficit) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<DailyExerciseCaloricDeficit>(
        this.baseUrl + "api/dailyexercisecaloricdeficit/update/" + dailyExerciseCaloricDeficit,
        dailyExerciseCaloricDeficit.id,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In DailyExerciseCaloricDeficitSvc update DailyExerciseCaloricDeficit");
        })
      );
  }

  deleteDailyExerciseCaloricDeficit(dailyExerciseCaloricDeficit: DailyExerciseCaloricDeficit) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/dailyexercisecaloricdeficit/delete/" + dailyExerciseCaloricDeficit.id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In DailyExerciseCaloricDeficitSvc delete DailyExerciseCaloricDeficit");
        })
      );
  }
}
