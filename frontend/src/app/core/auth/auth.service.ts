import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginRequest, LoginResponse, UserInfo } from './auth.models';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly apiUrl = 'http://localhost:8080/api/auth';
  private readonly tokenKey = 'accessToken';
  private readonly refreshTokenKey = 'refreshToken';
  private readonly userKey = 'user';

  constructor(private http: HttpClient) {}

  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, request).pipe(
      tap(response => this.saveSession(response))
    );
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.refreshTokenKey);
    localStorage.removeItem(this.userKey);
  }

  getAccessToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getCurrentUser(): UserInfo | null {
    const user = localStorage.getItem(this.userKey);
    return user ? JSON.parse(user) as UserInfo : null;
  }

  isLoggedIn(): boolean {
    return !!this.getAccessToken();
  }

  private saveSession(response: LoginResponse): void {
    localStorage.setItem(this.tokenKey, response.accessToken);
    localStorage.setItem(this.refreshTokenKey, response.refreshToken);
    localStorage.setItem(this.userKey, JSON.stringify(response.user));
  }
}
