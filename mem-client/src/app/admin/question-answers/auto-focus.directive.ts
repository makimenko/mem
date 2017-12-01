import { Directive, Renderer, ElementRef, AfterViewInit, AfterViewChecked, OnInit, Renderer2, ChangeDetectorRef } from "@angular/core";

@Directive({
    selector: '[autoFocus]'
})
export class AutoFocusDirective implements AfterViewInit {
    constructor(public renderer: Renderer,
        public elementRef: ElementRef,
        private cdRef: ChangeDetectorRef) { }

    ngAfterViewInit() {
        //console.log("ngAfterViewInit")
        this.renderer.invokeElementMethod(this.elementRef.nativeElement, 'focus', []);
        this.cdRef.detectChanges();
    }

}
