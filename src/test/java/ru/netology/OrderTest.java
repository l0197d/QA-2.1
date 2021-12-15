package ru.netology;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class OrderTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        inputs.find(Condition.attribute("type","tel")).setValue("+71234567890");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void LatinName() {
        open("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Ivanov Petr");
        $(".button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldWriteNumberTwelveSymbols(){
        open("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        inputs.find(Condition.attribute("type","tel")).setValue("+790926809090");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldWriteNumberWithoutPlus() {
        open("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        inputs.find(Condition.attribute("type","tel")).setValue("89092680909");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldFieldsEmpty(){
        open("http://localhost:9999");
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCheckboxEmpty(){
        open("http://localhost:9999");
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        inputs.find(Condition.attribute("type","tel")).setValue("+79092680909");
        $(".button").click();
        $(".input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}



