import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void 
  {
    const header = document.getElementById("header");
    if(header != null)
      {
        window.addEventListener("scroll",(event)=>
        {
          header.classList.toggle("scrolled",window.scrollY> 50)
        });
  }
  }

}
