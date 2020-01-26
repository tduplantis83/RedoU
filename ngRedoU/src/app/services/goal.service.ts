import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Goal } from '../models/goal';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GoalService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getGoalById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Goal>(this.baseUrl + "goal/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In GoalSvc get by Id");
      })
    );
  }

  getGoalByName(goalName: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Goal>(this.baseUrl + "goal/goalname/" + goalName).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In GoalSvc get by Name");
      })
    );
  }

  getAllGoals() {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Goal[]>(this.baseUrl + "goal/all").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In GoalSvc get All");
      })
    );
  }

  createGoal(goal: Goal) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/goal/create", goal, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In GoalSvc create Goal");
        })
      );
  }

  updateGoal(goal: Goal) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<Goal>(
        this.baseUrl + "api/goal/update/" +
        goal.id, goal,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In GoalSvc update Goal");
        })
      );
  }

  deleteGoal(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/goal/delete/" + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In GoalSvc delete Goal");
        })
      );
  }

}
