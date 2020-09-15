package com.example.Linkedin.Scrapper.linkedinScrap;


import lombok.Data;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class LinkedinLoginImpl implements LinkedinLogin {


    private WebDriver driver;

    public LinkedinLoginImpl() {
        this.driver = new ChromeDriver();
    }

    public WebDriver loginLinkedin() {

        try {
            System.setProperty("webdriver.chrome.driver",  "/Users/berkkoylu/Desktop/bitbucket-JLSI20/Linkedin.Scrapper/chromedriver");
            driver.manage().window().maximize();
            driver.get("https://www.linkedin.com/login");

            WebElement username=driver.findElement(By.id("username"));
            WebElement password=driver.findElement(By.id("password"));
            WebElement login=driver.findElement(By.xpath("//button[text()='Sign in']"));

            username.sendKeys("your_mail");
            password.sendKeys("your_password");

            login.click();



        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
}
