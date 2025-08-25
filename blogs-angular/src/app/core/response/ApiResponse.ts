import { ResponseStatus } from "../enums/ResponseStatus";

export class ApiResponse<T>{
    status:ResponseStatus;
    message:String;
    data:T
}