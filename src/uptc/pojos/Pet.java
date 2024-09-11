package uptc.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet {
    private String name;
    private PetType petType;
    private int weight;

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", petType=" + petType +
                ", weight=" + weight +
                '}';
    }
}
