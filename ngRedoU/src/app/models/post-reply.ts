import { Post } from "./post";
import { User } from "./user";
export class PostReply {
  id: number;
  replyUser: User;
  originalPost: Post;
  replyToReply: PostReply;
  repliesToPostReply: PostReply[];
  replyContent: string;
  dateCreated: Date;
  dateUpdated: Date;

  constructor(
    id?: number,
    replyUser?: User,
    originalPost?: Post,
    replyToReply?: PostReply,
    repliesToPostReply?: PostReply[],
    replyContent?: string,
    dateCreated?: Date,
    dateUpdated?: Date
  ) {
    this.id = id;
    this.replyUser = replyUser;
    this.originalPost = originalPost;
    this.replyToReply = replyToReply;
    this.repliesToPostReply = repliesToPostReply;
    this.replyContent = replyContent;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
