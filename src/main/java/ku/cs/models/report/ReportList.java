package ku.cs.models.report;

import ku.cs.services.Filterer;
import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> reports;

    public ReportList(){
        reports = new ArrayList<>();
    }

    public void addReport(Report report){
        reports.add(report);
    }

    public ArrayList<Report> getAllReports(){
        return reports;
    }

    public ReportList filterBy(Filterer<Report> filterer){
        ReportList filtered = new ReportList();
        for(Report report: reports){
            if(filterer.filter(report)){
                filtered.addReport(report);
            }
        }
        return filtered;
    }
}
