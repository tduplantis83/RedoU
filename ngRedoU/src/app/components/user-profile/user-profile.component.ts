import { AvatarService } from './../../services/avatar.service';
import { AuthService } from 'src/app/services/auth.service';
import { PostReplyService } from './../../services/post-reply.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { Avatar } from 'src/app/models/avatar';
import { Router, NavigationEnd } from '@angular/router';
import { Goal } from 'src/app/models/goal';
import { Post } from 'src/app/models/post';
import { UserAvatarService } from 'src/app/services/user-avatar.service';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit, OnDestroy {
  // tslint:disable-next-line: max-line-length
  constructor(
    private userSvc: UserService,
    private router: Router,
    private postReplySvc: PostReplyService,
    private authSvc: AuthService,
    private avatarSvc: AvatarService,
    private userAvatarSvc: UserAvatarService
  ) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.currentAvatar = null;
        this.postsWithNewReplies = [];
        this.ngOnInit();
      }
    });
  }

  user: User;
  allUsers: User[] = [];
  userCurrentGoal: Goal = new Goal();
  currentAvatar: Avatar;
  postsWithNewReplies: Post[] = [];
  navigationSubscription;
  measurementSystem = 'US';
  caloricIntakeByDateMap = new Map<Date, number>();
  caloricDeficitByDateMap = new Map<Date, number>();
  caloricPerformanceByDate = [] = [];


  ngOnInit() {
    this.userSvc.getLoggedInUser().subscribe(
      data => {
        this.user = data;
        if (this.user.role === 'admin') {
          this.findAllUsers();
        }
        this.getCurrentUserAvatar();
        this.getUserCurrentGoal();
        this.getNewPostReplies();
        if (this.user.userDailyCaloricIntakes.length > 0) {
          this.caloricIntakeByDateMap = this.groupCaloricIntakeByDate(this.user.userDailyCaloricIntakes);
        }
        if (this.user.userDailyExerciseCaloricDeficits.length > 0) {
          this.caloricDeficitByDateMap = this.groupCaloricDeficitByDate(this.user.userDailyExerciseCaloricDeficits);
        }
      },
      err => console.error('In User Component getLoggedInUser Error')
    );
  }

  getCurrentUserAvatar() {
    // first find the user's CURRENT avatar
    this.user.userAvatars.forEach(ua => {
      if (ua.current === true) {
        this.currentAvatar = ua.avatar;
      }
    });

    // find most recent data from intake & deficit map
    var maxIntakeDate = this.user.dateCreated;
    var maxDeficitDate = this.user.dateCreated;

    for (let key of this.caloricIntakeByDateMap.keys()) {
      if(key >= maxIntakeDate ) {
        maxIntakeDate = key;
      }
    }

    for (let key of this.caloricDeficitByDateMap.keys()) {
      if(key >= maxDeficitDate ) {
        maxDeficitDate = key;
      }
    }

    // if user goal is weight loss...
    if (this.userCurrentGoal.goalName === 'Weight Loss') {
      // if the value at either is null, then don't change avatar
      if (this.caloricIntakeByDateMap.get(maxIntakeDate) == null || this.caloricDeficitByDateMap.get(maxDeficitDate) == null) {
        this.user.userAvatars.forEach(ua => {
          if (ua.current === true) {
            this.currentAvatar = ua.avatar;
          }
        });
      }
      // if the values subtracted = 0, then don't change avatar - no change
      else if (this.caloricIntakeByDateMap.get(maxIntakeDate) - this.caloricDeficitByDateMap.get(maxDeficitDate) === 0) {
        this.user.userAvatars.forEach(ua => {
          if (ua.current === true) {
            this.currentAvatar = ua.avatar;
          }
        });
      }
      // if your calorie intake was LESS than what you burned, make avatar thin
      else if (this.caloricIntakeByDateMap.get(maxIntakeDate) - this.caloricDeficitByDateMap.get(maxDeficitDate) < 0) {
        if (this.currentAvatar.bodyType === 'Fat') {
          this.userAvatarSvc.updateCURRENTUserAvatar('Average', this.user.id).subscribe(
            data => {
              this.currentAvatar = data.avatar;
            },
            error => console.error('In User Component getCurrentUserAvatar Error')
          );
        }
        else if (this.currentAvatar.bodyType === 'Average') {
          this.userAvatarSvc.updateCURRENTUserAvatar('Thin', this.user.id).subscribe(
            data => {
              this.currentAvatar = data.avatar;
            },
            error => console.error('In User Component getCurrentUserAvatar Error')
          );
        }
        // if user avatar is already thin, do nothing
      }
      // if your calorie intake was MORE than what you burned, make avatar FAT
      else if (this.caloricIntakeByDateMap.get(maxIntakeDate) - this.caloricDeficitByDateMap.get(maxDeficitDate) < 0) {
        if (this.currentAvatar.bodyType === 'Thin') {
          this.userAvatarSvc.updateCURRENTUserAvatar('Average', this.user.id).subscribe(
            data => {
              this.currentAvatar = data.avatar;
            },
            error => console.error('In User Component getCurrentUserAvatar Error')
          );
        }
        else if (this.currentAvatar.bodyType === 'Average') {
          this.userAvatarSvc.updateCURRENTUserAvatar('Fat', this.user.id).subscribe(
            data => {
              this.currentAvatar = data.avatar;
            },
            error => console.error('In User Component getCurrentUserAvatar Error')
          );
        }
        // if user avatar is already fat, do nothing
      }
    }
    // if user goal is muscle building...
    else if (this.userCurrentGoal.goalName === 'Muscle Building') {
      console.log('add muscle building mod here');
    }
    // if user goal is maintanence...
    else if (this.userCurrentGoal.goalName === 'General Fitness Maintanence') {
      console.log('add muscle building mod here');
    }

  }

  getUserCurrentGoal() {
    this.user.userCurrentGoals.forEach(ucg => {
      if (ucg.enabled === true) {
        this.userCurrentGoal = ucg.goal;
      }
    });
  }

  getNewPostReplies() {
    this.user.userPosts.forEach(p => {
      p.originalPostReplies.forEach(pr => {
        if (pr.unread === true) {
          this.postsWithNewReplies.push(p);
        }
      });
    });

    // remove duplicate posts from array
    for (let i = 0; i < this.postsWithNewReplies.length; i++) {
      this.postsWithNewReplies.forEach(post => {
        if (this.postsWithNewReplies[i].id === post.id) {
          this.postsWithNewReplies.splice(i, 1);
        }
      });
    }
  }

  groupCaloricIntakeByDate(arr: any []) {
    const res = new Map<Date, number>();
    let sum = 0;
    for (let i = 0; i < arr.length; i ++) {
        if (!res.has(arr[i].dateCreated)) {
          res.set(arr[i].dateCreated, arr[i].caloriesThisMeal);
        }
        else {
          sum = res.get(arr[i].dateCreated);
          sum += arr[i].caloriesThisMeal;
          res.set(arr[i].dateCreated, sum);
          sum = 0;
        }
    }
    return res;
  }

  groupCaloricDeficitByDate(arr: any []) {
    const res = new Map<Date, number>();
    let sum = 0;
    for (let i = 0; i < arr.length; i ++) {
        if (!res.has(arr[i].dateCreated)) {
          res.set(arr[i].dateCreated, arr[i].totalCaloriesBurned);
        }
        else {
          sum = res.get(arr[i].dateCreated);
          sum += arr[i].caloriesThisMeal;
          res.set(arr[i].dateCreated, sum);
          sum = 0;
        }
    }
    return res;
  }

    markReplyAsRead(replyID: number) {
    this.postReplySvc.markPostReplyRead(replyID).subscribe(
      data => {
        this.ngOnInit();
      },
      err => console.error('In User Component markReplyAsRead Error')
    );
  }

    findAllUsers() {
    this.userSvc.getAllUsers().subscribe(
      data => {
        this.allUsers = data;
      },
      err => console.error('In User Component findAllUsers Error')
    );
  }

    disableUser(u: User) {
    this.userSvc.disableUser(u.id).subscribe(
      data => {
        this.ngOnInit();
      },
      err => console.error('In User Component disableUser Error')
    );
  }

    enableUser(u: User) {
    this.userSvc.enableUser(u.id).subscribe(
      data => {
        this.ngOnInit();
      },
      err => console.error('In User Component enableUser Error')
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
}
