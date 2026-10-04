USE msvc_tecnico;

INSERT INTO tecnicos (dni, nombre_completo, estado, hora_inicio, hora_fin) VALUES
('11111111', 'Carlos Martinez', 'DISPONIBLE', '08:00:00', '17:00:00'),
('22222222', 'Juan Perez', 'DISPONIBLE', '08:00:00', '17:00:00'),
('33333333', 'Roberto Gomez', 'DISPONIBLE', '09:00:00', '18:00:00'),
('44444444', 'Luis Fernandez', 'DISPONIBLE', '07:00:00', '16:00:00'),
('55555555', 'Miguel Sanchez', 'DISPONIBLE', '08:00:00', '17:00:00'),
('66666666', 'Pedro Ramirez', 'DISPONIBLE', '10:00:00', '19:00:00'),
('77777777', 'Jorge Torres', 'DISPONIBLE', '08:00:00', '17:00:00'),
('88888888', 'Andres Suarez', 'DISPONIBLE', '09:00:00', '18:00:00'),
('99999999', 'Fernando Ruiz', 'DISPONIBLE', '08:00:00', '17:00:00'),
('10101010', 'Ricardo Silva', 'DISPONIBLE', '07:00:00', '16:00:00');

INSERT INTO tecnico_entity_especialidades (tecnico_entity_id, especialidades) VALUES
(1, 'Frenos'), (1, 'Motor'),
(2, 'Pintura'), (2, 'Carroceria'),
(3, 'Diagnostico_Electronico'),
(4, 'Mecanica_Rapida'), (4, 'Aceite'),
(5, 'Transmision'),
(6, 'Frenos'), (6, 'Suspension'),
(7, 'Motor'),
(8, 'Aire_Acondicionado'),
(9, 'Pintura'),
(10, 'Diagnostico_Electronico');
