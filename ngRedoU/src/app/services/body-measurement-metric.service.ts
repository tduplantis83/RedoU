import { AuthService } from "./auth.service";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { tap, catchError } from "rxjs/operators";
import { throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { BodyMeasurementMetric } from '../models/body-measurement-metric';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class BodyMeasurementMetricService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getBodyMeasurementById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<BodyMeasurementMetric>(this.baseUrl + "/api/bodymeasurementmetric/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In BodyMeasurementSvc get by Id");
      })
    );
  }

  getBodyMeasurementByUserId(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<BodyMeasurementMetric[]>(this.baseUrl + "/api/bodymeasurementmetric/userid/" + user.id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In BodyMeasurementSvc get by UserId");
      })
    );
  }

  getBodyMeasurementByUsername(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<BodyMeasurementMetric[]>(this.baseUrl + "/api/bodymeasurementmetric/username/" + user.userName).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In BodyMeasurementSvc get by Username");
      })
    );
  }

  createBodyMeasurement(bodymeasurement: BodyMeasurementMetric) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/bodymeasurementmetric/create", bodymeasurement, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In BodyMeasurementSvc create BodyMeasurement");
        })
      );
  }

  updateBodyMeasurement(bodymeasurement: BodyMeasurementMetric) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<BodyMeasurementMetric>(
        this.baseUrl + "api/bodymeasurementmetric/update/" + bodymeasurement,
        bodymeasurement.id,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In BodyMeasurementSvc update BodyMeasurement");
        })
      );
  }

  deleteBodyMeasurement(bodymeasurement: BodyMeasurementMetric) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/bodymeasurementmetric/delete/" + bodymeasurement.id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In BodyMeasurementSvc delete BodyMeasurement");
        })
      );
  }


}
