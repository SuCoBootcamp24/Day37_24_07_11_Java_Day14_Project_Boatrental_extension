import java.time.LocalDateTime;

public class Car extends Mietobjekt{

    private String type;

    public Car(String name, String type, int personCount, boolean isLicenceRequired, double pricePerHour) {
        super(name, personCount, isLicenceRequired, pricePerHour);
        setType(type);
       
    }
    

  
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
