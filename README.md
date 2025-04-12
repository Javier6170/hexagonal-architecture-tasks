# 🧩 Proyecto Tasks - Arquitectura Hexagonal con Spring Boot

Este proyecto es una API REST para la gestión de tareas, desarrollada con arquitectura hexagonal. Incluye prácticas modernas como validaciones, manejo de errores, documentación automática con Swagger, y mapeo entre capas usando MapStruct. Ideal para reforzar conceptos de diseño limpio y buenas prácticas. 🚀

## ⚙️ Tecnologías

- Java 17
- Spring Boot 3.4.4
- Maven
- PostgreSQL
- MapStruct
- Swagger/OpenAPI
- Docker & Docker Compose
- Arquitectura Hexagonal 🧱

## 📁 Estructura de carpetas (Clean & Hexagonal)

📦src ┣ 📂domain ┃ ┣ 📂model → Entidades del dominio ┃ ┗ 📂port → Interfaces que el dominio espera ┣ 📂application → Casos de uso (Servicios) ┣ 📂infrastructure ┃ ┣ 📂controller → Controladores REST ┃ ┣ 📂dto → DTOs para requests/responses ┃ ┣ 📂persistence → Adaptadores y repositorios JPA ┃ ┗ 📂mapper → Mapeos con MapStruct


---

## 🐳 Cómo Ejecutar con Docker

```bash
./build-and-run.sh
```

Este script hace lo siguiente:

🧪 Ejecuta los tests

🔨 Compila el proyecto

🐳 Reconstruye la imagen y levanta el servicio con Docker Compose


