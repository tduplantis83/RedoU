import { Component, OnInit, OnDestroy, PipeTransform } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router, NavigationEnd } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { GoalService } from 'src/app/services/goal.service';
import { UserCurrentGoalService } from 'src/app/services/user-current-goal.service';
import { Goal } from 'src/app/models/goal';
import { UserCurrentGoal } from 'src/app/models/user-current-goal';
import { NgForm } from '@angular/forms';
import { Avatar } from 'src/app/models/avatar';
import { AvatarService } from 'src/app/services/avatar.service';
import { UserAvatarService } from 'src/app/services/user-avatar.service';
import { BodyMeasurementMetric } from 'src/app/models/body-measurement-metric';
import { BodyMeasurementMetricService } from 'src/app/services/body-measurement-metric.service';
import { MeasurementConverterPipe } from 'src/app/Pipes/measurement-converter.pipe';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy  {

  // tslint:disable-next-line: max-line-length
  constructor(private authSvc: AuthService,
    private router: Router,
    private usersvc: UserService,
    private goalSvc: GoalService,
    private userGoalSvc: UserCurrentGoalService,
    private avatarSvc: AvatarService,
    private userAvatarSvc: UserAvatarService,
    private bodyMeasureSvc: BodyMeasurementMetricService,
    private measurementConverterPipe: MeasurementConverterPipe
    ) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.loggedIn = false;
        this.error = false;
        this.ucgChosen = false;
        this.uavgroupChosen = false;
      }
    });
  }

  newUser: User = new User();
  allGoals: Goal [] = [];
  userCurrGoal: UserCurrentGoal = new UserCurrentGoal();
  allAvatars: Avatar [] = [];
  groupedAvatars: Avatar [] = [];
  loggedIn = false;
  error = false;
  ucgChosen = false;
  uavgroupChosen = false;
  measurementSystem = 'US';
  measurement: BodyMeasurementMetric = new BodyMeasurementMetric();
  bmi: number;
  navigationSubscription;

  ngOnInit() {
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
        this.newUser.id = data.id;
        this.authSvc.login(this.newUser.username, this.newUser.password).subscribe(
          next => {
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
    this.userGoalSvc.createUserCurrentGoal(this.userCurrGoal).subscribe(
      data => {
        this.getAllAvatars();
        this.ucgChosen = true;
      },
      err => {
        console.log('In RegisterComponent, Error creatingUserCurrentGoal');
      }
    );
  }

  getAllAvatars() {
    this.avatarSvc.getAvatarsBySex(this.newUser.sex).subscribe(
      data => {
        this.allAvatars = data;
        this.groupedAvatars = this.groupAvatars(this.allAvatars, 5);
    },
    err => console.error('In RegisterComponent getAllAvatars Error')
  );
}

groupAvatars(arr, size) {
  const res = [];
  for (let i = 0; i < arr.length; i = i + size) {
        res.push(arr.slice(i, i + size));
      }
  return res;
}

createUserAvatar(avGroup: number) {
  this.userAvatarSvc.createUserAvatar(this.newUser.id, avGroup).subscribe(
    data => {
      this.uavgroupChosen = true;
    },
    err => console.error('In RegisterComponent createUserAvatar Error')
  );
}

createBodyMeasurement() {
  // if US selected, convert to metric first
  if (this.measurementSystem === 'US') {
    this.measurement.heightMM = this.measurementConverterPipe.transform(this.measurement.heightMM, 'in', 'mm');
    this.measurement.weightKg = this.measurementConverterPipe.transform(this.measurement.weightKg, 'lb', 'kg');
    this.measurement.waistMM = this.measurementConverterPipe.transform(this.measurement.waistMM, 'in', 'mm');

    if (this.measurement.neckMM !== null) {
      this.measurement.neckMM = this.measurementConverterPipe.transform(this.measurement.neckMM, 'in', 'mm');
    }
    if (this.measurement.shouldersMM !== null) {
      this.measurement.shouldersMM = this.measurementConverterPipe.transform(this.measurement.shouldersMM, 'in', 'mm');
    }
    if (this.measurement.chestMM !== null) {
      this.measurement.chestMM = this.measurementConverterPipe.transform(this.measurement.chestMM, 'in', 'mm');
    }
    if (this.measurement.bicepMM !== null) {
      this.measurement.bicepMM = this.measurementConverterPipe.transform(this.measurement.bicepMM, 'in', 'mm');
    }
    if (this.measurement.hipsMM !== null) {
      this.measurement.hipsMM = this.measurementConverterPipe.transform(this.measurement.hipsMM, 'in', 'mm');
    }
    if (this.measurement.thighMM !== null) {
      this.measurement.thighMM = this.measurementConverterPipe.transform(this.measurement.thighMM, 'in', 'mm');
    }
  }

  this.bodyMeasureSvc.createBodyMeasurement(this.measurement).subscribe(
    data => {
      // choose initial avatar based on BMI
      this.setCurrentAvatar();
    },
    err => {
      console.error('In RegisterComponent createBodyMeasurement Error');
    }
  );
}

setCurrentAvatar() {
  // find BMI
  let heightm = this.measurementConverterPipe.transform(this.measurement.heightMM, 'mm', 'm');
  this.bmi = this.measurement.weightKg / (heightm * heightm);

  let bodyType;
  if (this.bmi < 18.5) {
    //thin avatar
    bodyType = 'Thin';
  }
  else if (this.bmi >= 18.5 && this.bmi <= 24.9) {
    //average avatar
    bodyType = 'Average';
  }
  else {
    //fat avatar
    bodyType = 'Fat';
  }

  this.userAvatarSvc.getUserAvatarByUserId(this.newUser.id).subscribe(
    data => {
      this.userAvatarSvc.updateCURRENTUserAvatar(bodyType, this.newUser.id).subscribe(
        good => {
          this.router.navigateByUrl('/users');
        },
        error => {
          console.error('In RegisterComponent updateCURRENTUserAvatar Error');
        }
      );
    },
    err => {
      console.error('In RegisterComponent getUserAvatarByUserId Error');
    }
  );
}

cancelRegistration() {
  this.usersvc.deleteUser(this.newUser.id).subscribe(
    data => {
      this.router.navigateByUrl('/home');
    },
    err => {
      console.error('In RegisterComponent cancelRegistration/deleteUser Error');
    }
  );
}

}
