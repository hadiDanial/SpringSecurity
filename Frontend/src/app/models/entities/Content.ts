import { UserProfile } from "./UserProfile";

export class Content
{
    private _id:  number;
	private _title:  string;
	private _text:  string;
	private _markdown:  string;
	private _releaseDate:  Date;
	private _lastEditDate:  Date;
	private _hasBeenEdited:  boolean;
	private _profile:  UserProfile;


	constructor(id:  number, title:  string, text:  string, markdown:  string, releaseDate:  Date, lastEditDate:  Date, hasBeenEdited:  boolean, profile:  UserProfile) {
		this._id = id;
		this._title = title;
		this._text = text;
		this._markdown = markdown;
		this._releaseDate = releaseDate;
		this._lastEditDate = lastEditDate;
		this._hasBeenEdited = hasBeenEdited;
		this._profile = profile;
	}


    /**
     * Getter id
     * @return { number}
     */
	public get id():  number {
		return this._id;
	}

    /**
     * Setter id
     * @param { number} value
     */
	public set id(value:  number) {
		this._id = value;
	}

    /**
     * Getter title
     * @return { string}
     */
	public get title():  string {
		return this._title;
	}

    /**
     * Setter title
     * @param { string} value
     */
	public set title(value:  string) {
		this._title = value;
	}

    /**
     * Getter text
     * @return { string}
     */
	public get text():  string {
		return this._text;
	}

    /**
     * Setter text
     * @param { string} value
     */
	public set text(value:  string) {
		this._text = value;
	}

    /**
     * Getter markdown
     * @return { string}
     */
	public get markdown():  string {
		return this._markdown;
	}

    /**
     * Setter markdown
     * @param { string} value
     */
	public set markdown(value:  string) {
		this._markdown = value;
	}

    /**
     * Getter releaseDate
     * @return { Date}
     */
	public get releaseDate():  Date {
		return this._releaseDate;
	}

    /**
     * Setter releaseDate
     * @param { Date} value
     */
	public set releaseDate(value:  Date) {
		this._releaseDate = value;
	}

    /**
     * Getter lastEditDate
     * @return { Date}
     */
	public get lastEditDate():  Date {
		return this._lastEditDate;
	}

    /**
     * Setter lastEditDate
     * @param { Date} value
     */
	public set lastEditDate(value:  Date) {
		this._lastEditDate = value;
	}

    /**
     * Getter hasBeenEdited
     * @return { boolean}
     */
	public get hasBeenEdited():  boolean {
		return this._hasBeenEdited;
	}

    /**
     * Setter hasBeenEdited
     * @param { boolean} value
     */
	public set hasBeenEdited(value:  boolean) {
		this._hasBeenEdited = value;
	}

    /**
     * Getter profile
     * @return { UserProfile}
     */
	public get profile():  UserProfile {
		return this._profile;
	}

    /**
     * Setter profile
     * @param { UserProfile} value
     */
	public set profile(value:  UserProfile) {
		this._profile = value;
	}


}