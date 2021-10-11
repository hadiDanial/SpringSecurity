export class ValidateTokenRequest
{
    constructor(public accessToken: string, public refreshToken: string) { }
}