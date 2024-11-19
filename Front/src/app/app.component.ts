import {Component} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import {NgClass, NgIf} from "@angular/common";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [
    NgClass,
    RouterOutlet,
    NgIf
  ],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentPage: string | null = null;
  showSidebar: boolean = true;

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.showSidebar = !(event.url === '/login' || event.url === '/cadastrar-usuario');
      }
    });
  }

  navigateTo(page: string): void {
    this.currentPage = page;
    this.router.navigate([`/${page}`]);
  }

  logout() {
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigate(['login']);
  }

}
