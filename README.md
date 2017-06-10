# :fire: Star App

#### Необходимо

- Java 8
- Maven 3.0+

### Инструкция для запуска программы

* #### Чтобы скачать с гитхаба проект запускаем команду

  ```
  git clone https://github.com/alikdemon1/starapp.git
  ```

* #### Заходим в папку:

      /starapp
      
* #### Далее запускаем команду:    

   ```
   mvn spring-boot:run
   ```
* #### Ждём, когда запуститься сервер. После запуска сервера, заходим в браузере по адресу:

   http://localhost:8080/
   <br>
   И видим результат.
     
* #### Команда для запуска тестов:    

   ```
   mvn clean test -Dtest=kz.alisher.greetgo.StarApplicationTest
   ```
