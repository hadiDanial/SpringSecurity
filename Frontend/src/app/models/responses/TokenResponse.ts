export class TokenResponse
{
    private _accessToken: string;
    private _refreshToken: string;
    private _message: string;
    private _success: boolean;


    constructor(accessToken: string, refreshToken: string, message: string, success: boolean)
    {
        this._accessToken = accessToken;
        this._refreshToken = refreshToken;
        this._message = message;
        this._success = success;
    }

    public get accessToken(): string
    {
        return this._accessToken;
    }

    public get refreshToken(): string
    {
        return this._refreshToken;
    }

    public get message(): string
    {
        return this._message;
    }

    public get success(): boolean
    {
        return this._success;
    }

    public set accessToken(value: string)
    {
        this._accessToken = value;
    }

    public set refreshToken(value: string)
    {
        this._refreshToken = value;
    }

    public set message(value: string)
    {
        this._message = value;
    }

    public set success(value: boolean)
    {
        this._success = value;
    }

}