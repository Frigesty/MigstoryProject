package ru.frigesty.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TopWrapperPage {

    @Step("Проверяем, что на странице есть текст '{value}'")
    public TopWrapperPage verifyTextOnPage(String value) {
        $(".page-top-main").shouldHave(text(value));
        return this;
    }
}
