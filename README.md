#Zadania Java MongoDB Driver

W klasie `MongoUserRepository`, `MongoPostRepository` i `MongoFollowRepository` należy zaimplementować 
interfejsy odpowiednio `UserRepository`, `PostRepository` i `FollowRepository` oraz metody tam zadeklarowane.
Do każdej metody w interfejsach zostały napisane testy automatyczne. 

Przed rozpoczęciem zadań należy:
1. zaintalować bazę mongo https://www.mongodb.com/try/download/community?tck=docs_server
2. włączyć annotation procesor w ustwieniach IntellJ 
    * Build, Execution, Deployment -> Compiler -> Annotation Processors
3. stworzyć osobnego brancha na którym będziesz pracować.
4. zdefiniować ustawienia połączenia do MongoDB w klasie `MongoSettingsProvider`
    * stworzyć `ConnectionString` np. `"mongodb://user:password@localhost:27017"`
    * zarejestrować dwa codec'i `PojoCodec` i standartowe w zdefiniowane w rejestrze 
5. dodać potrzebne zależności do pom.xml.
    * ```xml
      <dependency>
          <groupId>org.mongodb</groupId>
          <artifactId>mongodb-driver-sync</artifactId>
          <version>4.0.5</version>
      </dependency>

Uwaga! Dane są usuwane z bazy przed każdym uruchomieniem testów.

Zadania do zrobienia:
1. Zaimplementuj metody które pozwolą wykonywać operacje na tabeli użytkowników:
    * Sprawdzić połączenie z bazą (użyj metody isValid()).
    * Pobrać wszystkich użytkowników. 
    * Dodać użytkownika. 
    * Znaleźć użytkownika po loginie. 
    * Zmienić hasło użytkownika.
    * Usunąć użytkownika.
2. Zaimplementuj metody które pozwolą wykonywać operacje na tabeli postów:
    * Pobrać wszystkie posty użytkownika.
    * Pobrać posty z dnia dzisiejszego.
    * Znaleźć post po Id.
    * Wyedytować treść posta.
    * Usunąć post.
    * Pobierać posty tylko obserwowanych użytkowników (dla chętnych).
3. Dodaj możliwość obserwowanie (follow) użytkownika przez drugiego użytkownika 
oraz możliwość zaprzestania subskrypcji.
