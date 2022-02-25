import { Comment } from "./Comment";
import { Content } from "./Content";
import { User } from "./User";
import { UserProfile } from "./UserProfile";

export class Post extends Content
{

    private _comments: Comment[];
    private _underscoredTitle: string;
    constructor(id: number, title: string, text: string, markdown: string, releaseDate: Date, lastEditDate: Date,
        hasBeenEdited: boolean, profile: UserProfile, authorName: string, comments: Comment[], underscoredTitle: string) 
    {
        super(id, title, text, markdown, releaseDate, lastEditDate, hasBeenEdited, profile, authorName);
        this._comments = comments;
        this._underscoredTitle = underscoredTitle;
    }
    static getEmptyPost(): Post
    {
        let date = new Date();
        return new Post(0, "Test", "This is my test text", "This is my **test** text", date, date, false, User.getDefaultUser().profile, "Guest", [], "Test");
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
     * Getter underscoredTitle
     * @return {string}
     */
    public get underscoredTitle(): string
    {
        if (this._underscoredTitle == null)
            return this.title.replace(" ", "/");
        return this._underscoredTitle;
    }

    /**
     * Setter underscoredTitle
     * @param {string} value
     */
    public set underscoredTitle(value: string)
    {
        this._underscoredTitle = value;
    }


}