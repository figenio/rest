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
  createAppName = '';
  createAppTime = "00:00";
  guest = "";
  guests: any[] = [];

  // Variável de cancelar
  cancelAppName = '';

  // Variáveis de buscar
  consultAppTime = "00:00"
  appListed: any[] = [];

  // Variável de participar
  joinAppName = ""

  constructor(private serverService: ServerService) { }


  /**
   * Método que adiciona convidados ao compromisso
   */
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
    // Cálculo de timestamp
    let startTime = this.createAppTime.split(":");
    let startHours = Number(startTime[0]) * 60 * 60 * 1000;
    let startMinutes = Number(startTime[1]) * 60 * 1000;
    let appDate = new Date().setHours(0,0,0,0) + startHours + startMinutes;

    // Montando objeto
    let app: Appointment = {
      name: this.createAppName,
      dateTime: appDate,
      guests: this.guests
    };

    // Chamada da API
    this.serverService.registerAppointment(app).subscribe((result) => {
      console.log(result);
      this.createAppName = "";
      this.createAppTime = "00:00";
      this.guests = []
    }, error => {
      console.error(error);
    });
  }

  /**
   * Método que cancela um compromisso pelo nome
   */
  cancelAppointment() {
    this.serverService.cancelAppointment(this.cancelAppName).subscribe((result) => {
      console.log(result);
      this.cancelAppName = "";
    }, error => {
      console.error(error);
    });
  }

  /**
   * Método que busca compromissos por hora
   */
  getAppointment() {
    // Cálculo de timestamp
    let startTime = this.consultAppTime.split(":");
    let startHours = Number(startTime[0]) * 60 * 60 * 1000;
    let appTime = new Date().setHours(0,0,0,0) + startHours;

    this.serverService.getAppointments(appTime).subscribe((result) => {
      console.log(result);
      this.consultAppTime = "00:00";

      // this.appListed = result;
    }, error => {
      console.error(error);
    });
  }

  /**
   * Método pro usuário entrar em um compromisso pelo nome
   */
  joinAppointment() {
    this.serverService.joinAppointment(this.name, this.joinAppName).subscribe((result) => {
      console.log(result);
      this.joinAppName = "";
    }, error => {
      console.error(error);
    });
  }




  // Método temporário
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
  guests: any[];
}

