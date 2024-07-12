import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Bootsverleih {
    private ArrayList<Boot> boote = new ArrayList<>();
    private ArrayList<Jetsky> jetskys = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Mietobjekt> allObjekts = new ArrayList<>();
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
    
    public void addObject(Mietobjekt object) {

        if (object instanceof Boot) {
            this.boote.add((Boot) object);
        }

        if (object instanceof Jetsky) {
            this.jetskys.add((Jetsky) object);
        }

        if (object instanceof Car) {
            this.cars.add((Car) object);
        }

        this.allObjekts.add(object);
        object.setOwner(this);

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
        System.out.println("Boote:");
        for (Boot b : boote) {
            System.out.println(b.getName());
        }

        System.out.println("");
        System.out.println("Jetskeys:");
        for (Jetsky j : jetskys) {
            System.out.println(j.getName());
        }
        System.out.println("");
        System.out.println("Autos:");
        for (Car c : cars) {
            System.out.println(c.getName());
        }
        System.out.println("");
    }

    public Mietobjekt objectWithHighestRevenue(int lastNDays) {
        LocalDateTime toTime = LocalDateTime.now().with(LocalTime.MAX);
        LocalDateTime fromTime =  LocalDateTime.now().minusDays(lastNDays).with(LocalTime.MIN);
        double mostRevenue = 0;
        Mietobjekt topObj = null;
       
        System.out.println("vom: " + fromTime + " bis: " + toTime);

        for (Mietobjekt o : this.allObjekts) {
            double boatRevenueSum = 0;

            for (Reservation r : o.getReservations()) {
    
                if (r.getFrom().isBefore(toTime) && r.getTo().isAfter(fromTime)) {
                    LocalDateTime start = r.getFrom().isAfter(fromTime) ? r.getFrom() : fromTime;
                    LocalDateTime end = r.getTo().isBefore(toTime) ? r.getTo() : toTime;

                    boatRevenueSum += Reservation.reservationTimeInHours(start, end) * o.getPicePerHour();
                }
            }
    
            if (mostRevenue < boatRevenueSum) {
                mostRevenue = boatRevenueSum;
                topObj = o;
            }
        }
    
        if (topObj == null) {
            return null;
        }     
        return topObj;
    }
}