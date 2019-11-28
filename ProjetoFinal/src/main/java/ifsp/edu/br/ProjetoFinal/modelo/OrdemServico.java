package ifsp.edu.br.ProjetoFinal.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Endereco endereco;

    @NotNull
    private Usuario cliente;

    private Usuario instalador;

    @Enumerated(EnumType.ORDINAL)
    private Plano plano;

    private Date dataContratacao;

    private Date dataInicioIntalacao;

    private Date dataFinalInstalacao;

    @PrePersist
    private void dataContratacao(){
        dataContratacao = new Date();
    }

    @PrePersist
    private void DataInicioIntalacao(){
        dataInicioIntalacao = new Date();
    }

    @PrePersist
    private void DataFinalInstalacao(){
        dataFinalInstalacao = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getInstalador() {
        return instalador;
    }

    public void setInstalador(Usuario instalador) {
        this.instalador = instalador;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Date getDataInicioIntalacao() {
        return dataInicioIntalacao;
    }

    public void setDataInicioIntalacao(Date dataInicioIntalacao) {
        dataInicioIntalacao = dataInicioIntalacao;
    }

    public Date getDataFinalInstalacao() {
        return dataFinalInstalacao;
    }

    public void setDataFinalInstalacao(Date dataFinalInstalacao) {
        dataFinalInstalacao = dataFinalInstalacao;
    }
}
