import { Post } from "./Post";
import { Comment } from "./Comment"
import { User } from "./User";

export class UserProfile
{
    private _about: string;
    private _comments: Comment[];
    private _posts: Post[];
    private _user: User | undefined;
    private _profileImage: Blob | undefined;


    constructor(about: string, comments: Comment[], posts: Post[], user?: User, profileImage?: Blob)
    {
        this._about = about;
        this._comments = comments;
        this._posts = posts;
        this._user = user;
        this._profileImage = profileImage;
    }


    /**
     * Getter about
     * @return {string}
     */
    public get about(): string
    {
        return this._about;
    }

    /**
     * Setter about
     * @param {string} value
     */
    public set about(value: string)
    {
        this._about = value;
    }

    /**
     * Getter comments
     * @return {Comment[]}
     */
    public get comments(): Comment[]
    {
        return this._comments;
    }

    /**
     * Setter comments
     * @param {Comment[]} value
     */
    public set comments(value: Comment[])
    {
        this._comments = value;
    }

    /**
     * Getter posts
     * @return {Post[]}
     */
    public get posts(): Post[]
    {
        return this._posts;
    }

    /**
     * Setter posts
     * @param {Post[]} value
     */
    public set posts(value: Post[])
    {
        this._posts = value;
    }

    /**
     * Getter user
     * @return {User }
     */
    public get user(): User
    {
        if (this._user == undefined) 
            return User.getDefaultUser();
        else
            return this._user;
    }

    /**
     * Setter user
     * @param {User } value
     */
    public set user(value: User)
    {
        this._user = value;
    }

    /**
     * Setter profileImage
     * @param {Blob } value
     */
    public set profileImage(value: Blob)
    {
        this._profileImage = value;
    }

}