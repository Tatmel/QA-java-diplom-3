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

public class TransitionInPersonalAccountTest {
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

            //генерирование данных для регистрации и входа пользователя
            Random random = new Random();
            email = "tatmel" + random.nextInt(100000000) + "@yandex.ru";
            name = "test";
            password = "123456";
        }

        @Test
        @Step("Перейти в личный кабинет через кнопку 'Личный кабинет', авторизовавшись под пользователем")
        public void testClickPersonalAccountWithAuth() {
            //переход на страницу регистрации
            driver.get("https://stellarburgers.nomoreparties.site/register");

            //объект класса страницы регистрации
            RegisterPageStellarBurgers objRegisterPage = new RegisterPageStellarBurgers(driver);
            //регистрация пользователя
            objRegisterPage.register(name, email, password);
            //вход пользователя
            LoginPageStellarBurgers objLoginPage = new LoginPageStellarBurgers(driver);
            objLoginPage.login(email, password);
            //проверка клика по кнопке личный кабинет
            HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
            objHomePage.clickButtonPersonalAccount();

            //ожидание загрузки страницы
            PersonalAccountPageStellarBurgers objPersonalAcc = new PersonalAccountPageStellarBurgers(driver);
            objPersonalAcc.waitLoadPage();

            String actual = driver.getCurrentUrl();
            String expected = "https://stellarburgers.nomoreparties.site/account/profile";
            Assert.assertEquals("Ошибка: пользователь НЕ находится в личном кабинете", expected, actual);
        }

        @Test
        @Step("Перейти в личный кабинет через кнопку 'Личный кабинет' под неавторизованным пользователем")
        public void testClickPersonalAccountWithoutAuth() {
            //переход на cтартовую страницу
            driver.get("https://stellarburgers.nomoreparties.site/");

            //проверка клика по кнопке личный кабинет без авторизации
            HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
            objHomePage.clickButtonPersonalAccount();
            String actual = driver.getCurrentUrl();
            String expected = "https://stellarburgers.nomoreparties.site/login";
            Assert.assertEquals("Ошибка: пользователь НЕ находится на странице входа", expected, actual);
        }

        @After
        public void teardown() {
            driver.quit();
        }
}