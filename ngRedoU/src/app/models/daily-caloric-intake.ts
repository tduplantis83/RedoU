import { User } from "./user";
import { MealType } from "./meal-type";
export class DailyCaloricIntake {
  id: number;
  mealType: MealType;
  dateCreated: Date;
  dateUpdated: Date;
  caloriesThisMeal: number;
  mealDescription: string;
  user: User;
  constructor(
    id?: number,
    mealType?: MealType,
    dateCreated?: Date,
    dateUpdated?: Date,
    caloriesThisMeal?: number,
    mealDescription?: string,
    user?: User
  ) {
    this.id = id;
    this.mealType = mealType;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
    this.caloriesThisMeal = caloriesThisMeal;
    this.mealDescription = mealDescription;
    this.user = user;
  }
}
