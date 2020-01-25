import { User } from "./user";
export class DailyExerciseCaloricDeficit {
  id: number;
  dateCreated: Date;
  dateUpdated: Date;
  totalCaloriesBurned: number;
  activityDescription: string;
  user: User;

  constructor(
    id?: number,
    dateCreated?: Date,
    dateUpdated?: Date,
    totalCaloriesBurned?: number,
    activityDescription?: string,
    user?: User
  ) {
    this.id = id;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
    this.totalCaloriesBurned = totalCaloriesBurned;
    this.activityDescription = activityDescription;
    this.user = user;
  }
}
