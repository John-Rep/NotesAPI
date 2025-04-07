# Comment cloner et exécuter ce projet en local

## 1. Cloner le projet

Ouvre un terminal et exécute la commande suivante :

```git clone https://github.com/John-Rep/NotesAPI.git```

## 2. Ouvrir dans un IDE

Tu peux ouvrir le projet avec l'IDE de ton choix :

### Avec Eclipse :
- File > Import > Existing Maven Projects
- Choisir le dossier cloné

### Avec IntelliJ IDEA :
- Open > Sélectionner le dossier du projet

## 3. Vérifier les dépendances

### Puisque ce projet utilise Maven :
mvn clean install

## 4. Configurer la base de données

Le projet de base utilise MySQL en local pour la base de données.

Ajuste la configuration pour la faire correspondre à ton environnement local :

```
spring.datasource.url=jdbc:mysql://localhost:3306/notes
spring.datasource.username=root
spring.datasource.password=yourpassword
```

> Vérifie que la base existe et que le serveur MySQL est en marche.

## 5. Lancer l'application

### Méthode 1 : Depuis ton IDE

Lance la classe principale : NotesApplication.java



## 6. Utiliser l'API par des Endpoints (avec Postman)

l'URL de base en local : ```localhost:8080```

### Endpoints des étudiants :
-  GET : /students
-  GET : /students/{id}
-  POST : /students
    > format du body
    ```
    {
      "name": "{name}",
      "age": {age}
    }
    ```
-  PUT : /students/{id}
    > format du body
    ```
    {
      "name": "{name}",
      "age": {age}
    }
    ```
-  DELETE : /students/{id}


### Endpoints des cours :
-  GET : /courses
-  GET : /courses/{id}
-  POST : /courses
    > format du body
    ```
    {
      "title": "{title}"
    }
    ```
-  PUT : /courses/{id}
    > format du body
    ```
    {
      "title": "{title}"
    }
    ```
-  DELETE : /courses/{id}


### Endpoints des notes :
-  GET : /grades/student/{id}
-  GET : /grades/course/{id}
-  POST : /grades
    > format du body
    ```
    {
      "grade": {grade},
      "studentId": {student_id},
      "courseId": {course_id}
    }
    ```
-  PUT : /grades/{id}
    > format du body
    ```
    {
      "grade": {grade},
      "studentId": {student_id},
      "courseId": {course_id}
    }
    ```
-  DELETE : /grades/{id}


### Endpoints des rapports :
-  GET : /reports/student/{id}
-  GET : /reports/course/{id}

