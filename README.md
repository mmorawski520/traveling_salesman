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

Widok ustawień będzie składać się z:
- RecyclerView przechowywującego miasta i odleglosci
- Przycisku do powrotu do ekranu głównego
- EditBox'a na nazwe miasta
- EditBox'a na odleglosc miasta
- Przycisku do dodawnia miasta

!!! Wazne kazdy item w recycler view bedzie mial przycisk do usuniecia miasta !!!
limit miast wynosi 16

Widok główny będzie składać się z: 
- TextView z tytułem, 
- TextView na wynik,
- Przycisku do zapisywania wyniku do pliku
- Przycisku do obliczenia trasy,
- Przycisku do przechodzenia do panelu ustawień

## Rozwiązania

Do rozwiazania problemu zamierzam uzyc algorytmu "nearest neighbour"
