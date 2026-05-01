-- Table: nfe_nfref

-- DROP TABLE nfe_nfref;

CREATE TABLE nfe_nfref
(
  nfref_tipo character(12),
  nfref_refnfe numeric(44,0),
  nfref_cuf character(2),
  nfref_aamm numeric(4,0),
  nfref_cnpj numeric(14,0),
  nfref_mod numeric(2,0),
  nfref_serie numeric(3,0),
  nfref_nnf numeric(9,0),
  ide_nnf numeric(9,0)
)
WITH (
  OIDS=TRUE
);
ALTER TABLE nfe_nfref
  OWNER TO osvaldo;
