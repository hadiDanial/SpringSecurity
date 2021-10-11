export class MessageBoolResponse
{
    private _message: string;
    private _result: boolean;

    constructor(message: string, result: boolean)
    {
        this._message = message;
        this._result = result;
    }

    public get message(): string
    {
        return this._message;
    }

    public get result(): boolean
    {
        return this._result;
    }

    public set message(value: string)
    {
        this._message = value;
    }

    public set result(value: boolean)
    {
        this._result = value;
    }
}