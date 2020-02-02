import { AuthService } from 'src/app/services/auth.service';
import { PostReplyReplyService } from './../../services/post-reply.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { Avatar } from 'src/app/models/avatar';
import { Router, NavigationEnd } from '@angular/router';
import { Goal } from 'src/app/models/goal';
import { Post } from 'src/app/models/post';

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
    private postReplySvc: PostReplyReplyService,
    private authSvc: AuthService
  ) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.userCurrentGoal = null;
        this.currentAvatar = null;
        this.postsWithNewReplies = [];
        this.ngOnInit();
      }
    });
  }

  user: User;
  allUsers: User[] = [];
  userCurrentGoal: Goal;
  currentAvatar: Avatar;
  postsWithNewReplies: Post[] = [];
  navigationSubscription;
  measurementSystem = 'US';

  ngOnInit() {
    this.userSvc.getLoggedInUser().subscribe(
      data => {
        this.user = data;
        if (this.user.role === 'admin') {
          this.findAllUsers();
        }
        this.getUserAvatar();
        this.getUserCurrentGoal();
        this.getNewPostReplies();
      },
      err => console.error('In User Component getLoggedInUser Error')
    );
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
