package com.skillmineauth.common;

import com.microsoft.playwright.Page;
import com.java.auth.Factory.PlaywrightFactory;
import com.skillmineauth.projects.auth.admin.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    PlaywrightFactory pf;
    Page page;
    protected Properties prop;
    protected static LoginPageAuthAdmin loginPage;
    protected static ScopesPage scopesPage;
    protected static ClientsSettingsPage clientsSettingsPage;
    protected static SchemasPage schemasPage;
    protected static PasswordPolicyPage passwordPolicyPage;
    protected static BlueprintsPage blueprintsPage;
    public static final Logger log = LogManager.getLogger(BaseTest.class);
    public static SoftAssert asserts = new SoftAssert();

    @BeforeSuite(alwaysRun = true)
    public void setup() {

        log.info("Starting test setup");
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        page = pf.initBrowser(prop);
        loginPage = new LoginPageAuthAdmin(page);
        scopesPage = new ScopesPage(page);
        clientsSettingsPage=new ClientsSettingsPage(page);
        schemasPage=new SchemasPage(page);
        passwordPolicyPage=new PasswordPolicyPage(page);
        blueprintsPage=new BlueprintsPage(page);
        log.info("Setup completed successfully");
    }


    @AfterSuite
    public void tearDown() {
        try {
            if (page != null) {
                log.info("Closing browser");
                page.context().browser().close();
            }
        } catch (Exception e) {
            log.error("Teardown failed: " + e.getMessage());
        }
    }
}
