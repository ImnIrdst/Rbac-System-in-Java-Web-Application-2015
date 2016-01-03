-- Database: automation_prj2
CREATE DATABASE automation_prj2
  WITH OWNER = iman
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


-- Role: iman
CREATE ROLE iman LOGIN
  ENCRYPTED PASSWORD 'md56e4aa9835cd2e0a9b51d64b981f32b90'
  SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;


-- Sequence: public.good_id_seq
CREATE SEQUENCE public.good_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 1000000
  START 32
  CACHE 1;
ALTER TABLE public.good_id_seq
  OWNER TO iman;


-- Sequence: public.permission_id_seq
CREATE SEQUENCE public.permission_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 1000000
  START 13
  CACHE 1;
ALTER TABLE public.permission_id_seq
  OWNER TO iman;


-- Table: public.role_permission
CREATE TABLE public.role_permission
(
  role_id integer NOT NULL,
  permission_id integer NOT NULL,
  creation_date timestamp with time zone,
  CONSTRAINT role_permission_pk PRIMARY KEY (role_id, permission_id),
  CONSTRAINT role_permission_permission_id_fkey FOREIGN KEY (permission_id)
      REFERENCES public.permissions (permission_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT role_permission_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES public.roles (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.role_permission
  OWNER TO iman;


-- Table: public.roles
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


-- Table: public.user_role
CREATE TABLE public.user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  creation_date timestamp with time zone,
  CONSTRAINT user_role_pk PRIMARY KEY (user_id, role_id),
  CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES public.roles (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_role
  OWNER TO iman;

-- Table: public.users
CREATE TABLE public.users
(
  user_id integer NOT NULL,
  fullname character varying(45),
  username character varying(45),
  password_hash character varying(100),
  email character varying(45),
  creation_date timestamp with time zone,
  CONSTRAINT user_pk PRIMARY KEY (user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO iman;

