import java.time.LocalDateTime;

public class Boot extends Mietobjekt {
    // länge, breite, tiefgang, freiboard
    // hasMotor: segelboot (masthöhe) vs. motorboot
    // personCount
    // isLicenceRequired 
    // availability: Reservation[] from, to, person
    // private String name;
     private String type; // Segelboot | Motorboot
    // private int personCount;
    // private boolean isLicenceRequired;
    // private double pricePerHour;
    // private ArrayList<Reservation> reservations;
    // private int rentalCount = 0;
    // private Bootsverleih owner;
    // private double umsatz;


    public Boot(String name, String type, int personCount, boolean isLicenceRequired, double pricePerHour) {
        super(name, personCount, isLicenceRequired, pricePerHour);
        this.setType(type);
    }

    public void setType(String type) {
        if(!type.equals("Segelboot") && !type.equals("Motorboot")) throw new IllegalArgumentException("Bootstyp muss Segelboot oder Motorboot sein");
        this.type = type;
    }
    
    public String getType() {
        return type;
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
