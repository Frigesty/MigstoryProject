package ru.frigesty.pages;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.io.File;
import java.io.IOException;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainPage {

    private final SelenideElement slides = $(".slides"),
                        nextButton = $(".flex-nav-next");

    public MainPage openPage() {
        open("");

        return this;
    }

    public MainPage setValueInSearchField(String value) {
        $(".search-input").setValue(value);

        return this;
    }

    public MainPage searchPreviewCheck(String value) {
        $(".title-search-result .scrollbar").shouldHave(text(value));

        return this;
    }

    public MainPage openContextMenuForCatalogItem() {

        $(".catalog").hover();
        return this;
    }

    public MainPage hoverOverButtonWithText(String button) {

        $$(".font_xs").findBy(text(button)).hover();
        return this;
    }

    public MainPage verifyMenuContentContainsText(String value) {

        $(".menu-navigation__content").shouldHave(text(value));
        return this;
    }

    public MainPage clickVisibleMenuItemWithText(String button) {

        $$(".menu-item .wrap").findBy(text(button)).shouldBe(visible).click();
        return this;
    }

    public MainPage verifyTextOnExpectedPage(String value) {

        $(".page-top-main").shouldHave(text(value));
        return this;
    }

    public PDF downloadPdfFile() throws IOException {
        File downloadedPdf = $("[href='/upload/price_1.pdf']").download();

        return new PDF(downloadedPdf);
    }

    public MainPage verifyNumberOfPages(PDF pdf, Integer expectedPages) {
        assertThat(pdf.numberOfPages).isEqualTo(expectedPages);
        return this;
    }

    public MainPage verifyPdfAuthor(PDF pdf, String expectedAuthor) {
        assertThat(pdf.author).isEqualTo(expectedAuthor);
        return this;
    }

    public MainPage verifyPdfProducer(PDF pdf, String expectedProducer) {
        assertThat(pdf.producer).isEqualTo(expectedProducer);
        return this;
    }

    public MainPage verifyPdfContainsText(PDF pdf, String expectedText) {
        assertThat(pdf.text).contains(expectedText);
        return this;
    }

    public MainPage verifySliderText(String value) {
        slides.shouldHave(text(value));

        return this;
    }

    public MainPage hoverOnSlide() {
        slides.hover();

        return this;
    }

    public MainPage nextSliderClick() {
        nextButton.shouldBe(Condition.interactable).click();
        return this;
    }

    public MainPage sleepOneSecond() throws InterruptedException {
        Thread.sleep(1000);

        return this;
    }
}