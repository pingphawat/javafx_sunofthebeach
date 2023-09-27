package ku.cs.services;

import javafx.scene.image.Image;
import ku.cs.models.account.Officer;
import ku.cs.models.account.OfficerList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class OfficerListDataSource implements DataSource<OfficerList> {
    private String directoryName;
    private String fileName;

    public OfficerListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public OfficerListDataSource(boolean doReadData) {
        if (doReadData) {
            readData();
        }
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
    public OfficerList readData() {
        OfficerList officerList = new OfficerList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                Officer officer = new Officer(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), Boolean.parseBoolean(data[4].trim()), data[5].trim(), data[6].trim());
                officerList.addOfficer(officer);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return officerList;
    }

    @Override
    public void writeData(OfficerList officerList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);

            for (Officer officer : officerList.getAllOfficers()) {
                String line = officer.toCSV();
                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Image getImage(String filepath) {
        File file = new File(filepath);
        return new Image(file.toURI().toString());
    }

    public void setImageFile(BufferedImage bi, String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (bi == null) {
            System.err.println("Image not given, finding one");
            try {
                bi = ImageIO.read(file);
            } catch (IOException ex) {
                System.err.println(filepath + " has no image, creating one");
                try {
                    file.createNewFile();
                    bi = ImageIO.read(new File("data" + File.separator + "images" + File.separator + "avatar" + File.separator + "default.png"));
                    ImageIO.write(bi, "PNG", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                ImageIO.write(bi, "PNG", file);
            } catch (IllegalArgumentException e) {
                file.delete();
                System.err.println("Cannot save picture");
            } catch (IOException e) {
                System.err.println("Can't write image");
            }
        }
    }

    public void setImageFile(BufferedImage bi, String directory, String filename) {
        setImageFile(bi,directory + File.separator + filename);
    }
}
