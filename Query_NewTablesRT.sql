DO $$
BEGIN
    -- Verificar o IP do banco de dados
    IF (inet_server_addr()::TEXT != '192.168.0.5/32') THEN
       RAISE EXCEPTION 'Processo abortado: o IP do banco de dados não é 192.168.0.5';
    END IF;

    -- Criar tabelas caso não existam
    CREATE TABLE IF NOT EXISTS cst_is (
        cst_codigo VARCHAR(3) PRIMARY KEY,  -- Definindo chave primária
        cst_descricao TEXT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS cclasstrib_is (
        class_codigo VARCHAR(6) PRIMARY KEY, -- Definindo chave primária
        class_descricao TEXT NOT NULL,
        class_gimpsel BOOLEAN DEFAULT FALSE,
        class_vbc BOOLEAN DEFAULT FALSE,
        class_pimpselvalor BOOLEAN DEFAULT FALSE,
        class_vimpsel BOOLEAN DEFAULT FALSE        
    );

    CREATE TABLE IF NOT EXISTS cst_ibscbs (
        cst_codigo NUMERIC(3,0) PRIMARY KEY,
        cst_descricao TEXT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS cclasstrib_ibscbs (
        class_codigo VARCHAR(6) PRIMARY KEY, -- Definindo chave primária
        class_descricao TEXT NOT NULL,
        class_lc TEXT,
        class_lcredacao TEXT,
        class_dinivig DATE,
        class_dfimvig DATE
    );

END $$;

