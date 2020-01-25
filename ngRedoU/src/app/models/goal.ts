import { UserCurrentGoal } from "./user-current-goal";
export class Goal {
  id: number;
  goalName: string;
  dateCreated: Date;
  dateUpdated: Date;
  userCurrentGoals: UserCurrentGoal[];

  constructor(
    id?: number,
    goalName?: string,
    dateCreated?: Date,
    dateUpdated?: Date,
    userCurrentGoals?: UserCurrentGoal[]
  ) {
    this.id = id;
    this.goalName = goalName;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
    this.userCurrentGoals = userCurrentGoals;
  }
}
