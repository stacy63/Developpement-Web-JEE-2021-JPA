# Objectif

L'objectif de ce TP est de découvrir SpringBoot avec l'utilisation de Java Persistance API (JPA).
Il s'agit de réaliser une application qui permet à un utilisateur de noter des tâches à faire et de les ordonner en "à faire", "en cours" et "terminé".
On ne cherche pas encore à manipuler une base non relationnelle et on ne cherche pas à faire une architecture REST.
Grâce à Spring Data, Spring permet de manipuler tous les types de bases de données mais nous allons nous limiter aux bases relationnelles. On peut les manipuler à différents niveaux : rester au plus proche du SGBD choisi (JDBC avec connecteur) à une abstraction complète avec ORM (Object Relational Machine) nommée JPA.

Nous allons gérer des tâches / post-its / notes. Ces tâches seront rangées par catégorie. Une tâche possède donc un contenu, une catégorie, une date de création et une date pour laquelle le travail doit être fait. Une catégorie est un simple nom.

# Environnement de développement

- Java : Java SE 8, 11 ou 17
- Gradlew
- SpringBoot
- On utilisera la base H2 (écrite en Java) en mode embedded (pas serveur) et fichier (pas inmemory).