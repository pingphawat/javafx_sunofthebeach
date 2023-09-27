package ku.cs.models.report;

import java.util.ArrayList;

public class InappropriateReportList {
    private ArrayList<InappropriateReport> inappropriateReports;

    public InappropriateReportList(){
        inappropriateReports = new ArrayList<>();
    }

    public void addInappropriateReport(InappropriateReport inappropriateReport){
        inappropriateReports.add(inappropriateReport);
    }

    public ArrayList<InappropriateReport> getAllInappropriateReports(){
        return inappropriateReports;
    }
}
