import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router, NavigationEnd } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy  {

  constructor(private authSvc: AuthService, private router: Router, private usersvc: UserService) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.error = false;
        this.ngOnInit();
      }
    });
  }

  newUser: User;
  error = false;
  navigationSubscription;

  ngOnInit() {
  }

  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  register(){
    this.newUser.enabled = true;
    this.newUser.role = "user";
    this.authSvc.register(this.newUser).subscribe(
      data => {
        this.authSvc.login(this.newUser.username, this.newUser.password).subscribe(
          next => {
            this.router.navigateByUrl('/users');
          },
          err => {
            console.log('In RegisterComponent, Error Logging In');
          }
        );
      },
      error => {
        console.log('In RegisterComponent, Error Registering User');
        console.log(error);
        this.error = true;
      }
    );
  }

}
