-- Table: nfe_inutnfe

-- DROP TABLE nfe_inutnfe;

CREATE TABLE nfe_inutnfe
(
  inutnfe_versao numeric(5,2),
  infinut_id character(43),
  infinut_tpamb numeric(1,0),
  infinut_xserv character(10),
  infinut_cuf numeric(2,0),
  infinut_ano numeric(2,0),
  infinut_cnpj character(14),
  infinut_mod numeric(2,0),
  infinut_serie numeric(3,0),
  infinut_nnfini numeric(9,0),
  infinut_nnffin numeric(9,0),
  infinut_xjust character(255),
  retinutnfe_versao numeric(5,2),
  retinutnfe_infinut_id character(17),
  retinutnfe_infinut_tpamb numeric(1,0),
  retinutnfe_infinut_veraplic character(20),
  retinutnfe_infinut_cstat numeric(3,0),
  retinutnfe_infinut_xmotivo character(255),
  retinutnfe_infinut_cuf numeric(2,0),
  retinutnfe_infinut_ano numeric(2,0),
  retinutnfe_infinut_cnpj character(14),
  retinutnfe_infinut_mod numeric(2,0),
  retinutnfe_infinut_serie numeric(3,0),
  retinutnfe_infinut_nnfini numeric(9,0),
  retinutnfe_infinut_nnffin numeric(9,0),
  retinutnfe_infinut_dhrecbto timestamp without time zone,
  retinutnfe_infinut_nprot numeric(15,0),
  
  xml_retinutnfe_autorizado xml
)
WITH (
  OIDS=TRUE
);
ALTER TABLE nfe_inutnfe
  OWNER TO osvaldo;