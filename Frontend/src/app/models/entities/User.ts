import { first } from "rxjs/operators";
import { Name } from "../other/Name";

export class User
{
    private _username: string;
    private _email: string;
    private _name: Name;

    constructor(username: string, email: string, name: Name) 
    {
        this._username = username;
        this._email = email;
        this._name = name;
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
}