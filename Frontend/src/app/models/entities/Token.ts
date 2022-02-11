export class Token
{
    private _accessToken: string;
    private _refreshToken: string;
    private _expiresAt: Date;
    constructor(accessToken: string, refreshToken: string, expiresAt:Date)
    {
        this._accessToken = accessToken;
        this._refreshToken = refreshToken;
        this._expiresAt = expiresAt;
    }

    public get accessToken(): string
    {
        return this._accessToken;
    }

    public get refreshToken(): string
    {
        return this._refreshToken;
    }

    public get expiresAt(): Date
    {
        return this._expiresAt;
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