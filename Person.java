import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Person {
    private String name;
    private boolean hasLicence;
    private int reservationCounter = 0;
    private HashMap<Mietobjekt, Integer> boatHistory = new HashMap<>();
    private HashMap<Mietobjekt, Integer> jetskyHistory = new HashMap<>();
    private HashMap<Mietobjekt, Integer> carHistory = new HashMap<>();
    private HashMap<Mietobjekt, Integer> objektHistory = new HashMap<>();
    

    public Person(String name, boolean hasLicence) {
        this.name = name;
        this.hasLicence = hasLicence;
    }

    public String getName() {
        return this.name;
    }
    
    public boolean hasLicence() {
        return this.hasLicence;
    }

    
    public int getReservationCounter() {
        return reservationCounter;
    }

    public void setReservationCounter(int reservationCounter) {
        this.reservationCounter = reservationCounter;
    }

    public HashMap<Mietobjekt, Integer> getJetskyHistory() {
        return jetskyHistory;
    }

    public HashMap<Mietobjekt, Integer> getCarHistory() {
        return carHistory;
    }

    public HashMap<Mietobjekt, Integer> getBoatHistory() {
        return boatHistory;
    }

    public void addObjektInRentHistory(Mietobjekt objekt) {

        if (objekt instanceof Boot) {
            if (this.boatHistory.containsKey(objekt)) {
                int currentCount = this.boatHistory.get(objekt);
                this.boatHistory.put(objekt, currentCount + 1);
            } else {
                this.boatHistory.put(objekt, 1);
            }
        }

        if (objekt instanceof Jetsky) {
            if (this.jetskyHistory.containsKey(objekt)) {
                int currentCount = this.jetskyHistory.get(objekt);
                this.jetskyHistory.put(objekt, currentCount + 1);
            } else {
                this.jetskyHistory.put(objekt, 1);
            }
        }
        
        if (objekt instanceof Car) {
            if (this.carHistory.containsKey(objekt)) {
                int currentCount = this.carHistory.get(objekt);
                this.carHistory.put(objekt, currentCount + 1);
            } else {
                this.carHistory.put(objekt, 1);
            }
        }
        
        if (this.objektHistory.containsKey(objekt)) {
            int currentCount = this.objektHistory.get(objekt);
            this.objektHistory.put(objekt, currentCount + 1);
        } else {
            this.objektHistory.put(objekt, 1);
        }
    }

    public void favoriteObjekt(){
        System.out.println("Die belibtesten Fahrzeuge: ");
        if (!this.boatHistory.isEmpty()) System.out.println("Boot: " + Collections.max(this.boatHistory.entrySet(), Map.Entry.comparingByValue()).getKey().getName()); 

        if (!this.jetskyHistory.isEmpty()) System.out.println("Jetsky: " + Collections.max(this.jetskyHistory.entrySet(), Map.Entry.comparingByValue()).getKey().getName()); 

        if (!this.carHistory.isEmpty()) System.out.println("Auto: " + Collections.max(this.carHistory.entrySet(), Map.Entry.comparingByValue()).getKey().getName()); 

        if (!this.objektHistory.isEmpty()) System.out.println("Allgemein: " + Collections.max(this.objektHistory.entrySet(), Map.Entry.comparingByValue()).getKey().getName()); 


    }
    public void unfavoriteObjekt(){
        System.out.println("Die unbelibtesten fahrzeuge: ");
        if (!this.boatHistory.isEmpty()) System.out.println("Boot: " + Collections.min(this.boatHistory.entrySet(), Map.Entry.comparingByValue()).getKey().getName()); 

        if (!this.jetskyHistory.isEmpty()) System.out.println("Jetsky: " + Collections.min(this.jetskyHistory.entrySet(), Map.Entry.comparingByValue()).getKey().getName()); 

        if (!this.carHistory.isEmpty()) System.out.println("Auto: " + Collections.min(this.carHistory.entrySet(), Map.Entry.comparingByValue()).getKey().getName()); 

        if (!this.objektHistory.isEmpty()) System.out.println("Allgemein: " + Collections.min(this.objektHistory.entrySet(), Map.Entry.comparingByValue()).getKey().getName()); 


    }

}
