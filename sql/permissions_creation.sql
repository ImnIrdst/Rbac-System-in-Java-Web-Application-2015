CREATE TABLE permissions
(
  permission_id integer NOT NULL,
  permission_name character varying(45),
  permission_description character varying(45),
  creation_date timestamp with time zone,
  CONSTRAINT permission_pk PRIMARY KEY (permission_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE permissions
  OWNER TO iman;
