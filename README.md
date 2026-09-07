 # Sistema de Gestión para Mecánica Automotriz Cajamarca (MAC)

    ![Java](https://img.shields.io/badge/Java-26-orange)
    ![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-
  brightgreen)
    ![Maven](https://img.shields.io/badge/Maven-Multimódulo-blue)

    Este repositorio contiene la implementación backend basada en
  **Arquitectura de Microservicios** utilizando los principios de
  **Domain-Driven Design (DDD)** para el taller Mecánica Automotriz
  Cajamarca (MAC).

    ## Arquitectura del Sistema

    El sistema está dividido en 13 microservicios (agregados),
  organizados en un proyecto multimódulo de Maven para facilitar su
  compilación y despliegue conjunto. Cada microservicio encapsula una
  lógica de negocio específica y se comunica con otros mediante
  **OpenFeign**.

    ### Contextos Acotados (Bounded Contexts)
    * **Recepción y Diagnóstico:** `msvc-cliente`, `msvc-vehiculo`,
  `msvc-recepcion`, `msvc-cotizacion`
    * **Operaciones de Taller:** `msvc-orden-trabajo`, `msvc-bahia`
    * **Control de Calidad y Entrega:** `msvc-calidad`, `msvc-entrega`
    * **Inventario y Almacén:** `msvc-repuesto`, `msvc-reserva`
    * **Agendamiento:** `msvc-cita`
    * **Gestión de Personal:** `msvc-tecnico`
    * **Facturación y Finanzas:** `msvc-comprobante`

    ## Requisitos Previos

    - Java JDK 26
    - Maven 3.8+
    - MySQL 8.0+ (Local o en Docker)
    - Postman (para pruebas de API)

    ## Configuración e Instalación (Para el Equipo)

    1. **Clonar el repositorio:**
       ```bash
       git clone https://github.
  com/TU_USUARIO/Microservicios_Mecanica_Automotriz_Cajamarca_MAC.git
       cd Microservicios_Mecanica_Automotriz_Cajamarca_MAC

  2. Compilar el proyecto:
  Desde la raíz del proyecto (donde está el pom.xml padre), ejecuta:
    mvn clean install -DskipTests
  Nota: Cada desarrollador deberá configurar las credenciales de base de
  datos en el application.properties de su microservicio asignado.

  ## Equipo de Desarrollo

  • Adriana
  • Darick
  • Rosselly
  • Melanie
  • Arturo