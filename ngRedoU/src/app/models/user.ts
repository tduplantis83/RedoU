import { PostReply } from "./post-reply";
import { Post } from "./post";
import { BodyMeasurementMetric } from "./body-measurement-metric";
import { UserAvatar } from "./user-avatar";
import { Image } from "./image";
import { DailyExerciseCaloricDeficit } from "./daily-exercise-caloric-deficit";
import { DailyCaloricIntake } from "./daily-caloric-intake";
import { UserCurrentGoal } from "./user-current-goal";
export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  birthday: Date;
  sex: string;
  email: string;
  enabled: boolean;
  role: string;
  dateCreated: Date;
  dateUpdated: Date;
  userCurrentGoals: UserCurrentGoal[];
  userDailyCaloricIntakes: DailyCaloricIntake[];
  userDailyExerciseCaloricDeficits: DailyExerciseCaloricDeficit[];
  userImages: Image[];
  userAvatars: UserAvatar[];
  userBodyMeasurementMetrics: BodyMeasurementMetric[];
  userPosts: Post[];
  userPostReplies: PostReply[];

  constructor(
    id?: number,
    username?: string,
    password?: string,
    firstName?: string,
    lastName?: string,
    birthday?: Date,
    sex?: string,
    email?: string,
    enabled?: boolean,
    role?: string,
    dateCreated?: Date,
    dateUpdated?: Date,
    userCurrentGoals?: UserCurrentGoal[],
    userDailyCaloricIntakes?: DailyCaloricIntake[],
    userDailyExerciseCaloricDeficits?: DailyExerciseCaloricDeficit[],
    userImages?: Image[],
    userAvatars?: UserAvatar[],
    userBodyMeasurementMetrics?: BodyMeasurementMetric[],
    userPosts?: Post[],
    userPostReplies?: PostReply[]
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.sex = sex;
    this.email = email;
    this.enabled = enabled;
    this.role = role;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
    this.userCurrentGoals = userCurrentGoals;
    this.userDailyCaloricIntakes = userDailyCaloricIntakes;
    this.userDailyExerciseCaloricDeficits = userDailyExerciseCaloricDeficits;
    this.userImages = userImages;
    this.userAvatars = userAvatars;
    this.userBodyMeasurementMetrics = userBodyMeasurementMetrics;
    this.userPosts = userPosts;
    this.userPostReplies = userPostReplies;
  }
}
