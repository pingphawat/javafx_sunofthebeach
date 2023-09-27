package ku.cs.services;

import ku.cs.models.report.Claiming;
import ku.cs.models.report.ClaimingList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ClaimingListDataSource implements DataSource<ClaimingList> {
    private String directoryName;
    private String fileName;

    public ClaimingListDataSource(String directoryName, String fileName){
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
    public ClaimingList readData() {
        ClaimingList claimingList = new ClaimingList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                Claiming claiming = new Claiming(data[0].trim(), data[1].trim());
                claimingList.addClaiming(claiming);
            }
        } catch(FileNotFoundException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        } finally{
            try{
                buffer.close();
                reader.close();
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        return claimingList;
    }

    @Override
    public void writeData(ClaimingList claimingList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            for(Claiming claiming : claimingList.getAllClaimings()){
                String line = claiming.toCSV();
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
