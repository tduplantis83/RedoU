import { AuthService } from "./auth.service";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { tap, catchError } from "rxjs/operators";
import { throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { DailyCaloricIntake } from '../models/daily-caloric-intake';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class DailyCaloricIntakeService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getDailyCaloricIntakeById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<DailyCaloricIntake>(this.baseUrl + "/api/DailyCaloricIntake/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In DailyCaloricIntakeSvc get by Id");
      })
    );
  }

  getDailyCaloricIntakeByUserId(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<DailyCaloricIntake[]>(this.baseUrl + "/api/DailyCaloricIntake/userid/" + user.id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In DailyCaloricIntakeSvc get by UserId");
      })
    );
  }

  getDailyCaloricIntakeByUsername(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<DailyCaloricIntake[]>(this.baseUrl + "/api/DailyCaloricIntake/username/" + user.userName).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In DailyCaloricIntakeSvc get by Username");
      })
    );
  }

  createDailyCaloricIntake(dailyCaloricIntake: DailyCaloricIntake) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/DailyCaloricIntake/create", dailyCaloricIntake, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In DailyCaloricIntakeSvc create DailyCaloricIntake");
        })
      );
  }

  updateDailyCaloricIntake(dailyCaloricIntake: DailyCaloricIntake) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<DailyCaloricIntake>(
        this.baseUrl + "api/DailyCaloricIntake/update/" + dailyCaloricIntake,
        dailyCaloricIntake.id,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In DailyCaloricIntakeSvc update DailyCaloricIntake");
        })
      );
  }

  deleteDailyCaloricIntake(dailyCaloricIntake: DailyCaloricIntake) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/DailyCaloricIntake/delete/" + dailyCaloricIntake.id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In DailyCaloricIntakeSvc delete DailyCaloricIntake");
        })
      );
  }

}
