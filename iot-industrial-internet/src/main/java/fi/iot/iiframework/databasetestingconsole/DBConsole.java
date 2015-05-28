/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.databasetestingconsole;

import fi.iot.iiframework.errors.dao.ErrorDAO;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;

public class DBConsole {

    @Autowired
    ErrorDAO eDAO;

    public String response;
    private final Scanner scan;
    private String input;

    public DBConsole() {
        response = "What do you want to enter to the database?";
        scan = new Scanner(System.in);
        input = " ";

    }

    public void run() {
        while (input.equals("exit") == false) {
            System.out.println(response);
            input = scan.nextLine();
            input = input.trim().toLowerCase();

            if (input.equals("error")) {
                response = "Lets create an error!";
                
            } else if (input.equals("source")) {
                response = "Not implemented :(";
                
            } else {
                response = "Not a valid command!";
                
            }

        }
        System.out.println(response = "Console stopped!");

    }

}
