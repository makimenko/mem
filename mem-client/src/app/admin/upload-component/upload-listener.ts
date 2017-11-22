import { UploadLocation } from "../../api-generated/index";

export interface UploadListener {

    isUploadSupported(file:File):boolean;

    onUploadComplete(uploadLocation:UploadLocation):void;

}