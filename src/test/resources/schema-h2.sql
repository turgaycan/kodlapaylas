CREATE SCHEMA kp

CREATE TABLE kp.article_type
(
  name character varying NOT NULL,
  icon character varying,
  createdate timestamp without time zone DEFAULT now(),
  parent_id bigint,
  id bigserial NOT NULL,
  deleted boolean NOT NULL DEFAULT false,
  CONSTRAINT pk_article_type PRIMARY KEY (id),
  CONSTRAINT unique_article_type_name UNIQUE (name)
)
WITH (
OIDS=FALSE
);


CREATE SEQUENCE kp.article_type_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 24
CACHE 1;

insert into kp.article_type VALUES ('test','',CURRENT_TIMESTAMP ,null,false)