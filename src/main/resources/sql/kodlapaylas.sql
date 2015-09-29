-- Role: kpadmin

-- DROP ROLE kpadmin;

CREATE ROLE kpadmin LOGIN
  ENCRYPTED PASSWORD 'md5cbf26e63ab70c0185f5d465ef81a1ca2'
  SUPERUSER INHERIT NOCREATEDB CREATEROLE NOREPLICATION;


-- Database: kodlapaylas

-- DROP DATABASE kodlapaylas;

CREATE DATABASE kodlapaylas
  WITH OWNER = kpadmin
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


-- Schema: kp

-- DROP SCHEMA kp;

CREATE SCHEMA kp
  AUTHORIZATION kpadmin;

-- Table: kp.seo_stat

-- DROP TABLE kp.seo_stat;

CREATE TABLE kp.seo_stat
(
  domain character varying(100) NOT NULL,
  google character varying(100),
  yahoo character varying(100),
  yandex character varying(100),
  alexa_global character varying(100),
  alexa_local character varying(100),
  alexa_backlink character varying(100),
  dmoz boolean DEFAULT false,
  pagerank integer DEFAULT 0,
  createdate timestamp without time zone NOT NULL DEFAULT now(),
  google_backlink character varying(100),
  id bigint NOT NULL DEFAULT nextval('kp."seo_stat_id_seq"'::regclass),
  deleted boolean NOT NULL DEFAULT false,
  CONSTRAINT pk_seo_stat PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE kp.seo_stat
  OWNER TO kpadmin;

-- Sequence: kp.seo_stat_id_seq

-- DROP SEQUENCE kp.seo_stat_id_seq;

CREATE SEQUENCE kp.seo_stat_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 726
CACHE 1;
ALTER TABLE kp.seo_stat_id_seq
OWNER TO kpadmin;



-- Table: kp.tag

-- DROP TABLE kp.tag;

CREATE TABLE kp.tag
(
  name character varying(100) NOT NULL,
  count integer NOT NULL,
  id bigint NOT NULL DEFAULT nextval('kp.tag_id_seq'::regclass),
  article_type_id bigint,
  deleted boolean NOT NULL DEFAULT false,
  CONSTRAINT pk_tag PRIMARY KEY (id),
  CONSTRAINT unique_tag_name UNIQUE (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE kp.tag
  OWNER TO kpadmin;

-- Sequence: kp.tag_id_seq

-- DROP SEQUENCE kp.tag_id_seq;

CREATE SEQUENCE kp.tag_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 776
CACHE 1;
ALTER TABLE kp.tag_id_seq
OWNER TO kpadmin;


-- Table: kp.kp_statistic

-- DROP TABLE kp.kp_statistic;

CREATE TABLE kp.kp_statistic
(
  counter integer NOT NULL,
  ip_address character varying NOT NULL,
  createdate timestamp without time zone NOT NULL DEFAULT now(),
  deleted boolean NOT NULL DEFAULT false,
  id bigint NOT NULL DEFAULT nextval('kp.kp_statistic_id_seq'::regclass),
  CONSTRAINT pk_kp_statistic PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE kp.kp_statistic
  OWNER TO kpadmin;

-- Sequence: kp.kp_statistic_id_seq

-- DROP SEQUENCE kp.kp_statistic_id_seq;

CREATE SEQUENCE kp.kp_statistic_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 121902
CACHE 1;
ALTER TABLE kp.kp_statistic_id_seq
OWNER TO kpadmin;

-- Table: kp."user"

-- DROP TABLE kp."user";

CREATE TABLE kp."user"
(
  email character varying(50) NOT NULL,
  username character varying(50) NOT NULL,
  password character varying(50) NOT NULL,
  user_status character varying(50) NOT NULL,
  createdate timestamp without time zone DEFAULT now(),
  fullname character varying(50),
  role character varying(20),
  website character varying,
  avatar character varying,
  deleted boolean NOT NULL DEFAULT false,
  id bigint NOT NULL DEFAULT nextval('kp."user_id_seq"'::regclass),
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT unique_user_email UNIQUE (email),
  CONSTRAINT unique_user_username UNIQUE (username)
)
WITH (
OIDS=FALSE
);
ALTER TABLE kp."user"
OWNER TO kpadmin;

-- Sequence: kp.user_id_seq

-- DROP SEQUENCE kp.user_id_seq;

CREATE SEQUENCE kp.user_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 46
CACHE 1;
ALTER TABLE kp.user_id_seq
OWNER TO kpadmin;

-- Column: role

-- ALTER TABLE kp."user" DROP COLUMN role;

ALTER TABLE kp."user" ADD COLUMN role character varying(20);


-- Table: kp.article_type

-- DROP TABLE kp.article_type;

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
ALTER TABLE kp.article_type
OWNER TO kpadmin;

-- Sequence: kp.article_type_id_seq

-- DROP SEQUENCE kp.article_type_id_seq;

CREATE SEQUENCE kp.article_type_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 24
CACHE 1;
ALTER TABLE kp.article_type_id_seq
OWNER TO kpadmin;


-- Table: kp.article

-- DROP TABLE kp.article;

CREATE TABLE kp.article
(
  content character varying(100000) NOT NULL,
  title character varying(100) NOT NULL,
  tags character varying NOT NULL,
  createdate timestamp without time zone NOT NULL DEFAULT now(),
  view_number integer,
  application_name character varying,
  download_number integer,
  modifydate timestamp without time zone DEFAULT now(),
  deleted boolean NOT NULL DEFAULT false,
  id bigserial NOT NULL,
  user_id bigint,
  article_status character varying(20),
  article_type_id bigint,
  CONSTRAINT pk_article PRIMARY KEY (id),
  CONSTRAINT fk_article_article_type FOREIGN KEY (article_type_id)
  REFERENCES kp.article_type (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_article_user FOREIGN KEY (user_id)
  REFERENCES kp."user" (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE kp.article
OWNER TO kpadmin;

-- Index: kp.fki_article_article_type

-- DROP INDEX kp.fki_article_article_type;

CREATE INDEX fki_article_article_type
ON kp.article
USING btree
(article_type_id);

-- Index: kp.fki_article_user

-- DROP INDEX kp.fki_article_user;

CREATE INDEX fki_article_user
ON kp.article
USING btree
(user_id);

-- Sequence: kp.article_id_seq

-- DROP SEQUENCE kp.article_id_seq;

CREATE SEQUENCE kp.article_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 488
CACHE 1;
ALTER TABLE kp.article_id_seq
OWNER TO kpadmin;


-- Table: kp.comment

-- DROP TABLE kp.comment;

CREATE TABLE kp.comment
(
  content character varying(4000) NOT NULL,
  createdate timestamp without time zone NOT NULL DEFAULT now(),
  id bigint NOT NULL DEFAULT nextval('kp.comment_id_seq'::regclass),
  user_id bigint,
  article_id bigint,
  deleted boolean NOT NULL DEFAULT false,
  comment_status character varying,
  CONSTRAINT pk_comment PRIMARY KEY (id),
  CONSTRAINT fk_comment_article FOREIGN KEY (article_id)
  REFERENCES kp.article (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_comment_user FOREIGN KEY (user_id)
  REFERENCES kp."user" (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE kp.comment
OWNER TO kpadmin;

-- Index: kp.fki_comment_article

-- DROP INDEX kp.fki_comment_article;

CREATE INDEX fki_comment_article
ON kp.comment
USING btree
(article_id);

-- Index: kp.fki_comment_user

-- DROP INDEX kp.fki_comment_user;

CREATE INDEX fki_comment_user
ON kp.comment
USING btree
(user_id);

-- Sequence: kp.comment_id_seq

-- DROP SEQUENCE kp.comment_id_seq;

CREATE SEQUENCE kp.comment_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE kp.comment_id_seq
OWNER TO kpadmin;

--URL from title--
update kp.article set url = lower(regexp_replace(title, '\W+', '-', 'g') );
