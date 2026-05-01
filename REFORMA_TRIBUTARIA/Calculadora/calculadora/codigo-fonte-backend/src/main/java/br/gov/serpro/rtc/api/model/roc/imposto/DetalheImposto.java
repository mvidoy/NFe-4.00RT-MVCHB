package br.gov.serpro.rtc.api.model.roc.imposto;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public final class DetalheImposto implements Comparable<DetalheImposto> {
    
    @EqualsAndHashCode.Include
    @JsonProperty(value = "nObj", index = 0)
    @JacksonXmlProperty(localName = "nItem", isAttribute = true)
    private Integer nItem;
    
    @JsonProperty(value = "tribCalc", index = 1)
    @JacksonXmlProperty(localName = "imposto")
    private Tributos imposto;
    
    @JsonIgnore
    public boolean possuiImpostoSeletivo() {
        return imposto != null && imposto.possuiImpostoSeletivo();
    }
    
    @JsonIgnore
    public ImpostoSeletivo getImpostoSeletivo() {
        return imposto != null ? imposto.getIS() : null;
    }
    
    @JsonIgnore
    public BigDecimal getValorImpostoSeletivo() {
        return possuiImpostoSeletivo() ? imposto.getValorImpostoSeletivo() : ZERO;
    }
    
    @JsonIgnore
    public boolean possuiBaseCalculoIBSCBS() {
        return imposto != null && imposto.possuiBaseCalculoIBSCBS();
    }
    
    @JsonIgnore
    public BigDecimal getValorBaseCalculoIBSCBS() {
        return possuiBaseCalculoIBSCBS() ? imposto.getValorBaseCalculoIBSCBS() : ZERO;
    }
    
    @JsonIgnore
    public boolean possuiCbs() {
        return imposto != null && imposto.possuiCbs();
    }
    
    @JsonIgnore
    public CBS getGCBS() {
        return imposto != null ? imposto.getGCBS() : null;
    }    
    
    @JsonIgnore
    public boolean possuiCreditoPresumidoCbs() {
        return imposto != null && imposto.possuiCreditoPresumidoCbs();
    }
    
    @JsonIgnore
    public boolean possuiCredPresIbs() {
        return imposto != null && imposto.possuiCredPresIbs();
    }
    
    @JsonIgnore
    public boolean possuiCredPresCondSusIbs() {
        return imposto != null && imposto.possuiCredPresCondSusIbs();
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresIbs() {
        return possuiCredPresIbs() ? imposto.getVCredPresIbs() : ZERO;
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresCondSusIbs() {
        return possuiCredPresCondSusIbs() ? imposto.getVCredPresCondSusIbs() : ZERO;
    }
    
    @JsonIgnore
    public GrupoIBSCBS getGrupoIBSCBS() {
        return imposto != null ? imposto.getGrupoIBSCBS() : null;
    }
    
    @JsonIgnore
    public boolean possuiIBSUF() {
        return imposto != null && imposto.possuiIBSUF();
    }
    
    @JsonIgnore
    public IBSUF getGIBSUF() {
        return imposto != null ? imposto.getGIBSUF() : null;
    }
    
    @JsonIgnore
    public boolean possuiIBSMun() {
        return imposto != null && imposto.possuiIBSMun();
    }
    
    @JsonIgnore
    public IBSMun getGIBSMun() {
        return imposto != null ? imposto.getGIBSMun() : null;
    }
    
    @JsonIgnore
    public boolean possuiMonofasia() {
        return imposto != null && imposto.possuiMonofasia();
    }
    
    @JsonIgnore
    public Monofasia getGIBSCBSMono() {
        return possuiMonofasia() ? imposto.getGIBSCBSMono() : null;
    }
    
    @JsonIgnore
    public boolean possuiTributacaoRegular() {
        return imposto != null && imposto.possuiTributacaoRegular();
    }
    
    @JsonIgnore
    public TributacaoRegular getTributacaoRegular() {
        return imposto != null ? imposto.getTributacaoRegular() : null;
    }

    @Override
    public int compareTo(DetalheImposto o) {
        return Comparator.nullsFirst(Integer::compareTo).compare(this.nItem, o.nItem);
    }
}
