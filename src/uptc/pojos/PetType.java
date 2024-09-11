package uptc.pojos;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PetType {
    CAT("Gato"),
    DOG("Perro");

    private final String displayName;

    PetType(String displayName) {
        this.displayName = displayName;
    }

    public static PetType fromDisplayName(String displayName) {
        return Arrays.stream(PetType.values())
                .filter(value -> value.getDisplayName().equals(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant co.edu.uptc.pojos.PetType." + displayName));
    }

}