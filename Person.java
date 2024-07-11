import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Person {
    private String name;
    private boolean hasLicence;
    private int reservationCounter = 0;
    private HashMap<Boot, Integer> boatHistory = new HashMap<>();

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

    public HashMap<Boot, Integer> getBoatHistory() {
        return boatHistory;
    }

    public void addBoatInBoatHistory(Boot boot) {
        if (this.boatHistory.containsKey(boot)) {
            int currentCount = this.boatHistory.get(boot);
            this.boatHistory.put(boot, currentCount + 1);
        } else {
            this.boatHistory.put(boot, 1);
        }
    }

    public Boot favoriteBoat(){
        if (this.boatHistory.isEmpty()) return null;
       return Collections.max(this.boatHistory.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    public Boot unfavoriteBoat(){
        if (this.boatHistory.isEmpty()) return null;
       return Collections.min(this.boatHistory.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

}
