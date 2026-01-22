import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeroCarousel } from './components/hero-carousel/hero-carousel';

@Component({
  selector: 'app-home',
  imports: [CommonModule, HeroCarousel],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  // Component is active and used in template
}
