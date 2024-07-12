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
        for (Reservation r : reservations) {
            System.out.println("\t" + r.toString());
        }
    }
}