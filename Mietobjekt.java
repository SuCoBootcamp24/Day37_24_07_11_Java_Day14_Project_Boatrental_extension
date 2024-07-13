import java.time.LocalDateTime;
import java.util.ArrayList;

public class Mietobjekt {
    private String name;
    private int personCount;
    private boolean isLicenceRequired;
    private double pricePerHour;
    private ArrayList<Reservation> reservations;
    private int rentalCount = 0;
    private Bootsverleih owner;
    private double umsatz;

    public Mietobjekt(String name, int personCount, boolean isLicenceRequired, double pricePerHour) {
        this.setName(name);
        this.setPersonCount(personCount);
        this.setIsLicenceRequired(isLicenceRequired);
        this.setPricePerHour(pricePerHour);
        this.reservations = new ArrayList<Reservation>();
    }



    public void setPersonCount(int personCount) {
        if(personCount <= 0) throw new IllegalArgumentException("Personcount muss größer als 0 sein");
        this.personCount = personCount;
    }
    public void setIsLicenceRequired(boolean isLicenceRequired) {
        this.isLicenceRequired = isLicenceRequired;
    }
    public void setPricePerHour(double pricePerHour) {
        if(pricePerHour < 0.0) throw new IllegalArgumentException("Personcount muss positiv sein");
        this.pricePerHour = pricePerHour;
    }

    public String getName() {
       return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public int getPersonCount() {
        return personCount;
    }
    public boolean isLicenceRequired() {
        return isLicenceRequired;
    }
    public double getPicePerHour() {
        return pricePerHour;
    }
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public Reservation addReservation(LocalDateTime from, LocalDateTime to, Person p) {
        boolean licenceOK = !isLicenceRequired() || isLicenceRequired() && p.hasLicence(); 
        if(!licenceOK) throw new IllegalStateException("Licence required to book this boat");

        // check availability based on from and to values!!!
        boolean isAvailable = checkIsAvailable(from, to);
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


    public boolean checkIsAvailable(LocalDateTime from, LocalDateTime to) {
        // if can find a reservation that overlaps --> return false
        // else return true
        for(int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            boolean isOverlapping =
                to.isAfter(reservation.getFrom()) && 
                from.isBefore(reservation.getTo());
            if(isOverlapping) return false;
        }
        return true;
    }

    public int getRentalCount() {
        return rentalCount;
    }

    public void upvoteRentalCount() {
        this.rentalCount++;
    }

    public Bootsverleih getOwner() {
        return owner;
    }

    public void setOwner(Bootsverleih boatOwner) {
        this.owner = boatOwner;
    }

    public double getUmsatz() {
        return umsatz;
    }

    public void setUmsatz(double bootUmsatz) {
        this.umsatz = bootUmsatz;
    }


    public void printReservations(){

        System.out.println(this.getName()+ ":");
        for (Reservation r : reservations) {
                System.out.println("\t" + r.toString());
        }
    }
}