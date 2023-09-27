package ku.cs.models.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Report {
    private String title;
    private String detail;
    private String type;
    private String typeDetail;
    private String dateTime;
    private String nisitUsername;
    private int vote;
    private String status;
    private String officerUsername;
    private String fixedDetail;
    private ArrayList<String> nisitVoted;

    public Report(String title, String detail, String type, String typeDetail, String dateTime, String nisitUsername) {
        this.title = title;
        this.detail = detail;
        this.type = type;
        this.typeDetail = typeDetail;
        this.dateTime = dateTime;
        this.nisitUsername = nisitUsername;
        vote = 0;
        status = "ยังไม่จัดการ";
        officerUsername = "-";
        fixedDetail = "-";
        nisitVoted = new ArrayList();
    }

    public Report(String title, String detail, String type, String typeDetail, String dateTime, String nisitUsername, int vote, String status, String officerUsername, String fixedDetail, ArrayList<String> nisitVoted) {
        this.title = title;
        this.detail = detail;
        this.type = type;
        this.typeDetail = typeDetail;
        this.dateTime = dateTime;
        this.nisitUsername = nisitUsername;
        this.vote = vote;
        this.status = status;
        this.officerUsername = officerUsername;
        this.fixedDetail = fixedDetail;
        this.nisitVoted = nisitVoted;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getType() {
        return type;
    }

    public String getTypeDetail(){
        return typeDetail;
    }

    public String getDateTime(){return dateTime;}

    public String getNisitUsername() {
        return nisitUsername;
    }

    public int getVote() {
        return vote;
    }

    public String getStatus() {
        return status;
    }

    public String getOfficerUsername() {
        return officerUsername;
    }

    public String getFixedDetail() {
        return fixedDetail;
    }

    public ArrayList<String> getNisitVoted(){
        return nisitVoted;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOfficerUsername(String officerUsername) {
        this.officerUsername = officerUsername;
    }

    public void setFixedDetail(String fixedDetail) {
        this.fixedDetail = fixedDetail;
    }

    @Override
    public String toString() {
        return title;
    }

    public LocalDateTime getLocalDateTime() {
        dateTime = getDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dtf);
        return localDateTime;
    }

    public boolean votedBy(String username){
        for(String nisit : nisitVoted){
            if(nisit.compareTo(username) == 0){
                return false;
            }
        }
        vote += 1;
        nisitVoted.add(username);
        return true;
    }

    public String toCSV(){
        String voted;
        if(nisitVoted.size() > 0) {
            voted = "";
            for (int i = 0; i < nisitVoted.size() - 1; i++) {
                voted += nisitVoted.get(i) + "-";
            }
            voted += nisitVoted.get(nisitVoted.size() - 1);
        } else{
            voted = null;
        }
        return title + "," + detail + "," + type + "," + typeDetail + "," + dateTime + "," + nisitUsername + "," + vote + "," + status + "," + officerUsername + "," + fixedDetail + "," + voted;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            Report report = (Report) obj;
            boolean checkTitle = title.compareTo(report.getTitle()) == 0;
            boolean checkDetail = detail.compareTo(report.getDetail()) == 0;
            boolean checkType = type.compareTo(report.getType()) == 0;
            boolean checkTypeDetail = typeDetail.compareTo(report.getTypeDetail()) == 0;
            boolean checkDateTime = dateTime.compareTo(report.getDateTime()) == 0;
            boolean checkNisitUsername = nisitUsername.compareTo(report.getNisitUsername()) == 0;

            return checkTitle && checkType && checkTypeDetail && checkDateTime && checkNisitUsername && checkDetail;
        }
    }

    public boolean isFinished(){
        if(status.equals("จัดการแล้ว")){
            return true;
        }
        return false;
    }

    public boolean isOnProgress(){
        if(status.equals("กำลังดำเนินการ")){
            return true;
        }
        return false;
    }

    public boolean isNotStarted(){
        if(status.equals("ยังไม่จัดการ")){
            return true;
        }
        return false;
    }
}
