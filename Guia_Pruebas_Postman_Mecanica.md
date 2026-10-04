# Guía de Pruebas Postman - Flujo Completo de Microservicios MAC

Esta guía contiene los pasos exactos y los JSON (payloads) necesarios para probar el 100% de la funcionalidad del sistema, llenando absolutamente todas las tablas de la base de datos (incluyendo evidencias, repuestos, fechas de creación y cierre de bahías).

## Prerrequisitos
Asegúrate de que los 5 microservicios estén corriendo en el IDE antes de comenzar. Sigue los pasos en este orden estricto.

---

### PASO 1: Crear una Bahía (`msvc-bahia` - Puerto 8001)
*Crea el espacio físico en el taller.*
* **Método:** `POST`
* **URL:** `http://localhost:8001/api/bahia`
* **Body (raw -> JSON):**
```json
{
  "codigo": "B-01",
  "estado": "DISPONIBLE",
  "tipo": "DIAGNOSTICO"
}
```

---

### PASO 2: Crear un Técnico (`msvc-tecnico` - Puerto 8012)
*Registra al mecánico asignando sus especialidades y horario.*
* **Método:** `POST`
* **URL:** `http://localhost:8012/api/tecnico`
* **Body (raw -> JSON):**
```json
{
  "dni": "76543210",
  "nombreCompleto": "Carlos Perez",
  "estado": "DISPONIBLE",
  "turnoLaboral": {
    "horaInicio": "08:00:00",
    "horaFin": "17:00:00"
  },
  "especialidades": [
    "Motor",
    "Frenos"
  ]
}
```

---

### PASO 3: Ingresar el Vehículo (`msvc-vehiculo` - Puerto 8013)
*Registra el vehículo del cliente.*
* **Método:** `POST`
* **URL:** `http://localhost:8013/api/vehiculo`
* **Body (raw -> JSON):**
```json
{
  "placa": "ABC-123",
  "vin": "VIN987654321",
  "marca": "Toyota",
  "modelo": "Corolla",
  "kilometrajeActual": 45000,
  "clienteId": 1
}
```

---

### PASO 4: Crear la Cotización (`msvc-cotizacion` - Puerto 8006)
*Se envía la fecha de creación manualmente y el ID de la tarea para evitar campos `null` en la tabla `cotizaciones` y `cotizacion_entity_tareas`.*
* **Método:** `POST`
* **URL:** `http://localhost:8006/api/cotizacion`
* **Body (raw -> JSON):**
```json
{
  "vehiculoId": 1,
  "estado": "CREADA",
  "fechaCreacion": "2026-10-02T09:00:00",
  "diasInactividadEstimados": 2,
  "tareas": [
    {
      "id": 1,
      "descripcion": "Cambio de aceite",
      "costoEstimado": 150.0
    }
  ]
}
```

---

### PASO 5: Aprobar la Cotización 
*El Cliente acepta el trabajo, registrando la evidencia de firma.*
* **Método:** `PUT`
* **URL:** `http://localhost:8006/api/cotizacion/1/aprobar`
* **Body (raw -> JSON):**
```json
{
  "metodoAutorizacion": "Firma Presencial",
  "fechaHora": "2026-10-02T10:00:00",
  "evidenciaUrl": "http://servidor.com/firma-cliente.pdf"
}
```

---

### PASO 6: Crear la Orden de Trabajo (`msvc-orden-trabajo` - Puerto 8008)
*Vinculamos el vehículo, el técnico y copiamos la tarea inicial.*
* **Método:** `POST`
* **URL:** `http://localhost:8008/api/orden-trabajo`
* **Body (raw -> JSON):**
```json
{
  "vehiculoId": 1,
  "tecnicoAsignadoId": 1,
  "cotizacionId": 1,
  "estado": "CREADA",
  "cerrada": false,
  "tareas": [
    {
      "descripcion": "Cambio de aceite",
      "completada": false
    }
  ]
}
```

---

### PASO 7: Asignar la Bahía a la Orden (Integración Feign)
*Ocupa la bahía remotamente.*
* **Método:** `PUT`
* **URL:** `http://localhost:8008/api/orden-trabajo/1/asignar-bahia?bahiaId=1`
* **Body:** *(Vacío, no enviar JSON)*

---

### PASO 8: Ejecutar el Trabajo (Agregar Evidencias y Repuestos)
*Este paso llena las tablas vacías `tarea_reparacion_entity_evidencias` y `tarea_reparacion_entity_repuestos_usados`. Se marca la tarea como completada adjuntando la foto y el repuesto.*
* **Método:** `PUT`
* **URL:** `http://localhost:8008/api/orden-trabajo/1`
* **Body (raw -> JSON):**
```json
{
  "id": 1,
  "vehiculoId": 1,
  "tecnicoAsignadoId": 1,
  "cotizacionId": 1,
  "estado": "EN_PROGRESO",
  "cerrada": false,
  "tareas": [
    {
      "id": 1,
      "descripcion": "Cambio de aceite",
      "completada": true,
      "evidencias": [
        {
          "urlFoto": "http://miservidor.com/fotos/aceite_nuevo.jpg",
          "fechaCaptura": "2026-10-02T11:30:00"
        }
      ],
      "repuestosUsados": [
        {
          "repuestoId": 99,
          "cantidadUtilizada": 1
        }
      ]
    }
  ]
}
```

---

### PASO 9: Cerrar la Orden (Finalizar y liberar Bahía)
*Llenará el campo `fecha_salida` en la tabla `ordenes_bahias` y habilitará la bahía para el próximo auto.*
* **Método:** `PUT`
* **URL:** `http://localhost:8008/api/orden-trabajo/1/cerrar`
* **Body:** *(Vacío, no enviar JSON)*

---
**Verificación final:** Si revisas la base de datos de Orden de Trabajo ahora, verás que las tablas de repuestos, evidencias y fechas de historial están llenas. Además, la Bahía 1 en el microservicio de Bahías volverá a estar `DISPONIBLE`.
