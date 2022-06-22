package selenium_core;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(String browser) throws Exception {
        DriverManager driverManager;

        switch (browser){
            case "CHROME":{
                driverManager = new ChromeDriverManager();
            }
            break;
            case "CHROME_H":{
                driverManager = new ChromeHeadlessDriverManager();
            }
            break;
            case "FIREFOX":{
                driverManager = new FirefoxDriverManager();
            }
            break;
            default:
                throw new Exception("Browser: " + browser + " is not supported!");
        }
        return driverManager;
    }

}
