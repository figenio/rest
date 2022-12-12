import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ServerService } from './services/server.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // Variáveis gerais
  name = '';
  loggedIn = false;

  // Variáveis de criação
  createCompName = '';
  createCompTime = "00:00";
  guest = "";
  guests: any[] = [];

  // Variável de cancelar
  cancelCompName = '';

  // Variáveis de buscar
  consultCompTime = "00:00"
  appListed: any[] = [];

  // Variável de participar
  joinCompName = ""

  constructor(private serverService: ServerService) { }


  addGuest() {
    this.guests.push(this.guest);
    this.guest = "";
  }


  /**
   * Método simples para testar se o servidor está online
   */
  testServer() {
    this.serverService.testHelloWorld().subscribe((result) => {
      console.log(result);
    })
  }

  /**
   * Método de login, que registra o novo cliente liberando acessou ou, em caso de erro, alerta o usuário
   */
  login() {
    this.serverService.registerClient(this.name).subscribe((result) => {
      console.log(result);
      this.loggedIn = true;
    }, error => {
      console.error(error);
    });
  }

  /**
   * Método que cadastra um compromisso e limpa os formulários se bem sucedido
   */
  setAppointment() {
    console.log("novo compromisso");
    let startTime = this.createCompTime.split(":");
    let startHours = Number(startTime[0]) * 60 * 60 * 1000;
    let startMinutes = Number(startTime[1]) * 60 * 1000;
    let compDate = new Date().setHours(0,0,0,0) + startHours + startMinutes;

    let app: Appointment = {
      name: this.createCompName,
      dateTime: compDate
    };

    this.serverService.registerAppointment(app).subscribe((result) => {
      console.log(result);
      this.createCompName = "";
      this.createCompTime = "00:00";
      this.guests = []
    }, error => {
      console.error(error);
    });
  }



  giveList() {
    this.appListed = ["Reunião", "Exame", "Consulta"]
  }

  // Validador do nome inserido
  get validName() {
    return this.name.length > 0;
  }
}

interface Appointment {
  name: string;
  dateTime: number;
}

