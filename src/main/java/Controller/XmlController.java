package Controller;

import Model.Cliente;
import Model.DadosXML;
import Repository.ClienteRepository;
import Repository.ProdutoRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/xml/importar")
public class XmlController {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public XmlController(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/tudo")
    public ResponseEntity<String> importarTudo(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum arquivo enviado");
        }

        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".xml")){
            return ResponseEntity.badRequest().body("Erro: o arquivo deve conter a extensão .xml");
        }

        try (InputStream inputStream = file.getInputStream()) {

            JAXBContext jaxbContext = JAXBContext.newInstance(DadosXML.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            DadosXML dadosXML = (DadosXML) unmarshaller.unmarshal(inputStream);

            if (dadosXML.getClientes() != null && !dadosXML.getClientes().isEmpty()){
                // Zera o ID forçando null (como mudamos para Long) para que o banco crie como novo
                dadosXML.getClientes().forEach(cliente -> cliente.setId(null));
                clienteRepository.saveAll(dadosXML.getClientes());
            }

            if (dadosXML.getProdutos() != null && !dadosXML.getProdutos().isEmpty()){
                // Zera o ID forçando null (como mudamos para Long) para que o banco crie como novo
                dadosXML.getProdutos().forEach(produto -> produto.setId(null));
                produtoRepository.saveAll(dadosXML.getProdutos());
            }
            return ResponseEntity.ok("Arquivo XML processado com sucesso para Clientes e Produtos");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao processar o arquivo XML: " + e.getMessage());
        }
    }
}