DO $$
DECLARE
    col_name TEXT;
    col_type TEXT;
BEGIN
    -- Verificar o IP do banco de dados
    IF (inet_server_addr()::TEXT != '192.168.0.5/32') THEN
        RAISE EXCEPTION 'Processo abortado: o IP do banco de dados não é 192.168.0.5';
    END IF;

    -- Adicionar colunas à tabela nfe
    FOR col_name, col_type IN
        SELECT unnest(ARRAY[
            'ide_tpnfdebito',
            'ide_tpnfcredito', 
            'tot_is_vbcsel',
            'tot_is_vimpsel',
            'tot_ibs_uf_vcrespres',
            'tot_ibs_uf_vcredprescondsus',
            'tot_ibs_uf_vdif',
            'tot_ibs_uf_vdevtrib',
            'tot_ibs_uf_vdeson',
            'tot_ibs_uf_vibs',
            'tot_ibs_mun_vcrespres',
            'tot_ibs_mun_vcredprescondsus',
            'tot_ibs_mun_vdif',
            'tot_ibs_mun_vdevtrib',
            'tot_ibs_mun_vdeson',
            'tot_ibs_mun_vibs',
            'tot_cbs_vcrespres',
            'tot_cbs_vcredprescondsus',
            'tot_cbs_vdif',
            'tot_cbs_vdevtrib',
            'tot_cbs_vdeson',
            'tot_cbs_vcbs',
            'tot_vbcibscbs',
            'tot_vtotnf',
            'ide_cmunfgibs',
            'ide_indmultajuros',
            'tot_ibs_vcrespres',
            'tot_ibs_vcredprescondsus'
        ]),
        unnest(ARRAY[
            'numeric(2,0)',
            'numeric(2,0)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(15,2)',
            'integer',
            'integer',
            'numeric(15,2)',
            'numeric(15,2)'
        ])
    LOOP
        IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'nfe' AND column_name = col_name) THEN
            EXECUTE format('ALTER TABLE nfe ADD COLUMN %I %s', col_name, col_type);
        END IF;
    END LOOP;

    -- Adicionar colunas à tabela nfe_det_prod
    FOR col_name, col_type IN
        SELECT unnest(ARRAY[
            'det_ibs_cst',
            'det_ibs_cclasstrib',
            'det_ibs_pibstotal',
            'det_ibs_pibsuf',
            'det_ibs_pibsmun',
            'det_ibsuf_vbc',
            'det_ibsuf_pibsuf',
            'det_ibsuf_vibsuf',
            'det_ibsmun_vbc',
            'det_ibsmun_pibsmun',
            'det_ibsmun_vibsmun',
            'det_is_cst',
            'det_is_cclasstrib',
            'det_is_vbcimpsel',
            'det_is_pimpsel',
            'det_is_vimpsel',
            'det_is_pimpselespec',
            'det_is_utrib',
            'det_is_qtrib',
            'det_is_cstibscbs',
            'det_is_cclasstribibscbs',
            'det_cbs_cst',
            'det_cbs_cclasstrib',
            'det_cbs_pcbsref',
            'det_cbs_vbc',
            'det_cbs_pcbs',
            'det_cbs_vcbs',
            'det_ibscbs_vbc'
        ]),
        unnest(ARRAY[
            'numeric(3,0)',
            'numeric(6,0)',
            'numeric(7,4)',
            'numeric(7,4)',
            'numeric(7,4)',
            'numeric(15,2)',
            'numeric(7,4)',
            'numeric(15,2)',
            'numeric(15,2)',
            'numeric(7,4)',
            'numeric(15,2)',
            'numeric(3,0)',
            'numeric(6,0)',
            'numeric(15,2)',
            'numeric(7,4)',
            'numeric(15,2)',
            'numeric(7,4)',
            'character varying(6) COLLATE pg_catalog."default"',
            'numeric(15,4)',
            'numeric(3,0)',
            'numeric(6,0)',
            'numeric(3,0)',
            'numeric(6,0)',
            'numeric(7,4)',
            'numeric(15,2)',
            'numeric(7,4)',
            'numeric(15,2)',
            'numeric(15,2)'
        ])
    LOOP
        IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'nfe_det_prod' AND column_name = col_name) THEN
            EXECUTE format('ALTER TABLE nfe_det_prod ADD COLUMN %I %s', col_name, col_type);
        END IF;
    END LOOP;
END $$;