package ku.cs.models.report;

public class InappropriateReport {
    private String type;
    private String detail;
    private Report report;

    public InappropriateReport(String type, String detail, Report report) {
        this.type = type;
        this.detail = detail;
        this.report = report;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Report getReport() {
        return report;
    }

    @Override
    public String toString() {
        return detail;
    }

    public String toCSV(){
        String reportStr = report.toCSV().replace(",", ";");
        return type + "," + detail + "," + reportStr;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        InappropriateReport inappropriateReport = (InappropriateReport) obj;
        if(this.detail.equals(inappropriateReport.getDetail()) && this.type.equals(inappropriateReport.getType()) && this.report.equals(inappropriateReport.getReport()))
            return true;
        return false;
    }
}
