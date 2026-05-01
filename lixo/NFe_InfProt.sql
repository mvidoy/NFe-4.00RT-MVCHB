-- Table: nfe_infprot

--DROP TABLE nfe_infprot;

CREATE TABLE nfe_infprot
(
  protnfe_nnf numeric(9,0),  
  protnfe_sequencia numeric(3,0),  
  protnfe_versao numeric(5,2),
  
  infprot_tpamb numeric(1,0),
  infprot_veraplic character(20),
  infprot_chnfe numeric(44,0),
  infprot_dhrecbto timestamp without time zone,
  infprot_nprot numeric(15,0),
  infprot_nid character(20),
  infprot_digval character(28),
  infprot_cstat numeric(3,0),
  infprot_xmotivo character(255)

  )
WITH (
  OIDS=TRUE
);
ALTER TABLE nfe_infprot
  OWNER TO osvaldo;