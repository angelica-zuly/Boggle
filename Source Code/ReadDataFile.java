package inputOutput;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadDataFile implements IReadDataFile
{
    private Scanner fileScan;
    private String fileName;
    private String BOOGLE_DATA_PATH = "../data/";
    private ArrayList<String> fileData = new ArrayList<String>();

    //Custom constructor to ReadDataFile
    public ReadDataFile(String file)
    {
        fileName = file;
    }

    /**
     * Method loops through dice data file until the end.
     */
    public void populateData()
    {
        try {
            URL url = getClass().getResource(BOOGLE_DATA_PATH + fileName);
            Scanner fileScan = new Scanner (new File(url.toURI()));

           //Returns true if there is another line
           while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                fileData.add(line);
            }
            fileScan.close();

        } catch (IOException e) {
            System.out.println("IO ERROR: " + e);
        } catch (URISyntaxException e) {
            System.out.println("URI SYNTAX ERROR: " + e);
        }
    }

    public ArrayList<String> getData()
    {
        return fileData;
    }
}