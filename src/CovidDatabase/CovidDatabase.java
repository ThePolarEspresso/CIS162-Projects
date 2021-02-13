package CovidDatabase;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

/***************************************************
 * Class CovidDatabase to query the Covid entries
 *
 * @author Anna Carvalho
 * @version 12/04/2020
 ****************************************************/
public class CovidDatabase {
    /** ArrayList of CovidEntry objects */
    private ArrayList <CovidEntry> db;

    /** contant - number of days to open a state safetly */
    private static final int SAFE = 5;

    /*****************************************************
     * Constructor for objects of class CovidDatabase
     ****************************************************/
    public CovidDatabase()  {
        this.db = new ArrayList <CovidEntry> ();
    }

    /*****************************************************
     * @return int - Grand Total deaths 
     ****************************************************/
    public int getTotalDeaths() {
        int sum = 0;
        for (CovidEntry c : db) {
            sum += c.getDailyDeaths();
        } 

        return sum;  // fix this   
    }

    /*****************************************************
     * @return int - Grand Total infections
     ****************************************************/
    public int getTotalInfections() {
        int sum = 0;
        for (CovidEntry c : db) {
            sum += c.getDailyInfections();
        }

        return sum;
    }

    /*****************************************************
     * @param filename - file name to read
     ****************************************************/
    public void readCovidData(String filename) {
        Scanner inFS = null; 
        FileInputStream fileByteStream = null;

        try{
            // open the File and set delimiters
            fileByteStream = new FileInputStream(filename);
            inFS = new Scanner(fileByteStream);
            inFS.useDelimiter("[,\r\n]+");

            //ignores the line of headers
            inFS.nextLine();
            // continue while there is more data to read
            while(inFS.hasNext()) {
                String s = inFS.next();
                int m = inFS.nextInt();
                int d  = inFS.nextInt();          
                int di = inFS.nextInt();
                int dd = inFS.nextInt();
                int ti = inFS.nextInt();
                int td = inFS.nextInt();
                db.add(new CovidEntry(s, m, d, di, dd, ti, td));

            }
            fileByteStream.close();
            // error while reading the file                      
        }
        catch(IOException error1) {
            System.out.println("Oops! Error related to: " + filename);
        }    
    }

    /*****************************************************
     * @return int - number of elements in the ArrayList 
     ****************************************************/
    public int countRecords() {
        return this.db.size();
    }

    /*****************************************************
     * @param m - month
     * @param d - day
     * @return int - total deaths for the month and day
     ****************************************************/
    public int countTotalDeaths(int m, int d) {
        int count = 0;
        
        for (CovidEntry c : this.db) {
            if (c.getMonth() == m && c.getDay() == d) {
                count += c.getDailyDeaths();
            }
        }
            
        return count;
    }

    /*****************************************************
     * @param m - month
     * @param d - day
     * @return int - total infections for the month and day
     ****************************************************/
    public int countTotalInfections(int m, int d) {
        int count = 0;
        
        for (CovidEntry c : this.db) {
            if (c.getMonth() == m && c.getDay() == d) {
                count += c.getDailyInfections();
            }
        }
                 
        return count;
    }

    /*****************************************************
     * @param st - state
     * @return - ArrayList of CovidEntry objects for the state
     ****************************************************/
    public ArrayList <CovidEntry> getStateEntries(String st) {
        ArrayList<CovidEntry> statesList = new ArrayList<CovidEntry>();
            
        for (CovidEntry c : this.db) {
            if (c.getState().equalsIgnoreCase(st)) {
                statesList.add(c);
            }
        }
            
        return statesList; 
    }

    /*****************************************************
     * @param st - state
     * @return CovidEntry with the highest number of deaths for the state
     ****************************************************/
    public CovidEntry peakDailyDeaths(String st) {
        CovidEntry highest = null;

        int deaths = 0;
        for (CovidEntry c : this.db) {
            if (c.getState().equalsIgnoreCase(st) && c.getDailyDeaths() > deaths) {
                highest = c;
                deaths = c.getDailyDeaths();
            }
        }

        return highest;
    }

    /*****************************************************
     * @param m -  month
     * @param d - day
     * @return - ArrayList of CovidEntry objects for the month and day
     ****************************************************/
    public ArrayList <CovidEntry> getDailyDeaths(int m, int d ) {
        ArrayList<CovidEntry> deathsList = new ArrayList<CovidEntry>();

        for (CovidEntry c : this.db) {
            if (c.getMonth() == m && c.getDay() == d) {
                deathsList.add(c);
            }
        }

        return deathsList;
    }

    /*****************************************************
     * @param m - month
     * @param d - day
     * @return CovidEntry with the highest number of deaths for month and day
     ****************************************************/
    public CovidEntry peakDailyDeaths(int m, int d) {
        CovidEntry highest = null;

        int deaths = 0;
        for (CovidEntry c : this.db) {
            if (c.getDay() == d && c.getMonth() == m && c.getDailyDeaths() > deaths) {
                highest = c;
                deaths = c.getDailyDeaths();
            }
        }

        return highest;
    }

    /*****************************************************
     * @return CovidEntry with the highest number of deaths 
     ****************************************************/
    public CovidEntry mostTotalDeaths() {
        CovidEntry mostTotalDeaths = null;

        int mostDeaths = this.db.get(0).getDailyDeaths();
        for (CovidEntry c : this.db) {
            if (c.getTotalDeaths() > mostDeaths) {
                mostTotalDeaths = c;
                mostDeaths = c.getTotalDeaths();
            }
        }

        return mostTotalDeaths;
    }

    /*****************************************************
     * @return CovidEntry with the fewest number of deaths
     ****************************************************/
    public CovidEntry fewestTotalDeaths() {
        CovidEntry fewestTotalDeaths = null;

        int fewestDeaths = Integer.MAX_VALUE;
        for (CovidEntry c : this.db) {
            if (c.getTotalDeaths() < fewestDeaths) {
                fewestTotalDeaths = c;
                fewestDeaths = c.getTotalDeaths();
            }
        }

        return fewestTotalDeaths;
    }

    /*****************************************************
     * @param m - month
     * @param d - day
     * @param min - minimum number of deaths to search for
     * @return ArrayList <CovidEntry> CovidEntries for the month, day with
     * a minimum of infections
     ****************************************************/
    public ArrayList <CovidEntry> listMinimumDailyInfections(int m, int d, int min) {
        ArrayList <CovidEntry> minimums = new ArrayList<CovidEntry>();
        
        for (CovidEntry c : this.db) {
            if (c.getMonth() == m && c.getDay() == d && c.getDailyInfections() >= min) {
                minimums.add(c);
            }
        }
        
        return minimums;
    }

    /*****************************************************
     * @param st - state
     * @return ArrayList <CovidEntry> consecutive five CovidEntries with descending daily
     * infections - safe to open
     ****************************************************/
    public ArrayList <CovidEntry> safeToOpen(String st) {
        ArrayList <CovidEntry> data = this.getStateEntries(st);
        ArrayList <CovidEntry> safe = new ArrayList <CovidEntry>();
        
        int count = 1;
        for (int n = 0; n < data.size() - 1; n++) {
            if (data.get(n + 1).getDailyInfections() < data.get(n).getDailyInfections()) {
                safe.add(data.get(n));
                count++;
                if (count == SAFE) {
                    safe.add(data.get(n + 1));
                    return safe;
                }
            } else {
                count = 1;
                safe.clear();
            }
        }

        return null;
    }
    
    /*****************************************************
     * @param m - month
     * @param d - day
     * @return ArrayList <CovidEntry> Top 10 number of deaths CovidEntries for the month and day 
     ****************************************************/
    public ArrayList <CovidEntry> topTenDeaths(int m, int d) {
        ArrayList <CovidEntry> dailyDeaths = this.getDailyDeaths(m, d);
        ArrayList <CovidEntry> topTen = new ArrayList<CovidEntry>();

        Collections.sort(dailyDeaths);
        int topCount = 10;
        
        for (CovidEntry ce : dailyDeaths) {
            if (topCount == 0) {
                return topTen;
            }
            topTen.add(ce);
            topCount--;
        }

        return topTen;
    }
}

