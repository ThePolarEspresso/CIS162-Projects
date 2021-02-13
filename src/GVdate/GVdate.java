package GVdate; /**
 * A Date Program
 *
 * @author Anna Carvalho
 * @version 10/16/20
 */
import java.text.DecimalFormat;
public class GVdate
{
    private int month;
    private int day;
    private int year;

    private final int MONTH = 1;
    private final int DAY = 9;

    public GVdate() {
        this.month = 10;
        this.day = 12;
        this.year = 2020;
    }

    public GVdate( int m, int d, int y ) {
        this.month = m;
        this.day = d;
        this.year = y;

        if(this.isValidDate(month, day, year)) {
            this.month = month;
            this.day = day;
            this.year = year;
        } else {
            this.month = 10;
            this.day = 12;
            this.year = 2020;
        }
    }

    public GVdate(String date) {
        int slash = date.indexOf("/");

        this.month = Integer.parseInt(date.substring(0, slash));

        date = date.substring(slash + 1, date.length());
        slash = date.indexOf("/");

        this.day = Integer.parseInt(date.substring(0, slash));

        date = date.substring(slash + 1, date.length());

        this.year = Integer.parseInt(date);

        if(this.isValidDate(month, day, year)) {
            this.month = month;
            this.day = day;
            this.year = year;
        } else {
            this.month = 10;
            this.day = 12;
            this.year = 2020;
        }
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getYear() {
        return this.year;
    }

    private String getMonthString (int m) {
        switch (m) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "Error";
        }
    }

    public String toString(int format) {
        DecimalFormat df2 = new DecimalFormat("00");
        DecimalFormat df4 = new DecimalFormat("0000");
        String monthString = this.getMonthString(this.month);

        if(format == 1) {
            return (this.month + "/" + this.day + "/" + this.year);
        } else if (format == 2) {
            return (df2.format(this.month) + "/" + df2.format(this.day) + "/" + df4.format(this.year));       
        } else if (format == 3) {
            return (monthString.substring(0, 3) + " " + this.day + ", " + df4.format(this.year));
        } else if (format == 4) {
            return (monthString + " " + this.day + ", " + df4.format(this.year));
        } else {
            return "Error";
        }
    }
    
    public String toString() {
        return (this.month + "/" + this.day + "/" + this.year);
    }

    public void nextDay() {
        if (this.day == this.getLastDayOfMonth(this.month, this.year)) {
            if (this.month == 12) {
                this.year += 1;
                this.month = 1;
                this.day = 1;
            } else {
                this.month += 1;
                this.day = 1;
            }
        } else {
            this.day += 1;
        }
    }

    public boolean isMyBirthday() {
        if (this.day ==  DAY && this.month == MONTH){
            return true;
        } else {
            return false;
        }
    }

    public boolean isLeapYear(int y) {
        if(((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean equals(GVdate otherDate) {       
        if ((this.month == otherDate.month) && (this.day == otherDate.day) && (this.year == otherDate.year)) {
            return true;
        } else {
            return false;
        }
    } 
    
    
    public void skipAhead(int numDays) {
        for ( int i = 1; i <= numDays; i++) {
            this.nextDay();
        }
    }

    private int getLastDayOfMonth(int m, int y) {
        if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
            return 31;
        } else if (m == 4 || m == 6 || m == 9 || m == 11) {
            return 30;
        } else if (m == 2) {
            if(this.isLeapYear(y)) {
                return 29;
            } else {
                return 28;
            }
        }

        return day;
    }

    private boolean isValidDate(int m, int d, int y) {
        if(y >= 1) {
            if((m >= 1) && (m <= 12)) {
                if((d >= 1) && (d <= this.getLastDayOfMonth(m, y))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void setMonth(int m) {
        if(this.isValidDate(m, this.day, this.year)) {
            this.month = m;
        } else { 
            System.out.println("Invalid Date: " + m + "/" + this.day + "/" + this.year);
        }
    }

    public void setDay(int d) {
        if(this.isValidDate(this.month, d, this.year)) {
            this.day = d;
        } else { 
            System.out.println("Invalid Date: " + this.month + "/" + d + "/" + this.year);
        }
    }

    public void setYear(int y) {
        if(this.isValidDate(this.month, this.day, y)) {
            this.year = y;
        } else { 
            System.out.println("Invalid Date: " + this.month + "/" + this.day + "/" + y);
        }
    }

    public void setDate (int m, int d, int y) {
        if(this.isValidDate(m, d, y)) {
            this.month = m;
            this.day = d;
            this.year = y;
        } else {
            System.out.println("Invalid Date: " + m + "/" + d + "/" + y);
        }
    }
}

