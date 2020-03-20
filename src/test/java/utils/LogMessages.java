package utils;
import java.util.Date;

public class LogMessages{
    static Date date = new Date();
    private final String endPoint =  date.toString() + " Endpoint initialized: >>>>>>>> %s <<<<<<<< \n";
    private final String body =  date.toString() + " Body added %s ";
    private final String headers =  date.toString() + " Header added with tag: %s and value: %s ";
    private final String status =  date.toString() + " the status obtained was: %s ";
    private final String response =  date.toString() + " response obtained was: %s ";
    private final String testStart =  date.toString() + " Test Case started ";
    private final String testFinish =  date.toString() + " Test Case finished ";

    public void onTestStart(String testCase) {
        Log.writeLog(String.format(testStart, testCase), "FINE");
    }

    public void onTestFinish(String testCase) {
        Log.writeLog(String.format(testFinish, testCase), "FINE");
    }

    public void setEndPoint(String endPoint){
        Log.writeLog(String.format(this.endPoint, endPoint), "FINE");
    }

    public void setHeaders(String tag, String value){
        Log.writeLog(String.format(this.headers, tag, value), "FINE");
    }

    public void setBody(String body){
        Log.writeLog(String.format(this.body, body), "FINE");
    }

    public void setStatus(int status){
        Log.writeLog(String.format(this.status, status), "FINE");
    }

    public void setStatus(int status, String lvl){
        Log.writeLog(String.format(this.status, status), "FINE");
    }

    public void setResponse(String response){
        Log.writeLog(String.format(this.response, response), "FINE");
    }

    public void setRequestGet(){
        Log.writeLog(date.toString() + " >>>>>>>>>  Request type GET <<<<<<<<<", "FINE");
    }

    public void setRequestPost(){
        Log.writeLog(date.toString() + " >>>>>>>>>  Request type POST <<<<<<<<<", "FINE");
    }

    public static void setError(String message) {
        message = "########################################################## \n" + date.toString()+" "+ message + "\n";
        Log.writeLog(message,"ERROR");
    }
}
