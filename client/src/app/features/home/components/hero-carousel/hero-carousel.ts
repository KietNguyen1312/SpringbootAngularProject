import { Component, OnInit, OnDestroy, signal } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-hero-carousel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './hero-carousel.html',
  styleUrl: './hero-carousel.css',
})
export class HeroCarousel implements OnInit, OnDestroy {
  products = [
    {
      image: 'https://images.unsplash.com/photo-1523381210434-271e8be1f52b?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
      title: 'Vintage Denim Collection',
      description: 'Authentic 90s styles, curated for you.'
    },
    {
      image: 'https://images.unsplash.com/photo-1550614000-4b9519e494b7?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
      title: 'Retro Sneakers',
      description: 'Step into the past with limited edition kicks.'
    },
    {
      image: 'https://images.unsplash.com/photo-1483985988355-763728e1935b?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
      title: 'Luxury Handbags',
      description: 'Pre-loved designer bags at unbeatable prices.'
    }
  ];

  currentIndex = signal(0);
  private intervalId: any;

  ngOnInit() {
    this.startAutoSlide();
  }

  ngOnDestroy() {
    this.stopAutoSlide();
  }

  startAutoSlide() {
    this.stopAutoSlide();
    this.intervalId = setInterval(() => {
      this.next();
    }, 3000);
  }

  stopAutoSlide() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  next() {
    this.currentIndex.update(val => (val + 1) % this.products.length);
  }

  prev() {
    this.currentIndex.update(val => (val - 1 + this.products.length) % this.products.length);
  }

  goTo(index: number) {
    this.currentIndex.set(index);
    this.startAutoSlide(); // Reset timer on manual click
  }
}
