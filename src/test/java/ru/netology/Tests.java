package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Tests {

    @Test
    void callBackServicePositiveTest() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+79999991111");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void invalidNameTest() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Maria Ivanova");
        form.$("[data-test-id=phone] input").setValue("+79999991111");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=name].input").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void invalidPhoneTest() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+7999999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=phone].input").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void emptyFormTest() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("");
        form.$(".button__content").click();
        $("[data-test-id=name].input").shouldHave(text("Поле обязательно для заполнения"));
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$(".button__content").click();
        $("[data-test-id=phone].input").shouldHave(text("Поле обязательно для заполнения"));
        form.$("[data-test-id=phone] input").setValue("+79999991111");
        form.$(".button__content").click();
        $("[data-test-id=agreement].input_invalid").should(exist);
    }
}
