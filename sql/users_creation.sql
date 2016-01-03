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