package br.com.service;

import br.com.model.dto.LivroDto;
import br.com.model.entity.LivroEntity;
import br.com.model.mapper.LivroMapper;
import br.com.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private LivroMapper mapper;

    public LivroDto cadastrar(LivroDto livroDto){
        LivroEntity livro = mapper.update(livroDto);

        livro = repository.save(livro);

        return mapper.update(livro);
    }

    public List<LivroDto> listar(){
        List<LivroEntity> listasEntities = repository.findAll();
        return mapper.updateListDto(listasEntities);
    }


}
