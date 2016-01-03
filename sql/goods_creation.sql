-- Table: public.permissions

-- DROP TABLE public.permissions;

CREATE TABLE public.goods
(
  good_id integer NOT NULL,
  good_name character varying(45),
  good_price integer NOT NULL,
  good_status character NOT NULL,
  good_description character varying(45),
  creation_date timestamp with time zone,
  CONSTRAINT good_pk PRIMARY KEY (good_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.goods
  OWNER TO iman;
