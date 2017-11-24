import * as API from '../api-generated';

export interface UploadListener {

    isUploadSupported(file: File): boolean;

    onUploadComplete(uploadLocation: API.UploadLocation): void;
    
}