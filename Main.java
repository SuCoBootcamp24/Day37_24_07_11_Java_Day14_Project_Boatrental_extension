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
        // von Fr. 14.6 bis So. 16.6 12uhr 3tage
        LocalDateTime start1 = LocalDateTime.of(2024, Month.JUNE, 14, 12, 0);
        LocalDateTime end1 = LocalDateTime.of(2024, Month.JUNE, 16, 12, 0);
        // von Fr. 5.7 7uhr bis So. 21.7 12uhr 16tage
        LocalDateTime start2 = LocalDateTime.of(2024, Month.JULY, 5, 8, 0);
        LocalDateTime end2 = LocalDateTime.of(2024, Month.JULY, 21, 12, 0);
        
        // von Fr. 19.7 7uhr bis So. 21.7 12uhr 3tage
        LocalDateTime start3 = LocalDateTime.of(2024, Month.JULY, 19, 8, 0);
        LocalDateTime end3 = LocalDateTime.of(2024, Month.JULY, 21, 12, 0);
        
        // von Mo. 29.7 7uhr bis Mo. 5.8 12uhr 14tage
        LocalDateTime start4 = LocalDateTime.of(2024, Month.JULY, 29, 8, 0);
        LocalDateTime end4 = LocalDateTime.of(2024, Month.AUGUST, 5, 12, 0);
        
        // von Mo. 1.7 7uhr bis Mo. 1.8 12uhr 32tage
        LocalDateTime start5 = LocalDateTime.of(2024, Month.JULY, 29, 8, 0);
        LocalDateTime end5 = LocalDateTime.of(2024, Month.AUGUST, 5, 12, 0);
        

        // boot3.addReservation(sonnabend12, sonntag11, peter); // Licence required!

        boot3.addReservation(start1, end1, andreas);
        boot1.addReservation(start1, end1, andreas);
        boot2.addReservation(start2, end2, andreas);
        // boot2.addReservation(start2, end3, anton); // Exeption da überschneidung || Check
        boot1.addReservation(start4, end4, andreas);
        boot5.addReservation(start3, end3, peter);
        boot5.addReservation(start4, end4, peter);
        // boot3.addReservation(start1, end1, anton); // Exeption da überschneidung || Check

        // jetsky1.addReservation(start1, end1, peter); // Exeption da keine Licence || Check
        jetsky1.addReservation(start3, end3, anton);
        // jetsky1.addReservation(start4, end4, peter); // Exeption da keine Licence || Check
        
        car1.addReservation(start5, end5, anton);
        car1.addReservation(start2, end2, andreas);
        

        aquaCodes.printAllReservation();
        System.out.println("");
        
        aquaCodes.printKunden();
        System.out.println("");

        aquaCodes.printFlotte();
        System.out.println("");

        System.out.println("das boot mit dem meisten umsatz der letzten 20 Tagen ist: " + aquaCodes.objectWithHighestRevenue(20).getName());
        System.out.println("");

        andreas.favoriteObjekt();
        
        System.out.println("");
        andreas.favoriteObjekt();
        System.out.println("");
        andreas.unfavoriteObjekt();
        System.out.println("");

        System.out.println("");
        anton.favoriteObjekt();
        System.out.println("");
        anton.unfavoriteObjekt();
        System.out.println("");

        System.out.println("");
        peter.favoriteObjekt();
        System.out.println("");
        peter.unfavoriteObjekt();
        System.out.println("");
        

        System.out.println("");
           }
}
