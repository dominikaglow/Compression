# Compression
Do programu docierają dane. Są to n-bitowe słowa (n < 9). Wiele z nich będzie się powtarzać. 

Idea:

Należy zbadać ile razy powtarza się dana sekwencja bitów (słowo).

Następnie należy zakodować dane tak, że najczęściej występujące słowo kodowane jest mniejszą niż n liczbą bitów, pozostałe kodowane są używając n+1 bitów.

Pozostałe słowa wymagają użycia n+1 bitów, ponieważ należy użyć jednego bitu do rozróżnienia pomiędzy sekwencjami kodującymi często powtarzające się słowa i resztą, która nie jest kodowana.

Sekwencje kodujące powtarzające się słowa zaczynają się zawsze od 0, a pozostałe od 1.
