#Serwlety

Serwlet - Jest to program napisany w języku Java należący do pakietu javax. Jego zadaniem jest przyjmowanie żądań od klientów (takich jak przeglądarki internetowe lub urządzenia mobilne) i odsyłanie im odpowiedzi. Bazuje on na protokole komunikacyjnym HTTP.  Serwlety pozwalają na zbudowanie aplikacji webowych, opartych na komponentach. 

Cechy serwletów:
- Dynamicznie rozszerzają funkcje serwera.
- Służą do tworzenia dynamicznie generowanej treści.
- Mają dostęp do całego API Javy w tym JDBC
- Pośredniczą pomiędzy aplikacjami np. klientem HTTP i serwerem.

Funkcje serwletów
- zaczytuje jawne dane przesyłane przez klienta , dotyczy to danych przesyłanych za pomocą formularzy HTML , apletów lub przez inny program klienta HTTP
- czyta niejawne dane HTTP przesyłane przez klienta (przeglądarki internetowe ) np. ciasteczka, systemy kompresji, które są znane przeglądarkom itp.
- przetwarza dane i generuje wyniki
- wysyła jawne dane np. pliki binarne, arkusze kalkulacyjne itp.
- wysyła dane niejawne, potrafi wysłać ciasteczko z ustawioną wartością, oraz zwrócić informacje jaki typ odpowiedzi jest wysyłany do klienta


Fazy życia serwletu:
- inicjalizacja – moment w którym serwlet jest inicjializowany i tworzona jest instancja obiektu klasy HttpServlet lub innej implementującej interfejs Servlet
- service – w tej fazie obsługiwane są żądania serwletu.
- destroy – faza w której serwlet jest niszczony
- później klasa serwletu jest przekazywana do garbage collectora 
