import { User } from "./user";

export class Image {
  id: number;
  user: User;
  imageUrl: string;
  dateCreated: Date;
  dateUpdated: Date;

  constructor(
    id?: number,
    user?: User,
    imageUrl?: string,
    dateCreated?: Date,
    dateUpdated?: Date
  ) {
    this.id = id;
    this.user = user;
    this.imageUrl = imageUrl;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
