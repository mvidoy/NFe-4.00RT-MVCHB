/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.TratamentoClassificacao;

@Repository
public interface TratamentoClassificacaoRepository extends JpaRepository<TratamentoClassificacao, Long> {

    @Query("""
            FROM TratamentoClassificacao
            WHERE classificacaoTributaria.id = :idClassificacaoTributaria
                AND (inicioVigencia <= :data AND (fimVigencia IS NULL OR fimVigencia >= :data))
            """)
    @Cacheable(cacheNames = "TratamentoClassificacaoRepository.buscarTratamentoClassificacao")
    TratamentoClassificacao buscarTratamentoClassificacao(Long idClassificacaoTributaria, LocalDate data);

    @Query(value = """
            SELECT
                ct.CLTR_CD,
                ct.CLTR_DESCRICAO,
                ct.CLTR_TIPO_ALIQUOTA,
                ct.CLTR_NOMENCLATURA,
                tt.TRTR_DESCRICAO,
                tt.TRTR_IN_INCOMPATIVEL_COM_SUSPENSAO,
                tt.TRTR_IN_EXIGE_GRUPO_DESONERACAO,
                tt.TRTR_IN_POSSUI_PERCENTUAL_REDUCAO
            FROM TRATAMENTO_CLASSIFICACAO tc
            JOIN CLASSIFICACAO_TRIBUTARIA ct ON tc.TRCL_CLTR_ID = ct.CLTR_ID
            JOIN TRATAMENTO_TRIBUTARIO tt ON tt.TRTR_ID = tc.TRCL_TRTR_ID
            WHERE ct.CLTR_SITR_ID = :idSituacaoTributaria
                AND :data BETWEEN tc.TRCL_INICIO_VIGENCIA AND COALESCE(tc.TRCL_FIM_VIGENCIA, :data)
                AND :data BETWEEN ct.CLTR_INICIO_VIGENCIA AND COALESCE(ct.CLTR_FIM_VIGENCIA, :data)
                AND :data BETWEEN tt.TRTR_INICIO_VIGENCIA AND COALESCE(tt.TRTR_FIM_VIGENCIA, :data)
            """, nativeQuery = true)
    List<Object[]> consultarTratamentoClassificacaoPorIdSituacaoTributaria(Long idSituacaoTributaria, LocalDate data);

    @Query(value = """
            SELECT
                ct.CLTR_CD,
                ct.CLTR_DESCRICAO,
                ct.CLTR_TIPO_ALIQUOTA,
                ct.CLTR_NOMENCLATURA,
                tt.TRTR_DESCRICAO,
                tt.TRTR_IN_INCOMPATIVEL_COM_SUSPENSAO,
                tt.TRTR_IN_EXIGE_GRUPO_DESONERACAO,
                tt.TRTR_IN_POSSUI_PERCENTUAL_REDUCAO
            FROM TRATAMENTO_CLASSIFICACAO tc
            JOIN CLASSIFICACAO_TRIBUTARIA ct ON tc.TRCL_CLTR_ID = ct.CLTR_ID
            JOIN TRATAMENTO_TRIBUTARIO tt ON tt.TRTR_ID = tc.TRCL_TRTR_ID
            JOIN TRIBUTO_SITUACAO_TRIBUTARIA tst ON tst.TRST_SITR_ID = ct.CLTR_SITR_ID
            WHERE :data BETWEEN tc.TRCL_INICIO_VIGENCIA AND COALESCE(tc.TRCL_FIM_VIGENCIA, :data)
              AND :data BETWEEN ct.CLTR_INICIO_VIGENCIA AND COALESCE(ct.CLTR_FIM_VIGENCIA, :data)
              AND :data BETWEEN tt.TRTR_INICIO_VIGENCIA AND COALESCE(tt.TRTR_FIM_VIGENCIA, :data)
              AND :data BETWEEN tst.TRST_INICIO_VIGENCIA AND COALESCE(tst.TRST_FIM_VIGENCIA, :data)
              AND tst.TRST_TBTO_ID == 2
            """, nativeQuery = true)
    List<Object[]> consultarTratamentoClassificacaoCbsIbs(LocalDate data);

    @Query(value = """
            SELECT
                ct.CLTR_CD,
                ct.CLTR_DESCRICAO,
                ct.CLTR_TIPO_ALIQUOTA,
                ct.CLTR_NOMENCLATURA,
                tt.TRTR_DESCRICAO,
                tt.TRTR_IN_INCOMPATIVEL_COM_SUSPENSAO,
                tt.TRTR_IN_EXIGE_GRUPO_DESONERACAO,
                tt.TRTR_IN_POSSUI_PERCENTUAL_REDUCAO
            FROM TRATAMENTO_CLASSIFICACAO tc
            JOIN CLASSIFICACAO_TRIBUTARIA ct ON tc.TRCL_CLTR_ID = ct.CLTR_ID
            JOIN TRATAMENTO_TRIBUTARIO tt ON tt.TRTR_ID = tc.TRCL_TRTR_ID
            JOIN TRIBUTO_SITUACAO_TRIBUTARIA tst ON tst.TRST_SITR_ID = ct.CLTR_SITR_ID
            WHERE :data BETWEEN tc.TRCL_INICIO_VIGENCIA AND COALESCE(tc.TRCL_FIM_VIGENCIA, :data)
              AND :data BETWEEN ct.CLTR_INICIO_VIGENCIA AND COALESCE(ct.CLTR_FIM_VIGENCIA, :data)
              AND :data BETWEEN tt.TRTR_INICIO_VIGENCIA AND COALESCE(tt.TRTR_FIM_VIGENCIA, :data)
              AND :data BETWEEN tst.TRST_INICIO_VIGENCIA AND COALESCE(tst.TRST_FIM_VIGENCIA, :data)
              AND tst.TRST_TBTO_ID == 1
            """, nativeQuery = true)
    List<Object[]> consultarTratamentoClassificacaoImpostoSeletivo(LocalDate data);

}
