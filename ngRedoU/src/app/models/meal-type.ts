import { DailyCaloricIntake } from "./daily-caloric-intake";
export class MealType {
  id: number;
  mealTypeName: string;
  dateCreated: Date;
  dateUpated: Date;
  dailyCaloricIntakesMealTypes: DailyCaloricIntake[];

  constructor(
    id: number,
    mealTypeName: string,
    dateCreated: Date,
    dateUpated: Date,
    dailyCaloricIntakesMealTypes: DailyCaloricIntake[]
  ) {
    this.id = id;
    this.mealTypeName = mealTypeName;
    this.dateCreated = dateCreated;
    this.dateUpated = dateUpated;
    this.dailyCaloricIntakesMealTypes = dailyCaloricIntakesMealTypes;
  }
}
