export class Token
{
    private _accessToken: string;
    private _refreshToken: string;

    constructor(accessToken: string, refreshToken: string)
    {
        this._accessToken = accessToken;
        this._refreshToken = refreshToken;
    }

    public get accessToken(): string
    {
        return this._accessToken;
    }

    public get refreshToken(): string
    {
        return this._refreshToken;
    }

    public set accessToken(accessToken: string)
    {
        this._accessToken = accessToken;
    }

    public set refreshToken(refreshToken: string)
    {
        this._refreshToken = refreshToken;
    }
}