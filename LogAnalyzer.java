/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Where to calculate the daily access counts.
    private int[] dailyCounts;
    // Successful access code
    private int success;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(){ 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the array object to hold the daily
        // access counts.
        dailyCounts = new int[31];
        // Set the correct access code
        success = 200;
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Create an object to analyze hourly web accesses.
     * Crea un nuevo archivo log con el nombre pedido
     */
    public LogAnalyzer(String nuevoLog){ 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the array object to hold the daily
        // access counts.
        dailyCounts = new int[31];
        // Set the correct access code
        success = 200;
        // Create the reader to obtain the data.
        reader = new LogfileReader(nuevoLog);
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData(){
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyDataWithCorrectAccess(){
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            if(entry.getState() == success){
                int hour = entry.getHour();
                hourCounts[hour]++;
            }
        }
    }
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData(){
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dailyCounts[day - 1]++;
        }
    }
    
    /** 
     * Analyze the hourly accesses in the given date
     *
     * @param day     The given day
     * @param month The given month
     * @param year  The given year
     */
    public void analyzeDateData(int day, int month, int year){
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            if (day == entry.getDay() && month == entry.getMonth() && year == entry.getYear()){
                int hour = entry.getHour();
                hourCounts[hour]++;
            }
        }
    }
    
    /**
     * Devuelve el numero total de accesos registrados en el archivo de log
     */
    public int numberOfAccesses(){
        int contador = 0;
        for (int registro : hourCounts){
            contador += registro;
        }
        return contador;
    }
    
    /**
     * Devuelve la hora con el mayor numero de accesos registrados
     */
    public int busiestHour(){
        int indice = 0;
        for (int index = 0; index < hourCounts.length; index++){
            if (hourCounts[index] > hourCounts[indice]){
                indice = index;
            }
        }
        return indice;
    }
    
    /**
     * Devuelve la hora con el menor numero de accesos registrados
     */
    public int quietestHour(){
        int indice = 0;
        for (int index = 0; index < hourCounts.length; index++){
            if (hourCounts[index] < hourCounts[indice]){
                indice = index;
            }
        }
        return indice;
    }
    
    /**
     * Calcula las 2 horas seguidas cuya suma de registros es la mayor del dia
     * Devuelve la primera hora de las dos
     */
    public int highestTwoHoursLoad(){
        int indice = 0;
        for (int index = 0; index < (hourCounts.length - 1); index++){
            if ((hourCounts[indice] + hourCounts[indice + 1]) < (hourCounts[index] + hourCounts[index + 1])){
                indice = index;
            }
        }
        return indice;
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts(){
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour < hourCounts.length){
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the daily counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printDailyCounts(){
        System.out.println("Day: Count");
        int day = 0;
        while(day < dailyCounts.length){
            System.out.println((day + 1) + ": " + dailyCounts[day]);
            day++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
