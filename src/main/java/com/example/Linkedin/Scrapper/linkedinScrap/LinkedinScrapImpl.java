package com.example.Linkedin.Scrapper.linkedinScrap;

import com.example.Linkedin.Scrapper.entity.MemberProfile;
import com.example.Linkedin.Scrapper.repository.MemberProfileRepository;
import com.example.Linkedin.Scrapper.utils.ConnectionUtility;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LinkedinScrapImpl implements LinkedinScrap {

    @Autowired
    ConnectionUtility connectionUtility;
    @Autowired
    private MemberProfileRepository memberProfileRepository;

    public void scrapLinkedinProfile(WebDriver driver) {

        try {

            List<String> namesList = fetchNamesList();

            for (int i = 0; i < namesList.size(); i++) {


                for (int index = 0; index < 26; index++) {
                    //    driver.get("https://www.linkedin.com/search/results/people/?facetGeoUrn=%5B%22102105699%22%5D&keywords=Abaza&origin=FACETED_SEARCH&title=IT");

                    driver.get("https://www.linkedin.com/search/results/people/?facetGeoUrn=%5B\"102105699\"%5D&keywords=" + namesList.get(i) + "&origin=GLOBAL_SEARCH_HEADER&page=" + index + "&title=IT");

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    //This will scroll the web page till end.
                    js.executeScript("window.scrollBy(0,500)");
                    Thread.sleep(1500);
                    js.executeScript("window.scrollBy(0,1000)");
                    Thread.sleep(1500);
                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

                    String pageSource = driver.getPageSource();
                    Document document = Jsoup.parse(pageSource);
                    Elements profileNotFound = document.getElementsByClass("t-20 t-black t-normal mb2");
                    Elements elements = document.getElementsByClass("search-result search-result__occluded-item ember-view");

                    if (!"No results found.".equals(profileNotFound.text())) {

                        for (Element element : elements) {
                            MemberProfile memberProfile = new MemberProfile();
                            memberProfile.setName(element.getElementsByClass("name actor-name").text());
                            memberProfile.setTitle(element.getElementsByClass("subline-level-1 t-14 t-black t-normal search-result__truncate").text());
                            memberProfile.setProfileUrl(element.getElementsByClass("search-result__result-link ember-view").attr("href"));
                            memberProfile.setLocation(element.getElementsByClass("subline-level-2 t-12 t-black--light t-normal search-result__truncate").text());

                            if (memberProfileRepository.findTopByProfileUrlEquals(memberProfile.getProfileUrl()) == null && !"".equals(memberProfile.getName())) {
                                memberProfileRepository.save(memberProfile);
                            }

                        }

                    }else{
                        break;
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public List<String> fetchNamesList() {
        Connection connection = null;
        List<String> list = new ArrayList<>();
        try {
            connection = connectionUtility.getConnection();
            connection.setAutoCommit(false);
            if (connection != null) {
                System.out.println("Connection Successful!");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select linkedin_scrapperDb.isimler.isimler from linkedin_scrapperDb.isimler where id between 1701 and 2000");
            connection.commit();


            while (resultSet.next()) {

                list.add(resultSet.getString("isimler"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }


}
