import { AuthService } from "./auth.service";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { tap, catchError } from "rxjs/operators";
import { throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { DailyCaloricIntake } from '../models/daily-caloric-intake';

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
    return this.http.get<DailyCaloricIntake>(this.baseUrl + "api/DailyCaloricIntake/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In DailyCaloricIntakeSvc get by Id");
      })
    );
  }

  getDailyCaloricIntakeByUserId(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<DailyCaloricIntake[]>(this.baseUrl + "api/DailyCaloricIntake/userid/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In DailyCaloricIntakeSvc get by UserId");
      })
    );
  }

  getDailyCaloricIntakeByUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<DailyCaloricIntake[]>(this.baseUrl + "api/DailyCaloricIntake/username/" + username).pipe(
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
        this.baseUrl + "api/DailyCaloricIntake/update/" +
        dailyCaloricIntake.id, dailyCaloricIntake,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In DailyCaloricIntakeSvc update DailyCaloricIntake");
        })
      );
  }

  deleteDailyCaloricIntake(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/DailyCaloricIntake/delete/" + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In DailyCaloricIntakeSvc delete DailyCaloricIntake");
        })
      );
  }

}
