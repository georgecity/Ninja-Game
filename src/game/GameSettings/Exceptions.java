package game.GameSettings;
/*
Handles exception messages when Loading and Saving
 */
public class Exceptions extends Exception{
    private String reason;

    public Exceptions(String r){
        this.reason = r;
    }

    public String getReason(){return reason;}}
