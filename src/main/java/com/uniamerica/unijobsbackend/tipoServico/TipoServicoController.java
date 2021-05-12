package com.uniamerica.unijobsbackend.tipoServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tiposServicos")
public class TipoServicoController {

    @Autowired
    private TipoServicoService tipoServicoService;

    @GetMapping
    public List<TipoServico> listar(){
        return tipoServicoService.listarTiposServicos() ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoServico cadastrar(@Valid @RequestBody TipoServico tipoServico){
        return tipoServicoService.novoTipoServico(tipoServico);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoServico atualizar(@Valid @RequestBody TipoServico novoTipoServico, @PathVariable("id") Integer id){
        return tipoServicoService.atualizarTipoServico( id, novoTipoServico);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable("id") Integer id){
        return tipoServicoService.deletarTipoServico(id);
    }
}
