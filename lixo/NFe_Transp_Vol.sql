-- Table: nfe_transp_vol

-- DROP TABLE nfe_transp_vol;

CREATE TABLE nfe_transp_vol
(
  transp_vol_nnf numeric(9,0),
  transp_vol_item numeric(3,0),
  transp_vol_qvol numeric(15,0),
  transp_vol_esp character(60),
  transp_vol_marca character(60),
  transp_vol_nvol character(60),
  transp_vol_pesol numeric(15,3),
  transp_vol_pesob numeric(15,3)
)
WITH (
  OIDS=TRUE
);
ALTER TABLE nfe_transp_vol
  OWNER TO osvaldo;