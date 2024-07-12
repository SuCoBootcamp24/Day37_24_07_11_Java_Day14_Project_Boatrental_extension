import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Bootsverleih {
    private ArrayList<Boot> boote = new ArrayList<>();
    private ArrayList<Person> kunden = new ArrayList<>();
    private double umsatz;
    private String name;

    public Bootsverleih (String name) {
        setName(name);
    }

    //--------
    
    public String getName() {
         return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public ArrayList<Boot> getBoote() {
        return boote;
    }
    
    public void addBoot(Boot boot) {
        this.boote.add(boot);
        boot.setBoatOwner(this);
    }
    
    public void removeBoot(Boot boot) {
      if(this.boote.contains(boot)) boote.remove(boot);
    }
    
    public ArrayList<Person> getKunden() {
        return kunden;
    }
    
    public void addKunde(Person kunde) {
       if (!this.kunden.contains(kunde)) kunden.add(kunde);
    }
    
    
    //-------------
    
    // the customer with the most reservation (count)
    public Person topCustomer() {
        int mostReservation = 0;
        Person topCosumer = null;
        for (Person p : this.kunden) {
            if (p.getReservationCounter() > mostReservation) {
                topCosumer = p;
                mostReservation = p.getReservationCounter();
            }
        }
        return topCosumer;
    }
    
    public Boot MostPopularboat() {
        int mostRentalBoat = 0;
        Boot topBoat = null;
        for (Boot b : this.boote) {
            if (b.getRentalCount() > mostRentalBoat) {
                topBoat = b;
                mostRentalBoat = b.getRentalCount();
            }
        }
        return topBoat;
    }

    public double getUmsatz() {
        return umsatz;
    }

    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }

    //------

    public void printKunden(){
        System.out.println("Kunden von " + getName() + ":");
        for (Person p : kunden) {
            System.out.println(p.getName());
        }
    }

    public void printFlotte(){
        System.out.println("Die Flotte von " + getName() + ":");
        for (Boot b : boote) {
            System.out.println(b.getName());
        }
    }

    public Boot boatWithHighestRevenue(int lastNDays) {
        LocalDateTime toTime = LocalDateTime.now().with(LocalTime.MAX);
        LocalDateTime fromTime =  LocalDateTime.now().minusDays(lastNDays).with(LocalTime.MIN);
    
        double mostRevenue = 0;
        Boot topBoat = null;
        System.out.println("");
        System.out.println("From: " + fromTime + " To: " + toTime);
        System.out.println("");

        for (Boot b : this.boote) {
            double boatRevenueSum = 0;
            
            System.out.println("Checking boat: " + b.getName());
    
            for (Reservation r : b.getReservations()) {
                System.out.println("Reservation from: " + r.getFrom() + " to: " + r.getTo());
    
                if (r.getFrom().isBefore(toTime) && r.getTo().isAfter(fromTime)) {
                    LocalDateTime start = r.getFrom().isAfter(fromTime) ? r.getFrom() : fromTime;
                    LocalDateTime end = r.getTo().isBefore(toTime) ? r.getTo() : toTime;
    
                    System.out.println("Adjusted Reservation from: " + start + " to: " + end);
    
                    boatRevenueSum += Reservation.reservationTimeInHours(start, end) * b.getPicePerHour();
                    System.out.println("Current boat revenue sum: " + boatRevenueSum);
                }
            }
    
            if (mostRevenue < boatRevenueSum) {
                mostRevenue = boatRevenueSum;
                topBoat = b;
                System.out.println("New top boat: " + topBoat.getName() + " with revenue: " + mostRevenue);
            }
        }
    
        if (topBoat == null) {
            System.out.println("No boat found with reservations in the given time period.");
        } else {
            System.out.println("Top boat: " + topBoat.getName() + " with revenue: " + mostRevenue);
        }
    
        return topBoat;
    }
}