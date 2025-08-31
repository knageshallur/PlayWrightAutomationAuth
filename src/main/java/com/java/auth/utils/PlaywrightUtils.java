package com.java.auth.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PlaywrightUtils {
    protected Page page;
    public static Logger log = LogManager.getLogger();

    public PlaywrightUtils(Page page) {
        this.page = page;
    }

    public void click(String locator) {
        page.waitForLoadState(LoadState.LOAD);
        scrollIntoViewText(locator);
        page.locator(locator).click();
    }

    public void fill(String locator, String value) {
        page.waitForLoadState(LoadState.LOAD);
        click(locator);
        page.locator(locator).clear();
        page.fill(locator, value);
    }

    public void letsWaitForTime(int time) {
        page.waitForTimeout(time);
    }

    public boolean isButtonEnable(String locator) {
        page.waitForLoadState(LoadState.LOAD);
        return page.locator(locator).isEnabled();
    }

    public boolean isTextVisible(String locator) {
        page.waitForLoadState(LoadState.LOAD);
        scrollIntoViewText(locator);
        return page.isVisible(locator);
    }

    public void scrollIntoViewText(String locator) {
        page.waitForLoadState(LoadState.LOAD);
        page.locator(locator).scrollIntoViewIfNeeded();
    }

    public static Map<String, Object> readJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = null;
        try {
            jsonData = mapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public void selectOption(String dropdownSelector, String optionType, String optionValue) {
        switch (optionType.toLowerCase()) {
            case "value":
                page.selectOption(dropdownSelector, optionValue);
                break;
            case "label":
                page.selectOption(dropdownSelector, new SelectOption().setLabel(optionValue));
                break;
            case "index":
                try {
                    int index = Integer.parseInt(optionValue);
                    page.selectOption(dropdownSelector, new SelectOption().setIndex(index));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid index value: " + optionValue, e);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid option type: " + optionType);
        }
    }

    public String alertMessage(String text) {

        // Wait for the success message element to appear
        ElementHandle successMessage = page.waitForSelector("div.toast-message[aria-label='" + text + "']", new Page.WaitForSelectorOptions().setTimeout(5000));
        String messageText = successMessage.textContent().trim();
        // Success message appeared
        System.out.println("Success message: " + messageText);
        return messageText;
    }

    public void typeForSearch(String locator, String value) {
        page.waitForLoadState(LoadState.LOAD);
        page.waitForTimeout(2_000);
        click(locator);
        page.type(locator, value);
        page.waitForLoadState(LoadState.LOAD);
        page.waitForTimeout(2_000);
    }

    public String getText(String text) {
        page.waitForLoadState(LoadState.LOAD);
        return page.locator(text).textContent();
    }

    public void clickOnEditScopeIcon(String scopeName) {
        page.waitForLoadState(LoadState.LOAD);
        page.locator("//td[.='" + scopeName + "']/..//button").click();
    }
    public void clickOnSchemaToggle(String schemaName) {
        page.waitForLoadState(LoadState.LOAD);
        page.locator("//input[@id='" + schemaName + "']/..//span").click();
    }

    public void searchAndSelectTextInDropDownList(String dropDown, String dropDownSearchBox, String searchValue, String selectLocator) {
        int maxAttempt = 5;
        int attempt = 0;
        while (attempt < maxAttempt) {
            try {
                click(dropDown);
                click(dropDownSearchBox);
                typeForSearch(dropDownSearchBox, searchValue);
                click(selectLocator);
                break;
            } catch (Exception e) {
                // Log or print an error message
                System.err.println("Failed to click select text. Retrying...");

                // Increment the attempt counter
                attempt++;

                // Add a short delay before retrying
                page.waitForTimeout(1_000); // Adjust the delay time as needed
            }

        }
    }

    public void waitForLocator(String locator) {
        page.waitForSelector(locator);
    }

    public boolean verifyFieldsData(List<String> locators, List<String> values) {
        for (int i = 0; i < locators.size(); i++) {
            String presentText = page.inputValue(locators.get(i));
            if (!values.get(i).equals(presentText)) {
                System.out.println("Data verification failed for field: " + locators.get(i));
                System.out.println("Expected: " + values.get(i) + ", Found: " + presentText);
                return false;
            }
        }
        return true;
    }

    public void clickOnDropDownAndSelectText(String dropdownLocator, String selectTextLocator) {
        int maxAttempt = 5;
        int attempt = 0;
        while (attempt < maxAttempt) {
            try {
                click(dropdownLocator);
                click(selectTextLocator);
                break;
            } catch (Exception e) {
                // Log or print an error message
                System.err.println("Failed to click select text. Retrying...");

                // Increment the attempt counter
                attempt++;

                // Add a short delay before retrying
                page.waitForTimeout(2_000); // Adjust the delay time as needed
            }

        }
    }

    public void pressKeyboardKey(String key) {
        page.keyboard().press(key);
    }

    public boolean customTextVisible(String text) {
        page.waitForLoadState(LoadState.LOAD);
        return page.locator(":text('" + text + "')").isVisible();
    }

    public void fillMultipleTexts(List<String> locators, List<String> values) {
        page.waitForLoadState(LoadState.LOAD);

        if (values.size() != locators.size()) {
            throw new IllegalArgumentException("Number of locators must match number of values");
        }

        for (int i = 0; i < values.size(); i++) {
            String value = values.get(i);
            String locator = locators.get(i);
            page.locator(locator).click();
            page.locator(locator).clear();
            page.locator(locator).scrollIntoViewIfNeeded();
            page.locator(locator).fill(value);
        }
    }


    public void setSliderValue(String selector, int value) {
        String js = String.format(
                "let el = document.querySelector('%s');" +
                        "if (el) {" +
                        "  el.value = '%d';" +
                        "  el.dispatchEvent(new Event('input'));" +
                        "  el.dispatchEvent(new Event('change'));" +
                        "}", selector, value
        );
        page.evaluate(js);
    }
}
