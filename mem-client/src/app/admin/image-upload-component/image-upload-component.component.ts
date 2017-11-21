import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UploadService } from '../../api-generated';


@Component({
  selector: 'app-image-upload-component',
  templateUrl: './image-upload-component.component.html',
  styleUrls: ['./image-upload-component.component.scss']
})
export class ImageUploadComponentComponent implements OnInit {
  form: FormGroup;
  loading: boolean = false;

  @ViewChild('fileInput') fileInput: ElementRef;

  constructor(
    private formBuilder: FormBuilder,
    private uploadService: UploadService
  ) {
    this.createForm();
  }

  ngOnInit() {
  }

  createForm() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      image: null
    });
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      let file = event.target.files[0];
      this.form.get('image').setValue(file);
    }
  }

  private prepareSave(): any {
    let input = new FormData();
    // This can be done a lot prettier; for example automatically assigning values by looping through `this.form.controls`, but we'll keep it as simple as possible here
    input.append('name', this.form.get('name').value);
    input.append('image', this.form.get('image').value);
    return input;
  }

  onSubmit() {
    console.log("Submitting file...");
    // const formModel = this.prepareSave();

    this.loading = true;
    let name = this.form.get('name').value;
    let image: File = this.form.get('image').value;

    console.log("name=" + name);
    console.log("Start image ---");
    console.log(image)
    console.log("--- end image.");
    //let imageBlob : Blob = image.slice();
    let imageBlob:Blob = new Blob(['aaa'], {type: 'image/png'})
    console.log(imageBlob);
    
    
    this.uploadService.uploadImagePost(name, imageBlob).subscribe(i => {
      this.loading = false;
      console.log("Upload completed! Image url: " + i.url);
    });
  }

  clearFile() {
    this.form.get('image').setValue(null);
    this.fileInput.nativeElement.value = '';
  }

}
