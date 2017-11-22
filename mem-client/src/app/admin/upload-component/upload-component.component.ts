import { Component, OnInit, ElementRef, ViewChild, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UploadService } from '../../api-generated';
import { UploadListener } from "./upload-listener";


@Component({
  selector: 'app-upload-component',
  templateUrl: './upload-component.component.html',
  styleUrls: ['./upload-component.component.scss']
})
export class UploadComponentComponent implements OnInit {

  @Input() accept: string;
  @Input() multiple: boolean = true;
  @Input() uploadListener: UploadListener;

  constructor(private uploadService: UploadService) { }

  ngOnInit() {
  }

  onFileChange(event) {
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      console.log("Submitting " + fileList.length + " files...");
      for (let i = 0; i < fileList.length; i++) {
        let file: File = fileList.item(i);
        console.log("Uploading file: " + file.name);
        this.uploadService.uploadPost(file).subscribe(i => {
          console.log("Upload completed! File url: " + i.url);
          if (this.uploadListener != undefined) {
            this.uploadListener.onUploadComplete(i);
          } else {
            console.log("No upload listeners")
          }
        });
      }
    } else {
      console.log("No file");
    }
  }

}
