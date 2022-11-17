import { Component } from '@angular/core';
import { ServerService } from './services/server.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'rest-client';
  name = '';
  loggedIn = false;

  constructor(private serverService: ServerService) { }

  setAppointment() {
    console.log("novo compromisso");

    let ap: Appointment = {
      name: "compromisso 1",
      dateTime: new Date(new Date).getTime(),
      guests: ["Mateus", "Nicole"]
    };

    console.log("Construído:", ap);

    this.serverService.registerAppointment(ap).subscribe((result) => {
      console.log(result);
    })
  }


  get validName() {
    return this.name.length > 0;
  }
}

interface Appointment {
  name: string;
  dateTime: number;
  guests: string[];
}

