package CovidDatabase;

import java.text.DecimalFormat;
/********************************************************************
 * This class represents Covid-19 statistics for a specified state
 * in the US on a particular day. 
 *
 * @author Anna Carvalho
 * @version 11/7/2020
 *******************************************************************/
public class CovidEntry implements Comparable{

    /** state 2-letter abbreviation */
    private String state;

    /** month  */
    private int month;

    /** day */
    private int day;

    /** daily infections */
    private int dailyInfections;

    /** daily deaths */
    private int dailyDeaths;

    /** total deaths up to the date*/
    private int totalDeaths;

    /** total infections up to the date*/
    private int totalInfections;

    /***************************************************************************
     * Constructor
     *  @param st 2-letter state abbreviation
     *  @param m month (1-12)
     *  @param d day (1-31)
     *  @param di daily infections
     *  @param dd daily deaths
     *  @param ti total infections
     *  @param td total deaths
     ***************************************************************************/
    public CovidEntry(String st, int m, int d, int di, int dd, int ti, int td){
        this.state = st;
        if (m <= 12 && m >= 1) {
            this.month = m;
        }
        if (d <= 31 && d >= 1) {
            this.day = d;
        }
        this.dailyInfections = di;
        this.dailyDeaths = dd;
        this.totalInfections = ti;
        this.totalDeaths = td;
    }

    /***************************************************************************
     * Allows comparison between two entries based on daily deaths.
     *  @return negative number if first object has fewer deaths, returns 0 if both
     *  entries have same number of deaths
     ***************************************************************************/
    public int compareTo(Object that){
        if (that instanceof CovidEntry) {
            CovidEntry c = (CovidEntry) that;
            if (this.dailyDeaths < c.dailyDeaths) {
                return 1;
            } else if (this.dailyDeaths == c.dailyDeaths) {
                return 0;
            } else {
                return -1;
            }
        }
        
        return -1;
    }

    /***************************************************************************
    @return daily infections
     ***************************************************************************/
    public int getDailyInfections(){

        return this.dailyInfections;
    }

    /***************************************************************************
    @return daily deaths
     ***************************************************************************/
    public int getDailyDeaths(){

        return this.dailyDeaths;
    }

    /***************************************************************************
    @return total infections
     ***************************************************************************/
    public int getTotalInfections(){

        return this.totalInfections;
    }

    /***************************************************************************
    @return total deaths
     ***************************************************************************/
    public int getTotalDeaths(){

        return this.totalDeaths;
    }

    /***************************************************************************
    @return state abbreviation
     ***************************************************************************/
    public String getState(){

        return this.state;
    }

    /***************************************************************************
    @return day 
     ***************************************************************************/
    public int getDay(){

        return this.day;
    }

    /***************************************************************************
    @return month 
     ***************************************************************************/
    public int getMonth(){

        return this.month;
    }

    /***************************************************************************
    @return a String represenation of some data (e.g. TX 4/20)
     ***************************************************************************/
    public String toString(){
        DecimalFormat df = new DecimalFormat("#,###");
        return state + " " + day + "/" + month + " " + df.format(dailyInfections) + " infections, " + df.format(dailyDeaths) + " deaths";
    }

}
