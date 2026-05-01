DO $$
BEGIN
    -- Verificar o IP do banco de dados
    IF (inet_server_addr()::TEXT != '192.168.0.5/32') THEN
       RAISE EXCEPTION 'Processo abortado: o IP do banco de dados não é 192.168.0.5';
    END IF;

    -- Criar tabelas caso não existam
    CREATE TABLE IF NOT EXISTS cst_is (
        cst_codigo varchar(3),
        cst_descricao text
    );

    CREATE TABLE IF NOT EXISTS cclasstrib_is (
        class_codigo varchar(6),
        class_descricao text,
	class_gimpsel boolean,
	class_vbc boolean,
	class_pimpselvalor boolean,
	class_vimpsel boolean        
    );

    CREATE TABLE IF NOT EXISTS cst_ibscbs (
        cst_codigo varchar(3),
        cst_descricao text
    );

    CREATE TABLE IF NOT EXISTS cclasstrib_ibscbs (
        class_codigo varchar(6),
        class_descricao text,
        class_lc text,
        class_lcredacao text,
        class_dinivig date,
        class_dfimvig date
    );

END $$;
