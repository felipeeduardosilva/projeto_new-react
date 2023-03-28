package br.com.api.produtos.Servico;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    //Método para listar todos os produtos
    public Iterable<ProdutoModelo>listar(){
        return pr.findAll();
    }
    //Metodo para cadastrar ou alterar produtos 
    public ResponseEntity<?> cadastrarAlterar (ProdutoModelo pm, String acao){

        if(pm.getNome().equals("")){
            rm.setMensagem("nome do produto é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if(pm.getMarca().equals("")){
            rm.setMensagem("o nome da marca e obrigatorio");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else{
            if (acao.equals("cadastrar ")){
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
            }else {
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);
            }

        }

    }
    ///Metodo para remover produtos
    public ResponseEntity<RespostaModelo> remover (long codigo){
        
        pr.deleteById(codigo);

        rm.setMensagem("o produto foi removido com sucesso");
        return new ResponseEntity<>(rm, HttpStatus.OK);


    }
}
