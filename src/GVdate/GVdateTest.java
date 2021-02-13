package GVdate;

/**
 * A Class to Test GVdate
 *
 * @author Anna Carvalho
 * @version 10/16/20
 */

public class GVdateTest {
    public static void main (String []  args) {
        int errors = 0;
        System.out.println ("Testing begins\n");

        GVdate today = new GVdate();
        if (today.getMonth() != 10){
            System.out.println("month should be 10");
            errors++;
        }
        if (today.getDay() != 12){
            System.out.println("day should be 12");
            errors++;
        }

        System.out.println("Testing constructor 1");
        System.out.println("=====================");
        System.out.println("Correct date: 10/12/2020 || Actual: " + today.toString(1) + "\n");
        
        GVdate theDay1 = new GVdate(1, 1, 2001);
        System.out.println("Testing constructor 2");
        System.out.println("=====================");
        System.out.println("Correct date: 1/1/2001 || Actual: " + theDay1.toString(1) + "\n");
        
        GVdate theDay = new GVdate("01/02/2020");
        System.out.println("Testing constructor 3");
        System.out.println("=====================");
        System.out.println("Correct date: 1/2/2020 || Actual: " + theDay.toString(1) + "\n");

        theDay.setMonth(12);
        System.out.println("Testing setMonth & getMonth");
        System.out.println("===========================");
        System.out.println("Correct month: 12 || Actual: " + theDay.getMonth() + "\n");
        
        theDay.setDay(1);
        System.out.println("Testing setDay & getDay");
        System.out.println("=======================");
        System.out.println("Correct day: 1 || Actual: " + theDay.getDay() + "\n");

        theDay.setYear(2001);
        System.out.println("Testing setYear & getYear");
        System.out.println("=========================");
        System.out.println("Correct year: 2001 || Actual: " + theDay.getYear() + "\n");

        theDay.setDate(12,31,1963);
        System.out.println("Testing toString");
        System.out.println("================");
        System.out.println("Format 1: " + theDay.toString(1));
        System.out.println("Format 2: " + theDay.toString(2));
        System.out.println("Format 3: " + theDay.toString(3));
        System.out.println("Format 4: " + theDay.toString(4));
        System.out.println("Invalid Parameter: " + theDay.toString(5) + "\n");
        
        System.out.println("Testing isMyBirthday");
        System.out.println("====================");
        System.out.println("Is 01/01/0020 my birthday? " + theDay.isMyBirthday());
        theDay.setDate(1, 9, 2020);  
        System.out.println("Is 01/09/2020 my birthday? " + theDay.isMyBirthday() + "\n");
          
        System.out.println("Testing isLeapYear");
        System.out.println("==================");
        System.out.println("Is 1900 a leap year? " + theDay.isLeapYear(1900));
        System.out.println("Is 1600 a leap year? " + theDay.isLeapYear(1600) + "\n");
        
        System.out.println("Testing isValidDate");
        System.out.println("===================");
        System.out.println("Is 00/00/0000 a valid date? ");
        theDay.setDate(0, 0, 0);
        System.out.println("Is 00/09/2020 a valid date? ");
        theDay.setMonth(0);
        System.out.println("Is 01/00/2020 a valid date? ");
        theDay.setDay(0);
        System.out.println("Is 01/09/0000 a valid date? ");
        theDay.setYear(0);
        System.out.println();
        
        theDay.setDate(10, 18, 2020);
        System.out.println("Testing nextDay & skipAhead");
        System.out.println("===========================");
        System.out.println("Today: " + theDay.toString(1));
        theDay.nextDay();
        System.out.println("Tomorrow: " + theDay.toString(1));
        theDay.setDate(10, 18, 2020);
        theDay.skipAhead(7);
        System.out.println("Next week: " + theDay.toString(1));
        theDay.setDate(10, 18, 2020);
        theDay.skipAhead(30);
        System.out.println("Next month: " + theDay.toString(1) + "\n");
        
        System.out.println("Testing equals");
        System.out.println("==============");
        GVdate theDayTwo = new GVdate("10/18/2020");
        theDay.setDate(10, 18, 2020);
        System.out.println("Is 10/18/2020 equal to 10/18/2020? " + theDay.equals(theDayTwo));
        theDayTwo.setDate(12, 01, 2001);
        System.out.println("Is 10/18/2020 equal to 12/01/2001? " + theDay.equals(theDayTwo) + "\n");
        
       
        System.out.println("Errors: " + errors);
        System.out.println ("Testing ends");
    }  

}
