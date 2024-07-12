import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    
    public static void main(String[] args) {
        Boot boot1 = new Boot("Nova1", "Segelboot", 4, false, 20);
        Boot boot2 = new Boot("Nova2","Motorboot", 4, false, 36);
        Boot boot3 = new Boot("Speedster01","Motorboot", 2, true, 28);
        Boot boot4 = new Boot("floatfun1","Segelboot", 16, true, 92);
        Boot boot5 = new Boot("Marry", "Segelboot", 2, false, 16);
        Jetsky jetsky1 = new Jetsky("FastJet", 2, true, 21);
        Car car1 = new Car("BMW", "325 E36", 5, true, 68.50);

        Bootsverleih aquaCodes = new Bootsverleih("AquaCodes");
        aquaCodes.addObject(boot1);
        aquaCodes.addObject(boot2);
        aquaCodes.addObject(boot3);
        aquaCodes.addObject(boot4);
        aquaCodes.addObject(boot5);
        aquaCodes.addObject(boot5);
        aquaCodes.addObject(jetsky1);
        aquaCodes.addObject(car1);


        Person andreas = new Person("Andreas", true);
        Person anton = new Person("Anton", true);
        Person peter = new Person("Peter", false);

        LocalDateTime sonnabend12 = LocalDateTime.of(2024, Month.JULY, 13, 12, 0);
        LocalDateTime sonntag11 = LocalDateTime.of(2024, Month.JULY, 14, 11, 0);
        LocalDateTime sonntagNach11 = LocalDateTime.of(2024, Month.JULY, 14, 11, 1);
        LocalDateTime montag12 = LocalDateTime.of(2024, Month.JULY, 15, 12, 0);
        LocalDateTime sonntag2025 = LocalDateTime.of(2025, Month.JULY, 14, 11, 1);
        LocalDateTime montag2025 = LocalDateTime.of(2025, Month.JULY, 15, 12, 0);

        LocalDateTime letztenFreitag = LocalDateTime.of(2024, Month.JULY, 5, 11, 1);
        LocalDateTime letztenSonntag = LocalDateTime.of(2024, Month.JULY, 7, 12, 0);
        
        // boot3.addReservation(sonnabend12, sonntag11, peter); // Licence required!

        boot3.addReservation(sonnabend12, sonntag11, andreas);
        boot1.addReservation(sonnabend12, sonntag11, andreas);
        boot2.addReservation(sonnabend12, sonntag11, andreas);
        boot2.addReservation(sonntag2025, montag2025, andreas);
        boot1.addReservation(sonntag2025, montag2025, andreas);
        boot5.addReservation(sonntag2025, montag2025, peter);
        boot5.addReservation(letztenFreitag, letztenSonntag, peter);

        boot3.addReservation(sonntagNach11, montag12, anton);

        boot3.printReservations();

        aquaCodes.printKunden();

        System.out.println("");
        aquaCodes.printFlotte();
        System.out.println("");

        
        andreas.favoriteObjekt();
        System.out.println("");
        andreas.unfavoriteObjekt();
        

        System.out.println("");
        System.out.println("das boot mit dem meisten umsatz der letzten 30 Tagen ist: " + aquaCodes.objectWithHighestRevenue(30));
    }
}
