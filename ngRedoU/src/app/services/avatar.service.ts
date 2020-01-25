import { AuthService } from "./auth.service";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { tap, catchError } from "rxjs/operators";
import { throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { Avatar } from "../models/avatar";

@Injectable({
  providedIn: "root"
})
export class AvatarService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) {}

  getAvatarById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Avatar>(this.baseUrl + "/avatar/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In AvatarSvc get by Id");
      })
    );
  }

  getAllAvatars() {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Avatar[]>(this.baseUrl + "/avatar/all").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In AvatarSvc get All");
      })
    );
  }

  getAvatarsBySex(sex: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Avatar[]>(this.baseUrl + "/avatar/sex/" + sex).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In AvatarSvc get by Sex");
      })
    );
  }

  getAvatarsByBodyType(bodyType: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
        // Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .get<Avatar[]>(this.baseUrl + "/avatar/bodytype/" + bodyType)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In AvatarSvc get by Body Type");
        })
      );
  }

  createAvatar(avatar: Avatar) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/avatar/create", avatar, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In AvatarSvc create Avatar");
        })
      );
  }

  updateAvatar(avatar: Avatar) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<Avatar>(
        this.baseUrl + "api/avatar/update/" + avatar,
        avatar.id,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In AvatarSvc update Avatar");
        })
      );
  }

  deleteAvatar(avatar: Avatar) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/avatar/delete/" + avatar.id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In AvatarSvc delete Avatar");
        })
      );
  }
}
