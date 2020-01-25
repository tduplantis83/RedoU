import { PostReply } from "./post-reply";
import { PostTopic } from "./post-topic";
import { User } from "./user";
export class Post {
  id: number;
  user: User;
  postTopic: PostTopic;
  title: string;
  content: string;
  dateCreated: Date;
  dateUpdated: Date;
  originalPostReplies: PostReply[];

  constructor(
    id?: number,
    user?: User,
    postTopic?: PostTopic,
    title?: string,
    content?: string,
    dateCreated?: Date,
    dateUpdated?: Date,
    originalPostReplies?: PostReply[]
  ) {
    this.id = id;
    this.user = user;
    this.postTopic = postTopic;
    this.title = title;
    this.content = content;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
    this.originalPostReplies = originalPostReplies;
  }
}
