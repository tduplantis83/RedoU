import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { MealType } from '../models/meal-type';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MealTypeService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getMealTypeById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<MealType>(this.baseUrl + "/mealtype/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In MealTypeSvc get by Id");
      })
    );
  }

  getMealTypeByName(mealTypeName: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<MealType>(this.baseUrl + "/mealtype/mealtypename/" + mealTypeName).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In MealTypeSvc get by Name");
      })
    );
  }

  getAllMealTypes() {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<MealType[]>(this.baseUrl + "/mealtype/all").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In MealTypeSvc get All");
      })
    );
  }

  createMealType(mealType: MealType) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/mealtype/create", mealType, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In MealTypeSvc create MealType");
        })
      );
  }

  updateMealType(mealType: MealType) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<MealType>(
        this.baseUrl + "api/mealtype/update/" + mealType,
        mealType.id,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In MealTypeSvc update MealType");
        })
      );
  }

  deleteMealType(mealType: MealType) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/mealtype/delete/" + mealType.id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In MealTypeSvc delete MealType");
        })
      );
  }

}
