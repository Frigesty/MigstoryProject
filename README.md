
# Проект по автоматизации тестирования для компании [Миг строй](https://migstroy.spb.ru/)

> Компания **«Миг-Строй»** — надежный поставщик строительного оборудования.
> 
## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#allure-testops">Интеграция с Allure TestOps</a>

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>

* <a href="#video">Примеры видео выполнения тестов на Selenoid</a>
____
<a id="tools"></a>
## <a name="Технологии и инструменты">**Технологии и инструменты:**</a>

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="files/images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="files/images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="files/images/logo/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="files/images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="files/images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="files/images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="files/images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="https://allurereport.org/"><img src="files/images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> 
<a href="https://qameta.io/"><img src="files/images/logo/Allure2.svg" width="50" height="50"  alt="Allure TestOps"/></a>   
<a href="https://www.jenkins.io/"><img src="files/images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
</p>

____
<a id="cases"></a>
## <a name="Примеры автоматизированных тест-кейсов">**Примеры автоматизированных тест-кейсов:**</a>
____
- ✓ *Проверка работы поиска*
- ✓ *Проверка субменю каталога*
- ✓ *Проверка перехода на страницы из меню*
- ✓ *Проверка скаченного PDF-файла с сайта*
- ✓ *Проверка карусели слайдов*



____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="files/images/logo/Jenkins.svg" width="25"/></a><a name="Сборка"></a>Сборка в [Jenkins](https://jenkins.autotests.cloud/job/HW14-VkusVill/)</a>
____
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/HW14-VkusVill/"><img src="files/images/screen/vkusvill_jenkins_build.png" alt="Jenkins" width="950"/></a>  
</p>


### **Параметры сборки в Jenkins:**

- *BROWSER (браузер, по умолчанию chrome)*
- *VERSION (версия браузера, по умолчанию 126)*
- *SIZE (размер окна браузера, по умолчанию 1920x1080)*
- *REMOTEURL (адрес хоста для удалённого запуска тестов)*


<a id="console"></a>
## Команды для запуска из терминала
___
***Локальный запуск:***
```bash  
gradle clean vkusvill_test
```
```bash  
gradle clean test
```

***Удалённый запуск через Jenkins:***
```bash  
clean 
vkusvill 
"-Dbrowser=${BROWSER}" 
"-DbrowserSize=${SIZE}" 
"-DbrowserVersion=${VERSION}" 
"-DremoteUrl=${REMOTEURL}"
```
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="files/images/logo/Allure.svg" width="25"/></a> <a name="Allure"></a>Allure [отчет](https://jenkins.autotests.cloud/job/FrigestyMigstroyProject/7/allure/)</a>
___

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="files/images/screen/allureReport.png" width="850">  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="files/images/screen/vkusvill_jenkins_tests.png" width="850">  
</p>

### *Графики*

  <p align="center">  
<img title="Allure Graphics" src="files/images/screen/vkusvill_allure_graphs.png" width="850">

</p>

___
<a id="allure-testops"></a>
## <img alt="Allure" height="25" src="files/images/logo/Allure2.svg" width="25"/></a>Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/4578/dashboards">Allure TestOps</a>
____

### *Авто тест-кейсы*

<p align="center">  
<img title="Allure TestOps Tests" src="files/images/screen/vkusvill_allure_autotestsN.png" width="850">  
</p>

____
<a id="telegram"></a>
## <img alt="Allure" height="25" src="files/images/logo/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="files/images/screen/Telegram.png" width="550">  
</p>

____
<a id="video"></a>
## <img alt="Selenoid" height="25" src="files/images/logo/Selenoid.svg" width="25"/></a> Примеры видео выполнения тестов на Selenoid
____
<p align="center">
<img title="Selenoid Video" src="files/images/video/ExampleOfPassingTest.gif" width="550" height="350"  alt="video">   
</p>

