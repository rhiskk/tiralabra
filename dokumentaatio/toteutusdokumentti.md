# Toteutusdokumentti

### Leveyshaku

Algoritmi saa parametreina alku- ja loppupisteen koordinaatit, sekä kartan ASCII-ruudukkona. Haku käy kartan pisteet läpi kerroksittain, siinä järjestyksessä kun ne tulevat vastaan. Tätä varten ohjelmaan on toteutettu jono. Algoritmi käyttää kahta jonoa, joista toiseen laitetaan x- ja toiseen y-koordinaatit. Algoritmi pitää kirjaa kunkin kerroksen solmujen määrästä. Kun kerroksen solmut on käsitelty kasvatetaan reitin pituutta yhdellä. Algoritmin suoritus loppuu, kun kaikki solmut on käsitelty tai kun loppupiste on löydetty.

### A*

Algoritmi alustaa etäisyystaulukon, jonka arvot asetetaan suuriksi. Haku lähtee käyntiin parametrina saadusta alku koordinaateista. Jokaisen käsiteltävän pisteen kohdalla luodaan uusi solmu-olio, jolle annetaan parametreina sen x- ja y-koordinaatit, sen etäisyys lähtöpisteestä ja sen diagonaalinen etäisyys loppupisteeseen. Solmu laitetaan algoritmia varten luotuun minimikeko tietorakenteeseen ja se merkitään käsitellyksi. Tämän jälkeen käydään pisteen naapurit läpi. Jos naapuriin pääsee pisteen kautta lyhyempää reittiä, päivitetään sen etäisyysarvoa, luodaan uusi naapuria vastaava solmu-olio ja lisätään se kekoon. Keosta otetaan solmu jolla on pienin lähtö- ja loppuetäisyyden summa käsittelyyn, kunnes keko on tyhjä tai loppupiste on löydetty.
