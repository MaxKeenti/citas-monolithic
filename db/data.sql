-- Generos
INSERT INTO cca01_genero (id_genero, tx_nombre, tx_descripcion, st_activo) VALUES (1, 'Masculino', 'Género Masculino', true) ON CONFLICT (id_genero) DO NOTHING;
INSERT INTO cca01_genero (id_genero, tx_nombre, tx_descripcion, st_activo) VALUES (2, 'Femenino', 'Género Femenino', true) ON CONFLICT (id_genero) DO NOTHING;
INSERT INTO cca01_genero (id_genero, tx_nombre, tx_descripcion, st_activo) VALUES (3, 'Otro', 'Otro Género', true) ON CONFLICT (id_genero) DO NOTHING;

-- Roles
INSERT INTO cca02_rol (id_rol, tx_nombre, tx_descripcion, st_activo) VALUES (1, 'admin', 'Administrador del Sistema', true) ON CONFLICT (id_rol) DO NOTHING;
INSERT INTO cca02_rol (id_rol, tx_nombre, tx_descripcion, st_activo) VALUES (2, 'cliente', 'Cliente del Sistema', true) ON CONFLICT (id_rol) DO NOTHING;
INSERT INTO cca02_rol (id_rol, tx_nombre, tx_descripcion, st_activo) VALUES (3, 'empleado', 'Empleado del Sistema', true) ON CONFLICT (id_rol) DO NOTHING;


INSERT INTO public.tca02_usuario
(id_usuario, fk_id_rol, tx_login, tx_password, st_activo)
VALUES(1, 1, 'meishidushantuan@jiafei.com', 'potaxie', true);

INSERT INTO public.tca01_persona
(id_persona, fk_id_genero, tx_nombre, tx_primer_apellido, tx_segundo_apellido, fh_nacimiento)
VALUES(nextval('tca01_persona_id_persona_seq'::regclass), 1, 'Jiafei', 'Diosa', 'Potaxie', '0001-12-23');

INSERT INTO public.tce01_establecimiento
(id_establecimiento, tx_nombre)
VALUES(nextval('tce01_establecimiento_id_establecimiento_seq'::regclass), '7 Diamantes');