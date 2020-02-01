import { PostReplyReplyService } from './../../services/post-reply.service';
import { PostService } from './../../services/post.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { Router, NavigationEnd } from '@angular/router';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit, OnDestroy{

  // tslint:disable-next-line: max-line-length
  constructor(private authSvc: AuthService, private usersvc: UserService, private router: Router, private postSvc: PostService, private postReplySvc: PostReplyReplyService) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.ngOnInit();
      }
    });
  }

  navigationSubscription;
  allPosts: Post [] = [];
  showReplies = false;
  user: User;

  ngOnInit() {
    this.postSvc.getAllPosts().subscribe(
      data => {
        this.allPosts = data;
      },
      err => {
        console.error('In Posts Component - get ALL Posts')
      }
    );
    if (this.authSvc.checkLogin()) {
      this.usersvc.getLoggedInUser().subscribe(
        data => {
          this.user = data;
        },
        err => console.log('In Post Component get Logged In User')
      );
    }
  }

  updatePost(post: Post) {
    post.title = "Test Title";
    this.postSvc.updatePost(post).subscribe(
      data => {
        this.ngOnInit();
      },
      err => console.log('In Post Component Delete Post')
    );
  }

  deletePost(id: number) {
    this.postSvc.deletePost(id).subscribe(
      data => {
        this.ngOnInit();
      },
      err => console.log('In Post Component Delete Post')
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
