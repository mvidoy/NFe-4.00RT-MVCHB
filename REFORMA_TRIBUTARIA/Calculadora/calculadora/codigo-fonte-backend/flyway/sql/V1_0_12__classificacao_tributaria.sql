INSERT INTO CLASSIFICACAO_TRIBUTARIA (
    CLTR_ID,
    CLTR_SITR_ID,
    CLTR_TIPO_ALIQUOTA,
    CLTR_CD,
    CLTR_DESCRICAO,
    CLTR_NOMENCLATURA,
    CLTR_MEMORIA_CALCULO,
    CLTR_IN_CREDITO_PARA_FRENTE,
    CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR,
    CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE,
    CLTR_CREDITO_PARA_FRENTE_FDLG_ID,
    CLTR_TMES_ID,
    CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE,
    CLTR_INICIO_VIGENCIA,
    CLTR_FIM_VIGENCIA
) VALUES
(
    -- CLTR_ID
    1,
    -- CLTR_SITR_ID
    1,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '000001',
    -- CLTR_DESCRICAO
    'Situações tributadas integralmente pelo IBS e CBS.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    2,
    -- CLTR_SITR_ID
    1,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '000002',
    -- CLTR_DESCRICAO
    'Exploração de via, observado o art. 11 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    3,
    -- CLTR_SITR_ID
    1,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '000003',
    -- CLTR_DESCRICAO
    'Regime automotivo - projetos incentivados, observado o art. 311 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    4,
    -- CLTR_SITR_ID
    1,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '000004',
    -- CLTR_DESCRICAO
    'Regime automotivo - projetos incentivados, observado o art. 312 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    5,
    -- CLTR_SITR_ID
    2,
    -- CLTR_TIPO_ALIQUOTA
    'Referência',
    -- CLTR_CD
    '010001',
    -- CLTR_DESCRICAO
    'Operações do FGTS não realizadas pela Caixa Econômica Federal, observado o art. 212 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de referência [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao] % na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    6,
    -- CLTR_SITR_ID
    3,
    -- CLTR_TIPO_ALIQUOTA
    'Referência',
    -- CLTR_CD
    '011001',
    -- CLTR_DESCRICAO
    'Planos de assistência funerária, observado o art. 236 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de referência de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao] % na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    7,
    -- CLTR_SITR_ID
    3,
    -- CLTR_TIPO_ALIQUOTA
    'Referência',
    -- CLTR_CD
    '011002',
    -- CLTR_DESCRICAO
    'Planos de assistência à saúde, observado o art. 237 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de referência de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao] % na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    8,
    -- CLTR_SITR_ID
    3,
    -- CLTR_TIPO_ALIQUOTA
    'Referência',
    -- CLTR_CD
    '011003',
    -- CLTR_DESCRICAO
    'Intermediação de planos de assistência à saúde, observado o art. 240 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de referência de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao] % na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    9,
    -- CLTR_SITR_ID
    4,
    -- CLTR_TIPO_ALIQUOTA
    'Referência',
    -- CLTR_CD
    '011004',
    -- CLTR_DESCRICAO
    'Concursos e prognósticos, observado o art. 246 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de referência de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao] % na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    10,
    -- CLTR_SITR_ID
    5,
    -- CLTR_TIPO_ALIQUOTA
    'Referência',
    -- CLTR_CD
    '011005',
    -- CLTR_DESCRICAO
    'Planos de assistência à saúde de animais domésticos, observado o art. 243 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de referência de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao] % na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    11,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200001',
    -- CLTR_DESCRICAO
    'Aquisições de máquinas, de aparelhos, de instrumentos, de equipamentos, de matérias-primas, de produtos intermediários e de materiais de embalagem realizadas entre empresas autorizadas a operar em zonas de processamento de exportação, observado o art. 103 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    12,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200002',
    -- CLTR_DESCRICAO
    'Fornecimento ou importação de tratores, máquinas e implementos agrícolas, destinados a produtor rural não contribuinte, e de veículos de transporte de carga destinados a transportador autônomo de carga pessoa física não contribuinte, observado o art. 110 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    13,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200003',
    -- CLTR_DESCRICAO
    'Vendas de produtos destinados à alimentação humana relacionados no Anexo I da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, que compõem a Cesta Básica Nacional de Alimentos, criada nos termos do art. 8º da Emenda Constitucional nº 132, de 20 de dezembro de 2023, observado o art. 125 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    14,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200004',
    -- CLTR_DESCRICAO
    'Venda de dispositivos médicos com a especificação das respectivas classificações da NCM/SH previstas no Anexo XII da Lei Complementar nº 214, de 2025, observado o art. 144 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    15,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200005',
    -- CLTR_DESCRICAO
    'Venda de dispositivos médicos com a especificação das respectivas classificações da NCM/SH previstas no Anexo IV da Lei Complementar nº 214, de 2025, quando adquiridos por órgãos da administração pública direta, autarquias e fundações públicas, observado o art. 144 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    16,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200006',
    -- CLTR_DESCRICAO
    'Situação de emergência de saúde pública reconhecida pelo Poder Legislativo federal, estadual, distrital ou municipal competente, ato conjunto do Ministro da Fazenda e do Comitê Gestor do IBS poderá ser editado, a qualquer momento, para incluir dispositivos não listados no Anexo XIII da Lei Complementar nº 214, de 2025, limitada a vigência do benefício ao período e à localidade da emergência de saúde pública, observado o art. 144 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    17,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200007',
    -- CLTR_DESCRICAO
    'Fornecimento dos dispositivos de acessibilidade próprios para pessoas com deficiência relacionados no Anexo XIV da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, observado o art. 145 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    18,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200008',
    -- CLTR_DESCRICAO
    'Fornecimento dos dispositivos de acessibilidade próprios para pessoas com deficiência relacionados no Anexo V da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, quando adquiridos por órgãos da administração pública direta, autarquias, fundações públicas e entidades imunes, observado o art. 145 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    19,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200009',
    -- CLTR_DESCRICAO
    'Fornecimento dos medicamentos relacionados no Anexo XIV da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, observado o art. 146 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    20,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200010',
    -- CLTR_DESCRICAO
    'Fornecimento dos medicamentos registrados na Anvisa, quando adquiridos por órgãos da administração pública direta, autarquias, fundações públicas e entidades imunes, observado o art. 146 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    21,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200011',
    -- CLTR_DESCRICAO
    'Fornecimento das composições para nutrição enteral e parenteral, composições especiais e fórmulas nutricionais destinadas às pessoas com erros inatos do metabolismo relacionadas no Anexo VI da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, quando adquiridas por órgãos da administração pública direta, autarquias e fundações públicas, observado o art. 146 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    22,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200012',
    -- CLTR_DESCRICAO
    'Situação de emergência de saúde pública reconhecida pelo Poder Legislativo federal, estadual, distrital ou municipal competente, ato conjunto do Ministro da Fazenda e do Comitê Gestor do IBS poderá ser editado, a qualquer momento, para incluir dispositivos não listados no Anexo XIV da Lei Complementar nº 214, de 2025, limitada a vigência do benefício ao período e à localidade da emergência de saúde pública, observado o art. 146 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    23,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200013',
    -- CLTR_DESCRICAO
    'Fornecimento de tampões higiênicos, absorventes higiênicos internos ou externos, descartáveis ou reutilizáveis, calcinhas absorventes e coletores menstruais, observado o art. 147 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    24,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200014',
    -- CLTR_DESCRICAO
    'Fornecimento dos produtos hortícolas, frutas e ovos, relacionados no Anexo XV da Lei Complementar nº 214 , de 2025, com a especificação das respectivas classificações da NCM/SH e desde que não cozidos, observado o art. 148 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    25,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200015',
    -- CLTR_DESCRICAO
    'Venda de automóveis de passageiros de fabricação nacional de, no mínimo, 4 (quatro) portas, inclusive a de acesso ao bagageiro, quando adquiridos por motoristas profissionais que exerçam, comprovadamente, em automóvel de sua propriedade, atividade de condutor autônomo de passageiros, na condição de titular de autorização, permissão ou concessão do poder público, e que destinem o automóvel à utilização na categoria de aluguel (táxi), ou por pessoas com deficiência física, visual, auditiva, deficiência mental severa ou profunda, transtorno do espectro autista, com prejuízos na   comunicação social e em padrões restritos ou repetitivos de comportamento de nível moderado ou grave, nos termos da legislação relativa à matéria, observado o disposto no art. 149 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    26,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200016',
    -- CLTR_DESCRICAO
    'Prestação de serviços de pesquisa e desenvolvimento por Instituição Científica, Tecnológica e de Inovação (ICT) sem fins lucrativos para a administração pública direta, autarquias e fundações públicas ou para o contribuinte sujeito ao regime regular do IBS e da CBS, observado o disposto no art. 156  da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    27,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200017',
    -- CLTR_DESCRICAO
    'Operações relacionadas ao FGTS, considerando aquelas necessárias à aplicação da Lei nº 8.036, de 1990, realizadas pelo Conselho Curador ou Secretaria Executiva do FGTS, observado o art. 212 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    28,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200018',
    -- CLTR_DESCRICAO
    'Operações de resseguro e retrocessão ficam sujeitas à incidência à alíquota zero, inclusive quando os prêmios de resseguro e retrocessão forem cedidos ao exterior, observado o art. 223 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    29,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200019',
    -- CLTR_DESCRICAO
    'Importador dos serviços financeiros seja contribuinte que realize as operações de que tratam os incisos I a V do caput do art. 182, será aplicada alíquota zero na importação, sem prejuízo da manutenção do direito de dedução dessas despesas da base de cálculo do IBS e da CBS, segundo, observado o art. 231 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    30,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200020',
    -- CLTR_DESCRICAO
    'Operação praticada por sociedades cooperativas optantes por regime específico do IBS e CBS, quando o associado destinar bem ou serviço à cooperativa de que participa, e a cooperativa fornecer bem ou serviço ao associado sujeito ao regime regular do IBS e da CBS, observado o art. 271 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    31,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200021',
    -- CLTR_DESCRICAO
    'Serviços de transporte público coletivo de passageiros ferroviário e hidroviário urbanos, semiurbanos e metropolitanos, observado o art. 285 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    32,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200022',
    -- CLTR_DESCRICAO
    'Operação originada fora da Zona Franca de Manaus que destine bem material industrializado de origem nacional a contribuinte estabelecido na Zona Franca de Manaus que seja habilitado nos termos do art. 442 da Lei Complementar nº 214, de 2025, e sujeito ao regime regular do IBS e da CBS ou optante pelo regime do Simples Nacional de que trata o art. 12 da Lei Complementar nº 123, de 2006, observado o art. 445 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    33,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200023',
    -- CLTR_DESCRICAO
    'Operação realizada por indústria incentivada que destine bem material intermediário para outra indústria incentivada na Zona Franca de Manaus, desde que a entrega ou disponibilização dos bens ocorra dentro da referida área, observado o art. 448 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    34,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '200024',
    -- CLTR_DESCRICAO
    'Operação originada fora das Áreas de Livre Comércio que destine bem material industrializado de origem nacional a contribuinte estabelecido nas Áreas de Livre Comércio que seja habilitado nos termos do art. 456 da Lei Complementar nº 214, de 2025, e sujeito ao regime regular do IBS e da CBS ou optante pelo regime do Simples Nacional de que trata o art. 12 da Lei Complementar nº 123, de 2006, observado o art. 463 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    35,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200025',
    -- CLTR_DESCRICAO
    'Fornecimento dos serviços de educação relacionados ao Programa Universidade para Todos (Prouni), instituído pela Lei nº 11.096, de 13 de janeiro de 2005, observado o art. 308 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    36,
    -- CLTR_SITR_ID
    8,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200026',
    -- CLTR_DESCRICAO
    'Locação de imóveis localizados nas zonas reabilitadas, pelo prazo de 5 (cinco) anos, contado da data de expedição do habite-se, e relacionados a projetos de reabilitação urbana de zonas históricas e de áreas críticas de recuperação e reconversão urbanística dos Municípios ou do Distrito Federal, a serem delimitadas por lei municipal ou distrital, observado o art. 158 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    37,
    -- CLTR_SITR_ID
    9,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200027',
    -- CLTR_DESCRICAO
    'Operações de locação, cessão onerosa e arrendamento de bens imóveis, observado o art. 261 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    38,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200028',
    -- CLTR_DESCRICAO
    'Fornecimento dos serviços de educação relacionados no Anexo II da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da Nomenclatura Brasileira de Serviços, Intangíveis e Outras Operações que Produzam Variações no Patrimônio (NBS), observado o art. 129 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    39,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200029',
    -- CLTR_DESCRICAO
    'Fornecimento dos serviços de saúde humana relacionados no Anexo III da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NBS, observado o art. 130 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    40,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200030',
    -- CLTR_DESCRICAO
    'Venda dos dispositivos médicos relacionados no Anexo IV da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, observado o art. 131 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    41,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200031',
    -- CLTR_DESCRICAO
    'Fornecimento dos dispositivos de acessibilidade próprios para pessoas com deficiência relacionados no Anexo V da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, observado o art. 132 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    42,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200032',
    -- CLTR_DESCRICAO
    'Fornecimento dos medicamentos registrados na Anvisa ou produzidos por farmácias de manipulação, ressalvados os medicamentos sujeitos à alíquota zero de que trata o art. 141 da Lei Complementar nº 214, de 2025, observado o art. 133 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    43,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200033',
    -- CLTR_DESCRICAO
    'Fornecimento das composições para nutrição enteral e parenteral, composições especiais e fórmulas nutricionais destinadas às pessoas com erros inatos do metabolismo relacionadas no Anexo VI da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, observado o art. 133 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    44,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200034',
    -- CLTR_DESCRICAO
    'Fornecimento dos alimentos destinados ao consumo humano relacionados no Anexo VII da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, observado o art. 135 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    45,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200035',
    -- CLTR_DESCRICAO
    'Fornecimento dos produtos de higiene pessoal e limpeza relacionados no Anexo VIII da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH, observado o art. 136 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    46,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200036',
    -- CLTR_DESCRICAO
    'Fornecimento de produtos agropecuários, aquícolas, pesqueiros, florestais e extrativistas vegetais in natura, observado o art. 137 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    47,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200037',
    -- CLTR_DESCRICAO
    'Fornecimento de serviços ambientais de conservação ou recuperação da vegetação nativa, mesmo que fornecidos sob a forma de manejo sustentável de sistemas agrícolas, agroflorestais e agrossilvopastoris, em conformidade com as definições e requisitos da legislação específica, observado o art. 137 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    48,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200038',
    -- CLTR_DESCRICAO
    'Fornecimento dos insumos agropecuários e aquícolas relacionados no Anexo IX da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NCM/SH e da NBS, observado o art. 138 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    49,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200039',
    -- CLTR_DESCRICAO
    'Fornecimento dos serviços e o licenciamento ou cessão dos direitos relacionados no Anexo X da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NBS, quando destinados às seguintes produções nacionais artísticas, culturais, de eventos, jornalísticas e audiovisuais: espetáculos teatrais, circenses e de dança, shows musicais, desfiles carnavalescos ou folclóricos, eventos acadêmicos e científicos, como congressos, conferências e simpósios, feiras de negócios, exposições, feiras e mostras culturais, artísticas e literárias; programas de auditório ou jornalísticos, filmes, documentários, séries, novelas, entrevistas e clipes musicais, observado o art. 139 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    50,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200040',
    -- CLTR_DESCRICAO
    'Fornecimento dos seguintes serviços de comunicação institucional à administração pública direta, autarquias e fundações públicas: serviços direcionados ao planejamento, criação, programação e manutenção de páginas eletrônicas da administração pública, ao monitoramento e gestão de suas redes sociais e à otimização de páginas e canais digitais para mecanismos de buscas e produção de mensagens, infográficos, painéis interativos e conteúdo institucional, serviços de relações com a imprensa, que reúnem estratégias organizacionais para promover e reforçar a comunicação dos órgãos e das entidades contratantes com seus públicos de interesse, por meio da interação com profissionais da imprensa, e serviços de relações públicas, que compreendem o esforço de comunicação planejado, coeso e contínuo que tem por objetivo estabelecer adequada percepção da atuação e dos objetivos institucionais, a partir do estímulo à compreensão mútua e da manutenção de padrões de relacionamento e fluxos de informação entre os órgãos e as entidades contratantes e seus públicos de interesse, no País e no exterior, observado o art. 140 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    51,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200041',
    -- CLTR_DESCRICAO
    'Operações relacionadas às seguintes atividades desportivas: fornecimento de serviço de educação desportiva, classificado no código 1.2205.12.00 da NBS, e gestão e exploração do desporto por associações e clubes esportivos filiados ao órgão estadual ou federal responsável pela coordenação dos desportos, inclusive por meio de venda de ingressos para eventos desportivos, fornecimento oneroso ou não de bens e serviços, inclusive ingressos, por meio de programas de sócio-torcedor, cessão dos direitos desportivos dos atletas e transferência de atletas para outra entidade desportiva ou seu retorno à atividade em outra entidade desportiva, observado o art. 141 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    52,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200042',
    -- CLTR_DESCRICAO
    'Operações relacionadas ao fornecimento de serviço de educação desportiva, classificado no código 1.2205.12.00 da NBS, observado o art. 141 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    53,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200043',
    -- CLTR_DESCRICAO
    'Fornecimento à administração pública direta, autarquias e fundações púbicas dos serviços e dos bens relativos à soberania e à segurança nacional, à segurança da informação e à segurança cibernética relacionados no Anexo XI da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NBS e da NCM/SH, observado o art. 142 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    54,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200044',
    -- CLTR_DESCRICAO
    'Operações e prestações de serviços de segurança da informação e segurança cibernética desenvolvidos por sociedade que tenha sócio brasileiro com o mínimo de 20% (vinte por cento) do seu capital social, relacionados no Anexo XI da Lei Complementar nº 214, de 2025, com a especificação das respectivas classificações da NBS e da NCM/SH, observado o art. 142 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    55,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200045',
    -- CLTR_DESCRICAO
    'Operações relacionadas a projetos de reabilitação urbana de zonas históricas e de áreas críticas de recuperação e reconversão urbanística dos Municípios ou do Distrito Federal, a serem delimitadas por lei municipal ou distrital, observado o art. 158 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    56,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200046',
    -- CLTR_DESCRICAO
    'Operações com bens imóveis, observado o art. 261 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    57,
    -- CLTR_SITR_ID
    12,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200047',
    -- CLTR_DESCRICAO
    'Bares e Restaurantes, observado o art. 275 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    58,
    -- CLTR_SITR_ID
    12,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200048',
    -- CLTR_DESCRICAO
    'Hotelaria, Parques de Diversão e Parques Temáticos, observado o art. 281 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    59,
    -- CLTR_SITR_ID
    12,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200049',
    -- CLTR_DESCRICAO
    'Transporte coletivo de passageiros rodoviário, ferroviário e hidroviário intermunicipais e interestaduais, observado o art. 286 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    60,
    -- CLTR_SITR_ID
    12,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200450',
    -- CLTR_DESCRICAO
    'Serviços de transporte aéreo regional coletivo de passageiros ou de carga, observado o art. 287 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    61,
    -- CLTR_SITR_ID
    12,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200051',
    -- CLTR_DESCRICAO
    'Agências de Turismo, observado o art. 289 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    62,
    -- CLTR_SITR_ID
    13,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200052',
    -- CLTR_DESCRICAO
    'Prestação de serviços das seguintes profissões intelectuais de natureza científica, literária ou artística, submetidas à fiscalização por conselho profissional: administradores, advogados, arquitetos e urbanistas, assistentes sociais, bibliotecários, biólogos, contabilistas, economistas, economistas domésticos, profissionais de educação física, engenheiros e agrônomos, estatísticos, médicos veterinários e zootecnistas, museólogos, químicos, profissionais de relações públicas, técnicos industriais e técnicos agrícolas, observado o art. 127 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    63,
    -- CLTR_SITR_ID
    14,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '210001',
    -- CLTR_DESCRICAO
    'Redutor social aplicado uma única vez na alienação de bem imóvel residencial novo, observado o art. 259 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'CIB',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    64,
    -- CLTR_SITR_ID
    14,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '210002',
    -- CLTR_DESCRICAO
    'Redutor social aplicado uma única vez na alienação de lote residencial, observado o art. 259 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'CIB',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    65,
    -- CLTR_SITR_ID
    15,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '210003',
    -- CLTR_DESCRICAO
    'Redutor social em operações de locação, cessão onerosa e arrendamento de bens imóveis de uso residencial, observado o art. 260 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'CIB',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    66,
    -- CLTR_SITR_ID
    16,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '220001',
    -- CLTR_DESCRICAO
    'Incorporação imobiliária submetida ao regime especial de tributação, observado o art. 485 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    67,
    -- CLTR_SITR_ID
    16,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '220002',
    -- CLTR_DESCRICAO
    'Incorporação imobiliária submetida ao regime especial de tributação, observado o art. 485 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    68,
    -- CLTR_SITR_ID
    16,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '220003',
    -- CLTR_DESCRICAO
    'Alienação de imóvel decorrente de parcelamento do solo, observado o art. 486 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    69,
    -- CLTR_SITR_ID
    17,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '221001',
    -- CLTR_DESCRICAO
    'Locação, cessão onerosa ou arrendamento de bem imóvel com alíquota sobre a receita bruta, observado o art. 487 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    70,
    -- CLTR_SITR_ID
    18,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '400001',
    -- CLTR_DESCRICAO
    'Fornecimento de serviços de transporte público coletivo de passageiros rodoviário e metroviário de caráter urbano, semiurbano e metropolitano, sob regime de autorização, permissão ou concessão pública, observado o art. 157 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    71,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410001',
    -- CLTR_DESCRICAO
    'Fornecimento de bonificações quando constem do respectivo documento fiscal e que não dependam de evento posterior, observado o art. 5º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    72,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410002',
    -- CLTR_DESCRICAO
    'Transferências entre estabelecimentos pertencentes ao mesmo contribuinte, observado o art. 6º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    73,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410003',
    -- CLTR_DESCRICAO
    'Doações, observado o art. 6º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    74,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410004',
    -- CLTR_DESCRICAO
    'Exportações de bens e serviços, observado o art. 8º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    75,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410005',
    -- CLTR_DESCRICAO
    'Fornecimentos realizados pela União, pelos Estados, pelo Distrito Federal e pelos Municípios, observado o art. 9º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    76,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410006',
    -- CLTR_DESCRICAO
    'Fornecimentos realizados por entidades religiosas e templos de qualquer culto, inclusive suas organizações assistenciais e beneficentes, observado o art. 9º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    77,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410007',
    -- CLTR_DESCRICAO
    'Fornecimentos realizados por partidos políticos, inclusive suas fundações, entidades sindicais dos trabalhadores e instituições de educação e de assistência social, sem fins lucrativos, observado o art. 9º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    78,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410008',
    -- CLTR_DESCRICAO
    'Fornecimentos de livros, jornais, periódicos e do papel destinado a sua impressão, observado o art. 9º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    79,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410009',
    -- CLTR_DESCRICAO
    'Fornecimentos de fonogramas e videofonogramas musicais produzidos no Brasil contendo obras musicais ou literomusicais de autores brasileiros e/ou obras em geral interpretadas por artistas brasileiros, bem como os suportes materiais ou arquivos digitais que os contenham, salvo na etapa de replicação industrial de mídias ópticas de leitura a laser, observado o art. 9º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    80,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410010',
    -- CLTR_DESCRICAO
    'Fornecimentos de serviço de comunicação nas modalidades de radiodifusão sonora e de sons e imagens de recepção livre e   gratuita, observado o art. 9º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    81,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410011',
    -- CLTR_DESCRICAO
    'Fornecimentos de ouro, quando definido em lei como ativo financeiro ou instrumento cambial, observado o art. 9º da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    82,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410012',
    -- CLTR_DESCRICAO
    'Fornecimento de condomínio edilício não optante pelo regime regular, observado o art. 26 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    83,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410013',
    -- CLTR_DESCRICAO
    'Exportações de combustíveis, observado o art. 98 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    84,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410014',
    -- CLTR_DESCRICAO
    'Fornecimento de produtor rural não contribuinte, observado o art. 164 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    85,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410015',
    -- CLTR_DESCRICAO
    'Fornecimento por transportador autônomo não contribuinte, observado o art. 169 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    86,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410016',
    -- CLTR_DESCRICAO
    'Fornecimento ou aquisição de resíduos sólidos, observado o art. 170 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    87,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410017',
    -- CLTR_DESCRICAO
    'Aquisição de bem móvel com crédito presumido sob condição de revenda realizada, observado o art. 171 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    88,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410018',
    -- CLTR_DESCRICAO
    'Operações relacionadas aos fundos garantidores e executores de políticas públicas, inclusive de habitação, previstos em lei, assim entendidas os serviços prestados ao fundo pelo seu agente operador e por entidade encarregada da sua administração, observado o art. 213 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    89,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410019',
    -- CLTR_DESCRICAO
    'Exclusão da gorjeta na base de cálculo no fornecimento de alimentação, observado o art. 274 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    90,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '410020',
    -- CLTR_DESCRICAO
    'Exclusão do valor de intermediação na base de cálculo no fornecimento de alimentação, observado o art. 274 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    91,
    -- CLTR_SITR_ID
    20,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '510001',
    -- CLTR_DESCRICAO
    'Operações, sujeitas a diferimento, com energia elétrica ou com direitos a ela relacionados, relativas à geração, comercialização, distribuição e transmissão, observado o art. 28 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota. O grupo diferimento tem percentual de diferimento de [percentual_diferimento]% e valor de diferimento de R$ [valor_diferimento].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    92,
    -- CLTR_SITR_ID
    20,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '510002',
    -- CLTR_DESCRICAO
    'Operações, sujeitas a diferimento, com insumos agropecuários e aquícolas destinados a produtor rural contribuinte, observado o art. 138 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota. O grupo diferimento tem percentual de diferimento de [percentual_diferimento]% e valor de diferimento de R$ [valor_diferimento].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    93,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550001',
    -- CLTR_DESCRICAO
    'Exportações de bens materiais, observado o art. 82 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    94,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550002',
    -- CLTR_DESCRICAO
    'Regime de Trânsito, observado o art. 84 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    95,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550003',
    -- CLTR_DESCRICAO
    'Regimes de Depósito, observado o art. 85 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    96,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550004',
    -- CLTR_DESCRICAO
    'Regimes de Depósito, observado o art. 87 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    97,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550005',
    -- CLTR_DESCRICAO
    'Regimes de Depósito, observado o art. 87 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    98,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550006',
    -- CLTR_DESCRICAO
    'Regimes de Permanência Temporária, observado o art. 88 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    99,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550007',
    -- CLTR_DESCRICAO
    'Regimes de Aperfeiçoamento, observado o art. 90 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    100,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550008',
    -- CLTR_DESCRICAO
    'Importação de bens para o Regime de Repetro-Temporário, de que tratam o inciso I do art. 93 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    101,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550009',
    -- CLTR_DESCRICAO
    'GNL-Temporário, de que trata o inciso II do art. 93 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    102,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550010',
    -- CLTR_DESCRICAO
    'Repetro-Permanente, de que trata o inciso III do art. 93 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    103,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550011',
    -- CLTR_DESCRICAO
    'Repetro-Industrialização, de que trata o inciso IV do art. 93 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    104,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550012',
    -- CLTR_DESCRICAO
    'Repetro-Nacional, de que trata o inciso V do art. 93 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    105,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550013',
    -- CLTR_DESCRICAO
    'Repetro-Entreposto, de que trata o inciso VI do art. 93 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    106,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550014',
    -- CLTR_DESCRICAO
    'Zona de Processamento de Exportação, observado os arts. 99, 100 e 102 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    107,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550015',
    -- CLTR_DESCRICAO
    'Regime Tributário para Incentivo à Modernização e à Ampliação da Estrutura Portuária - Reporto, observado o art. 105 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    108,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550016',
    -- CLTR_DESCRICAO
    'Regime Especial de Incentivos para o Desenvolvimento da Infraestrutura - Reidi, observado o art. 106 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    109,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550017',
    -- CLTR_DESCRICAO
    'Regime Tributário para Incentivo à Atividade Econômica Naval - Renaval, observado o art. 107 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    110,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550018',
    -- CLTR_DESCRICAO
    'Desoneração da aquisição de bens de capital, , observado o art. 109 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    111,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550019',
    -- CLTR_DESCRICAO
    'Importação de bem material por indústria incentivada para utilização na Zona Franca de Manaus, observado o art. 443 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    112,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '550020',
    -- CLTR_DESCRICAO
    'Áreas de livre comércio, observado o art. 461 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo]. O grupo de desoneração tem base de cálculo de R$ [base_calculo], alíquota efetiva de [aliquota_desoneracao]% e montante desonerado de R$ [montante_desoneracao].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    113,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Ad Rem (por NCM)',
    -- CLTR_CD
    '620001',
    -- CLTR_DESCRICAO
    'Tributação monofásica sobre combustíveis, observados os art. 172 e art. 179, I, da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    114,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Ad Rem (por NCM)',
    -- CLTR_CD
    '620002',
    -- CLTR_DESCRICAO
    'Tributação monofásica com responsabilidade pela retenção sobre combustíveis, observado o art. 178 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    115,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Ad Rem (por NCM)',
    -- CLTR_CD
    '620003',
    -- CLTR_DESCRICAO
    'Tributação monofásica com tributos retidos por responsabilidade sobre combustíveis, observado o art. 178 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    116,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Ad Rem (por NCM)',
    -- CLTR_CD
    '620004',
    -- CLTR_DESCRICAO
    'Tributação monofásica sobre mistura de EAC com gasolina A em percentual superior ou inferior ao obrigatório, observado o art. 179 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    117,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Ad Rem (por NCM)',
    -- CLTR_CD
    '620005',
    -- CLTR_DESCRICAO
    'Tributação monofásica sobre combustíveis cobrada anteriormente, observador o art. 180 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    118,
    -- CLTR_SITR_ID
    23,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '800001',
    -- CLTR_DESCRICAO
    'Fusão, cisão ou incorporação, observado o art. 55 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    119,
    -- CLTR_SITR_ID
    23,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '800002',
    -- CLTR_DESCRICAO
    'Transferência de crédito do associado, inclusive as cooperativas singulares, para cooperativa de que participa das operações antecedentes às operações em que fornece bens e serviços e os créditos presumidos, observado o art. 272 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    120,
    -- CLTR_SITR_ID
    24,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '810001',
    -- CLTR_DESCRICAO
    'Crédito presumido sobre o valor apurado nos fornecimentos a partir da Zona Franca de Manaus, observado o art. 450 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    121,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '820001',
    -- CLTR_DESCRICAO
    'Documento com informações de fornecimento de serviços de planos de assinstência à saúde, mas com tributação realizada por outro meio, observado o art. 235 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    122,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '820002',
    -- CLTR_DESCRICAO
    'Documento com informações de fornecimento de serviços de planos de assinstência funerária, mas com tributação realizada por outro meio, observado o art. 236 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    123,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '820003',
    -- CLTR_DESCRICAO
    'Documento com informações de fornecimento de serviços de planos de assinstência à saúde de animais domésticos, mas com tributação realizada por outro meio, observado o art. 243 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    124,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '820004',
    -- CLTR_DESCRICAO
    'Documento com informações de prestação de serviços de consursos de prognósticos, mas com tributação realizada por outro meio, observado o art. 248 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    125,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquota Zero',
    -- CLTR_CD
    '820005',
    -- CLTR_DESCRICAO
    'Documento com informações de alienação de bens imóveis, mas com tributação realizada por outro meio,, observado o art. 254 da Lei Complementar nº 214, de 2025.',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    126,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000000',
    -- CLTR_DESCRICAO
    'Primeiro Fornecimento -  Art. 412, inciso I - do primeiro fornecimento',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    127,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000001',
    -- CLTR_DESCRICAO
    'Arrematação em hasta pública - art. 412, inciso II',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    128,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000002',
    -- CLTR_DESCRICAO
    'Transferência não onerosa - Art. 412, inciso III',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    129,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000003',
    -- CLTR_DESCRICAO
    'Da incorporação do bem ao ativo imobilizado pelo fabricante - Art. 412, inciso IV',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    130,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000004',
    -- CLTR_DESCRICAO
    'Consumo do bem/mercadoria pelo fabricante - Art. 412, inciso VI',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    131,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000005',
    -- CLTR_DESCRICAO
    'Fornecimento direto pelo fabricante ao consumidor final  - Art. 412, inciso I',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    132,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000006',
    -- CLTR_DESCRICAO
    'Venda de produto enviado para terceiro para processamento/industrialização, art. 412, inciso I',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    133,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000007',
    -- CLTR_DESCRICAO
    'Venda efetiva de mercadoria remetida para venda fora do estabelecimento, art. 412, inciso I',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    134,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000008',
    -- CLTR_DESCRICAO
    'Importação - Art. 412, inciso VIII',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    135,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000009',
    -- CLTR_DESCRICAO
    'Extração de bem mineral - Art. 412,  inciso V - primeiro fornecimento Mercado Interno ou Exportação',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    136,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000010',
    -- CLTR_DESCRICAO
    'Prognósticos, Apostas e Fantasy Game, art. 409, inciso VII e Art. 412, inciso VII',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    137,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000011',
    -- CLTR_DESCRICAO
    'Fornecimento para fins de exportação - Art. 426, ressalvado o 412, inciso V,',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    138,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000012',
    -- CLTR_DESCRICAO
    'Remessa para filial/depósito fechado, Art. 409, § 2º',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    139,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000013',
    -- CLTR_DESCRICAO
    'Remessa para terceiro para processamento e industrialização, Art. 409, § 2º',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    140,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000014',
    -- CLTR_DESCRICAO
    'Remessa para adquirente por conta e ordem do fabricante, Art. 409, § 2º',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    141,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000015',
    -- CLTR_DESCRICAO
    'Remessa para venda fora do estabelecimento, Art. 409, § 2º',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    142,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000016',
    -- CLTR_DESCRICAO
    'Retorno de mercadoria enviada para processamento/industrialização, Art. 409, § 2º',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    143,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000017',
    -- CLTR_DESCRICAO
    'Retorno de remessa enviada para venda fora do estabelecimento, Art. 409, § 2º',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    144,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000018',
    -- CLTR_DESCRICAO
    'Venda de produto tributado em operação anterior',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    145,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000019',
    -- CLTR_DESCRICAO
    'Venda - embalagem não primária (embalagem não pronta para consumo) - Bebida e Fumo - Art. 409, § 2º',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    146,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000020',
    -- CLTR_DESCRICAO
    'Devolução - documento fiscal emitido pelo comprador/adquirente. Art. 418',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    147,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000021',
    -- CLTR_DESCRICAO
    'Devolução - documento fiscal emitido pelo fornecedor. Art. 418',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    148,
    -- CLTR_SITR_ID
    30,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000022',
    -- CLTR_DESCRICAO
    'Remessa para Comercial Exportadora. Art. 426',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    149,
    -- CLTR_SITR_ID
    31,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000023',
    -- CLTR_DESCRICAO
    'Art. 418 - gás natural destinado à utilização como insumo em processo
industrial - Venda pelo produtor extrativista',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    150,
    -- CLTR_SITR_ID
    31,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000024',
    -- CLTR_DESCRICAO
    'Art. 418 - gás natural destinado à utilização como insumo em processo
industrial - Importação para indústria - Importador',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    151,
    -- CLTR_SITR_ID
    31,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000025',
    -- CLTR_DESCRICAO
    'Art. 418 - gás natural destinado à utilização como insumo em processo
industrial - Importação para indústria  - Importador Distribuidor/Varejista',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    152,
    -- CLTR_SITR_ID
    31,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000026',
    -- CLTR_DESCRICAO
    'Art. 144, inciso I',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    153,
    -- CLTR_SITR_ID
    31,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000027',
    -- CLTR_DESCRICAO
    'Art. 144, inciso II',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    154,
    -- CLTR_SITR_ID
    32,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000028',
    -- CLTR_DESCRICAO
    'Art. 435, inciso I',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    155,
    -- CLTR_SITR_ID
    32,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000029',
    -- CLTR_DESCRICAO
    'Art. 435, inciso II',
    -- CLTR_NOMENCLATURA
    NULL,
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_CREDITO_PARA_FRENTE
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_CREDITO_PARA_FRENTE_FDLG_ID
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_TIPO_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
);
