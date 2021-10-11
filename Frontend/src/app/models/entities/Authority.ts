export class Authority
{
    private _authorityName: string;
    private _authorityIdentifier: string;


	constructor(authorityName: string, authorityIdentifier: string) {
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
    
}