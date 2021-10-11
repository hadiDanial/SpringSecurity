import { Name } from "../other/Name";

export class SignupRequest
{
    constructor(public username: string, public email: string, public password: string, public name: Name) { }
}