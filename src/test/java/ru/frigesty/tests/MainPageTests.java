package ru.frigesty.tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.frigesty.pages.MainPage;
import static io.qameta.allure.Allure.step;

@Tag("simple")
public class MainPageTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверяем что вводе поискового значения в превью будет нужный результат")
    void SearchPreviewDisplaysExpectedValueTest() {

        step("Открываем главную страницу", () -> mainPage.openPage());
        step("Вводим значение 'Пила' в поиск", () -> mainPage.setValueInSearchField("Пила"));
        step("Проверяем что в превью к поиску есть искомый результат", () -> mainPage
                .searchPreviewCheck("Пила дисковая ИНТЕРСКОЛ"));
    }

    @CsvSource(delimiter = '|', value = {
            "Строительные рамные леса (ЛРСП)|Готовые комплекты строительных лесов",
            "Опалубка|Комплектующие для Опалубки",
            "Строительный инструмент|Абразивный, Алмазный инструмент",
            "Спецодежда, обувь, СИЗ|Защитные очки"
    })
    @ParameterizedTest
    @DisplayName("Проверяем что в субменю каталога {0} есть значение {1}")
    void verifySubmenusForAllCatalogItemsTest(String button, String value) {

        step("Открываем главную страницу", () -> mainPage.openPage());
        step("Открываем меню каталога", () -> mainPage.openContextMenuForCatalogItem());
        step("В открывшемся меню наводим на кнопку", () -> mainPage.hoverOverButtonWithText(button));
        step("Проверяем что в контент меню есть нужное значение", () -> mainPage
                .verifyMenuContentContainsText(value));
    }

    @CsvSource(delimiter = '|', value = {
            "Каталог|Каталог",
            "О компании|О компании",
            "Услуги|Услуги",
            "Оплата и доставка|Оплата и доставка",
            "Контакты|Контакты"
    })
    @ParameterizedTest
    @DisplayName("Проверяем что при клике на кнопку {0} мы попадем на страницу с надписью {1}")
    void verifyPageAfterMenuItemClickTest(String button, String value) {

        step("Открываем главную страницу", () -> mainPage.openPage());
        step("Открываем контекстное меню каталога", () -> mainPage.openContextMenuForCatalogItem());
        step("Кликаем на элемент меню", () -> mainPage.clickVisibleMenuItemWithText(button));
        step("Проверяем что на открывшейся странице есть нужное значение", () -> mainPage.
                verifyTextOnExpectedPage(value));
    }

    @Test
    @DisplayName("")
    void verifyPDFFileOnPageTest() throws Exception {

        step("Открываем главную страницу", () -> mainPage.openPage());
        PDF pdf = step("Скачиваем PDF-файл", () -> mainPage.downloadPdfFile());
        step("Проверяем PDF-файл", () -> {
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
        step("Проверяем текст на слайде", () -> mainPage
                .verifySliderText("Строительные леса ЛРСП-40 б/у со скидкой!"));
        step("Переключаем на следующий слайд", () -> {
            mainPage.hoverOnSlide()
                    .nextSliderClick();
        });
        step("Проверяем текст на слайде", () -> mainPage
                .verifySliderText("Строительные леса и монолитная опалубка"));
        step("Переключаем на следующий слайд", () -> mainPage.nextSliderClick());
        step("Проверяем текст на слайде", () -> mainPage
                .verifySliderText("Комплексное снабжение строительных объектов по выгодным ценам"));
        step("Переключаем на следующий слайд", () -> mainPage.nextSliderClick());
        step("Проверяем текст на слайде", () -> mainPage
                .verifySliderText("Строительное оборудование в лизинг"));
        step("Переключаем на следующий слайд", () -> mainPage.nextSliderClick());
        step("Проверяем текст на слайде", () -> mainPage
                .verifySliderText("Профессиональный строительный инструмент"));
        step("Переключаем на следующий слайд", () -> mainPage.nextSliderClick());
        step("Проверяем текст на слайде", () -> mainPage
                .verifySliderText("Скидка на винтовые подкосы ЖБИ крюк-крюк и крюк-пятка"));
        step("Переключаем на следующий слайд", () -> {
            mainPage.sleepOneSecond()
                    .nextSliderClick();
        });
        step("Проверяем текст на слайде", () -> mainPage
                .verifySliderText("Строительные леса ЛРСП-40 б/у со скидкой!"));
    }
}