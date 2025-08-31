package com.java.auth.Factory;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class PlaywrightFactory {

    Properties prop;
    private static Logger log;
    private HashMap<String, String> environmentUrls;
    private String environment;
    private String url;


    // ThreadLocal which create the local copy of object for better test management

    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

    public static Playwright getPlayWright(){
        return tlPlaywright.get();
    }
    public static Browser getBrowser(){
        return tlBrowser.get();
    }
    public static BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }
    public static Page getPage(){
        return tlPage.get();
    }
    public Page initBrowser(Properties prop){
        log = LogManager.getLogger();
        int maxRetries = 3;
        int retryCount = 0;
        boolean success = false;
        String browserName=prop.getProperty("browserName").trim();
        log.info("Executing test scripts in {} browser",browserName);
        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());
        Path traceDirectory = Paths.get("src/build/");
        // Initialize the environment-to-URL mapping
        environmentUrls = new HashMap<>();
        environmentUrls.put("automation", "https://portal-demo-accounts-qa.licentio.com/");
        environmentUrls.put("qa", "https://portal-demo-accounts.licentio.com/load");
        environmentUrls.put("prod", "https://portal-demo-accounts.licentio.com/load");
        environment = System.getProperty("environment", "qa");

        // Get the URL corresponding to the environment
        url = environmentUrls.get(environment);

        switch (browserName.toLowerCase()){
            case "chromium":
                // browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
                tlBrowser.set(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(prop.getProperty("headless"))).setSlowMo(300).setTracesDir(traceDirectory)));
                break;

            case "firefox":
                // browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlayWright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(prop.getProperty("headless")))));
                break;

            case "safari":
                //browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlayWright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(prop.getProperty("headless")))));
                break;

            case "chrome":
                //browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                tlBrowser.set(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(Boolean.parseBoolean(prop.getProperty("headless"))).setSlowMo(300)));
                break;

            default:
                System.out.println("Please pass the correct browser name");
                break;
        }
        tlBrowserContext.set(getBrowser().newContext());
        tlBrowserContext.get().tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
        tlBrowserContext.get().grantPermissions(Arrays.asList("notifications"));
        tlPage.set(getBrowserContext().newPage());
        //getPage().navigate(prop.getProperty("url").trim());
        // getPage().navigate(url);
        while (!success && retryCount < maxRetries) {
            try {
                // Perform URL launching action here
                getPage().navigate(url);
                success = true; // Set success flag if action succeeds
            } catch (Exception e) {
                // Log error message or perform additional error handling
                System.out.println("Error occurred: " + e.getMessage());
                retryCount++;
                // Wait for a short duration before retrying
                getPage().waitForTimeout(1_000);// Adjust timing as needed
            }
        }

        //getPage().waitForURL(prop.getProperty("url").trim());
        return getPage() ;

    }

    /**
     * This method used to initialize the properties from config file
     */
    public Properties init_prop() {
        try{
            FileInputStream ip= new FileInputStream("src/test/resources/config/config.properties");
            prop= new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }










}
