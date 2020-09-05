# Testausdokumentti

## JUnit-testaus
Sovelluksen luokat, lukuunottamatta PathfindingUi-, PathfindingService- ja PerformanceTest-luokkia on testattu JUnit-testeillä.

### Algoritmit
Algoritmien testeissä on testattu, että algoritmit:
* palauttavat -1, kun polkua ei löydy, tai annetut koordinaatit eivät ole käyttökelpoisia.
* palauttavat oikean pituuden, kun reitti löytyy
* palauttavat oikean määrä tehtyjä operaatioita
* palauttavat oikean reitin

Algortimien testejä varten on luotu test.map-tiedosto ja kutakin algoritmia varten kolme .map-tiedostoa, joihin on merkattu valmiiksi reitti, joka niiden tulisi palauttaa.

### Tietorakenteet
#### Jono
Jonon testeissä on testattu, että jono:
* on tyhjä heti sen luomisen jälkeen
* palauttaa false, jos se ei ole tyhjä
* palauttaa siihen talletetut solmut oikeassa järjestyksessä

#### Minimikeko
Minimikeon testeissä on testattu, että keko:
* on tyhjä heti sen luomisen jälkeen
* ei ole tyhjä, kun siihen on lisätty solmu
* solmut palautetaan keosta oikeassa järjestyksessä

### Kartanlukija
Kartanlukija-testeissä on testattu, että:
* kartanlukija geittää FilesNotFoundExceptionin, jos tiedostoa ei löydy
* ./maps kansiosta haetut kartat eivät sisällä .DS_Store-tiedostoa
* kartta luetaan oikein

## Käyttöliittymän testaus
Käyttöliittymää on testattu manuaalisesti.
Käyttöliittymää testattaessa on pyritty testaamaan, että:
* kartat piityvät oikein
* koordinaatit asettuvat kartalle oikein
* reitti piirtyy oikein

## Suorituskykytestaus

Suorituskykyä on testattu PerformanceTest-luokan avulla, joka ajaa algoritmit 20 kertaa, laskee suoritusajat yhteen ja palauttaa niiden keskiarvon. Tämän lisäksi kukin algoritmi laskee kuinka monta kertaa algoritmin suurimman aikavaativuuden operaatioita suoritetaan. Leveyshaussa lasketaan siis jonoon lisäämisiä ja siitä poistamisia, jotka toimivat ajassa O(1). A*- ja JPS-algoritmeissa lasketaan puolestaan kekoon lisäämisiä ja siitä poistamisia, jotka toimivat ajassa O(log n).

Algoritmeja on testattu neljän eri kartan kolmella eri kokoluokalla (256x256, 512x512, 1024x0124). Alku ja loppupiste on pyritty asettamaan mahdollisimman kauas toisitaan, kuitenkin niin että myös leveyshaku löytää lyhimmän mahdollisen reitin.

Testit voidaan toistaa valitsemalla käyttöliittymässä testeissä käytetyt kartat ja alku- ja loppupisteet.

### BFS

| Syötteen koko | Kartta | Alku | Loppu | Reitin pituus | Aika (ms) | Operaatiot O(1) |
|---|---|---|---|---|---|---|
| 65536 | Berlin_0_256.map | (0, 0) | (255, 255) | 396,358 | 19 | 91970 |
| 65536 | Milan_0_256.map | (0, 0) | (255, 255) | 386,4 | 20 | 93308 |
| 65536 | Moscow_0_256.map | (0, 36) | (255, 255) | 353,328| 12 | 95120 |
| 65536 | London_1_256.map | (0, 0) | (255, 255) | 397,328| 14 | 94378 |
| 262144 | Berlin_0_512.map | (0, 0) | (511, 511) | 793,544 | 160 | 374352 |
| 262144 | Milan_0_512.map | (0, 0) | (380, 511) | 668,402 | 125 | 375699 |
| 262144 | Moscow_0_512.map | (0, 73) | (511, 511) | 704,728 | 100 | 389056 |
| 262144 | London_1_512.map | (0, 0) | (511, 511) | 792,656| 122 | 388834 |
| 1048576 | Berlin_0_1024.map | (0, 0) | (1023, 1023) | 1587,916 | 764 | 1510238 |
| 1048576 | Milan_0_1024.map | (0, 0) | (810, 1023) | 1358,513 | 495 | 1525288 |
| 1048576 | Moscow_0_1024.map | (0, 150) | (1023, 1023) | 1405,697 | 390 | 1574294 |
| 1048576 | London_1_1024.map | (0, 0) | (960, 1023) | 1527,928| 396 | 1580893 |



### A*

| Syötteen koko | Kartta | Alku | Loppu | Reitin pituus | Aika (ms) | Operaatiot O(log n) |
|---|---|---|---|---|---|---|
| 65536 | Berlin_0_256.map | (0, 0) | (255, 255) | 396,358 | 13 | 74112 |
| 65536 | Milan_0_256.map | (0, 0) | (255, 255) | 386,4 | 15 | 64326 |
| 65536 | Moscow_0_256.map | (0, 36) | (255, 255) | 353,328| 6 | 30357 |
| 65536 | London_1_256.map | (0, 0) | (255, 255) | 397,328| 8 | 39285 |
| 262144 | Berlin_0_512.map | (0, 0) | (511, 511) | 793,544 | 120 | 306073 |
| 262144 | Milan_0_512.map | (0, 0) | (380, 511) | 668,402 | 75 | 156228 |
| 262144 | Moscow_0_512.map | (0, 73) | (511, 511) | 704,728 | 50 | 113803 |
| 262144 | London_1_512.map | (0, 0) | (511, 511) | 792,656| 74 | 166564 |
| 1048576 | Berlin_0_1024.map | (0, 0) | (1023, 1023) | 1587,916 | 602 | 1249238 |
| 1048576 | Milan_0_1024.map | (0, 0) | (810, 1023) | 1358,513 | 311 | 502053 |
| 1048576 | Moscow_0_1024.map | (0, 150) | (1023, 1023) | 1405,697 | 214 | 455299 |
| 1048576 | London_1_1024.map | (0, 0) | (960, 1023) | 1527,928| 215 | 470410 |



### JPS

| Syötteen koko | Kartta | Alku | Loppu | Reitin pituus | Aika (ms) | Operaatiot O(log n) |
|---|---|---|---|---|---|---|
| 65536 | Berlin_0_256.map | (0, 0) | (255, 255) | 396,358 | 3 | 512 |
| 65536 | Milan_0_256.map | (0, 0) | (255, 255) | 386,4 | 3 | 1132 |
| 65536 | Moscow_0_256.map | (0, 36) | (255, 255) | 353,328| 1 | 356 |
| 65536 | London_1_256.map | (0, 0) | (255, 255) | 397,328| 1 | 557 |
| 262144 | Berlin_0_512.map | (0, 0) | (511, 511) | 793,544 | 7 | 576 |
| 262144 | Milan_0_512.map | (0, 0) | (380, 511) | 668,402 | 11 | 848 |
| 262144 | Moscow_0_512.map | (0, 73) | (511, 511) | 704,728 | 5 | 446 |
| 262144 | London_1_512.map | (0, 0) | (511, 511) | 792,656| 8 | 895 |
| 1048576 | Berlin_0_1024.map | (0, 0) | (1023, 1023) | 1587,916 | 33 | 935 |
| 1048576 | Milan_0_1024.map | (0, 0) | (810, 1023) | 1358,513 | 45 | 1366 |
| 1048576 | Moscow_0_1024.map | (0, 150) | (1023, 1023) | 1405,697 | 20 | 677 |
| 1048576 | London_1_1024.map | (0, 0) | (960, 1023) | 1527,928| 22| 1116 |

