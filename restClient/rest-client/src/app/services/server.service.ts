import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from '../models/appointment';

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  url = "http://localhost:8080/restServer-1.0-SNAPSHOT";

  headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private httpClient: HttpClient) { }

  testHelloWorld(): Observable<string> {
    return this.httpClient.get(this.url + '/hello-world', { responseType: 'text' });
  }

  // Cadastro do cliente
  registerClient(name: String): Observable<any> {
    const body=JSON.stringify({
      clientName: name
    });

    return this.httpClient.post(this.url + '/client', body, { headers : this.headers });
  }

  // Cadastro de compromisso
  registerAppointment(ap: Appointment): Observable<any> {
    const body=JSON.stringify(ap);

    return this.httpClient.post(this.url + '/client/appointment', body, { headers : this.headers });
  }

  // Cancelamento de compromisso
  cancelAppointment(appName: String) {
    return this.httpClient.delete(this.url + '/client/' + appName, { headers : this.headers })
  }

  // Pega compromissos
  getAppointments(appTime: number) {
    return this.httpClient.get(this.url + '/client/' + appTime, {headers : this.headers});
  }

  // Se coloca em um compromisso
  joinAppointment(clientName: String, appointmentName: string) {
    const body=JSON.stringify({
      clientName: clientName,
      appointmentName: appointmentName
    });

    return this.httpClient.put(this.url + '/client', body, { headers : this.headers });
  }
}
