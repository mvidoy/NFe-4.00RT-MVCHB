package br.gov.serpro.rtc.api.model.roc.imposto;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class CompraGovernamental {
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota do IBS de competência do Estado")
    @JsonProperty(value = "pIBSUF", index = 0)
    @Builder.Default
    private BigDecimal pIBSUF = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo do IBS da UF calculado")
    @JsonProperty(value = "vIBSUF", index = 1)
    @Builder.Default       
    private BigDecimal vIBSUF = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota do IBS de competência do Município")
    @JsonProperty(value = "pIBSMun", index = 2)
    @Builder.Default
    private BigDecimal pIBSMun = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo do IBS do Município calculado")
    @JsonProperty(value = "vIBSMun", index = 3)
    @Builder.Default
    private BigDecimal vIBSMun = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota da CBS")
    @JsonProperty(value = "pCBS", index = 4)
    @Builder.Default
    private BigDecimal pCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo da CBS calculado")
    @JsonProperty(value = "vCBS", index = 5)
    @Builder.Default
    private BigDecimal vCBS = ZERO;

}
