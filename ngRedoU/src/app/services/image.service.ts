import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Image } from '../models/image';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getImageById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Image>(this.baseUrl + "api/image/id/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In ImageSvc get by Id");
      })
    );
  }

  getImageByUserId(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Image[]>(this.baseUrl + "api/image/userid/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In ImageSvc get by UserId");
      })
    );
  }

  getImageByUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Image[]>(this.baseUrl + "api/image/username/" + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("In ImageSvc get by Username");
      })
    );
  }

  createImage(image: Image) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .post(this.baseUrl + "api/image/create", image, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In ImageSvc create Image");
        })
      );
  }

  updateImage(image: Image) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<Image>(
        this.baseUrl + "api/image/update/" +
        image.id, image,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In ImageSvc update Image");
        })
      );
  }

  deleteImage(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http
      .delete(this.baseUrl + "api/image/delete/" + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("In ImageSvc delete Image");
        })
      );
  }

}
