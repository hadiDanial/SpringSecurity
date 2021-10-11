import { Token } from "../entities/Token";
import { User } from "../entities/User";

export class LoginResponse
{
    private _token: Token;
    private _user: User;
    private message: string;

    constructor(token: Token, user: User, $message: string)
    {
        this._token = token;
        this._user = user;
        this.message = $message;
    }

    public get token(): Token
    {
        return this._token;
    }

    public get user(): User
    {
        return this._user;
    }

    public get $message(): string
    {
        return this.message;
    }

    public set token(value: Token)
    {
        this._token = value;
    }

    public set user(value: User)
    {
        this._user = value;
    }

    public set $message(value: string)
    {
        this.message = value;
    }
}