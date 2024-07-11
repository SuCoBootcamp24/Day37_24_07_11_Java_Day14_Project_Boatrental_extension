import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    
    public static void main(String[] args) {
        Boot boot1 = new Boot("Nova1", "Segelboot", 4, false, 20);
        Boot boot2 = new Boot("Nova2","Motorboot", 4, false, 36);
        Boot boot3 = new Boot("Speedster01","Motorboot", 2, true, 28);
        Boot boot4 = new Boot("floatfun1","Segelboot", 16, true, 92);
        Boot boot5 = new Boot("Marry", "Segelboot", 2, false, 16);

        Bootsverleih aquaCodes = new Bootsverleih("AquaCodes");
        aquaCodes.addBoot(boot1);
        aquaCodes.addBoot(boot2);
        aquaCodes.addBoot(boot3);
        aquaCodes.addBoot(boot4);
        aquaCodes.addBoot(boot5);


        Person andreas = new Person("Andreas", true);
        Person anton = new Person("Anton", true);
        Person peter = new Person("Peter", false);

        LocalDateTime sonnabend12 = LocalDateTime.of(2024, Month.JULY, 13, 12, 0);
        LocalDateTime sonntag11 = LocalDateTime.of(2024, Month.JULY, 14, 11, 0);
        LocalDateTime sonntagNach11 = LocalDateTime.of(2024, Month.JULY, 14, 11, 1);
        LocalDateTime montag12 = LocalDateTime.of(2024, Month.JULY, 15, 12, 0);
        LocalDateTime sonntag2025 = LocalDateTime.of(2025, Month.JULY, 14, 11, 1);
        LocalDateTime montag2025 = LocalDateTime.of(2025, Month.JULY, 15, 12, 0);
        
        // boot3.addReservation(sonnabend12, sonntag11, peter); // Licence required!

        boot3.addReservation(sonnabend12, sonntag11, andreas);
        boot1.addReservation(sonnabend12, sonntag11, andreas);
        boot2.addReservation(sonnabend12, sonntag11, andreas);
        boot2.addReservation(sonntag2025, montag2025, andreas);
        boot1.addReservation(sonntag2025, montag2025, andreas);
        boot5.addReservation(sonntag2025, montag2025, peter);

        boot3.addReservation(sonntagNach11, montag12, anton);

        boot3.printReservations();

        aquaCodes.printKunden();

        System.out.println("");
        aquaCodes.printFlotte();
        System.out.println("");

        System.out.println(andreas.favoriteBoat().getName());
        System.out.println(andreas.unfavoriteBoat().getName());
        

    }
}
