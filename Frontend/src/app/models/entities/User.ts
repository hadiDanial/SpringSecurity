import { Name } from "../other/Name";
import { Role } from "./Role";

export class User
{
    private static defaultUser = new User(0, "Guest", "",new Name("Guest",""), []);
    private _id: number;
    private _username: string;
    private _email: string;
    private _name: Name;
    private _roles: Role[];

    constructor(id: number, username: string, email: string, name: Name, roles: Role[]) 
    {
        this._id = id;
        this._username = username;
        this._email = email;
        this._name = name;
        this._roles = roles;
    }
    public get id(): number
    {
        return this._id;
    }
    public get username(): string
    {
        return this._username;
    }
    
    public get email(): string
    {
        return this._email;
    }
    
    public get name() : Name
    {
        return this._name;
    }
    
    public set username(username:string)
    {
        this._username = username;
    }
    
    public set email(email: string)
    {
        this._email = email;
    }
    
    public set name(name: Name)
    {
        this._name = name;
    }
    
    public setName(firstName: string, lastName: string, middleName?: string)
    {
        let name: Name = new Name(firstName,lastName, middleName);
        this._name = name;
    }
    public getName():string
    {
        return this.name.toString();
    }

	public get roles(): Role[]  {
		return this._roles;
	}

	public set roles(value: Role[] ) {
		this._roles = value;
	}

    static getDefaultUser(): User
    {
      return this.defaultUser;
    }

    public equals(other: User)
    {
      return this._username == other._username;
    }
}