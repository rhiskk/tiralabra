# Määrittelydokumentti

## Sovelluksen tarkoitus

Sovelluksen avulla voidaan vertailla leveyshaun (BFS), A*- ja mahdollisesti JPS-algoritmin toimintaa, kun etsitään lyhin polku kahden verkon solmun välillä.

## Algoritmit ja tietorakenteet

Valitsin reitinhakuun leveyshaun, koska se sopii hyvin lyhimmän polun etsimiseen ja A*-algoritmin, koska se sopii tilanteeseen, jossa etsitään lyhin reitti juuri kahden tietyn solmun välille.
Algoritmien toteutuksessa tullaan käyttämään tietorakenteina kekoa, jonoa ja taulukkoa.

## Syötteet

Syötteenä annetaan jokin [Moving AI Lab](https://movingai.com/benchmarks/grids.html):in sivuilta löytyvä kartta ja kaksi tämän kartan pistettä. Kartat ovat ruudukkoja, jotka ajatellaan verkkoina algoritmeja toteutettaessa.
Kartan pisteet taas ajatellaan verkon solmuina, joiden välinen lyhin polku etsitään.

## Aikavaativuudet

Levyshaulla tulisi päästä aikavaativuuteen O(|V|+|E|) ja tilavaativuuteen O(|V|).
A*-algoritmilla tavoitteena on päästä aikavaativuuteen O(|E|) ja tilavaativuuteen O(|V|).
Joissa V on kaarien- ja E solmujen määrä.

## Lähteet

[Breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search) Wikipedia

[A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm) Wikipedia
