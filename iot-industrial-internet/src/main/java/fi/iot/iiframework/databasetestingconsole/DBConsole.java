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

    public DBConsole() {
        response = "Enter a new error!";
        scan = new Scanner(System.in);

    }

    public void run() {
        while (true) {
            System.out.println(response);
            String input = scan.nextLine();

            System.out.println(input);

            break;

        }

    }

}
