import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8888/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' })
};

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    return this.http.post(AUTH_API + 'login', body.toString(), httpOptions);
  }

  register(username: string, password: string, email: string): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      password,
      email
    });
  }

}