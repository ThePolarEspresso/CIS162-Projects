package CovidDatabase;

/********************************************
 * Testing the CovidDatabase 
 *
 * @author Ana Posada
 * @version 1.0.0 
 *************************************************/
public class CovidDatabaseTest {
    
    public static void main () {
        System.out.println ("Testing starts");
        CovidDatabase db = new CovidDatabase() ;
        db.readCovidData("covid_data.csv");

        // check number of records, total infections and total deaths
        assert db.countRecords() == 10346 : "database should have 10,346"; 
        assert db.getTotalDeaths() == 196696 : "Total deaths should be: 196,696"; 
        assert db.getTotalInfections() ==  7032090 : "infections should be: 7,032,090"; 

        // check peack daily deaths 
        CovidEntry mostDeaths = db.peakDailyDeaths(5, 5);
        assert mostDeaths.getState().equals("PA") : "State with most deaths for 5/5 is PA";
        assert mostDeaths.getDailyDeaths() ==  554 : "Deaths for 5/5 is PA: 554";
        
        // test other methods
       
        System.out.println ("Testing ends");
    }
}
