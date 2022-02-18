import { Authority } from "./Authority";

export class Role
{
	private _id: number;
    private _roleName: string;
    private _roleIdentifier: string;
	private _authorities: Authority[];


	constructor(id:number, roleName: string, roleIdentifier: string, authorities: Authority[]) {
		this._roleName = roleName;
		this._roleIdentifier = roleIdentifier;
		this._id = id;
		this._authorities = authorities;
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

	public get id(): number {
		return this._id;
	}

	public get authorities(): Authority[] {
		return this._authorities;
	}

	public set authorities(value: Authority[]) {
		this._authorities = value;
	}

}