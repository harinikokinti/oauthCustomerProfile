package com.dhilli.cowin.selinium

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Service
import java.lang.Exception


@Service
class AutomateBooking {

    init {
        System.setProperty("webdriver.chrome.driver", "/Users/dhilli/Downloads/chromedriver")
    }

    fun automateBooking(pin: Int?) {
        val driver: WebDriver = ChromeDriver()
        driver.manage().window().maximize()
        driver.get("https://selfregistration.cowin.gov.in/")

        val mobile = driver.findElement(By.id("mat-input-0"))
        mobile.sendKeys("9100161110")
        var button = driver.findElement(By.tagName("ion-button"))
        button.click()

        WebDriverWait(driver, 100).until { d ->
            val el = d.findElement(By.id("mat-input-1"))
            val value = el.getAttribute("value")
            value.length == 6
        }

        button = driver.findElement(By.tagName("ion-button"))
        button.click()

        WebDriverWait(driver, 50).until { d ->
            var element: WebElement? = d.findElement(By.xpath("//a[@href='/dashboard']"))
            if (element == null)
                false
            else
                ExpectedConditions.elementToBeClickable(element)
        }

        try {
            Thread.sleep(2000)
        } catch (e: Exception) {

        }
        val scheduleLink = driver.findElement(By.xpath("//a[@href='/dashboard']"))
        scheduleLink.click()

        var pincodeBy = By.id("mat-input-2")

        WebDriverWait(driver, 50).until { d ->
            var element: WebElement? = d.findElement(pincodeBy)
            element != null
        }

        val pinElement = driver.findElement(pincodeBy)
        pinElement.sendKeys("$pin")

        button = driver.findElement(By.tagName("ion-button"))
        button.click()

        var iconRow = By.tagName("ion-row")

        WebDriverWait(driver, 50).until { d ->
            var elements = d.findElements(iconRow)
            !elements.isNullOrEmpty()
        }

        val checkBox = driver.findElement(By.id("c1"))
        checkBox.click()
    }


}