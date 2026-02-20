# PictsManager-Api

API backend du projet **PictsManager** (MSC Pro – 4e année).

Cette API fournit les briques serveur permettant :

- l’authentification sécurisée via JWT
- la gestion des utilisateurs
- l’upload et la gestion de médias
- le stockage objet compatible S3
- la persistance des données via MySQL

---

## 🚀 Stack technique

- **Java 23**
- **Spring Boot 3.3.4**
- **Spring Web**
- **Spring Validation**
- **Spring Data JPA**
- **Spring Security**
- **JWT (jjwt)**
- **MySQL 8**
- **AWS SDK S3** (compatible AWS, MinIO, Scaleway, etc.)
- **Lombok**
- **Docker / Docker Compose**

---

## 📦 Prérequis

### ▶ Exécution via Docker (recommandé)

- Docker
- Docker Compose

### ▶ Exécution en local (sans Docker)

- Java 23
- Maven (ou Maven Wrapper `./mvnw`)

---

## ⚡ Démarrage rapide (Docker Compose)

Le `docker-compose.yml` configure automatiquement :

- `db` : MySQL 8.0
- `phpmyadmin` : interface d’administration MySQL
- `api` : API Spring Boot (build via `docker/DockerfileApi`)
- `app` : image `pictsmanager-web-prod:latest` exposée en HTTP

---

### 1️⃣ Configurer les variables d’environnement

Créer un fichier `.env` à la racine du projet :

```env
# ========================
# Database
# ========================
DB_HOST=db
DB_PORT=3306
DB_NAME=your_database
DB_USERNAME=your_user
DB_PASSWORD=your_password

# ========================
# phpMyAdmin
# ========================
PMA_HOST=db
PMA_PASSWORD=your_password

# ========================
# JWT
# ========================
JWT_SECRET=your_super_secret_key
JWT_EXPIRATION=86400000

# ========================
# S3 (AWS / MinIO / etc.)
# ========================
S3_KEY=your_secret_key
S3_KEY_ID=your_access_key
S3_BUCKET=your_bucket
S3_ENDPOINT=https://your-endpoint
S3_REGION=eu-west-1

# ========================
# CORS
# ========================
ALLOWED_ORIGINS=http://localhost:8080
```

## 2️⃣ Lancer le projet avec Docker

Le moyen le plus simple de démarrer l'ensemble de l'écosystème est d'utiliser Docker Compose.

### Démarrage standard

```bash
docker compose up --build

```

> **Note :** L'option `--build` permet de reconstruire les images si nécessaire. Lors du premier lancement, le
> téléchargement des images peut prendre quelques minutes.

### Commandes utiles

* **Lancer en arrière-plan :** `docker compose up -d`
* **Arrêter les services :** `docker compose down`
* **Réinitialiser totalement :** `docker compose down -v`
* ⚠️ *Attention : Cela supprime également les volumes et les données de la base MySQL.*

> [!IMPORTANT]
> L’image front-end doit être build en local. Il est nécessaire d’avoir accès au repository GitHub du projet pour la
> générer correctement.

---

## 3️⃣ Accès aux services

Une fois les conteneurs démarrés, les services sont accessibles aux adresses suivantes :

| Service             | URL                                                                            |
|---------------------|--------------------------------------------------------------------------------|
| **Application Web** | [http://localhost:8080](https://www.google.com/search?q=http://localhost:8080) |
| **API (Backend)**   | [http://localhost:4000](https://www.google.com/search?q=http://localhost:4000) |
| **phpMyAdmin**      | [http://localhost:8081](https://www.google.com/search?q=http://localhost:8081) |

---

## 🛠 Lancement sans Docker (Développement Java)

Si tu souhaites lancer l'application Spring Boot nativement sur ta machine :

### 1. Prérequis

* Une instance **MySQL** en cours d’exécution.
* Les **variables d’environnement** configurées (DB, JWT, S3, etc.).

### 2. Build et exécution

Utilise le wrapper Maven fourni dans le projet :

```bash
# Compiler et installer les dépendances
./mvnw clean install

# Lancer l'application
./mvnw spring-boot:run

```

Si Maven est installé globalement sur ton système, tu peux simplement utiliser :

```bash
mvn spring-boot:run

```

Par défaut, l’API sera accessible sur : **`http://localhost:4000`**
