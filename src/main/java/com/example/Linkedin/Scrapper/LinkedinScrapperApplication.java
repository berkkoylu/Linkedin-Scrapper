package com.example.Linkedin.Scrapper;

import com.example.Linkedin.Scrapper.entity.MemberProfile;
import com.example.Linkedin.Scrapper.linkedinScrap.LinkedinLogin;
import com.example.Linkedin.Scrapper.linkedinScrap.LinkedinLoginImpl;
import com.example.Linkedin.Scrapper.linkedinScrap.LinkedinScrap;
import com.example.Linkedin.Scrapper.linkedinScrap.LinkedinScrapImpl;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LinkedinScrapperApplication implements CommandLineRunner {

	@Autowired
	LinkedinLogin linkedinLogin;
	@Autowired
	LinkedinScrap linkedinScrap;

	public static void main(String[] args) {
		SpringApplication.run(LinkedinScrapperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		WebDriver driver = linkedinLogin.loginLinkedin();
		linkedinScrap.scrapLinkedinProfile(driver, "mehmet");
	}
}
