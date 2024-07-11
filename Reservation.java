import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private Person person;
    private LocalDateTime from;
    private LocalDateTime to;

    public Reservation(Person person, LocalDateTime from, LocalDateTime to) {
        this.person = person;
        this.from = from;
        this.to = to;
        person.setReservationCounter(person.getReservationCounter() + 1);
    }

    public Person getPerson() {
        return person;
    }
    public LocalDateTime getFrom() {
        return from;
    }
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE dd.MMM HH:mm");
        return "From " + this.getFrom().format(format) +
            " | To " + this.getTo().format(format) + 
            " | By " + this.getPerson().getName() + " (Licence: " + this.getPerson().hasLicence() + ")";
            
    }
}
