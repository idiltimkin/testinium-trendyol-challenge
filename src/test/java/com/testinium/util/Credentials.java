package com.testinium.util;

import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;

public class Credentials {

    private String email;
    private String password;

    private static Credentials instance = null;

    private Credentials(){
        String[] line = getFirstLine();
        this.email = line[0];
        this.password = line[1];
    }

    public static Credentials getInstance(){
        if(instance == null)
            instance = new Credentials();
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private String[] getFirstLine(){
        File file = new File("src/test/resources/users.csv");
        CsvParserSettings settings = new CsvParserSettings();
        settings.setNumberOfRowsToSkip(1);
        return new CsvParser(settings).parseAll(file).get(0);
    }
}
