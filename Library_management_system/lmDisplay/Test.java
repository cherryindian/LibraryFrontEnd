package lmDisplay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        // Sample data
        String returnDateString = "2023-12-20";
        boolean isReturned = false;

        // Current date
        Date currentDate = new Date();

        // Parse return date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date returnDate = dateFormat.parse(returnDateString);

            // Check if the book is returned and if the return date is before the current
            // date
            if (!isReturned && returnDate.before(currentDate)) {
                // Calculate fine based on overdue days
                long daysOverdue = TimeUnit.DAYS.convert(currentDate.getTime() - returnDate.getTime(),
                        TimeUnit.MILLISECONDS);
                int fine = (int) (daysOverdue * 5);
                System.out.println("Fine: " + fine);
            } else {
                System.out.println("No fine");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
