package ru.frigesty.tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.frigesty.pages.MainPage;
import static io.qameta.allure.Allure.step;

public class MainPageTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("")
    void SearchPreviewDisplaysExpectedValueTest() {

        step("Открываем главную страницу", () -> mainPage.openPage());
        step("Открываем главную страницу", () -> mainPage.setValueInSearchField("Пила"));
        step("Открываем главную страницу", () -> mainPage.searchPreviewCheck("Пила дисковая ИНТЕРСКОЛ"));
    }

    @CsvSource(delimiter = '|', value = {
            "Строительные рамные леса (ЛРСП)|Готовые комплекты строительных лесов",
            "Опалубка|Комплектующие для Опалубки",
            "Строительный инструмент|Абразивный, Алмазный инструмент",
            "Спецодежда, обувь, СИЗ|Защитные очки"
    })
    @ParameterizedTest
    @DisplayName("")
    void verifySubmenusForAllCatalogItemsTest(String button, String value) {

        step("Открываем главную страницу", () -> mainPage.openPage());
        step("Открываем главную страницу", () -> mainPage.openContextMenuForCatalogItem());
        step("Открываем главную страницу", () -> mainPage.hoverOverButtonWithText(button));
        step("Открываем главную страницу", () -> mainPage.verifyMenuContentContainsText(value));
    }

    @CsvSource(delimiter = '|', value = {
            "Каталог|Каталог",
            "О компании|О компании",
            "Услуги|Услуги",
            "Оплата и доставка|Оплата и доставка",
            "Контакты|Контакты"
    })
    @ParameterizedTest
    @DisplayName("")
    void verifyPageAfterMenuItemClickTest(String button, String value) {

        step("Открываем главную страницу", () -> mainPage.openPage());
        step("Открываем главную страницу", () -> mainPage.openContextMenuForCatalogItem());
        step("Открываем главную страницу", () -> mainPage.clickVisibleMenuItemWithText(button));
        step("Открываем главную страницу", () -> mainPage.verifyTextOnExpectedPage(value));
    }

    @Test
    @DisplayName("")
    void verifyPDFFileOnPageTest() throws Exception {

        step("Открываем главную страницу", () -> mainPage.openPage());
        PDF pdf = step("Скачиваем PDF-файл", () -> mainPage.downloadPdfFile());
        step("Открываем главную страницу", () -> {
            mainPage.verifyNumberOfPages(pdf, 1)
                    .verifyPdfAuthor(pdf, "RePack by Diakov")
                    .verifyPdfProducer(pdf, "Microsoft® Word 2016")
                    .verifyPdfContainsText(pdf, "ПРОДАЖА, АРЕНДА СТРОИТЕЛЬНЫХ ЛЕСОВ");
        });
    }

    @Test
    @DisplayName("")
    void shouldShowCorrectSlideTextAfterNextClickTest() {

        step("Открываем главную страницу", () -> mainPage.openPage());
        step("Открываем главную страницу", () -> mainPage
                .verifySliderText("Строительные леса ЛРСП-40 б/у со скидкой!"));
        step("Открываем главную страницу", () -> {
            mainPage.hoverOnSlide()
                    .nextSliderClick();
        });
        step("Открываем главную страницу", () -> mainPage
                .verifySliderText("Строительные леса и монолитная опалубка"));
        step("Открываем главную страницу", () -> mainPage.nextSliderClick());
        step("Открываем главную страницу", () -> mainPage
                .verifySliderText("Комплексное снабжение строительных объектов по выгодным ценам"));
        step("Открываем главную страницу", () -> mainPage.nextSliderClick());
        step("Открываем главную страницу", () -> mainPage
                .verifySliderText("Строительное оборудование в лизинг"));
        step("Открываем главную страницу", () -> mainPage.nextSliderClick());
        step("Открываем главную страницу", () -> mainPage
                .verifySliderText("Профессиональный строительный инструмент"));
        step("Открываем главную страницу", () -> mainPage.nextSliderClick());
        step("Открываем главную страницу", () -> mainPage
                .verifySliderText("Скидка на винтовые подкосы ЖБИ крюк-крюк и крюк-пятка"));
        step("Открываем главную страницу", () -> {
            mainPage.sleepOneSecond()
                    .nextSliderClick();
        });
        step("Открываем главную страницу", () -> mainPage
                .verifySliderText("Строительные леса ЛРСП-40 б/у со скидкой!"));
    }
}