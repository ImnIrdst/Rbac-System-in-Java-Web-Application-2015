CREATE TABLE public.role_permission
(
  role_id integer NOT NULL references roles(role_id),
  permission_id integer NOT NULL references permissions(permission_id),
  creation_date timestamp with time zone,
  CONSTRAINT role_permission_pk PRIMARY KEY (role_id, permission_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.role_permission
  OWNER TO iman;
