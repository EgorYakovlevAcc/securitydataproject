package project.persistence.model;

import lombok.Getter;

public enum HashFunctionType {

    MD5("md5"),
    MD5_256("md5_256"),
    SHA1("sha1");

    @Getter
    private String type;

    private HashFunctionType(String type) {
        this.type = type;
    }
}
