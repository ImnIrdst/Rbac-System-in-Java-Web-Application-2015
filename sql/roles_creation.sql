CREATE TABLE public.roles
(
  role_id integer NOT NULL,
  role_name character varying(45),
  role_description character varying(45),
  creation_date timestamp with time zone,
  CONSTRAINT role_pk PRIMARY KEY (role_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.roles
  OWNER TO iman;