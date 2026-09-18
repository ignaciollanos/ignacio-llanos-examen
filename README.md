# Proyecto de Automatización de Pruebas y Pipeline CI/CD

**Asignatura:** Automatización de Pruebas  
**Alumno:** Ignacio Llanos  
**Repositorio:** `ignacio-llanos-examen/automatizacion-pruebas`  

---

## 📋 Descripción del Proyecto

Este proyecto consiste en la implementación de una suite de pruebas automatizadas en Java utilizando **Maven**, **JUnit** y **Selenium WebDriver**, junto con la configuración de un pipeline completo de Integración Continua y Despliegue Continuo (CI/CD) en **GitHub Actions**.

El objetivo principal es asegurar la calidad del software mediante pruebas unitarias, de integración y de aceptación, garantizando un flujo de entrega continuo con mecanismos de mitigación como **Rollback automático**.

---

## 🛠️ Estrategia de Pruebas Implementada

Se aplicó la pirámide de automatización de pruebas, dividiendo las pruebas en tres niveles principales:

1. **Pruebas Unitarias (`CalculadoraTest.java`):**
   - **Objetivo:** Verificar la lógica matemática básica (suma, resta, multiplicación y división) de la clase `Calculadora`.
   - **Herramienta:** JUnit 5.
   - **Ubicación:** `src/test/java/com/examen/CalculadoraTest.java`.

2. **Pruebas de Integración y Aceptación (`GoogleSearchIT.java`):**
   - **Objetivo:** Validar la interacción de la aplicación con componentes externos y navegación web simulando el comportamiento del usuario.
   - **Herramienta:** Selenium WebDriver + ChromeDriver.
   - **Ubicación:** `src/test/java/com/examen/GoogleSearchIT.java`.

---

## 📑 Detalle de Actividades

### 🔹 Actividad 1: Configuración de Git, Flujo de Ramas y Proyecto Maven

* **Flujo de Trabajo:** Se utilizó un flujo basado en ramas (*Trunk-Based Development* / *GitFlow*), manteniendo `main` como rama principal para entregables estables.
* **Gestión de Dependencias (`pom.xml`):**
  - **Java Version:** 17.
  - **JUnit Jupiter API & Engine (5.10.0):** Para la ejecución y aserciones de pruebas unitarias.
  - **Selenium Java (4.18.1):** Para la automatización del navegador web.
  - **Maven Compiler Plugin:** Configurado para la correcta compilación del código fuente.

---

### 🔹 Actividad 2: Pipeline de Integración Continua (CI)

Se diseñó el workflow `.github/workflows/ci.yml` ejecutado automáticamente ante cada evento `push` o `pull_request` en la rama `main`.

**Stages del Pipeline de CI:**
1. **Checkout:** Clona el código fuente del repositorio.
2. **Setup JDK 17:** Configura el entorno de Java con la distribución Temurin.
3. **Build & Unit Tests (`mvn clean test`):** Compila el código fuente y ejecuta el conjunto de pruebas unitarias.
4. **Integration Tests (`mvn test -Dtest=GoogleSearchIT`):** Ejecuta las pruebas de integración con Selenium.

---

### 🔹 Actividad 3: Pipeline de Despliegue (CD) y Estrategia de Rollback

Se creó el workflow `.github/workflows/deploy.yml` que gestiona el flujo de entrega continua y despliegue hacia un ambiente de pruebas (*Staging*).

**Stages del Pipeline de CD:**
1. **Build & Test:** Validación previa de compilación y pruebas unitarias.
2. **Acceptance Tests (`acceptance-tests`):** Ejecución de pruebas de aceptación del sistema e interfaz usando Selenium WebDriver.
3. **Deploy Staging (`deploy-staging`):** Simulación del despliegue del artefacto compilado en el ambiente de pruebas.
4. **Rollback Strategy (`rollback-strategy`):**
   - **Mecanismo:** Configurado mediante la condición `if: failure()`.
   - **Funcionamiento:** En caso de que las pruebas de aceptación o el stage de despliegue detecten un fallo, el pipeline activa de manera automática el proceso de restauración (*rollback*) a la última versión estable (v1.0.0), previniendo despliegues fallidos en producción.

---

## 🚀 Instrucciones de Ejecución Local

### Prerrequisitos
- JDK 17 o superior instalado.
- Apache Maven 3.8+ instalado.
- Navegador Google Chrome actualizado.

### Pasos para Ejecutar

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/tu-usuario/ignacio-llanos-examen.git](https://github.com/tu-usuario/ignacio-llanos-examen.git)
   cd ignacio-llanos-examen/automatizacion-pruebas

2. **Ejecutar pruebas unitarias**
```bash
mvn clean test

3- **Ejecutar Pruebas de Integración / Selenium:**
```bash
mvn test -Dtest=GoogleSearchIT
