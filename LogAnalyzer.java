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
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(){ 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
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
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
