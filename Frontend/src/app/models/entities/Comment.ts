import { Content } from "./Content";
import { Post } from "./Post";
import { User } from "./User";
import { UserProfile } from "./UserProfile";

export class Comment extends Content
{
    private _post: Post;
    private _replyTo: Comment | undefined;
    private _replies: Comment[];


    constructor(id: number, title: string, text: string, markdown: string, releaseDate: Date, lastEditDate: Date,
        hasBeenEdited: boolean, profile: UserProfile, authorName: string, post: Post, replyTo?: Comment, replies?: Comment[])
    {
        super(id, title, text, markdown, releaseDate, lastEditDate, hasBeenEdited, profile, authorName);
        this._post = post;
        this._replyTo = replyTo;
        if (replies != undefined)
            this._replies = replies;
        else
            this._replies = [];
    }

    static getEmptyComment(): Comment
    {
        return new Comment(0, "Comment", "Text1234", "Text1234", new Date(), new Date(), false, User.getDefaultUser().profile, "User", Post.getEmptyPost(), undefined, [])
    }
    /**
 * Getter post
 * @return {Post}
 */
    public get post(): Post
    {
        return this._post;
    }

    /**
     * Setter post
     * @param {Post} value
     */
    public set post(value: Post)
    {
        this._post = value;
    }

    /**
     * Getter replyTo
     * @return {Comment}
     */
    public get replyTo(): Comment
    {
        if (this._replyTo == undefined)
            return Comment.getEmptyComment();
        return this._replyTo;
    }

    /**
     * Setter replyTo
     * @param {Comment} value
     */
    public set replyTo(value: Comment)
    {
        this._replyTo = value;
    }

    /**
     * Getter replies
     * @return {Comment[]}
     */
    public get replies(): Comment[]
    {
        return this._replies;
    }

    /**
     * Setter replies
     * @param {Comment[]} value
     */
    public set replies(value: Comment[])
    {
        this._replies = value;
    }

}