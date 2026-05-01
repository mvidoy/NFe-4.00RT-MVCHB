DO $$
BEGIN
    -- Verificar o IP do banco de dados
	IF (inet_server_addr()::TEXT != '192.168.0.5/32') THEN
	   RAISE EXCEPTION 'Processo abortado: o IP do banco de dados não é 192.168.0.5';
	END IF;

	
	ALTER TABLE nfe
	ADD COLUMN ide_cmunfgibs numeric(7,0),
	ADD COLUMN ide_xmunfgibs character(60),
        ADD COLUMN ide_indmultajuros numeric(1,0);

        ADD COLUMN totis_vbcsel numeric(15,2),
        ADD COLUMN totis_vimpsel numeric(15,2),
        ADD COLUMN totis_vbcibscbs numeric(15,2),
        
        ADD COLUMN totibsuf_vdif numeric(15,2),
        ADD COLUMN totibsuf_vdevtrib numeric(15,2),
        ADD COLUMN totibsuf_vdeson numeric(15,2),
        ADD COLUMN totibsuf_vibsuf numeric(15,2),
        
        ADD COLUMN totibsmun_vdif numeric(15,2),
        ADD COLUMN totibsmun_vdevtrib numeric(15,2),
        ADD COLUMN totibsmun_vdeson numeric(15,2),
        ADD COLUMN totibsmun_vibsmun numeric(15,2),
        ADD COLUMN totibsmun_vibstot numeric(15,2),
        
        ADD COLUMN totibs_vcrepres numeric(15,2),
        ADD COLUMN totibs_vcredprescondsus numeric(15,2),

        ADD COLUMN totcbs_vcrepres numeric(15,2),
        ADD COLUMN totcbs_vcredprescondsus numeric(15,2),
        
        ADD COLUMN totcbs_vdif numeric(15,2),
        ADD COLUMN totcbs_vdevtrib numeric(15,2),

        ADD COLUMN totcbs_vdeson numeric(15,2),
        ADD COLUMN totcbs_vcbs numeric(15,2),

        ADD COLUMN totnf_vtotnf numeric(15,2),


        ALTER TABLE nfe_det_prod

        -- Informações do Imposto Seletivo
	
        -- Grupo de Informações do Imposto Seletivo 
        ADD COLUMN det_is_cst numeric(3,0),
	ADD COLUMN det_is_cclasstrib character(6),
	ADD COLUMN det_is_vbcimpsel numeric(15,2),
	ADD COLUMN det_is_pimpsel numeric(9,4),
	ADD COLUMN det_is_pimpselespec numeric(9,4),
	ADD COLUMN det_is_utrib character(6),
	ADD COLUMN det_is_qtrib numeric(15,4),
	ADD COLUMN det_is_vimpsel numeric(15,2),
        ADD COLUMN det_is_cst_ibscbs numeric(3,0),
	ADD COLUMN det_is_cclasstrib_ibscbs character(6),
        ADD COLUMN det_is_indperecimento character(1),

        -- Grupo de Informações do IBS, CBS e Imposto Seletivo 
        ADD COLUMN det_ibscbs_vbc numeric(15,4),

        -- Grupo de Informações do IBS para a UF
        ADD COLUMN det_ibsuf_pibsuf numeric(9,4),

        -- Sequencia XML
        ADD COLUMN det_ibsuf_vtribop numeric(15,2),

        -- Grupo de Informações do Diferimento 
        ADD COLUMN det_ibsuf_pdif numeric(9,4),
        ADD COLUMN det_ibsuf_vdif numeric(15,2),

        -- Grupo de Informações da devolução de tributos 
        ADD COLUMN det_ibsuf_vdevtrib numeric(15,2),

        -- Grupo de informações da redução da alíquota
        ADD COLUMN det_ibsuf_predaliq numeric(9,4),
        ADD COLUMN det_ibsuf_paliqefet numeric(9,4),

        -- Grupo de informações da Desoneração
        ADD COLUMN det_ibsuf_cst varchar(3),           
        ADD COLUMN det_ibsuf_cclasstrib varchar(6),      
        ADD COLUMN det_ibsuf_vbc numeric(15,4),              
        ADD COLUMN det_ibsuf_paliq numeric(9,4),
        ADD COLUMN det_ibsuf_vdeson numeric(15,2),
	ADD COLUMN det_ibsuf_vlbsuf numeric(15,2),


	-- Grupo de Informações do IBS para o município
        ADD COLUMN det_ibsmun_pibsmun numeric(9,4),

        -- Sequencia XML
        ADD COLUMN det_ibsmun_vtribop numeric(15,2),

        -- Grupo de Informações do Diferimento 
        ADD COLUMN det_ibsmun_pdif numeric(9,4),
        ADD COLUMN det_ibsmun_vdif numeric(15,2),

        -- Grupo de Informações da devolução de tributos 
        ADD COLUMN det_ibsmun_vdevtrib numeric(15,2),

        -- Grupo de informações da redução da alíquota
        ADD COLUMN det_ibsmun_predaliq numeric(9,4),
        ADD COLUMN det_ibsmun_paliqefet numeric(9,4),

        -- Grupo de informações da Desoneração
        ADD COLUMN det_ibsmun_cst varchar(3),              
        ADD COLUMN det_ibsmun_cclasstrib varchar(6),       
        ADD COLUMN det_ibsmun_vbc numeric(15,4),              
        ADD COLUMN det_ibsmun_paliq numeric(9,4),
        ADD COLUMN det_ibsmun_vdeson numeric(15,2),
	ADD COLUMN det_ibsmun_vlibsmun numeric(15,2),

	-- Grupo de Informações do Crédito Presumido referente ao IBS
        ADD COLUMN det_ibscredpres_ccredpres character(2),               
        ADD COLUMN det_ibscredpres_pcredpres numeric(9,4),
        ADD COLUMN det_ibscredpres_vcredpres numeric(15,2),              
        ADD COLUMN det_ibscredpres_vcredprescondsus numeric(15,2),

        -- Grupo de Informações da CBS
        ADD COLUMN det_cbs_pcbs numeric(9,4),

        -- Grupo de Informações da devolução de tributos 
        ADD COLUMN det_cbs_vtribop numeric(15,2),
        -- Grupo de Informações do Crédito Presumido referente a CBS
        ADD COLUMN det_cbscredpres_ccredpres character(2),               
        ADD COLUMN det_cbscredpres_pcredpres numeric(9,4),
        ADD COLUMN det_cbscredpres_vcredpres numeric(15,2),              
        ADD COLUMN det_cbscredpres_vcredprescondsus numeric(15,2),

        -- Grupo de Informações do Diferimento 
        ADD COLUMN det_cbs_pdif numeric(9,4),
        ADD COLUMN det_cbs_vdif numeric(15,2),

        -- Grupo de Informações da devolução de tributos 
        ADD COLUMN det_cbs_vdevtrib numeric(15,2),

        -- Grupo de informações da redução da alíquota
        ADD COLUMN det_cbs_predaliq numeric(9,4),
        ADD COLUMN det_cbs_paliqefet numeric(9,4),

        -- Grupo de informações da Desoneração
        ADD COLUMN det_cbs_cst varchar(3),              
        ADD COLUMN det_cbs_cclasstrib varchar(6),        
        ADD COLUMN det_cbs_vbc numeric(15,4),              
        ADD COLUMN det_cbs_paliq numeric(9,4),
        ADD COLUMN det_cbs_vdeson numeric(15,2),
	ADD COLUMN det_cbs_vcbs numeric(15,2);

	
END $$;
