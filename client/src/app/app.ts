import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './core/layout/header/header';
import { Footer } from './core/layout/footer/footer';
import { TopBar } from './core/layout/top-bar/top-bar';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header, Footer, TopBar],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('client');
}
