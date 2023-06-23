package LogoutTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.HomePageStellarBurgers;
import pageObject.LoginPageStellarBurgers;
import pageObject.PersonalAccountPageStellarBurgers;
import pageObject.RegisterPageStellarBurgers;

import java.util.Random;

public class LogoutTest {
        private WebDriver driver;
        private String email;
        private String name;
        private String password;

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
        }

        @Test
        @Step("Выйти из аккаунта")
        public void testLogoutPersonalAccount() {
            HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
            objHomePage.clickButtonPersonalAccount();

            PersonalAccountPageStellarBurgers objAccountPage = new PersonalAccountPageStellarBurgers(driver);
            objAccountPage.clickButtonExit();

            //ожидание загрузки страницы
            LoginPageStellarBurgers objLoginPage = new LoginPageStellarBurgers(driver);
            objLoginPage.waitLoadPage();

            String actual = driver.getCurrentUrl();
            String expected = "https://stellarburgers.nomoreparties.site/login";
            Assert.assertEquals("Ошибка, пользователь НЕ вышел из системы", expected, actual);
        }

        @After
        public void teardown() {
            driver.quit();
        }
}
