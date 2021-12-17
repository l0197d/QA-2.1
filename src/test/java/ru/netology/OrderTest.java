package ru.netology;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class OrderTest {

    @BeforeEach
    void setup ()
    {
        open("http://localhost:9999");
    }
    @Test
    //Positive test
    void shouldSubmitRequest() {
      //  Configuration.holdBrowserOpen = true;
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        inputs.find(Condition.attribute("type","tel")).setValue("+71234567890");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
        //Negative test
    void LatinName() {
       // Configuration.holdBrowserOpen = true;
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Ivanov Petr");
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
        //Negative test
    void shouldWriteNumberTwelveSymbols(){
        //Configuration.holdBrowserOpen = true;
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        inputs.find(Condition.attribute("type","tel")).setValue("+790926809090");
        $(".checkbox__box").click();
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
        //Negative test
    void shouldWriteNumberWithoutPlus() {
        //Configuration.holdBrowserOpen = true;
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        inputs.find(Condition.attribute("type","tel")).setValue("89092680909");
        $(".checkbox__box").click();
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
        //Negative test
    void shouldFieldsEmpty(){
        //Configuration.holdBrowserOpen = true;
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
        //Negative test
    void shouldCheckboxEmpty(){
       // Configuration.holdBrowserOpen = true;
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        inputs.find(Condition.attribute("type","tel")).setValue("+79092680909");
        $(".button").click();
        $(".input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
        //Negative test
    void shouldWithOutPhoneNumber() {
       // Configuration.holdBrowserOpen = true;
        ElementsCollection inputs = $$ (".input__control");
        inputs.find(Condition.attribute("type","text")).setValue("Иванов Петр");
        $(".button").click();
        $(".checkbox__box").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}



