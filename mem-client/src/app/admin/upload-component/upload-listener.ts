import { UploadLocation } from "../../api-generated/index";

export interface UploadListener {

    onUploadComplete(uploadLocation:UploadLocation):void;

}