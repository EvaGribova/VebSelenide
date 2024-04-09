package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class Tests {

    @Test
    void callBackServiceTest() {

        open("http://localhost:9999/");
        SelenideElement form = $(".App_appContainer__3jRx1");
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+79999991111");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void callBackServiceTest2() {

        open("http://localhost:9999/");
        SelenideElement form = $(".App_appContainer__3jRx1");
        form.$("[data-test-id=name] input").setValue("Maria Ivanova");
        form.$("[data-test-id=phone] input").setValue("+79999991111");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=name].input_invalid").shouldNot(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void callBackServiceTest3() {

        open("http://localhost:9999/");
        SelenideElement form = $(".App_appContainer__3jRx1");
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+7999999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=phone].input_invalid").shouldNot(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
