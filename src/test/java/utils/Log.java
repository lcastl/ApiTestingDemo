package utils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private static Log manager;
    static String date = "";
    static String dateUnique;
    static int i = 0;
    static Logger logger = Logger.getLogger("Test");
    static String pathFile;

    public static Log getInstance(){
        if (manager==null){
            pathFile = createFile() + "\\" + dateVerify() + "TestLog.log";
            manager = new Log();
        }
        return manager;
    }

    public static String dateVerify(){
        date = dateWorks("yyyy-MM-dd HH:mm:ss" , 0);

        i++;
        date = date.replace(" ", "");
        date = date.replace(":", "-");
        if (i == 1){
            dateUnique = date;
        }

        return dateUnique;
    }

    public static void writeLog(String message, String lvl ) {
        getInstance();
        try {
            FileHandler fh = new FileHandler(pathFile, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            switch (lvl){
                case "FINE":
                    logger.setLevel(Level.FINE);
                    break;
                case "ERROR":
                    logger.setLevel(Level.WARNING);
                    break;
            }
            logger.log(logger.getLevel(),message);
            System.out.println(message);
            fh.close();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createFile(){
        String dateFile = dateWorks("yyyy-MM-dd", 0);
        String path = "target\\evidence\\logs\\" + dateFile;
        new File(path).mkdirs();
        return  path;
    }

    public static String dateWorks(String formatDate, int days) {
        SimpleDateFormat format = new SimpleDateFormat(formatDate);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return format.format(cal.getTime());
    }
}
