import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { Router, NavigationEnd } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit, OnDestroy{

  constructor(private authSvc: AuthService, private usersvc: UserService, private router: Router) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.error = false;
        this.userToLogIn = new User();
        // this.userSelected = null;
        this.userFullName = null;
        this.loggedIn = false;
        this.isAdmin = false;
        this.ngOnInit();
      }
    });
  }

  userToLogIn: User = new User();
  // userSelected: User = null;
  userFullName: string = null;
  loggedIn = false;
  navigationSubscription;
  error = false;
  isAdmin = false;

  ngOnInit() {
    if (this.authSvc.checkLogin()) {
      this.loggedIn = true;
      this.getUser();
    }
  }

  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  isLoggedIn() {
    return this.authSvc.checkLogin();
  }

  logOut() {
    this.userFullName = null;
    this.loggedIn = false;
    this.authSvc.logout();
    this.isAdmin = false;
  }

  getUser() {
    this.usersvc.getLoggedInUser().subscribe(
      data => {
        if (data.role === 'admin') {
          this.isAdmin = true;
        }
        // this.userSelected = data;
        this.userFullName = data.firstName + ' ' + data.lastName;
      },
      err => console.error('Get Logged In User Error in Nav-Bar Component.ts')
    );

    // this.userSelected = null;
  }

  login() {
    this.error = false;
    this.authSvc.login(this.userToLogIn.username, this.userToLogIn.password).subscribe(
      data => {
        this.router.navigateByUrl('/users');
      },
      err => {
        console.error(err);
        this.error = true;
        this.userToLogIn = null;
      }
    );
  }



}
