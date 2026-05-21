import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../../core/auth/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  user = this.authService.getCurrentUser();
  apiResponse: unknown;

  constructor(private authService: AuthService, private router: Router, private http: HttpClient) {}

  callProtectedApi(): void {
    this.http.get('http://localhost:8080/api/me').subscribe({
      next: response => this.apiResponse = response,
      error: error => this.apiResponse = error.error || error.message
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
