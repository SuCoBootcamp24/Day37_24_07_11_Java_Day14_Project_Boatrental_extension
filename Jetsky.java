import java.time.LocalDateTime;

public class Jetsky extends Mietobjekt{

    public Jetsky(String name, int personCount, boolean isLicenceRequired, double pricePerHour) {
        super(name, personCount, isLicenceRequired, pricePerHour);
      
    }
 
    

    public Reservation addReservation(LocalDateTime from, LocalDateTime to, Person p) {
        boolean licenceOK = !isLicenceRequired() || isLicenceRequired() && p.hasLicence(); 
        if(!licenceOK) throw new IllegalStateException("Licence required to book this boat");

        // check availability based on from and to values!!!
        boolean isAvailable = super.checkIsAvailable(from, to);
        if(!isAvailable) throw new IllegalStateException("Boat not available, please try a different time");
        
        Reservation reservation = new Reservation(p, from, to);
        getReservations().add(reservation);
        upvoteRentalCount();
        p.addObjektInRentHistory(this);
        getOwner().addKunde(p);

        double sum = Reservation.reservationTimeInHours(from, to) * getPicePerHour();
        getOwner().setUmsatz(getOwner().getUmsatz() + sum);
        setUmsatz(getUmsatz() + sum);

        return reservation;
    }
}
