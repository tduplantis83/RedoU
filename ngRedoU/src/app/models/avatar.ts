import { UserAvatar } from "./user-avatar";

export class Avatar {
  id: number;
  avatarGroup: number;
  bodyType: string;
  avatarUrl: string;
  dateCreated: Date;
  dateUpdated: Date;
  userAvatars: UserAvatar[];

  constructor(
    id?: number,
    avatarGroup?: number,
    bodyType?: string,
    avatarUrl?: string,
    dateCreated?: Date,
    dateUpdated?: Date,
    userAvatars?: UserAvatar[]
  ) {
    this.id = id;
    this.avatarGroup = avatarGroup;
    this.bodyType = bodyType;
    this.avatarUrl = avatarUrl;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
    this.userAvatars = userAvatars;
  }
}
