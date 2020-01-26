import { Goal } from "./goal";
import { User } from "./user";

export class UserCurrentGoal {
  id: number;
  user: User;
  goal: Goal;
  enabled: boolean;
  dateCreated: Date;
  dateUpdated: Date;

  constructor(
    id?: number,
    user?: User,
    goal?: Goal,
    enabled?: boolean,
    dateCreated?: Date,
    dateUpdated?: Date
  ) {
    this.id = id;
    this.user = user;
    this.goal = goal;
    this.enabled = enabled;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
