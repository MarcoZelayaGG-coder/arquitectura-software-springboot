# 📘 Investigación Aplicada - DWF

## 👥 Integrantes
- Daniel Adrian Castillo  
- Rudy Mauricio Gonzales  
- Francisco Josue Santos  
- Rodrigo Leandro Hernandez  
- Marco Aurelio Zelaya  

## 🚀 Descripción del Proyecto
Se desarrollaron **3 aplicaciones** con diferentes arquitecturas de software, con el objetivo de comparar y aplicar buenas prácticas en el desarrollo backend:

1. **Arquitectura Limpia (Clean Architecture)**  
2. **Arquitectura Hexagonal**  
3. **Arquitectura Monolítica**

📌 Cada arquitectura se encuentra implementada en **ramas diferentes** dentro de este repositorio.

## 🛠️ Tecnologías Utilizadas
- **Spring Boot** → Framework principal para el desarrollo de las aplicaciones.  
- **Postman** → Herramienta utilizada para probar los endpoints.  
- **JPA (Java Persistence API)** → Notaciones para el manejo de entidades.  
- **Hibernate** → ORM utilizado para la persistencia de datos.  
- **H2 Database** → Base de datos en memoria para pruebas y desarrollo.  


## 📊 Comparación de Arquitecturas

| Arquitectura          | Características principales                                                                 | Ventajas                                                                 | Desventajas                                                             |
|-----------------------|---------------------------------------------------------------------------------------------|--------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Limpia (Clean)**    | Separación estricta en capas (entidades, casos de uso, interfaces).                         | Alta mantenibilidad, independencia de frameworks, fácil de probar.        | Mayor complejidad inicial, curva de aprendizaje más alta.               |
| **Hexagonal**         | Basada en puertos y adaptadores, facilita la integración con sistemas externos.              | Flexibilidad para cambiar tecnologías externas, buen aislamiento.         | Puede ser más abstracta y difícil de entender al inicio.                 |
| **Monolítica**        | Toda la lógica en una sola aplicación, estructura más simple.                               | Fácil de implementar, menor tiempo de desarrollo inicial.                  | Difícil de escalar, menor mantenibilidad, dependencias fuertemente acopladas. |

