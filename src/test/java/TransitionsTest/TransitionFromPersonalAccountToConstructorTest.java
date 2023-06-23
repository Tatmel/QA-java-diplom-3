package TransitionsTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.HomePageStellarBurgers;
import pageObject.LoginPageStellarBurgers;
import pageObject.PersonalAccountPageStellarBurgers;
import pageObject.RegisterPageStellarBurgers;

import java.util.Random;

public class TransitionFromPersonalAccountToConstructorTest {
        private WebDriver driver;
        private String email;
        private String password;
        private String name;

        @Before
        public void setUp() {
            //драйвер для браузера Chrome
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-notifications");
            this.driver = new ChromeDriver(options);

            /**драйвер для яндекс браузера
            System.setProperty("webdriver.chrome.driver","укажи_путь_к_драйверу");
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            this.driver = new ChromeDriver(service);
            */

            //регистрация пользователя
            driver.get("https://stellarburgers.nomoreparties.site/register");
            RegisterPageStellarBurgers objRegisterPage = new RegisterPageStellarBurgers(driver);
            Random random = new Random();
            email = "tatmel" + random.nextInt(100000000) + "@yandex.ru";
            name = "test";
            password = "123456";
            objRegisterPage.register(name, email, password);
            //логин
            LoginPageStellarBurgers objLoginPage = new LoginPageStellarBurgers(driver);
            objLoginPage.login(email, password);

            //переход в личный кабинет
            HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
            objHomePage.clickButtonPersonalAccount();
        }

        @Test
        @Step("Перейти из личного кабинета в конструктор через кнопку 'Конструктор'")
        public void testClickConstructorFromPersonalAccount() {
            PersonalAccountPageStellarBurgers objAccountPage = new PersonalAccountPageStellarBurgers(driver);
            //клик по разделу конструктор
            objAccountPage.clickButtonConstructor();
            String actual = driver.getCurrentUrl();
            String expected = "https://stellarburgers.nomoreparties.site/";
            Assert.assertEquals("Ошибка: пользователь находится не в конструкторе", expected, actual);
        }

        @Test
        @Step("Перейти из личного кабинета в конструктор через клик по логотипу в хедере")
        public void testClickLogoFromPersonalAccount() {
            PersonalAccountPageStellarBurgers objAccountPage = new PersonalAccountPageStellarBurgers(driver);
            //клик по логотипу
            objAccountPage.clickLogoStellarBurgers();
            String actual = driver.getCurrentUrl();
            String expected = "https://stellarburgers.nomoreparties.site/";
            Assert.assertEquals("Ошибка: пользователь находится не в конструкторе", expected, actual);
        }
        @After
        public void teardown() {
            driver.quit();
        }
}
