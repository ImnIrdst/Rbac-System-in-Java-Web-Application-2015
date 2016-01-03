CREATE TABLE public.user_role
(
  user_id integer NOT NULL references users(user_id),
  role_id integer NOT NULL references roles(role_id),
  creation_date timestamp with time zone,
  CONSTRAINT user_role_pk PRIMARY KEY (user_id, role_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_role
  OWNER TO iman;
