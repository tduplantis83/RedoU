import { AuthService } from './../../services/auth.service';
import { PostReplyService } from './../../services/post-reply.service';
import { Component, OnInit, OnDestroy, PipeTransform } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { Avatar } from 'src/app/models/avatar';
import { Router, NavigationEnd } from '@angular/router';
import { Goal } from 'src/app/models/goal';
import { Post } from 'src/app/models/post';
import { Image } from 'src/app/models/image';
import { UserAvatarService } from 'src/app/services/user-avatar.service';
import { DailyCaloricIntakeService } from 'src/app/services/daily-caloric-intake.service';
import { MealTypeService } from 'src/app/services/meal-type.service';
import { MealType } from 'src/app/models/meal-type';
import { DailyCaloricIntake } from 'src/app/models/daily-caloric-intake';
import { MeasurementConverterPipe } from 'src/app/Pipes/measurement-converter.pipe';
import { DailyExerciseCaloricDeficit } from 'src/app/models/daily-exercise-caloric-deficit';
import { DailyExerciseCaloricDeficitService } from 'src/app/services/daily-exercise-caloric-deficit.service';
import { ImageService } from 'src/app/services/image.service';
import { BodyMeasurementMetricService } from 'src/app/services/body-measurement-metric.service';
import { BodyMeasurementMetric } from 'src/app/models/body-measurement-metric';
import { DecimalPipe, DatePipe } from '@angular/common';
import { UserCurrentGoal } from 'src/app/models/user-current-goal';
import { GoalService } from 'src/app/services/goal.service';
import { UserCurrentGoalService } from 'src/app/services/user-current-goal.service';



@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit, OnDestroy {
  constructor(
    private userSvc: UserService,
    private authSvc: AuthService,
    private router: Router,
    private postReplySvc: PostReplyService,
    private userAvatarSvc: UserAvatarService,
    private dailyCalorieSvc: DailyCaloricIntakeService,
    private dailyCalorieBurnSvc: DailyExerciseCaloricDeficitService,
    private mealTypeSvc: MealTypeService,
    private measurementConverterPipe: MeasurementConverterPipe,
    private decimalPipe: DecimalPipe,
    private imgSvc: ImageService,
    private bodyMeasurementSvc: BodyMeasurementMetricService,
    private goalSvc: GoalService,
    private userGoalSvc: UserCurrentGoalService,
    private datePipe: DatePipe
  ) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.currentAvatar = null;
        this.postsWithNewReplies = [];
        this.newCalorieRecord = false;
        this.weightLossUS = null;
        this.weightLossMetric = null;
        this.ngOnInit();
      }
    });
  }

  user: User;
  newUser: User = new User();
  userUpdate = false;
  userPassUpdate = false;
  allUsers: User[] = [];
  userCurrentGoal: Goal = new Goal();
  userMostCurrentGoalWeight: number;
  userMostCurrentWeight: number;
  userMostCurrentHeight: number;
  bmiAvatar: Avatar;
  currentAvatar: Avatar;
  postsWithNewReplies: Post[] = [];
  navigationSubscription;
  measurementSystem = 'US';
  caloricIntakeByDateMap = new Map<Date, number>();
  caloricDeficitByDateMap = new Map<Date, number>();
  caloricPerformanceByDate: [] = [];
  userImagesByDate = null;
  newCalorieRecord = false;
  newCalorieBurnRecord = false;
  mealTypes: MealType[] = [];
  caloricIntake = null;
  caloricDeficit = null;
  bodyMeasurement = null;
  newBodyMeasurement = false;
  bmi: number;
  bodyType: string;
  progressImageFront = null;
  progressImageSide = null;
  newprogressImages = false;
  progressImage = null;
  allGoals: Goal [] = [];
  userCurrGoal: UserCurrentGoal = new UserCurrentGoal();
  updateUCG = false;
  calcWeightLoss = false;
  calcWeightLossStart: Date;
  calcWeightLossEnd: Date;
  calTotal: number;
  weightLossUS: number;
  weightLossMetric: number;
  BMR: number;
  age: number;

  ngOnInit() {
    this.userSvc.getLoggedInUser().subscribe(
      data => {
        this.user = data;
        this.newUser.id = this.user.id;
        this.newUser.username = this.user.username;
        this.newUser.firstName = this.user.firstName;
        this.newUser.lastName = this.user.lastName;
        this.newUser.birthday = this.user.birthday;
        this.newUser.sex = this.user.sex;
        this.newUser.email = this.user.email;
        this.newUser.enabled = this.user.enabled;
        this.newUser.role = this.user.role;
        if (this.user.role === 'admin') {
          this.findAllUsers();
          this.getCurrentUserAvatar();
        }
        else {
          this.getAllGoals();
          this.getUserCurrentGoal();
          this.getBMIUserAvatar();
          this.getAge();
          this.getBMR();
          this.getNewPostReplies();
          this.getMealTypes();
          if (this.user.userDailyCaloricIntakes.length > 0) {
            this.caloricIntakeByDateMap = this.groupCaloricIntakeByDate(this.user.userDailyCaloricIntakes);
          }
          if (this.user.userDailyExerciseCaloricDeficits.length > 0) {
            this.caloricDeficitByDateMap = this.groupCaloricDeficitByDate(this.user.userDailyExerciseCaloricDeficits);
          }
          this.getCurrentUserAvatar();
          this.userImagesByDate = this.groupUserImagesByDate(this.user.userImages);
        }
      },
      err => console.error('In User Component getLoggedInUser Error')
    );
  }

  updateUserInfo() {
    // const newPasswordEntered = (typeof this.newUser.password !== 'undefined');
    this.userSvc.updateUser(this.newUser).subscribe(
      data => {
        this.user = data;
        this.userUpdate = false;
        if (this.userPassUpdate === true) {
          console.log('Username &/or Password changed: You must login again');
          this.authSvc.logout();
          this.router.navigateByUrl('/home');
        }
      },
      err => console.error('In User Component updateUserInfo Error')
    );
  }

  getMealTypes() {
    this.mealTypeSvc.getAllMealTypes().subscribe(
      data => {
        this.mealTypes = data;
      },
      err => console.error('In User Component getMealTypes Error')
    );
  }

  getBMIUserAvatar() {
    // // find BMI & use as initial avatar each time
    let maxMeasureDate = this.user.dateCreated;
    let i = 0;

    this.user.userBodyMeasurementMetrics.forEach((m, index) => {
      if (m.dateMeasured >= maxMeasureDate) {
        maxMeasureDate = m.dateMeasured;
        i = index;
      }
    });

    //find and set user's most current weight and goal weight
    this.userMostCurrentWeight = this.user.userBodyMeasurementMetrics[i].weightKg;
    this.userMostCurrentHeight = this.user.userBodyMeasurementMetrics[i].heightMM;
    this.userMostCurrentGoalWeight = this.user.userBodyMeasurementMetrics[i].goalWeightKg;

    const heightm = this.measurementConverterPipe.transform(
      this.user.userBodyMeasurementMetrics[i].heightMM,
      'mm',
      'm'
    );

    this.bmi =
      this.user.userBodyMeasurementMetrics[i].weightKg / (heightm * heightm);

    if (this.bmi < 18.5) {
      // thin avatar
      this.bodyType = 'Thin';
    } else if (this.bmi >= 18.5 && this.bmi <= 24.9) {
      // average avatar
      this.bodyType = 'Average';
    } else {
      // fat avatar
      this.bodyType = 'Fat';
    }

    // use bmi to find the user's initial avatar
    this.userAvatarSvc
      .updateCURRENTUserAvatar(this.bodyType, this.user.id)
      .subscribe(
        data => {
          this.bmiAvatar = data.avatar;
        },
        error => console.error('In User Component getBMIUserAvatar Error')
      );
  }

  getAge() {
    const today = new Date();
    const bday = new Date(this.user.birthday);
    this.age = today.getFullYear() - bday.getFullYear();
    const m = today.getMonth() - bday.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < bday.getDate())) {
      this.age--;
    }
  }

  getBMR() {
    if (this.user.sex === 'M') {
      // 10 * weight (kg) + 6.25 * height (cm) – 5 * age (y) + s (kcal / day) , where s is +5 for males and -161 for females.
      this.BMR = (10 * this.userMostCurrentWeight) + (6.25 * (this.userMostCurrentHeight / 10)) - (5 * this.age) + 5;
      this.BMR = Math.round(this.BMR);
    }
    if (this.user.sex === 'F') {
      // 10 * weight (kg) + 6.25 * height (cm) – 5 * age (y) + s (kcal / day) , where s is +5 for males and -161 for females.
      this.BMR = (10 * this.userMostCurrentWeight) + (6.25 * (this.userMostCurrentHeight / 10)) - (5 * this.age) + (-161);
      this.BMR = Math.round(this.BMR);
    }
  }

  getCurrentUserAvatar() {
    if (this.user.role === 'admin') {
      this.user.userAvatars.forEach(ua => {
        if (ua.current === true) {
          this.currentAvatar = ua.avatar;
        }
      });
    }

    else {
      // now use most recent caloric info to update current avatar
      let maxIntakeDate = this.user.dateCreated;
      let maxDeficitDate = this.user.dateCreated;

      for (const key of this.caloricIntakeByDateMap.keys()) {
        if (key >= maxIntakeDate) {
          maxIntakeDate = key;
        }
      }

      for (const key of this.caloricDeficitByDateMap.keys()) {
        if (key >= maxDeficitDate) {
          maxDeficitDate = key;
        }
      }

      // if user goal is weight loss...
      if (this.userCurrentGoal.goalName === 'Weight Loss') {
        // if the value at either is null, then don't change avatar
        if (
          this.caloricIntakeByDateMap.get(maxIntakeDate) == null ||
          this.caloricDeficitByDateMap.get(maxDeficitDate) == null
        ) {
          this.user.userAvatars.forEach(ua => {
            if (ua.current === true) {
              this.currentAvatar = ua.avatar;
            }
          });
        } else if (
          this.caloricIntakeByDateMap.get(maxIntakeDate) -
            this.caloricDeficitByDateMap.get(maxDeficitDate) ===
          0
        ) {
          this.user.userAvatars.forEach(ua => {
            if (ua.current === true) {
              this.currentAvatar = ua.avatar;
            }
          });
        } else if (
          this.caloricIntakeByDateMap.get(maxIntakeDate) -
            this.caloricDeficitByDateMap.get(maxDeficitDate) <
          0
        ) {

          if (this.bodyType === 'Fat') {
            this.userAvatarSvc
              .updateCURRENTUserAvatar('Average', this.user.id)
              .subscribe(
                data => {
                  this.currentAvatar = data.avatar;
                },
                error =>
                  console.error('In User Component getCurrentUserAvatar Error')
              );
          } else if (this.bodyType === 'Average') {
            this.userAvatarSvc
              .updateCURRENTUserAvatar('Thin', this.user.id)
              .subscribe(
                data => {
                  this.currentAvatar = data.avatar;
                },
                error =>
                  console.error('In User Component getCurrentUserAvatar Error')
              );
          }
          // if user avatar is already thin, do nothing
        } else if (
          this.caloricIntakeByDateMap.get(maxIntakeDate) -
            this.caloricDeficitByDateMap.get(maxDeficitDate) >
          0
        ) {
          if (this.bodyType === 'Thin') {
            this.userAvatarSvc
              .updateCURRENTUserAvatar('Average', this.user.id)
              .subscribe(
                data => {
                  this.currentAvatar = data.avatar;
                },
                error =>
                  console.error('In User Component getCurrentUserAvatar Error')
              );
          } else if (this.bodyType === 'Average') {
            this.userAvatarSvc
              .updateCURRENTUserAvatar('Fat', this.user.id)
              .subscribe(
                data => {
                  this.currentAvatar = data.avatar;
                },
                error =>
                  console.error('In User Component getCurrentUserAvatar Error')
              );
          }
          // if user avatar is already fat, do nothing
        }
      } else if (this.userCurrentGoal.goalName === 'Muscle Building') {
        console.log('add muscle building mod here');
      } else if (
        this.userCurrentGoal.goalName === 'General Fitness Maintanence'
      ) {
        console.log('add muscle building mod here');
      }
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

groupCaloricIntakeByDate(arr: any[]) {
    const res = new Map<Date, number>();
    let sum = 0;
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < arr.length; i++) {
      if (!res.has(arr[i].dateCreated)) {
        res.set(arr[i].dateCreated, arr[i].caloriesThisMeal);
      } else {
        sum = res.get(arr[i].dateCreated);
        sum += arr[i].caloriesThisMeal;
        res.set(arr[i].dateCreated, sum);
        sum = 0;
      }
    }
    return res;
  }

groupCaloricDeficitByDate(arr: any[]) {
    const res = new Map<Date, number>();
    let sum = 0;
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < arr.length; i++) {
      if (!res.has(arr[i].dateCreated)) {
        res.set(arr[i].dateCreated, arr[i].totalCaloriesBurned);
      } else {
        sum = res.get(arr[i].dateCreated);
        sum += arr[i].caloriesThisMeal;
        res.set(arr[i].dateCreated, sum);
        sum = 0;
      }
    }
    return res;
  }

groupUserImagesByDate(arr: any[]) {
    // this gives an object with dates as keys
    const groups = arr.reduce((groups, image) => {
      const date = image.dateCreated;
      if (!groups[date]) {
        groups[date] = [];
      }
      groups[date].push(image);
      return groups;
    }, {});

    // Edit: to add it in the array format instead
    const groupArrays = Object.keys(groups).map((date) => {
      return {
        date,
        image: groups[date]
      };
    });


    return groupArrays;
  }

addACaloricRecord() {
    this.caloricIntake = new DailyCaloricIntake();
    this.newCalorieRecord = true;
  }

addACaloricBurnRecord() {
    this.caloricDeficit = new DailyExerciseCaloricDeficit();
    this.caloricDeficit.totalCaloriesBurned = this.BMR;
    this.newCalorieBurnRecord = true;
  }

addNewBodyMeasurementRecord() {
    this.newBodyMeasurement = true;
    this.bodyMeasurement = new BodyMeasurementMetric();
  }

createBodyMeasurement() {
    if (this.measurementSystem === 'US') {
      this.convertBodyMeasurementsToMetric();
    }

    this.bodyMeasurementSvc.createBodyMeasurement(this.bodyMeasurement).subscribe(
      data => {
        this.newBodyMeasurement = false;
        this.bodyMeasurement = null;
        this.router.navigateByUrl('/users');
      },
      err => {
        console.error('In User Component createBodyMeasurement Error');
      }
      );
  }

setUpdateBodyMeasurmentRecord(toUpdate: BodyMeasurementMetric) {
    if (this.measurementSystem === 'metric') {
      this.bodyMeasurement = Object.assign({}, toUpdate);
    }
    else if (this.measurementSystem === 'US') {
      this.bodyMeasurement = Object.assign({}, toUpdate);
      this.convertBodyMeasurementsToUS();
    }
  }

convertBodyMeasurementsToUS() {
      this.bodyMeasurement.heightMM = this.measurementConverterPipe.transform(this.bodyMeasurement.heightMM, 'mm', 'in');
      this.bodyMeasurement.heightMM = this.decimalPipe.transform(this.bodyMeasurement.heightMM, '1.0-2');
      this.bodyMeasurement.weightKg = this.measurementConverterPipe.transform(this.bodyMeasurement.weightKg, 'kg', 'lb');
      this.bodyMeasurement.weightKg = this.decimalPipe.transform(this.bodyMeasurement.weightKg, '1.0-2');
      this.bodyMeasurement.goalWeightKg = this.measurementConverterPipe.transform(this.bodyMeasurement.goalWeightKg, 'kg', 'lb');
      this.bodyMeasurement.goalWeightKg = this.decimalPipe.transform(this.bodyMeasurement.goalWeightKg, '1.0-2');
      this.bodyMeasurement.waistMM = this.measurementConverterPipe.transform(this.bodyMeasurement.waistMM, 'mm', 'in');
      this.bodyMeasurement.waistMM = this.decimalPipe.transform(this.bodyMeasurement.waistMM, '1.0-2');

      if (this.bodyMeasurement.neckMM !== null) {
        this.bodyMeasurement.neckMM = this.measurementConverterPipe.transform(this.bodyMeasurement.neckMM, 'mm', 'in');
        this.bodyMeasurement.neckMM = this.decimalPipe.transform(this.bodyMeasurement.neckMM, '1.0-2');
      }
      if (this.bodyMeasurement.shouldersMM !== null) {
        this.bodyMeasurement.shouldersMM = this.measurementConverterPipe.transform(this.bodyMeasurement.shouldersMM, 'mm', 'in');
        this.bodyMeasurement.shouldersMM = this.decimalPipe.transform(this.bodyMeasurement.shouldersMM, '1.0-2');
      }
      if (this.bodyMeasurement.chestMM !== null) {
        this.bodyMeasurement.chestMM = this.measurementConverterPipe.transform(this.bodyMeasurement.chestMM, 'mm', 'in');
        this.bodyMeasurement.chestMM = this.decimalPipe.transform(this.bodyMeasurement.chestMM, '1.0-2');
      }
      if (this.bodyMeasurement.bicepMM !== null) {
        this.bodyMeasurement.bicepMM = this.measurementConverterPipe.transform(this.bodyMeasurement.bicepMM, 'mm', 'in');
        this.bodyMeasurement.bicepMM = this.decimalPipe.transform(this.bodyMeasurement.bicepMM, '1.0-2');
      }
      if (this.bodyMeasurement.hipsMM !== null) {
        this.bodyMeasurement.hipsMM = this.measurementConverterPipe.transform(this.bodyMeasurement.hipsMM, 'mm', 'in');
        this.bodyMeasurement.hipsMM = this.decimalPipe.transform(this.bodyMeasurement.hipsMM, '1.0-2');
      }
      if (this.bodyMeasurement.thighMM !== null) {
        this.bodyMeasurement.thighMM = this.measurementConverterPipe.transform(this.bodyMeasurement.thighMM, 'mm', 'in');
        this.bodyMeasurement.thighMM = this.decimalPipe.transform(this.bodyMeasurement.thighMM, '1.0-2');
      }
  }

convertBodyMeasurementsToMetric() {
      this.bodyMeasurement.heightMM = this.measurementConverterPipe.transform(this.bodyMeasurement.heightMM, 'in', 'mm');
      this.bodyMeasurement.weightKg = this.measurementConverterPipe.transform(this.bodyMeasurement.weightKg, 'lb', 'kg');
      this.bodyMeasurement.weightKg = this.decimalPipe.transform(this.bodyMeasurement.weightKg, '1.0-2');
      this.bodyMeasurement.goalWeightKg = this.measurementConverterPipe.transform(this.bodyMeasurement.goalWeightKg, 'lb', 'kg');
      this.bodyMeasurement.goalWeightKg = this.decimalPipe.transform(this.bodyMeasurement.goalWeightKg, '1.0-2');
      this.bodyMeasurement.waistMM = this.measurementConverterPipe.transform(this.bodyMeasurement.waistMM, 'in', 'mm');

      if (this.bodyMeasurement.neckMM !== null) {
        this.bodyMeasurement.neckMM = this.measurementConverterPipe.transform(this.bodyMeasurement.neckMM, 'in', 'mm');
      }
      if (this.bodyMeasurement.shouldersMM !== null) {
        this.bodyMeasurement.shouldersMM = this.measurementConverterPipe.transform(this.bodyMeasurement.shouldersMM, 'in', 'mm');
      }
      if (this.bodyMeasurement.chestMM !== null) {
        this.bodyMeasurement.chestMM = this.measurementConverterPipe.transform(this.bodyMeasurement.chestMM, 'in', 'mm');
      }
      if (this.bodyMeasurement.bicepMM !== null) {
        this.bodyMeasurement.bicepMM = this.measurementConverterPipe.transform(this.bodyMeasurement.bicepMM, 'in', 'mm');
      }
      if (this.bodyMeasurement.hipsMM !== null) {
        this.bodyMeasurement.hipsMM = this.measurementConverterPipe.transform(this.bodyMeasurement.hipsMM, 'in', 'mm');
      }
      if (this.bodyMeasurement.thighMM !== null) {
        this.bodyMeasurement.thighMM = this.measurementConverterPipe.transform(this.bodyMeasurement.thighMM, 'in', 'mm');
      }
  }

updateBodyMeasurmentRecord() {
    if (this.measurementSystem === 'US') {
      this.convertBodyMeasurementsToMetric();
    }

    // tslint:disable-next-line: max-line-length
    if (confirm('Are you sure you want to UPDATE the Body Measurement for ' + this.bodyMeasurement.dateMeasured + '?')) {
    this.bodyMeasurementSvc.updateBodyMeasurement(this.bodyMeasurement).subscribe(
      data => {
        this.bodyMeasurement = null;
      },
        err => console.error('In User Component updateBodyMeasurmentRecord Error')
      );
  }
    this.router.navigateByUrl('/users');
  }

deleteBodyMeasurmentRecord(toDelete: Image) {
    // tslint:disable-next-line: max-line-length
    if (confirm('Are you sure you want to DELETE the Body Measurement for ' + this.bodyMeasurement.dateMeasured + '?')) {
      this.bodyMeasurementSvc.deleteBodyMeasurement(toDelete.id).subscribe(
        data => {

        },
        err => console.error('In User Component deleteBodyMeasurmentRecord Error')
      );
    }
    this.router.navigateByUrl('/users');
  }

addCalories() {
    this.dailyCalorieSvc.createDailyCaloricIntake(this.caloricIntake).subscribe(
      data => {
        this.newCalorieRecord = false;
        this.caloricIntake = null;
        this.router.navigateByUrl('/users');
      },
      err => console.error('In User Component addCalories Error')
    );
  }

addCalorieBurn() {
    this.dailyCalorieBurnSvc.createDailyExerciseCaloricDeficit(this.caloricDeficit).subscribe(
      data => {
        this.newCalorieBurnRecord = false;
        this.caloricDeficit = null;
        this.router.navigateByUrl('/users');
      },
      err => console.error('In User Component addCalories Error')
    );
  }

addAProgressImage() {
    this.progressImageFront = new Image();
    this.progressImageSide = new Image();
    this.newprogressImages = true;
  }

createNewProgressImages() {
    if(this.progressImageFront.imageUrl !== null && this.progressImageSide.imageUrl !== null) {
      //front facing image
      this.imgSvc.createImage(this.progressImageFront).subscribe(
        data => {
          //side facing image
          this.imgSvc.createImage(this.progressImageSide).subscribe(
          data => {
            this.progressImageFront = null;
            this.progressImageSide = null;
            this.newprogressImages = false;
            this.router.navigateByUrl('/users');
          },
          err => console.error('In User Component createNewProgressImages (SIDE FACING) Error')
        );
        },
        err => console.error('In User Component createNewProgressImages (FRONT FACING) Error')
      );
    }
    else if (this.progressImageFront.imageUrl !== null && this.progressImageSide.imageUrl === null) {
      //front facing image
      this.imgSvc.createImage(this.progressImageFront).subscribe(
        data => {
            this.progressImageFront = null;
            this.progressImageSide = null;
            this.newprogressImages = false;
            this.router.navigateByUrl('/users');
        },
        err => console.error('In User Component createNewProgressImages (FRONT FACING) Error')
      );
    }
    else if (this.progressImageFront.imageUrl === null && this.progressImageSide.imageUrl !== null) {
      //side facing image
      this.imgSvc.createImage(this.progressImageSide).subscribe(
        data => {
            this.progressImageFront = null;
            this.progressImageSide = null;
            this.newprogressImages = false;
            this.router.navigateByUrl('/users');
        },
        err => console.error('In User Component createNewProgressImages (SIDE FACING) Error')
      );
    }
  }

setUpdateAProgressImage(toUpdate: Image) {
    this.progressImage = Object.assign({}, toUpdate);
  }

updateAProgressImage() {
    // tslint:disable-next-line: max-line-length
    if (confirm('Are you sure you want to UPDATE the Progress Image for ' + this.progressImage.dateCreated + '?')) {
      this.imgSvc.updateImage(this.progressImage).subscribe(
        data => {
          this.progressImage = null;
        },
        err => console.error('In User Component updateAProgressImage Error')
      );
    }
    this.router.navigateByUrl('/users');
  }

deleteAProgressImage(toDelete: Image) {
    // tslint:disable-next-line: max-line-length
    if (confirm('Are you sure you want to DELETE the Progress Image for ' + toDelete.dateCreated + '?')) {
      this.imgSvc.deleteImage(toDelete.id).subscribe(
        data => {

        },
        err => console.error('In User Component deleteCalories Error')
      );
    }
    this.router.navigateByUrl('/users');
  }

setUpdateCalorieRecord(toUpdate: DailyCaloricIntake) {
    this.caloricIntake = Object.assign({}, toUpdate);
  }

setUpdateCalorieBurnRecord(toUpdate: DailyExerciseCaloricDeficit) {
    this.caloricDeficit = Object.assign({}, toUpdate);
  }

updateCalories() {
    // tslint:disable-next-line: max-line-length
    if (confirm('Are you sure you want to UPDATE the Calorie Intake record for ' + this.caloricIntake.mealType.mealTypeName + ' on ' + this.caloricIntake.dateCreated + '?')) {
      this.dailyCalorieSvc.updateDailyCaloricIntake(this.caloricIntake).subscribe(
        data => {
          this.caloricIntake = null;
        },
        err => console.error('In User Component updateCalories Error')
      );
    }
    this.router.navigateByUrl('/users');
  }

updateCalorieBurn() {
    // tslint:disable-next-line: max-line-length
    if (confirm('Are you sure you want to UPDATE the Calorie Deficit record for ' + this.caloricDeficit.activityDescription + ' on ' + this.caloricDeficit.dateCreated + '?')) {
      this.dailyCalorieBurnSvc.updateDailyExerciseCaloricDeficit(this.caloricDeficit).subscribe(
        data => {
          this.caloricDeficit = null;
        },
        err => console.error('In User Component updateCalorieBurn Error')
      );
    }
    this.router.navigateByUrl('/users');
  }

deleteCalories(toDelete: DailyCaloricIntake) {
    // tslint:disable-next-line: max-line-length
    if (confirm('Are you sure you want to DELETE the Calorie Intake record for ' + toDelete.mealType.mealTypeName + ' on ' + toDelete.dateCreated + '?')) {
      this.dailyCalorieSvc.deleteDailyCaloricIntake(toDelete.id).subscribe(
        data => {

        },
        err => console.error('In User Component deleteCalories Error')
      );
    }
    this.router.navigateByUrl('/users');
  }

deleteCalorieBurn(toDelete: DailyExerciseCaloricDeficit) {
    // tslint:disable-next-line: max-line-length
    if (confirm('Are you sure you want to DELETE the Calorie Deficit record for ' + toDelete.activityDescription + ' on ' + toDelete.dateCreated + '?')) {
      this.dailyCalorieBurnSvc.deleteDailyExerciseCaloricDeficit(toDelete.id).subscribe(
        data => {

        },
        err => console.error('In User Component deleteCalories Error')
      );
    }
    this.router.navigateByUrl('/users');
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
        this.router.navigateByUrl('/users');
      },
      err => console.error('In User Component disableUser Error')
    );
  }

enableUser(u: User) {
    this.userSvc.enableUser(u.id).subscribe(
      data => {
        this.router.navigateByUrl('/users');
      },
      err => console.error('In User Component enableUser Error')
    );
  }

getAllGoals() {
    this.goalSvc.getAllGoals().subscribe(
      data => {
        this.allGoals = data;
      },
      err => {
        console.log('In User Component, Error gettingAllGoals');
      }
    );
  }

updateUserGoal() {
    this.user.userCurrentGoals.forEach(g => {
      if (g.enabled === true) {
        this.userCurrGoal.id = g.id;
      }
    });
    this.userCurrGoal.user = this.user;
    this.userCurrGoal.enabled = true;
    this.userGoalSvc.updateUserCurrentGoal(this.userCurrGoal).subscribe(
      data => {
        this.updateUCG = false;
        this.userCurrGoal = null;
      },
      err => {
        console.log('In User Component, Error creatingUserCurrentGoal');
      }
    );
  }

calculateWeightLoss() {
    let calIn = 0;
    let calOut = 0;

    // GET CALORIES EATEN DURING THE GIVEN TIMEFRAME
    for(let i = 0; i < this.user.userDailyCaloricIntakes.length; i++) {
      if (this.checkDateInRange(this.user.userDailyCaloricIntakes[i].dateCreated, this.calcWeightLossStart, this.calcWeightLossEnd)) {
        calIn += this.user.userDailyCaloricIntakes[i].caloriesThisMeal;
      }
    }

    // GET CALORIES EATEN DURING THE GIVEN TIMEFRAME
    for(let i = 0; i < this.user.userDailyExerciseCaloricDeficits.length; i++) {
      if (this.checkDateInRange(this.user.userDailyExerciseCaloricDeficits[i].dateCreated, this.calcWeightLossStart, this.calcWeightLossEnd)) {
        calOut += this.user.userDailyExerciseCaloricDeficits[i].totalCaloriesBurned;
      }
    }
    this.calTotal = calIn - calOut;
    this.weightLossUS = this.calTotal / 3500;
    this.weightLossMetric = this.calTotal / 7700;
  }

checkDateInRange(dateToCheck: any, minInput: Date, maxInput: Date): boolean {
    if(minInput <= dateToCheck && dateToCheck <= maxInput){
        return true;
    } else {
      return false;
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
}
