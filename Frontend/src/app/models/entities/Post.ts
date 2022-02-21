import { Comment } from "./Comment";
import { Content } from "./Content";
import { UserProfile } from "./UserProfile";

export class Post extends Content
{
    private _comments: Comment[];

	constructor(id:  number, title:  string, text:  string, markdown:  string, releaseDate:  Date, lastEditDate:  Date, 
        hasBeenEdited:  boolean, profile:  UserProfile, comments: Comment[]) 
    {	
        super(id, title, text, markdown, releaseDate, lastEditDate, hasBeenEdited, profile);
        this._comments = comments;
	}


    /**
     * Getter comments
     * @return {Comment[]}
     */
	public get comments(): Comment[] {
		return this._comments;
	}

    /**
     * Setter comments
     * @param {Comment[]} value
     */
	public set comments(value: Comment[]) {
		this._comments = value;
	}

}