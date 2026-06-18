package Controller;

import Model.Produto;
import Repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @GetMapping("/todos")
    public List<Produto> getAllProdutos(){
        return repository.findAll();
    }

    @RequestMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetalhes){
        Produto produto = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(produtoDetalhes.getNome());
        produto.setCategoria(produtoDetalhes.getCategoria());
        produto.setDescricao(produtoDetalhes.getDescricao());
        produto.setEstoque(produtoDetalhes.getEstoque());
        produto.setPreco(produtoDetalhes.getPreco());

        return repository.save(produto);

    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable long id){
        repository.deleteById(id);
    }

}
