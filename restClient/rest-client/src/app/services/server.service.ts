import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from '../models/appointment';

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  url = "http://localhost:8080";

  headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private httpClient: HttpClient) { }

  testHelloWorld(): Observable<string> {
    return this.httpClient.get(this.url + '/hello', { responseType: 'text' });
  }

  // Cadastro do cliente
  registerClient(name: String): Observable<any> {;

    //const params = new HttpParams().set('clientName', name.valueOf());

    return this.httpClient.post(this.url + '/client?clientName=' + name, null);
  }

  // Cadastro de compromisso
  registerAppointment(ap: Appointment, name: String): Observable<any> {
    const body=JSON.stringify(ap);

    return this.httpClient.post(this.url + '/appointment?clientName=' + name, body, { headers : this.headers });
  }

  // Cancelamento de compromisso
  cancelAppointment(clientName:String, appName: String) {
    return this.httpClient.delete(this.url + '/appointment?clientName=' + clientName + '&appName=' + appName);
  }

  // Pega compromissos
  getAppointments(clientName:String, appTime: number) {
    return this.httpClient.get(this.url + '/appointment?clientName=' + clientName + '&dateTime=' + appTime);
  }

  // Se coloca em um compromisso
  joinAppointment(clientName: String, appointmentName: string) {
    return this.httpClient.put(this.url + '/appointment?clientName=' + clientName + '&appName=' + appointmentName, null);
  }
}
