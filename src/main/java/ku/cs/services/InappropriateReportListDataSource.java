package ku.cs.services;

import ku.cs.models.report.InappropriateReport;
import ku.cs.models.report.InappropriateReportList;
import ku.cs.models.report.Report;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class InappropriateReportListDataSource implements DataSource<InappropriateReportList> {
    private String directoryName;
    private String fileName;

    public InappropriateReportListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted(){
        File file = new File(directoryName);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public InappropriateReportList readData() {
        InappropriateReportList inappropriateReportList = new InappropriateReportList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            String line = "";
            while((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                String[] reportData = data[2].split(";");
                String[] voteData = reportData[10].trim().split("-");
                ArrayList<String> voted = new ArrayList<String>(Arrays.asList(voteData));
                Report report = new Report(reportData[0].trim(), reportData[1].trim(), reportData[2].trim(), reportData[3].trim(), reportData[4].trim(), reportData[5].trim(), Integer.parseInt(reportData[6].trim()), reportData[7].trim(), reportData[8].trim(), reportData[9].trim(), voted);
                InappropriateReport inappropriateReport = new InappropriateReport(data[0].trim(), data[1].trim(), report);
                inappropriateReportList.addInappropriateReport(inappropriateReport);
            }
        } catch(FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        } finally{
            try{
                buffer.close();
                reader.close();
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        return inappropriateReportList;
    }

    @Override
    public void writeData(InappropriateReportList inappropriateReportList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            for(InappropriateReport inappropriateReport : inappropriateReportList.getAllInappropriateReports()){
                String line = inappropriateReport.toCSV();
                buffer.append(line);
                buffer.newLine();
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        } finally{
            try{
                buffer.close();
                writer.close();
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
