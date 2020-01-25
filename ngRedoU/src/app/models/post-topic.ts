import { Post } from "./post";
export class PostTopic {
  id: number;
  topicName: string;
  dateCreated: Date;
  dateUpdated: Date;
  posts: Post[];

  constructor(
    id?: number,
    topicName?: string,
    dateCreated?: Date,
    dateUpdated?: Date,
    posts?: Post[]
  ) {
    this.id = id;
    this.topicName = topicName;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
