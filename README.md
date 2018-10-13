# WeatherAdminDataViewer

![Travis](https://travis-ci.com/WeatherAdminJEE/WeatherAdminDataViewer.svg?branch=master)

## Système de connexion
L'autentification des utilisateurs est géré via les variables de session.
Un filtre de servlet est appelé avant chaque appel à une servlet et vérifie que l'utilisateur est bien connecté.
S'il n'est pas connecté, il est redirigé vers la page de connexion.

