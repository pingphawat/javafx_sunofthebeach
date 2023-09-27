package ku.cs.models.report;

public class Claiming {
    private String reason;
    private String username;

    public Claiming(String username, String reason){
        this.reason = reason;
        this.username = username;
    }

    public String getReason(){
        return reason;
    }

    public String getUsername(){
        return username;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public String toString() {
        return reason;
    }

    public String toCSV(){
        return username + "," + reason;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        Claiming claiming = (Claiming) obj;
        if(this.reason.equals(claiming.getReason()) && this.username.equals(claiming.getUsername()))
            return true;
        return false;
    }
}
