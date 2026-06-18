package Controller;

import Model.Cliente;
import Repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @GetMapping("/todos")
    public List<Cliente> getAllClientes(){
        return repository.findAll();
    }

    @RequestMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalhes){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(clienteDetalhes.getNome());
        cliente.setEmail(clienteDetalhes.getEmail());
        cliente.setEndereco(clienteDetalhes.getEndereco());
        cliente.setTelefone(clienteDetalhes.getTelefone());

        return repository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id){
        repository.deleteById(id);
    }

}
