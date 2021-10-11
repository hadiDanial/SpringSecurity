export class Role
{
    private _roleName: string;
    private _roleIdentifier: string;


	constructor(roleName: string, roleIdentifier: string) {
		this._roleName = roleName;
		this._roleIdentifier = roleIdentifier;
	}

	public get roleName(): string {
		return this._roleName;
	}

	public get roleIdentifier(): string {
		return this._roleIdentifier;
	}

	public set roleName(value: string) {
		this._roleName = value;
	}

	public set roleIdentifier(value: string) {
		this._roleIdentifier = value;
	}

}