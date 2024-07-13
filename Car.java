
public class Car extends Mietobjekt{

    private String type;

    public Car(String name, String type, int personCount, boolean isLicenceRequired, double pricePerHour) {
        super(name, personCount, isLicenceRequired, pricePerHour);
        setType(type);
       
    }
    

  
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
