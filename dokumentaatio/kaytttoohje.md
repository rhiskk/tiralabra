# Käyttöohje

Lataa [release-sivulta](https://github.com/HiskiR/tiralabra/releases/tag/loppupalautus) tiedosto pathfinding.jar

## Ohjelman käynnistäminen

Ohjelman saa käynnistettyä komentoriviltä komennolla 
```
java -jar pathfinding.jar
```

Sovellus olettaa, että suoritushakemistossa on "maps"-kansio, joka sisältää ainakin yhden .map-tiedoston.

## Kartan valitseminen

Kartan voi valita pudotuslaatikosta

## Koordinaattien asettaminen

Voit asettaa koordinaati, joko klikkaamalla karttaa, tai kirjoittamalla koordinaatit. 
256-kartat ottavat koordinaatteja 0-255.
512-kartat ottavat koordinaattteja 0-511.
1024-kartat ottavat koordinaatteja 0-1023.
Sovellus ilmoittaa mikäli valitsemasi koordinaatit ovat virheelliset.

## Reittien hakeminen

Koordinaattien asettamisen jälkeen saat lyhimmät reitit näkyviin painamalla halutun algortimin vieressä olevaa "Path"-painiketta. Sovellus piirtää reitin kartalle ja ilmoittaa sen pituuden. Huomaa, että BFS ei aina löydä lyhintä reittiä ja A* reitit saattavat tarkemmilla kartoilla näyttää silmämääräisesti lyhyemmiltä kuin muut.

Saat reittien hakuun kuluneen ajan painamalla Performance-painiketta.

Jos haluat poistaa edelliset reitit kartalta paina "Clear paths"-painiketta.

# Karttojen lisääminen

Jos haluat lisätä sovellukseen karttoja voit ladata niitä [täältä](https://movingai.com/benchmarks/grids.html) ja asettaa ne suoritushakemistossa olevaan maps kansioon.

