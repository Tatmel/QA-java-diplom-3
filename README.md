<h1 align="center">Третье задание дипломного проекта</h1>
<h2 align ="center" style = "margin-top: -20px">Тестирование веб-приложения Stellar Burgers</h2>

  <p align="center" id="project-name">
    Яндекс.Практикум: Дипломная работа, Задание 3
    <br />
    <a href="https://github.com/Tatmel/QA-java-displom-3"><strong>git *</strong></a>
    <a href="https://stellarburgers.nomoreparties.site/"><strong>site</strong></a>
    <br />

  </p>



<!-- Содержание -->
<details>
  <summary><b>Содержание</b></summary>
  <ol>
    <li><a href="#tasks">Задачи проекта</a></li>
    <li><a href="#technologies">Использованные технологии</a></li>
    <li><a href="#start-tests">Генерирование отчета</a></li>
  </ol>
</details>



<!-- Задачи проекта -->
<h2 id="tasks">Задачи проекта</h2>

_**Общее описание:**_
<p>Тестирование функциональности веб-приложения Stellar Burgers в Google Chrome и Яндекс.Браузере.</p>

_**Протестировать:**_
1. Регистрация:
<p style="margin: 5px 30px;">a) успешная регистрация;</p>
<p style="margin: 5px 30px;">б) ошибка для некорректного пароля.</p>

2. Вход:
<p style="margin: 5px 30px;">a) вход по кнопке "Войти в аккаунт" на главной странице;</p>
<p style="margin: 5px 30px;">б) вход через кнопку "Личный кабинет";</p>
<p style="margin: 5px 30px;">в) вход через кнопку в форме регистрации;</p>
<p style="margin: 5px 30px;">г) вход через кнопку в форме восстановления пароля.</p>


3. Переход в личный кабинет:
<p style="margin: 5px 30px;">a) переход по клику на "Личный кабинет".</p>

4. Переход из личного кабинета в конструктор:
<p style="margin: 5px 30px;">a) переход по клику на "Конструктор" и на логотип Stellar Burgers.</p>

5. Выход из аккаунта:
<p style="margin: 5px 30px;">a) выход по кнопке "Выйти" в личном кабинете.</p>

6. Раздел "Конструктор":
<p style="margin: 5px 30px;">a) переход к разделу "Булки";</p>
<p style="margin: 5px 30px;">б) переход к разделу "Соусы";</p>
<p style="margin: 5px 30px;">в) переход к разделу "Начинки".</p>

<!-- Использованные технологии -->
<h2 id="technologies">Использованные технологии</h2>

В проекте были использованы следующие фреймворки/библиотеки:
* Java 11,
* JUnit 4.13.2,
* selenium-java 3.141.59
* webdrivermanager 4.4.3
* Allure-junit4 2.22.0,
* Aspectj 1.9.7

<p align="right">(<a href="#project-name">back to top</a>)</p>



<!-- Генерирование отчета -->
<h2 id="start-tests">Генерирование отчета</h2>

* mvn
  ```sh
  mvn clean test
  ```
* mvn
  ```sh
  mvn allure:serve
  ```

<p align="right">(<a href="#project-name">back to top</a>)</p>