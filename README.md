## Задача №1 - Максимальное покрытие

Вам нужно взять уже полюбившуюся функцию расчёта комиссии при переводе и написать для неё автотесты:

![](pic/vk-commission.png)

Подключите JUnit4 и JaCoCo. Добейтесь того, чтобы покрытие кода по branch было не менее 80%:

![](pic/branches.png)

Информацию о том, что значит по branch вы найдёте на [официальном сайте JaCoCo](https://www.eclemma.org/jacoco/trunk/doc/counters.html).

Итог: у вас должен быть репозиторий на GitHub, в котором будет ваш Gradle-проект. Автотесты также должны храниться в репозитории.

В случае возникновения проблем с генерацией отчёта, смотрите [соответствующий раздел](https://github.com/netology-code/kt-homeworks/blob/master/04_functions/README.md#%D0%B2%D0%BE%D0%B7%D0%BC%D0%BE%D0%B6%D0%BD%D1%8B%D0%B5-%D0%BF%D1%80%D0%BE%D0%B1%D0%BB%D0%B5%D0%BC%D1%8B-%D0%B8-%D0%B8%D1%85-%D1%80%D0%B5%D1%88%D0%B5%D0%BD%D0%B8%D1%8F)


## Задача №2 - CI

Запускать тесты на своём компьютере - хорошо, а запускать их при каждом пуше в облаке – ещё лучше. Когда вы будете работать в команде, сразу будет видно, кто «сломал» сборку, а кто прислал "«нерабочий PR (Pull-Request)». В этом вся прелесть командной работы 😈!

Мы настроим CI на базе GitHub Actions - уже встроенной в GitHub системы.

После того, как вы сделали задачу №1, перейдите в ваш репозиторий на вкладку GitHub Actions:

![](pic/actions1.png)

GitHub Actions предложить вам настроить Simple Workflow, соглашаемся:

![](pic/actions2.png)

В открывшемся окне делаем по шагам:
1. Меняем имя файла на `gradle.yml`.
1. Меняем весь код, на тот что ниже.
1. Нажимаем «Start Commit».
1. Нажимаем «Commit new file».

![](pic/actions3.png)

Код для `gradle.yml` скопируйте и замените целиком - не переписывайте вручную:

```yml
name: Kotlin CI with Gradle

on:
  push:
    branches: [ master, main ]
  pull_request:
    branches: [ master, main ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 1.8
      uses: actions/setup-java@v1
      with:
        java-version: 1.8
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    - name: Build with Gradle
      run: ./gradlew build --info

```

«Everything as code» и «Configuration as code» - большинство современных CI систем следуют этому подходу, когда все необходимые настройки хранятся в текстовом файле в самом репозитории. В случае GitHub Actions в вашем репозитории создастся файл `./github/workflows/gradle.yml`, поэтому не забудьте сделать `git pull`, чтобы он попал и в ваш локальный репозиторий.

Если вы скопируете конфигурацию неправильно, GitHub Actions сообщит вам об этом (мы удалили у первого слова первую букву):

![](pic/actions4.png)

После того, как вы закоммитите корректный файл конфигурации, на вкладке GitHub Actions можно посмотреть прогресс выполнения:

![](pic/actions5.png)

Через какое-то время получим общий итог. PASS — зелёный флажок или FAIL —красный крестик:

![](pic/actions6.png)

Для каждого коммита, для которого производилась сборка, на всех страницах GitHub будет отображаться статус. Иконки статуса кликабельны:

![](pic/actions7.png)

Можно «провалиться» внутрь, чтобы посмотреть логи сборки:

![](pic/actions8.png)

И раскрыть интересующий нас раздел:

![](pic/actions9.png)

Если мы специально или случайно уроним сборку, то это тоже будет видно:

![](pic/actions10.png)

![](pic/actions11.png)

Полностью настроенный CI на основе примеров с лекции вы можете найти по адресу: https://github.com/netology-code/ktci.

### Задача

Соответственно, ваша задача - подключить к вашему репозиторию GitHub Actions, следуя пошаговой инструкции, которая была приведена выше.

Чтобы удостовериться, что CI действительно работает - добавьте (как мы в примере) коммит, ломающий сборку (выставьте в тестах неправильное ожидаемое значение). Убедитесь, что после Push вам покажут эту проблему.

**Важно**: вам не нужно каждый раз создавать заново файл конфигурации GitHub Actions, достаточно добавлять его в новый репозиторий так же, как вы это делаете с `.gitignore`.

Итог:
1. У вас должен быть репозиторий на GitHub, в котором расположен ваш Gradle-проект.
1. К репозиторию должен быть подключён GitHub Actions.
1. В истории должен быть хотя бы один коммит, ломающий сборку.

## Возможные проблемы и их решения

В случае, если тесты проходят, но при запуске команды `gradle test jacocoTestReport` получается похожая картина:

![image](https://user-images.githubusercontent.com/13727567/112562424-46854400-8de8-11eb-978f-2668f981ca58.png)

Следует убедиться, что используется JDK версии 14 или ниже. Для этого идём в File -> Project Structure

![image](https://user-images.githubusercontent.com/13727567/112562737-096d8180-8de9-11eb-9ad4-77127976f3f8.png)

На вкладке Project проверяем версию. Меняем в случае необходимости

![image](https://user-images.githubusercontent.com/13727567/112562819-36ba2f80-8de9-11eb-93ec-fe83263d5e23.png)

Если Jacoco отказывается работать даже в этом случае, следует изменить ваш `gradle.yml` на следующий вариант:

```yml
name: Kotlin CI with Gradle

on:
  push:
    branches: [ master, main ]
  pull_request:
    branches: [ master, main ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 1.8
      uses: actions/setup-java@v1
      with:
        java-version: 1.8
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    - name: Build with Gradle
      run: ./gradlew test jacocoTestReport
    - name: Upload Build Artifact
      uses: actions/upload-artifact@v2
      with:
          name: jacoco_reports
          path: build/reports/jacoco/test/html

```
В таком случае Jacoco отчёт будет сгенерирован на каждый пуш в master/main ветку. Либо на каждый пуш в ветку, на которую открыт PR в master/main. И загружен на Github в виде артефакта для дальнейшего скачивания.

Скачать и посмотреть отчёт можно следующим образом:

Нажимаем на статус сборки, переходим в детали
![image](https://user-images.githubusercontent.com/13727567/112563989-741fbc80-8deb-11eb-8567-b5a816db5520.png)

Дожидаемся успешной сборки, если статус желтый. Далее скачиваем полученный архив
![image](https://user-images.githubusercontent.com/13727567/112564243-f7411280-8deb-11eb-8530-4f615ba50caf.png)

Далее распаковываем архив, смотрим результаты в index.html
