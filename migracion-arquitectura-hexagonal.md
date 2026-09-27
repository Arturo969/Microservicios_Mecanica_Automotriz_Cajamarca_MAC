# Informe de Migración a Arquitectura Hexagonal

## 1. Análisis del Proyecto Actual

El proyecto `Microservicios_Mecanica_Automotriz_Cajamarca_MAC` se compone actualmente de dos microservicios bajo un entorno Spring Boot:
*   `msvc-bahia`
*   `msvc-orden-trabajo`

### Estado Actual de la Arquitectura
Actualmente, los microservicios están construidos utilizando una **Arquitectura en Capas tradicional** (N-Tier Architecture), que se evidencia en la siguiente estructura de paquetes:
*   `controllers`: Contiene controladores REST de Spring MVC.
*   `services`: Interfaces e implementaciones de la lógica de negocio (fuertemente acoplada a Spring mediante `@Service`, `@Transactional`, y `@Autowired`).
*   `repositories`: Interfaces de Spring Data (ej. `CrudRepository` o `JpaRepository`).
*   `models/entity`: Clases de dominio que a la vez son Entidades JPA (anotadas con `@Entity`, `@Table`, etc.).
*   `clients`: Clases de integración HTTP (ej. clientes Feign para comunicación entre microservicios).

### Problemas del Modelo Actual
1.  **Alto Acoplamiento al Framework**: Las clases del modelo de negocio (`models/entity`) conocen la infraestructura de base de datos (JPA). Los servicios de negocio están atados a Spring (`@Transactional`).
2.  **Dificultad para Pruebas Unitarias**: Probar la lógica de negocio exige en muchas ocasiones levantar contextos de Spring o lidiar con dependencias de base de datos.
3.  **Evolución Restringida**: Si el día de mañana se requiere cambiar la base de datos o el framework web, el refactor será invasivo a lo largo de todas las capas.

---

## 2. Visión de la Arquitectura Hexagonal (Ports and Adapters)

El objetivo de la Arquitectura Hexagonal es colocar el **Dominio** de la aplicación en el centro, aislado de cualquier framework o tecnología externa (Bases de datos, UI, APIs, colas de mensajería, etc.).

Se compone de tres zonas principales:
1.  **Dominio (Domain)**: Reglas de negocio puras (POJOs).
2.  **Aplicación (Application)**: Casos de uso de la aplicación y declaración de "Puertos" (Interfaces de entrada y salida).
3.  **Infraestructura (Infrastructure)**: Implementación de los "Adaptadores" (Controladores REST, JPA, Feign, etc.).

---

## 3. Detalle Técnico: Qué se debe agregar sobre lo existente

Para adaptar el proyecto, será necesario reestructurar los paquetes y agregar nuevos componentes arquitectónicos. A continuación se detalla lo que se debe incorporar en cada microservicio:

### 3.1. Capa de Dominio Pura (Nueva)
*   **Modelos de Dominio**: Crear clases Java puras sin anotaciones de Spring ni de JPA. Por ejemplo, en `msvc-bahia`, crear un `BahiaTrabajo` puramente lógico. En `msvc-orden-trabajo`, crear `OrdenTrabajo`, `TareaReparacion`, etc.
*   **Servicios de Dominio (Opcional)**: Si existe lógica que involucra a múltiples modelos y no pertenece a un caso de uso específico.

### 3.2. Capa de Aplicación (Nueva)
*   **Puertos de Entrada (Input Ports)**: Interfaces que definen qué operaciones puede realizar un actor externo sobre el sistema. (ej. `CrearOrdenTrabajoUseCase`, `ConsultarBahiaUseCase`).
*   **Puertos de Salida (Output Ports)**: Interfaces que el dominio usa para comunicarse con el exterior, pero sin conocer la implementación. (ej. `OrdenTrabajoRepositoryPort`, `BahiaClientPort`).
*   **Implementaciones de Casos de Uso**: Clases puras de Java que implementan los Puertos de Entrada, orquestando el dominio y los Puertos de Salida. (Reemplazarán a `BahiaServiceImpl` y `OrdenTrabajoServiceImpl`, pero sin anotaciones de Spring).

### 3.3. Capa de Infraestructura (Adaptación de lo existente)
*   **Adaptadores de Entrada (Driving Adapters)**:
    *   **Controladores REST**: Los actuales `BahiaController` y `OrdenTrabajoController` se mueven aquí. Dejan de llamar a `Service` y pasan a llamar a los *Input Ports* (Casos de uso).
*   **Adaptadores de Salida (Driven Adapters)**:
    *   **Persistencia**:
        *   Mover los `models/entity` actuales a un paquete de infraestructura `infrastructure/out/persistence/entity` y renombrarlos (ej. `BahiaTrabajoEntity`).
        *   Crear clases adaptadoras (ej. `BahiaPersistenceAdapter`) que implementen el *Output Port* (`BahiaRepositoryPort`). Este adaptador internamente inyecta y usa los actuales `BahiaRepository` de Spring Data.
    *   **Clientes Externos**:
        *   Los actuales `clients` (Feign) se mueven a `infrastructure/out/rest`.
        *   Crear adaptadores (ej. `OrdenTrabajoClientAdapter`) que implementen el *Output Port* correspondiente y usen el cliente Feign internamente.
*   **Mappers (Nuevo)**: Clases para convertir entre objetos de Dominio y objetos de Infraestructura (ej. `BahiaMapper.toDomain(BahiaTrabajoEntity)`, `BahiaMapper.toEntity(BahiaTrabajo)`). Se puede utilizar **MapStruct** para automatizar esto.
*   **Configuración de Spring (Nuevo)**: Clases anotadas con `@Configuration` encargadas de instanciar los Casos de Uso (que no tienen `@Service`) e inyectarles los Adaptadores de Salida, registrándolos como Beans en el contexto de Spring.

---

## 4. Pasos Lógicos y Estructurados para la Migración

Se recomienda aplicar esta refactorización progresivamente, microservicio por microservicio, siguiendo estos pasos:

### Paso 1: Reestructuración de Paquetes
Para cada microservicio (ej. `msvc-bahia`), crear la nueva estructura base:
```text
org.mac.taller.msvc_bahia
├── domain
│   ├── models/
│   └── exceptions/
├── application
│   ├── ports/
│   │   ├── in/  (Casos de Uso)
│   │   └── out/ (Interfaces de repositorios/clientes)
│   └── services/ (Implementaciones de casos de uso)
└── infrastructure
    ├── adapters/
    │   ├── in/
    │   │   └── web/ (Controladores)
    │   └── out/
    │       ├── persistence/ (Entidades JPA, Repositorios Spring Data, Adaptadores)
    │       └── client/ (Clientes Rest/Feign, Adaptadores)
    └── config/ (Configuraciones de Spring)
```

### Paso 2: Creación del Dominio y Puertos
1.  Crear las clases POJO puras en `domain/models/` copiando los campos de los Entities actuales, pero omitiendo anotaciones como `@Entity`, `@Id`, `@Column`.
2.  Extraer las operaciones de entrada actuales en interfaces en `application/ports/in/` (ej. `GuardarBahiaUseCase`).
3.  Definir las interfaces de salida en `application/ports/out/` basándose en qué datos necesita guardar/leer el negocio (ej. `BahiaRepositoryPort`).

### Paso 3: Mover la Lógica a la Capa de Aplicación
1.  Crear clases en `application/services/` que implementen los *Input Ports*.
2.  Copiar la lógica de los antiguos `ServiceImpl` a estas nuevas clases.
3.  Reemplazar las inyecciones de Spring Data (`Repository`) y Clientes Rest por inyecciones a través de constructor de los **Puertos de Salida** (`Output Ports`).
4.  Asegurar que estas clases solo importan clases del paquete `domain` o `application` (Cero referencias a Spring).

### Paso 4: Implementar Adaptadores de Infraestructura de Salida (Persistencia)
1.  Mover los actuales `@Entity` a `infrastructure/adapters/out/persistence/entities/`.
2.  Mover las interfaces actuales que heredan de `CrudRepository` a `infrastructure/adapters/out/persistence/repositories/`.
3.  Crear un Mapper (manual o usando MapStruct) para transformar de Entidad JPA a Modelo de Dominio y viceversa.
4.  Crear un `PersistenceAdapter` que implemente el *Output Port* (`BahiaRepositoryPort`). En sus métodos, debe recibir Modelos de Dominio, mapearlos a Entities, usar el `CrudRepository` de Spring, y mapear el resultado de vuelta al Dominio.

### Paso 5: Implementar Adaptadores de Clientes Rest
1.  Mover los interfaces Feign a `infrastructure/adapters/out/client/`.
2.  Crear un adaptador (`RestClientAdapter`) que implemente el *Output Port* pertinente. Este adaptador llamará al Feign Client y transformará los DTOs de respuesta al modelo de Dominio.

### Paso 6: Adaptar la Entrada (Web)
1.  Mover los controladores actuales a `infrastructure/adapters/in/web/`.
2.  En lugar de inyectar el antiguo `ServiceImpl`, inyectar los **Puertos de Entrada** (`Input Ports` / Casos de Uso).
3.  Mapear los DTOs que recibe el Controller hacia Modelos de Dominio y pasarlos al Caso de Uso.

### Paso 7: Inyección de Dependencias (Wiring)
Dado que los Casos de Uso de la capa de Aplicación son ahora POJOs sin la anotación `@Service`, Spring no los reconocerá automáticamente.
1.  Crear una clase en `infrastructure/config/ApplicationConfig.java` anotada con `@Configuration`.
2.  Crear métodos `@Bean` que instancien los Casos de Uso, pasándoles por constructor los adaptadores de infraestructura correspondientes (Spring inyectará los adaptadores automáticamente ya que estos sí llevarán anotaciones como `@Component` o `@Repository`).

### Paso 8: Pruebas y Limpieza
1.  Escribir/adaptar Unit Tests para la Capa de Aplicación, mockeando los Puertos de Salida (muy sencillo ahora que no hay dependencias de Spring).
2.  Escribir Integration Tests para los Adaptadores de Infraestructura (para validar queries y controladores).
3.  Eliminar los paquetes y clases antiguas (`services.BahiaServiceImpl`, la estructura antigua de paquetes) que han quedado huérfanos.
