# AutoMode

Application web de gestion de véhicules, d'entretien et de dépenses automobiles.

AutoMode est composée d'une interface React, d'une API Spring Boot sécurisée par JWT et d'une base PostgreSQL. Le projet peut être lancé localement avec Docker Compose ou séparément pour le développement.

## Fonctionnalités

- Création de compte et authentification JWT
- Ajout, modification et suppression de véhicules
- Recherche de véhicules par marque ou modèle
- Gestion des dépenses de carburant
- Gestion des réparations et des vidanges
- Tableau de bord des dépenses
- Images de véhicules et fallback visuel pour les données de démonstration
- Initialisation optionnelle de données fictives

## Stack technique

| Partie | Technologie |
| --- | --- |
| Frontend | React 19, React Router, Bootstrap, Axios |
| Backend | Java 21, Spring Boot 4, Spring Security, Spring Data JPA |
| Authentification | JWT |
| Base de données | PostgreSQL 16 |
| Tests backend | JUnit, H2 |
| Déploiement local | Docker Compose, Nginx |

## Démarrage rapide avec Docker

### Prérequis

- Docker Desktop avec le moteur Linux démarré
- Git

### Lancer l'application

Depuis la racine du projet :

```powershell
docker compose up --build -d
```

Services disponibles :

- Application web : <http://localhost:3000>
- API backend : <http://localhost:8090>
- PostgreSQL : `localhost:5432`

Vérifier l'état des conteneurs :

```powershell
docker compose ps
```

Arrêter les services :

```powershell
docker compose down
```

Supprimer également les données PostgreSQL et recréer la base de démonstration :

```powershell
docker compose down -v
docker compose up --build -d
```

## Compte de démonstration

Lorsque `APP_SEED_DATA=true`, le backend crée automatiquement des données fictives :

```text
Email    : test@example.com
Mot de passe : password123
```

Ces identifiants sont réservés au développement et aux démonstrations. Ils ne doivent jamais être utilisés en production.

## Configuration Docker

Les valeurs par défaut de Compose sont adaptées au développement local. Elles peuvent être remplacées par des variables d'environnement :

| Variable | Valeur par défaut | Description |
| --- | --- | --- |
| `POSTGRES_DB` | `automode` | Nom de la base |
| `POSTGRES_USER` | `postgres` | Utilisateur PostgreSQL |
| `POSTGRES_PASSWORD` | `postgres` | Mot de passe local PostgreSQL |
| `JWT_SECRET` | valeur de développement | Secret de signature JWT |
| `APP_SEED_DATA` | `true` | Active les données fictives |

Exemple PowerShell avant le démarrage :

```powershell
$env:POSTGRES_PASSWORD = "mot-de-passe-local"
$env:JWT_SECRET = "une-cle-secrete-developpement-de-32-caracteres-minimum"
$env:APP_SEED_DATA = "false"
docker compose up --build -d
```

Pour un environnement de production, utiliser un gestionnaire de secrets et une base PostgreSQL dédiée. Ne pas publier de fichier `.env` contenant des secrets.

## Développement sans Docker

### Frontend

Prérequis : Node.js 22 ou version compatible.

```powershell
npm ci
npm start
```

L'interface de développement est disponible sur <http://localhost:3000>.

### Backend

Prérequis : JDK 21.

```powershell
cd automodebackend
.\mvnw.cmd spring-boot:run
```

Le backend utilise PostgreSQL par défaut sur `localhost:5432`. Les paramètres peuvent être configurés avec `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` et `SERVER_PORT`.

## Tests et build

Tests frontend :

```powershell
npm test -- --watchAll=false --runInBand
```

Build frontend :

```powershell
npm run build
```

Tests backend :

```powershell
cd automodebackend
.\mvnw.cmd test
```

Les tests backend utilisent H2 en mémoire et ne nécessitent pas de PostgreSQL externe.

## Structure du projet

```text
.
├── src/                         # Application React
├── public/                      # Ressources publiques frontend
├── automodebackend/
│   ├── src/main/java/           # API, sécurité, entités et repositories
│   ├── src/main/resources/      # Configuration Spring
│   └── src/test/                # Tests backend
├── Dockerfile                   # Image frontend Nginx
├── automodebackend/Dockerfile   # Image backend Java
├── compose.yaml                 # Frontend, backend et PostgreSQL
└── nginx.conf                   # Routage SPA et proxy frontend
```

## API principale

Quelques endpoints publics :

```text
GET  /search?query={marque-ou-modele}
GET  /random?limit={nombre}
GET  /getCar/{matricule}
GET  /vehicles/bestPublic
GET  /vehicles/{matricule}/repairExpensesPublic
```

Les opérations de création et de modification nécessitent une authentification JWT.

## Publication sur Git

Avant le premier push :

```powershell
git init
git add .
git status
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/<utilisateur>/<depot>.git
git push -u origin main
```

Vérifier avant le commit que les dossiers suivants ne sont pas suivis :

- `node_modules/`
- `build/`
- `automodebackend/target/`
- `.idea/`
- les fichiers `.env`

## Licence

Aucune licence open source n'est définie pour le moment. Ajouter une licence avant une distribution publique si nécessaire.
