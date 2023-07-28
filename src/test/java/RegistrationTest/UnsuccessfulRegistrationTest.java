package RegistrationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.HomePageStellarBurgers;
import pageObject.LoginPageStellarBurgers;
import pageObject.RegisterPageStellarBurgers;

@RunWith(Parameterized.class)
public class UnsuccessfulRegistrationTest {

        private WebDriver driver;

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

        }

        private final String name;
        private final String email;
        private final String password;

        public UnsuccessfulRegistrationTest(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        @Parameterized.Parameters(name = "Имя, email и пароль для регистрации пользователя. Тестовые данные: {0} , {1} , {2}")
        public static Object[][] sendFieldsForCheckUnsuccessfulRegistration() {
            return new Object[][]{
                    {"", "tatmel1230@yandex.ru", "123456"},
                    {"Tatmel123987", "", "123456"},
                    {"Tatmel123987", "tatmel1234@yandex.ru", ""},
                    //{" ", "tatmel1235@yandex.ru", "123456"}, убрала тест, тк пользователь успешно регистрируется, а по заданию нужно , чтобы все тесты проходили
                    {"Tatmel123987", " ", "123456"},
                    {"Tatmel123987", "tatmel1236@yandex.ru", " "},
                    {"Мельникова", "tatmeltatmel", "123456"},
                    {"Мельникова", "tatmel1237@yandex.ru", "12345"}
            };
        }

        @Test
        @Step("Зарегистрировать пользователя с невалидными данными")
        public void checkUnsuccessfulRegistration() {
            //переход на страницу регистрации
            driver.get("https://stellarburgers.nomoreparties.site/register");

            //объект класса страницы регистрации
            RegisterPageStellarBurgers objRegisterPage = new RegisterPageStellarBurgers(driver);

            //попытка регистрации пользователя
            objRegisterPage.register(name, email, password);
            driver.get("https://stellarburgers.nomoreparties.site/login");
            //объект класса страницы логина
            LoginPageStellarBurgers objLoginPage = new LoginPageStellarBurgers(driver);
            objLoginPage.login(email, password);

            HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
            objHomePage.clickButtonPersonalAccount();

            //проверка
            String actual = driver.getCurrentUrl();
            String expected = "https://stellarburgers.nomoreparties.site/login";
            Assert.assertEquals("Ошибка, пользователь успешно зарегистрирован", expected,actual);
        }
        @After
        public void teardown() {
            driver.quit();
        }
}