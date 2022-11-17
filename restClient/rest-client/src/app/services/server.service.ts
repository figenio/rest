import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from '../models/appointment';

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  url = "http://localhost:4200/api/appointment"

  constructor(private httpClient: HttpClient) { }

  registerAppointment(ap: Appointment): Observable<[]> {
    return this.httpClient.post<[]>(this.url, ap);
  }
}
