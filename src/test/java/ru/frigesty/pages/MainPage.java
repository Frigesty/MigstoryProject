package ru.frigesty.pages;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainPage {

    private final SelenideElement slides = $(".slides"),
                              nextButton = $(".flex-nav-next");

    @Step("Открываем главную страницу")
    public MainPage openPage() {
        open("");

        return this;
    }

    @Step("Вводим значение '{value}' в поле поиска")
    public MainPage setValueInSearchField(String value) {
        $(".search-input").setValue(value);

        return this;
    }

    @Step("Проверяем, что в превью поиска есть результат '{value}'")
    public MainPage searchPreviewCheck(String value) {
        $(".title-search-result .scrollbar").shouldHave(text(value));

        return this;
    }

    @Step("Открываем контекстное меню каталога")
    public MainPage openContextMenuForCatalogItem() {

        $(".catalog").hover();
        return this;
    }

    @Step("Наводим на кнопку '{button}' в меню каталога")
    public MainPage hoverOverButtonWithText(String button) {

        $$(".font_xs").findBy(text(button)).hover();
        return this;
    }

    @Step("Проверяем, что в контент-меню есть текст '{value}'")
    public MainPage verifyMenuContentContainsText(String value) {

        $(".menu-navigation__content").shouldHave(text(value));
        return this;
    }

    @Step("Кликаем на элемент меню '{button}'")
    public MainPage clickVisibleMenuItemWithText(String button) {

        $$(".menu-item .wrap").findBy(text(button)).shouldBe(visible).click();
        return this;
    }

    @Step("Скачиваем PDF-файл")
    public PDF downloadPdfFile() throws IOException {
        File downloadedPdf = $("[href='/upload/price_1.pdf']").download();

        return new PDF(downloadedPdf);
    }

    @Step("Проверяем, что PDF-файл содержит {expectedPages} страниц")
    public MainPage verifyNumberOfPages(PDF pdf, Integer expectedPages) {
        assertThat(pdf.numberOfPages).isEqualTo(expectedPages);
        return this;
    }

    @Step("Проверяем автора PDF-файла: '{expectedAuthor}'")
    public MainPage verifyPdfAuthor(PDF pdf, String expectedAuthor) {
        assertThat(pdf.author).isEqualTo(expectedAuthor);
        return this;
    }

    @Step("Проверяем производителя PDF-файла: '{expectedProducer}'")
    public MainPage verifyPdfProducer(PDF pdf, String expectedProducer) {
        assertThat(pdf.producer).isEqualTo(expectedProducer);
        return this;
    }

    @Step("Проверяем, что PDF-файл содержит текст '{expectedText}'")
    public MainPage verifyPdfContainsText(PDF pdf, String expectedText) {
        assertThat(pdf.text).contains(expectedText);
        return this;
    }

    @Step("Проверяем текст на слайде: '{value}'")
    public MainPage verifySliderText(String value) {
        slides.shouldHave(text(value));

        return this;
    }

    @Step("Наводим курсор на слайд")
    public MainPage hoverOnSlide() {
        slides.hover();

        return this;
    }

    @Step("Нажимаем кнопку 'Следующий слайд'")
    public MainPage nextSliderClick() {
        nextButton.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Ждем 1 секунду")
    public MainPage sleepOneSecond() {
        sleep(1000);

        return this;
    }
}