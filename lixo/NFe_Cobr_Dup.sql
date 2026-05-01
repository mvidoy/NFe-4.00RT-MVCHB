-- Table: nfe_cobr_dup

-- DROP TABLE nfe_cobr_dup;

CREATE TABLE nfe_cobr_dup
(
  cobr_dup_nnf numeric(9,0),
  cobr_dup_item numeric(3,0),
  cobr_dup_ndup character(60),
  cobr_dup_dvenc timestamp without time zone,
  cobr_dup_vdup numeric(15,2)
)
WITH (
  OIDS=TRUE
);
ALTER TABLE nfe_cobr_dup
  OWNER TO osvaldo;
