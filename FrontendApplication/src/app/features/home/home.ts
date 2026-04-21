import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

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

  events: ArtEvent[] = [
    {
      id: 1,
      category: 'CRAFTS & POTTERY',
      title: 'Pottery Workshop Basics',
      organizer: 'Ceramic Studio Wroc',
      date: 'Sat, Oct 14',
      location: 'Nadodrze',
      price: 120,
      imageUrl:
        'https://images.unsplash.com/photo-1610701596007-11502861dcfa?q=80&w=150&auto=format&fit=crop',
    },
    {
      id: 2,
      category: 'PAINTING',
      title: 'Canvas Painting Masterclass',
      organizer: 'Art Academy Wroclaw',
      date: 'Sun, Oct 15',
      location: 'Rynek 12',
      price: 85,
      imageUrl:
        'https://images.unsplash.com/photo-1460661419201-fd4cecdf8a8b?q=80&w=150&auto=format&fit=crop',
    },
    {
      id: 3,
      category: 'THEATER & IMPROV',
      title: 'Modern Theater Improv',
      organizer: 'Wroclaw Improv Group',
      date: 'Fri, Oct 20',
      location: 'Plac Solny',
      price: 40,
      imageUrl:
        'https://images.unsplash.com/photo-1507676184212-d0c30a430b06?q=80&w=150&auto=format&fit=crop',
    },
  ];

  acceptCookies() {
    this.showCookieBanner = false;
    // TO BE IMPLEMENTED
  }

  declineCookies() {
    this.showCookieBanner = false;
  }
}
