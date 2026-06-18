package Model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "DadosEmpresa")
@XmlAccessorType(XmlAccessType.FIELD)
public class DadosXML {

    @XmlElementWrapper(name = "Produtos")
    @XmlElement(name = "Produto")
    private List<Produto> produtos;

    @XmlElementWrapper(name = "Clientes")
    @XmlElement(name = "Cliente")
    private List<Cliente> clientes;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}