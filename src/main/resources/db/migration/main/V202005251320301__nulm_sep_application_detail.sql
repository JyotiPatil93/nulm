-- Table: public.nulm_sep_application_document

-- DROP TABLE public.nulm_sep_application_document;

CREATE TABLE public.nulm_sep_application_document
(
  document_uuid character varying(64) NOT NULL,
  filestore_id character varying NOT NULL,
  application_uuid character varying(64) NOT NULL,
  document_type character varying(256) NOT NULL,
  tenant_id character varying(256),
  is_active boolean NOT NULL,
  created_by character varying(64),
  created_time bigint,
  last_modified_by character varying(256),
  last_modified_time bigint,
  CONSTRAINT nulm_sep_application_document_pkey PRIMARY KEY (document_uuid),
  CONSTRAINT fk_nulm_sep_application_document_uuid FOREIGN KEY (application_uuid)
      REFERENCES public.nulm_sep_application_detail (application_uuid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_uuid_document_type UNIQUE (application_uuid, document_type, tenant_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.nulm_sep_application_document
  OWNER TO postgres;
