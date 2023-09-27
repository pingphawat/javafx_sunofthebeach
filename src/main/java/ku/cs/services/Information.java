package ku.cs.services;

import ku.cs.models.account.Admin;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.Officer;
import ku.cs.models.report.Claiming;
import ku.cs.models.report.InappropriateReport;
import ku.cs.models.report.Report;

public class Information {
    private Admin admin;
    private Nisit nisit;
    private Officer officer;
    private Report report;
    private Claiming claiming;
    private InappropriateReport inappropriateReport;

    public Information(){
        admin = null;
        nisit = null;
        officer = null;
        report = null;
        claiming = null;
        inappropriateReport = null;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Nisit getNisit() {
        return nisit;
    }

    public void setNisit(Nisit nisit) {
        this.nisit = nisit;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Claiming getClaiming() {
        return claiming;
    }

    public void setClaiming(Claiming claiming) {
        this.claiming = claiming;
    }

    public InappropriateReport getInappropriateReport() {
        return inappropriateReport;
    }

    public void setInappropriateReport(InappropriateReport inappropriateReport) {
        this.inappropriateReport = inappropriateReport;
    }
}
