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
import pageObject.RegisterPageStellarBurgers;

import java.util.Random;

@RunWith(Parameterized.class)
public class SuccessfulRegistrationTest {
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

        public SuccessfulRegistrationTest(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        @Parameterized.Parameters(name = "Имя, email и пароль для регистрации пользователя. Тестовые данные: {0} , {1} , {2}")
        public static Object[][] sendFieldsForSuccessfulRegistration() {
            Random random = new Random();
            return new Object[][]{
                    {"Tatmel123987", "tatmel" + random.nextInt(100000000) +"@yandex.ru", "123456"},
                    {"Татьяна", "tatmel" + random.nextInt(100000000) + "@gmail.com", "test123"},
                    {"Т", random.nextInt(100000000) + "@mail.ru", "_test_"},
                    {"ОченьДлинноеИмяОченьДлинноеИмяОченьДлинноеИмя", "t-melnikova" + random.nextInt(100000000) + "@ya.ru", "ТестовыйПарольТестовыйПарольТестовыйПароль"},
                    {"Имя с пробелами", random.nextInt(100000000) + "@ya.ru", "Пароль с пробелами"}
            };
        }

        @Test
        @Step("Успешно зарегистрировать пользователя")
        public void checkSuccessfulRegistration() {
            //переход на страницу регистрации
            driver.get("https://stellarburgers.nomoreparties.site/register");

            //объект класса страницы регистрации
            RegisterPageStellarBurgers objRegisterPage = new RegisterPageStellarBurgers(driver);

            //зарегистрировать пользователя
            objRegisterPage.register(name, email, password);
            //убедиться, что пользователь успешно зарегистрирован
            driver.get("https://stellarburgers.nomoreparties.site/register");
            objRegisterPage.register(name, email, password);

            String actual = objRegisterPage.findTextUserAlreadyRegistered();

            Assert.assertEquals("Ошибка: пользователь НЕ зарегистрирован", "Такой пользователь уже существует",actual);
        }

        @After
        public void teardown() {
            driver.quit();
        }
}
