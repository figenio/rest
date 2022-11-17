import { Timestamp } from "rxjs";

export interface Appointment {
    name: string;
    dateTime: number;
    guests: string[];
}
