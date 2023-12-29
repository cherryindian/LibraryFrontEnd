package lmMain;

import java.time.LocalDate;

public class Sample {
    // main method
    public static void main(String[] args) {

        // creating a LocalDate object
        // storing the current date
        LocalDate currentDate = LocalDate.now();

        System.out.println("Date 1: " + currentDate);

        // specifiedDate is assigned a specific date
        LocalDate specifiedDate = LocalDate.of(2034, 10, 10);

        System.out.println("Date 2: " + specifiedDate);

        System.out.println("On checking these 2 dates: ");

        // Use isBefore() method to check if currentDate is before specifiedDate
        System.out.println(currentDate.isBefore(specifiedDate));
    }
}
