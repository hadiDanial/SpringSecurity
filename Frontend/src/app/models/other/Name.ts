export class Name
{
    private _firstName: string;
    private _lastName: string;
    private _middleName: string;

    constructor(firstName: string, lastName: string, middleName?: string)
    {
        this._firstName = firstName;
        this._lastName = lastName;
        if (middleName != null && middleName != undefined)
        {
            this._middleName = middleName;
        }
        else
        {
            this._middleName = "";
        }
    }

    public get firstName(): string
    {
        return this._firstName;
    }

    public set firstName(value: string)
    {
        this._firstName = value;
    }

    public get lastName(): string
    {
        return this._lastName;
    }

    public set lastName(value: string)
    {
        this._lastName = value;
    }

    public get middleName(): string
    {
        return this._middleName;
    }

    public set middleName(value: string)
    {
        this._middleName = value;
    }

    public toString():string
    {
        return this.firstName + " " + ((this.middleName!= undefined)? " " + this.middleName + " " : "") + this.lastName;
    }
}