export class Authority
{
	private _id: number;
    private _authorityName: string;
    private _authorityIdentifier: string;

	
	constructor(id: number, authorityName: string, authorityIdentifier: string) {
		this._id = id;
		this._authorityName = authorityName;
		this._authorityIdentifier = authorityIdentifier;
	}
	
	public get authorityName(): string {
		return this._authorityName;
	}

	public get authorityIdentifier(): string {
		return this._authorityIdentifier;
	}

	public set authorityName(value: string) {
		this._authorityName = value;
	}

	public set authorityIdentifier(value: string) {
		this._authorityIdentifier = value;
	}
    
	public get id(): number {
		return this._id;
	}
}