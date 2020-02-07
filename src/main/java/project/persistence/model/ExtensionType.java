package project.persistence.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ExtensionType {
    PREFIX("Sequential Prefix Superposition Method"),
    TRANSPOSITION("Transposition Method");

    @Getter
    private final String type;

    ExtensionType(String type) {
        this.type = type;
    }

    public static List<String> getAllExtensions() {
        return Arrays.stream(ExtensionType.values()).map(ExtensionType::getType).collect(Collectors.toList());
    }

    public String getType() {
        return type;
    }
}
