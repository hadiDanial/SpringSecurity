import { Post } from "./Post";
import { Comment } from "./Comment"

export class UserProfile
{
    private _about: string;
    private _comments: Comment[];
    private _posts: Post[];
    private _profileImage: Blob | undefined;


	constructor(about: string, comments: Comment[], posts: Post[], profileImage?: Blob) {
		this._about = about;
		this._comments = comments;
		this._posts = posts;
		this._profileImage = profileImage;
	}


    /**
     * Getter about
     * @return {string}
     */
	public get about(): string {
		return this._about;
	}

    /**
     * Setter about
     * @param {string} value
     */
	public set about(value: string) {
		this._about = value;
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

    /**
     * Getter posts
     * @return {Post[]}
     */
	public get posts(): Post[] {
		return this._posts;
	}

    /**
     * Setter posts
     * @param {Post[]} value
     */
	public set posts(value: Post[]) {
		this._posts = value;
	}

    /**
     * Setter profileImage
     * @param {Blob } value
     */
	public set profileImage(value: Blob ) {
		this._profileImage = value;
	}

}