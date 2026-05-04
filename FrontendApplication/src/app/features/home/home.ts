import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventService } from '../../services/events';

interface ArtEvent {
  id: number;
  category: string;
  title: string;
  organizer: string;
  date: string;
  location: string;
  price: number;
  imageUrl: string;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
})
export class HomeComponent {
  showCookieBanner = true;

  private service = inject(EventService);

  events$ = this.service.getAll();



  acceptCookies() {
    this.showCookieBanner = false;
    // TO BE IMPLEMENTED
  }

  declineCookies() {
    this.showCookieBanner = false;
  }
}
