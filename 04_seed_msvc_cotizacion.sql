USE msvc_cotizacion;

INSERT INTO cotizaciones (vehiculo_id, estado, total_estimado, dias_inactividad_estimados, fecha_creacion, metodo_autorizacion, fecha_hora, evidencia_url) VALUES
(1, 'APROBADA', 150.00, 1, '2026-10-01 08:00:00', 'Firma Presencial', '2026-10-01 10:00:00', 'http://firma.com/doc1.pdf'),
(2, 'APROBADA', 300.50, 2, '2026-10-02 09:30:00', 'Firma Digital', '2026-10-02 11:15:00', 'http://firma.com/doc2.pdf'),
(3, 'APROBADA', 80.00, 1, '2026-10-02 14:00:00', 'Firma Presencial', '2026-10-02 15:30:00', 'http://firma.com/doc3.pdf'),
(4, 'APROBADA', 450.00, 3, '2026-10-03 08:45:00', 'Firma Digital', '2026-10-03 10:20:00', 'http://firma.com/doc4.pdf'),
(5, 'APROBADA', 120.00, 1, '2026-10-03 11:10:00', 'Firma Presencial', '2026-10-03 12:00:00', 'http://firma.com/doc5.pdf'),
(6, 'APROBADA', 600.00, 4, '2026-10-04 07:30:00', 'Firma Digital', '2026-10-04 09:00:00', 'http://firma.com/doc6.pdf'),
(7, 'APROBADA', 220.00, 2, '2026-10-04 10:00:00', 'Firma Presencial', '2026-10-04 11:30:00', 'http://firma.com/doc7.pdf'),
(8, 'APROBADA', 340.00, 2, '2026-10-05 13:20:00', 'Firma Digital', '2026-10-05 15:00:00', 'http://firma.com/doc8.pdf'),
(9, 'APROBADA', 90.00, 1, '2026-10-05 16:00:00', 'Firma Presencial', '2026-10-05 17:15:00', 'http://firma.com/doc9.pdf'),
(10, 'APROBADA', 510.00, 3, '2026-10-06 08:00:00', 'Firma Digital', '2026-10-06 09:45:00', 'http://firma.com/doc10.pdf');

INSERT INTO cotizacion_entity_tareas (cotizacion_entity_id, tarea_id, costo_estimado, descripcion) VALUES
(1, 1, 150.00, 'Cambio de aceite y filtro'),
(2, 2, 200.00, 'Pintura puerta izquierda'), (2, 3, 100.50, 'Pulido de faros'),
(3, 4, 80.00, 'Revision de frenos'),
(4, 5, 450.00, 'Cambio de kit de distribucion'),
(5, 6, 120.00, 'Alineacion y balanceo'),
(6, 7, 600.00, 'Reparacion de transmision'),
(7, 8, 220.00, 'Cambio de amortiguadores delanteros'),
(8, 9, 340.00, 'Mantenimiento preventivo mayor'),
(9, 10, 90.00, 'Cambio de bujias'),
(10, 11, 510.00, 'Reparacion de sistema de aire acondicionado');
