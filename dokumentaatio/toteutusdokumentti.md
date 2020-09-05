# Toteutusdokumentti

## Yleisrakenne
Ohjelma on jaettu viiteem eri pakkaukseen: algoritmipakkaukseen, tietorakennepakkaukseen, IO-pakkaukseen, käyttöliittymäpakkaukseen ja domainpakkaukseen.

Algoritmipakkaus sisältää toteutetut algoritmit (BFS, A*, JPS). Tietorakennepakkaus sisältää solmu luokan, sekä toteutetut tietorakenteet (jono, minimikeko). IO-pakkaus sisältää MapScanner-luokan, joka vastaa tiedostonluvusta. Käyttöliittymäpakkauksessa on käyttöliittymä-luokan ja pääluokan. Domainpakkaus sisältää sovelluslogiikan ja tehokkuusvertailuluokan.

## Saavutetut aika- ja tilavaativuudet

### Tietorakenteet

#### Jono
Sekä lisäyksen, että poistamisen aikavaativuus on O(1).
Tilavaativuus on O(n)

#### Minimikeko
Sekä lisäyksen, että poistamisen aikavaativuus on O(log n).
Tilavaativuus on O(n)

### Algoritmit

#### Leveyshaku
Aikavaativuus on O(n + m).
Tilavaativuus on O(n).

#### A*
Aikavaativuus on O(n + m log n).
Tilavaativuus on O(n).

#### JPS
Aikavaativuus on O(log n).
Tilavaativuus on O(n).

## Vertailu
Vaikka leveyshaun aikavaativuus on pienempi kuin A* algoritmin, suoriutuu A* suorituskykytesteissä kaikissa tapauksissa hieman leveyshakua nopeammin. 
JPS-algortimin nopeus alkaa näkyä selvästi suuremmilla syötteillä, jolloin se on suorituskykytesteissä kaikissa tapauksissa noin kymmenen kertaa A*-algoritmia nopeampi.

## Lähteet
[JPS](http://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-aaai11.pdf)

[A*](https://en.wikipedia.org/wiki/A*_search_algorithm)

[BFS](https://en.wikipedia.org/wiki/Breadth-first_search)
