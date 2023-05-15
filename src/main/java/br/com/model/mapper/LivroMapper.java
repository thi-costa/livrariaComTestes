package br.com.model.mapper;

import br.com.model.dto.LivroDto;
import br.com.model.entity.LivroEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {
    public LivroDto update(LivroEntity livro){
        LivroDto livroDto = new LivroDto();

        livroDto.setId(livro.getId());
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setResumo(livro.getResumo());
        livroDto.setSumario(livro.getSumario());
        livroDto.setPreco(livro.getPreco());
        livroDto.setPaginas(livro.getPaginas());
        livroDto.setIsbn(livro.getIsbn());
        livroDto.setDataPublicacao(livro.getDataPublicacao());

        return livroDto;
    }
    public LivroEntity update(LivroDto livro){
        LivroEntity livroEntity = new LivroEntity();

        livroEntity.setId(livro.getId());
        livroEntity.setTitulo(livro.getTitulo());
        livroEntity.setResumo(livro.getResumo());
        livroEntity.setSumario(livro.getSumario());
        livroEntity.setPreco(livro.getPreco());
        livroEntity.setPaginas(livro.getPaginas());
        livroEntity.setIsbn(livro.getIsbn());
        livroEntity.setDataPublicacao(livro.getDataPublicacao());

        return livroEntity;
    }

    public List<LivroEntity> updateListEntity(List<LivroDto> listaDto){
        return listaDto.stream()
                .map(this::update)
                .toList();
    }

    public List<LivroDto> updateListDto(List<LivroEntity> listaEntity){
        return listaEntity.stream()
                .map(this::update)
                .toList();
    }
}
