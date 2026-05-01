DO $$
BEGIN
    -- Verificar o IP do banco de dados
    IF (inet_server_addr()::TEXT != '192.168.0.5/32') THEN
       RAISE EXCEPTION 'Processo abortado: o IP do banco de dados não é 192.168.0.5';
    END IF;

    ALTER TABLE nfe_det_prod ADD COLUMN IF NOT EXISTS det_ibs_cst VARCHAR(3),
                             ADD COLUMN IF NOT EXISTS det_ibs_cclasstrib VARCHAR(6),
                             ADD COLUMN IF NOT EXISTS det_ibs_pibstotal NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_ibs_pibsuf NUMERIC(7,4),    
                             ADD COLUMN IF NOT EXISTS det_ibs_pibsmun NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_ibscbs_vbc NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_ibsuf_vbc NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_ibsuf_pibsuf NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_ibsuf_vibsuf NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_ibsmun_vbc NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_ibsmun_pibsmun NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_ibsmun_vibsmun NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_is_cst VARCHAR(3),
                             ADD COLUMN IF NOT EXISTS det_is_cclasstrib VARCHAR(6),
                             ADD COLUMN IF NOT EXISTS det_is_vbcimpsel NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_is_pimpsel NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_is_vimpsel NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_is_pimpselespec NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_is_utrib VARCHAR(6),
                             ADD COLUMN IF NOT EXISTS det_is_qtrib NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_is_cstibscbs NUMERIC(3,0),
                             ADD COLUMN IF NOT EXISTS det_is_cclasstribibscbs NUMERIC(6,0),
                             ADD COLUMN IF NOT EXISTS det_cbs_cst VARCHAR(3),
                             ADD COLUMN IF NOT EXISTS det_cbs_cclasstrib VARCHAR(6),
                             ADD COLUMN IF NOT EXISTS det_cbs_pcbsref NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_cbs_vbc NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_cbs_pcbs NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_cbs_vcbs NUMERIC(15,2);

    ALTER TABLE nfe ADD COLUMN IF NOT EXISTS ide_cmunfgibs NUMERIC(7,0),
                    ADD COLUMN IF NOT EXISTS tot_is_vbcsel NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_is_vimpsel NUMERIC(15,2),                    
                    
                    ADD COLUMN IF NOT EXISTS tot_ibs_uf_vcrespres NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_uf_vcredprescondsus NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_uf_vdif NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_uf_vdevtrib NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_uf_vdeson NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_uf_vibsuf NUMERIC(15,2),

                    ADD COLUMN IF NOT EXISTS tot_ibs_mun_vcrespres NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_mun_vcredprescondsus NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_mun_vdif NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_mun_vdevtrib NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_mun_vdeson NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_mun_vibsmun NUMERIC(15,2),

                    ADD COLUMN IF NOT EXISTS tot_ibs_vcrespres NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_ibs_vcredprescondsus NUMERIC(15,2),
    
                    ADD COLUMN IF NOT EXISTS tot_cbs_vcrespres NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_cbs_vcredprescondsus NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_cbs_vdif NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_cbs_vdevtrib NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_cbs_vdeson NUMERIC(15,2),
                    ADD COLUMN IF NOT EXISTS tot_cbs_vcbs NUMERIC(15,2),

                    ADD COLUMN IF NOT EXISTS tot_vbcibscbs NUMERIC(15,2),                    
                    ADD COLUMN IF NOT EXISTS tot_vtotnf NUMERIC(15,2);

    ALTER TABLE canfeitenot  ADD COLUMN IF NOT EXISTS det_ibs_cst VARCHAR(3),
                             ADD COLUMN IF NOT EXISTS det_ibs_cclasstrib VARCHAR(6),
                             ADD COLUMN IF NOT EXISTS det_ibs_pibstotal NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_ibs_pibsuf NUMERIC(7,4),    
                             ADD COLUMN IF NOT EXISTS det_ibs_pibsmun NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_ibscbs_vbc NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_ibsuf_vbc NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_ibsuf_pibsuf NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_ibsuf_vibsuf NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_ibsmun_vbc NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_ibsmun_pibsmun NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_ibsmun_vibsmun NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_is_cst VARCHAR(3),
                             ADD COLUMN IF NOT EXISTS det_is_cclasstrib VARCHAR(6),
                             ADD COLUMN IF NOT EXISTS det_is_vbcimpsel NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_is_pimpsel NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_is_vimpsel NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_is_pimpselespec NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_is_utrib VARCHAR(6),
                             ADD COLUMN IF NOT EXISTS det_is_qtrib NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_is_cstibscbs NUMERIC(3,0),
                             ADD COLUMN IF NOT EXISTS det_is_cclasstribibscbs NUMERIC(6,0),
                             ADD COLUMN IF NOT EXISTS det_cbs_cst VARCHAR(3),
                             ADD COLUMN IF NOT EXISTS det_cbs_cclasstrib VARCHAR(6),
                             ADD COLUMN IF NOT EXISTS det_cbs_pcbsref NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_cbs_vbc NUMERIC(15,2),
                             ADD COLUMN IF NOT EXISTS det_cbs_pcbs NUMERIC(7,4),
                             ADD COLUMN IF NOT EXISTS det_cbs_vcbs NUMERIC(15,2);

    ALTER TABLE canfenotfis ADD COLUMN IF NOT EXISTS tot_is_vbcsel NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_is_vimpsel NUMERIC(15,2),                    
                    
                            ADD COLUMN IF NOT EXISTS tot_ibscbs_vbc NUMERIC(15,2),

                            ADD COLUMN IF NOT EXISTS tot_ibs_uf_vcrespres NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_uf_vcredprescondsus NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_uf_vdif NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_uf_vdevtrib NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_uf_vdeson NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_uf_vibs NUMERIC(15,2),

                            ADD COLUMN IF NOT EXISTS tot_ibs_mun_vcrespres NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_mun_vcredprescondsus NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_mun_vdif NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_mun_vdevtrib NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_mun_vdeson NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_mun_vibs NUMERIC(15,2),

                            ADD COLUMN IF NOT EXISTS tot_ibs_vcrespres NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_ibs_vcredprescondsus NUMERIC(15,2),
    
                            ADD COLUMN IF NOT EXISTS tot_cbs_vcrespres NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_cbs_vcredprescondsus NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_cbs_vdif NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_cbs_vdevtrib NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_cbs_vdeson NUMERIC(15,2),
                            ADD COLUMN IF NOT EXISTS tot_cbs_vcbs NUMERIC(15,2),


END $$;
