package project.persistence.model;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum HashFunctionType {

    MD5("md5"),
    MD5_256("md5_256"),
    SHA1("sha1");

    @Getter
    private String type;

    HashFunctionType(String type) {
        this.type = type;
    }

    public static List<String> getAllHashFunctions() {
        return Arrays.stream(HashFunctionType.values()).map(HashFunctionType::getType).collect(Collectors.toList());
    }

    public String getType() {
        return type;
    }
}
