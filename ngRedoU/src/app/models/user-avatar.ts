import { Avatar } from "./avatar";
import { User } from "./user";
export class UserAvatar {
  id: number;
  user: User;
  avatar: Avatar;
  current: boolean;
  dateCreated: Date;
  dateUpdated: Date;

  constructor(
    id?: number,
    user?: User,
    avatar?: Avatar,
    current?: boolean,
    dateCreated?: Date,
    dateUpdated?: Date
  ) {
    this.id = id;
    this.user = user;
    this.avatar = avatar;
    this.current = current;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
