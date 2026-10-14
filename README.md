# LoginJEE - Login JSP + Servlet + JDBC MySQL

## 1. Base de donnees
Executer `sql/database.sql` dans MySQL (phpMyAdmin / MySQL Workbench / console).
Puis adapter USER / PASSWORD dans `src/main/java/com/iset/login/util/DBConnection.java`.

## 2. Lancer le projet (Maven + Tomcat7)
    mvn clean tomcat7:run

Ouvrir : http://localhost:8080/LoginJEE/

## 3. Utilisation
1. "Creer un compte" -> l'utilisateur est insere dans la table `users` (mot de passe hache SHA-256)
2. Se connecter -> redirection vers `home.jsp`
3. "Deconnexion" -> invalide la session
