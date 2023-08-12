package PageObjectSteps;

import Utility.BasePage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

public class BaseTest extends BasePage {

    public LandingSteps landingSteps;

    public void initializedStep() {
        landingSteps = new LandingSteps();
    }

    @Parameters({"ExecutionType","BrowserName"})
    @BeforeClass(alwaysRun = true)
    public void testSetup(String ExecutionType, String BrowserName ) throws MalformedURLException {
        printDebugMessage("Execution type is: " + ExecutionType);
        printDebugMessage("Browser name is: " + BrowserName);
        openBrowserAndNavigateToParabank(ExecutionType, BrowserName);
        initializedStep();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        printDebugMessage("Test tear down");
        if (getDriver()!=null) {
            printDebugMessage("Clearing driver - SessionID: " + ((RemoteWebDriver) getDriver()).getSessionId());
            getDriver().quit();
            setDriverToNull();
            printDebugMessage("Driver cleared");
        }
    }

    public static String getTCData(String TCName, String TCKey, String jsonFileName) throws IOException, ParseException {
        String tcjValue = "";
        String jsonFileFullPath;
        System.out.println("Thread" + Thread.currentThread().getId() + " - getTCData");
        System.out.println("Thread" + Thread.currentThread().getId() + " user.dir >> " + System.getProperty("user.dir"));

        // to generate .jar files
        if (System.getProperty("user.dir").contains("target")) {
            jsonFileFullPath = System.getProperty("user.dir") + "//.." + "//TestData//" + jsonFileName + ".json";
        }
        else {
            // to run locally
            jsonFileFullPath = System.getProperty("user.dir") + "//TestData//" + jsonFileName + ".json";
        }
        System.out.println("Thread" + Thread.currentThread().getId() + " JSONFile_FullPath >> " + jsonFileFullPath);

        FileReader fr = new FileReader(jsonFileFullPath);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(fr);
        JSONArray jsonArray = (JSONArray) obj;

        for(int i=0; i<jsonArray.size(); i++) {
            JSONObject jsonDetails = (JSONObject) jsonArray.get(i);
            System.out.println("Thread" + Thread.currentThread().getId() + " JSONDetails >> " + i + jsonDetails.toString());
            if(jsonDetails.containsKey(TCName)) {
                JSONObject tcObject = (JSONObject) jsonDetails.get(TCName);
                tcjValue = (String) tcObject.get(TCKey);
                System.out.println("Thread" + Thread.currentThread().getId() + " RETURNING TCKey = " + TCKey + "  >> " + tcjValue);
            }
        }
        return tcjValue;
    }


}
