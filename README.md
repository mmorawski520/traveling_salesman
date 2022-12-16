# traveling_salesman app
## Funkcjonalność
Program ma za zadanie rozwiązywać problem komiwojażera
Jest to problem obliczeniowy
Problem polega na tym że
dane jest n miast, które komiwojażer ma odwiedzić, oraz odległość / cena podróży / czas podróży pomiędzy każdą parą miast.
Celem jest znalezienie najkrótszej / najtańszej / najszybszej drogi łączącej wszystkie miasta, 
zaczynającej się i kończącej się w określonym punkcie

## Wygląd
Aplikacja będzie się składać z dwóch widoków
Widoku ustawień, oraz widoku głównego.
Layout ma być oparty o LinearLayouty (Horizontal/Vertical)

Widok ustawień będzie składać się z:
- RecyclerView przechowywującego miasta i odleglosci
- Przycisku do losowania miasta
- Przycisku do powrotu do ekranu głównego
- EditBox'a na nazwe miast (normalna klawiatura)
- EditBox'a na odleglosc miasta (numeryczna klawiatura)
- Przycisku do dodawnia miasta

## Recycler view
W projekcie będą znajdować się 2 typy itemów dla recycler view

Basic RecyclerView składa się z:
    - Nazwy miasta

Advanced RecyclerView składa się z:
    - Nazwy miasta
    - Nazwy drugiego miasta
    - Dystans WZGLĘDEM ostatniego dodanego miasta(dystans według pozostałych miast będzie generowany automatycznie i nie będzie wyświetlany)

## WAŻNE
!!! Wazne kazdy item w recycler view bedzie mial przycisk do usuniecia miasta !!!
limit miast wynosi 8 
lista miast MUSI BYĆ przekazywana pomiędzy aktywnościami (Najlepiej za pomocą singletonu)
odleglosc miasta od pozostałych miast ma być losowana względem dystansu od ostatniego miasta podanego przez użytkownika

Widok główny będzie składać się z: 
- TextView z tytułem, 
- RecyclerView na wynik,
- TextView na wynik
- Przycisku do zapisywania wyniku do pliku
- Przycisku do obliczenia trasy,
- Przycisku do przechodzenia do panelu ustawień

## Rozwiązania

Do rozwiazania problemu zamierzam uzyc algorytmu "nearest neighbour"
Algorytm ma się znajdować w oddzielnej klasie.

## Zabezpieczenia
Ochrona przed crashowaniem aplikacji w wypadku gdy użytkokownik doda żadnych danych
Ochrona przed zapisaniem wyniku, gdy aplikacja nie obliczyła jeszcze wyniku

## Cykl życia aplikacji
Dane mają być zapisane a aplikacji do zabicia procesu.
