package dataProviders;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class repositoryFileReader {

    public static ArrayList<String> findElementRepo(String elementIdentifier){

        // variables
        String path = constantConfigs.locatorRepositoryPath;
        ArrayList<String> locatorInformation = new ArrayList<>();
        Fillo fillo = new Fillo();

        //Query Execute
        String strQuery = "SELECT * FROM "+constantConfigs.locatorWorksheet +" WHERE ElementID='"+elementIdentifier+"'";

        try {
            Connection connection = fillo.getConnection(path);
            Recordset recordset = connection.executeQuery(strQuery);

            while(recordset.next()){
                locatorInformation.add(recordset.getField("ElementPath"));
                locatorInformation.add(recordset.getField("Method"));
            }

            recordset.close();
            connection.close();

        } catch (FilloException e) {
            throw new RuntimeException(e);
        }

        return locatorInformation;
    }

    public static By constructElement(ArrayList<String> locatorInformation){

        String elementPath = locatorInformation.get(0);
        String method = locatorInformation.get(1);

        switch (method){
            case "By.classname":
                return new By.ByClassName(elementPath);

            case "By.xpath":
                return new By.ByXPath(elementPath);

            case "By.cssSelector":
                return new By.ByCssSelector(elementPath);

            case "By.id":
                return new By.ById(elementPath);

            case "By.name":
                return new By.ByName(elementPath);

            case "By.linkText":
                return new By.ByLinkText(elementPath);

            case "By.partialLinkText":
                return new By.ByPartialLinkText(elementPath);

            case "By.tagName":
                return new By.ByTagName(elementPath);

            default:
                throw new RuntimeException("Locator type is unknown");
        }
    }
}
