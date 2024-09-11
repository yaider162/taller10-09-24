package uptc.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetParent {
    private String emailAdress;
    private Long phoneNumber;
    private String name;
    private String lastName;

    @Override
    public String toString() {
        return "PetParent{" +
                "emailAdress='" + emailAdress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
