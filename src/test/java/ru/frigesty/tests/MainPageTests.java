package ru.frigesty.tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.frigesty.pages.MainPage;
import ru.frigesty.pages.TopWrapperPage;

@Tag("simple")
public class MainPageTests extends TestBase {

    private final MainPage mainPage = new MainPage();
    private final TopWrapperPage topWrapperPage = new TopWrapperPage();

    @Test
    @DisplayName("Проверяем, что при вводе поискового значения в превью будет нужный результат")
    void searchPreviewDisplaysExpectedValueTest() {
        mainPage.openPage()
                .setValueInSearchField("Пила")
                .sleepOneSecond()
                .searchPreviewCheck("Пила дисковая ИНТЕРСКОЛ");
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "Строительные рамные леса (ЛРСП)|Готовые комплекты строительных лесов",
            "Опалубка|Комплектующие для Опалубки",
            "Строительный инструмент|Абразивный, Алмазный инструмент",
            "Спецодежда, обувь, СИЗ|Защитные очки"
    })
    @DisplayName("Проверяем, что в субменю каталога {0} есть значение {1}")
    void verifySubmenusForAllCatalogItemsTest(String button, String value) {
        mainPage.openPage()
                .openContextMenuForCatalogItem()
                .hoverOverButtonWithText(button)
                .verifyMenuContentContainsText(value);
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "Каталог|Каталог",
            "О компании|О компании",
            "Услуги|Услуги",
            "Оплата и доставка|Оплата и доставка",
            "Контакты|Контакты"
    })
    @DisplayName("Проверяем, что при клике на кнопку {0} мы попадем на страницу с надписью {1}")
    void verifyPageAfterMenuItemClickTest(String button, String value) {
        mainPage.openPage()
                .openContextMenuForCatalogItem()
                .clickVisibleMenuItemWithText(button);
        topWrapperPage.verifyTextOnPage(value);
    }

    @Test
    @DisplayName("Проверяем, что в скачанном PDF-файле есть нужные значения")
    void verifyPDFFileOnPageTest() throws Exception {
        mainPage.openPage();
        PDF pdf = mainPage.downloadPdfFile();

        mainPage.verifyNumberOfPages(pdf, 1)
                .verifyPdfAuthor(pdf, "RePack by Diakov")
                .verifyPdfProducer(pdf, "Microsoft® Word 2016")
                .verifyPdfContainsText(pdf, "ПРОДАЖА, АРЕНДА СТРОИТЕЛЬНЫХ ЛЕСОВ");
    }

    @Test
    @DisplayName("Проверяем, что корректно отображаются слайды при перелистывании")
    void shouldShowCorrectSlideTextAfterNextClickTest() {

        String[] slideTexts = {
                "Строительные леса ЛРСП-40 б/у со скидкой!",
                "Строительные леса и монолитная опалубка",
                "Комплексное снабжение строительных объектов по выгодным ценам",
                "Строительное оборудование в лизинг",
                "Профессиональный строительный инструмент",
                "Скидка на винтовые подкосы ЖБИ крюк-крюк и крюк-пятка",
                "Строительные леса ЛРСП-40 б/у со скидкой!"
        };

        mainPage.openPage().verifySliderText(slideTexts[0]);

        for (int i = 1; i < slideTexts.length; i++) {
            if (i == slideTexts.length - 1) {
                mainPage.sleepOneSecond();
            }
            mainPage.hoverOnSlide()
                    .nextSliderClick()
                    .verifySliderText(slideTexts[i]);
        }
    }
}