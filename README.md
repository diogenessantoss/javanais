# <p align="center">Service RESTFul qui trasnforme une phrase française en javanais et inversement</p>

Le Javanais est de l'argot consistant à insérer des syllabes parasitaires dans les mots. Voici les règles simplifiées implémentées dans ce service :
- Dans un mot, "av"est ajouté entre chaque consonne suivie d'une voyelle.
- Si le mot commence par une voyelle, "av" est ajouté devant cette voyelle.

Quelques exemples :
- "bonjour" devient "bavonjavour"
- "moyen" devient "mavoyen"
- "exemple" devient "avexavemplave"
- "au" devient "avau"

Stack technique
- Java 17
- Spring Boot
- Maven
