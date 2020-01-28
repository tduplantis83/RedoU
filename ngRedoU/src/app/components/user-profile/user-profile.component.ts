import { Component, OnInit, OnDestroy } from "@angular/core";
import { UserService } from "src/app/services/user.service";
import { User } from "src/app/models/user";
import { Avatar } from "src/app/models/avatar";
import { Router, NavigationEnd } from "@angular/router";
import { Goal } from 'src/app/models/goal';

@Component({
  selector: "app-user-profile",
  templateUrl: "./user-profile.component.html",
  styleUrls: ["./user-profile.component.css"]
})
export class UserProfileComponent implements OnInit, OnDestroy {
  constructor(private userSvc: UserService, private router: Router) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.user = null;
        this.currentAvatar = null;
        this.userCurrentGoal = null;
      }
    });
  }

  user: User;
  userCurrentGoal: Goal;
  currentAvatar: Avatar;
  navigationSubscription;

  ngOnInit() {
    this.userSvc.getLoggedInUser().subscribe(
      data => {
        this.user = data;
        this.getUserAvatar();
        this.getUserCurrentGoal();
      },
      err => console.error("Get user by id error in User Component")
    );
  }

  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  getUserAvatar() {
    this.user.userAvatars.forEach(ua => {
      if (ua.current === true) {
        this.currentAvatar = ua.avatar;
      }
    });
  }

  getUserCurrentGoal() {
    this.user.userCurrentGoals.forEach(ucg => {
      if (ucg.enabled === true) {
        this.userCurrentGoal = ucg.goal;
      }
    });
  }
}
