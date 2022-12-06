import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from '../models/appointment';

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  url = "http://localhost:8080/restServer-1.0-SNAPSHOT"

  constructor(private httpClient: HttpClient) { }

  testHelloWorld(): Observable<string> {
    return this.httpClient.get(this.url+ '/hello-world', { responseType: 'text' });
  }

  registerAppointment(ap: Appointment): Observable<[]> {
    return this.httpClient.post<[]>(this.url, ap);
  }
}
