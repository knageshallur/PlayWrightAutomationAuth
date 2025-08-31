package com.java.auth.helpers;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.Base64;
import static com.java.auth.Factory.PlaywrightFactory.getPage;

public class CaptureHelpers {
    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        return Base64.getEncoder().encodeToString(buffer);
    }
}
