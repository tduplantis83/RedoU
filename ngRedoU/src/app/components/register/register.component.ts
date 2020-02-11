import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router, NavigationEnd } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { GoalService } from 'src/app/services/goal.service';
import { UserCurrentGoalService } from 'src/app/services/user-current-goal.service';
import { Goal } from 'src/app/models/goal';
import { UserCurrentGoal } from 'src/app/models/user-current-goal';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy  {

  // tslint:disable-next-line: max-line-length
  constructor(private authSvc: AuthService, private router: Router, private usersvc: UserService, private goalSvc: GoalService, private userGoalSvc: UserCurrentGoalService) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.error = false;
        this.ngOnInit();
      }
    });
  }

  newUser: User = new User();
  allGoals: Goal [] = [];
  userCurrGoal: UserCurrentGoal = new UserCurrentGoal();
  loggedIn = false;
  error = false;
  navigationSubscription;

  ngOnInit() {
    this.newUser = new User();
    this.getAllGoals();
  }

  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  register() {
    this.newUser.enabled = true;
    this.newUser.role = 'user';
    this.authSvc.register(this.newUser).subscribe(
      data => {
        this.authSvc.login(this.newUser.username, this.newUser.password).subscribe(
          next => {
            // this.router.navigateByUrl('/users');
            this.loggedIn = true;
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

  getAllGoals() {
    this.goalSvc.getAllGoals().subscribe(
      data => {
        this.allGoals = data;
      },
      err => {
        console.log('In RegisterComponent, Error gettingAllGoals');
      }
    );
  }

  createUserGoal() {
    this.userCurrGoal.enabled = true;
    console.log(this.userCurrGoal);
    // this.userGoalSvc.createUserCurrentGoal(this.userGoal).subscribe(
    //   data => {
    //     this.router.navigateByUrl('/users');
    //   },
    //   err => {
    //     console.log('In RegisterComponent, Error creatingUserCurrentGoal');
    //   }
    // );
  }

}
