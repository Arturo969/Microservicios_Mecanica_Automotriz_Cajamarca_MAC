# Guía de Pruebas en Postman


---

### 1. Registrar las bahías de prueba

**Crear bahía de mecánica:**
* **POST** `http://localhost:8001/api/bahia`
```json
{
  "codigo": "B-MEC-01",
  "estado": "DISPONIBLE",
  "tipo": "MECANICA_GENERAL"
}
```

**Crear bahía de pintura:**
* **POST** `http://localhost:8001/api/bahia`
```json
{
  "codigo": "B-PIN-02",
  "estado": "DISPONIBLE",
  "tipo": "PINTURA_HORNO"
}
```

---

### 2. Crear una orden de trabajo
Simulación ingreso de un vehículo. Generamos una orden con una tarea pendiente.

* **POST** `http://localhost:8008/api/orden-trabajo`
```json
{
  "cotizacionId": 774,
  "vehiculoId": 205,
  "tecnicoAsignadoId": 12,
  "estado": "EN_RECEPCION",
  "cerrada": false,
  "tareas": [
    {
      "descripcion": "Cambio de pastillas de freno",
      "completada": false,
      "evidencias": [],
      "repuestosUsados": []
    }
  ]
}
```

### 3. Validar la integración (Feign)
probamos que los microservicios se estén comunicando correctamente al mover el vehículo.

**Asignar a la bahía 1:**
* **PUT** `http://localhost:8008/api/orden-trabajo/1/asignar-bahia?bahiaId=1`
* *Comprobación:* (`http://localhost:8001/api/bahia`)

**Mover a la bahía 2:**
* **PUT** `http://localhost:8008/api/orden-trabajo/1/asignar-bahia?bahiaId=2`


---

### 4. Validaciones de negocio y evidencias

**Forzar un error (Cierre prematuro):**
* **PUT** `http://localhost:8008/api/orden-trabajo/1/cerrar`
* Postman debería rebotar la petición con un 400 Bad Request, ya que la regla de dominio impide cerrar órdenes con tareas sin terminar.

**Cargar el trabajo realizado:**
Para poder cerrar la orden, primero actualizamos la tarea marcándola como completada y le mandamos su evidencia.
* **PUT** `http://localhost:8008/api/orden-trabajo/1`
```json
{
  "cotizacionId": 774,
  "vehiculoId": 205,
  "tecnicoAsignadoId": 12,
  "estado": "LISTO_PARA_ENTREGA",
  "cerrada": false,
  "tareas": [
    {
      "descripcion": "Cambio de pastillas de freno - COMPLETADO",
      "completada": true,
      "evidencias": [
        { "urlFoto": "[https://servidor.com/fotos/frenos_nuevos.jpg](https://servidor.com/fotos/frenos_nuevos.jpg)" }
      ],
      "repuestosUsados": [
        { "repuestoId": 802, "cantidadUtilizada": 4 }
      ]
    }
  ]
}
```

---

### 5. Cierre exitoso de la orden
Ahora sí, la orden tiene todo lo necesario para cerrarse.

* **PUT** `http://localhost:8008/api/orden-trabajo/1/cerrar`

---

### 6. Extra: Eliminación en cascada (Prueba bidireccional)
Vamos a comprobar si el Feign de reversa limpia bien los historiales.

* **DELETE** `http://localhost:8001/api/bahia/1`
* *Comprobación:* Hazle un GET a tu orden de trabajo (`http://localhost:8008/api/orden-trabajo/1`). Busca en el array de `historialBahias`; el registro viejo que apuntaba a la bahía 1 debió desaparecer solito.