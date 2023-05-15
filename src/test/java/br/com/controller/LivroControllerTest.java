package br.com.controller;

import br.com.model.dto.LivroDto;
import br.com.model.entity.LivroEntity;
import br.com.model.mapper.LivroMapper;
import br.com.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LivroControllerTest {
    @BeforeAll
    public void init(){

        LivroDto livro1 = new LivroDto();

        livro1.setTitulo("Rita Lee: Outra autobiografia");
        livro1.setResumo("Achei que nada mais tão digno de nota pudesse acontecer em minha vidinha besta. Mas é" +
                "aquela velha história: enquanto a gente faz planos e acha que sabe de alguma coisa, Deus dá uma" +
                "risadinha sarcástica.");
        livro1.setSumario("1. Introdução\n2.Desenvolvimento\n3. Conclusão");
        livro1.setPreco(64.9);
        livro1.setPaginas(192);
        livro1.setIsbn("978-6559871124");
        livro1.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        repository.save(livroMapper.update(livro1));

    }

    @Autowired
    private LivroController controller;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LivroRepository repository;
    @Autowired
    private LivroMapper livroMapper;

    @Test
    public void carregouContexto(){
        Assertions.assertTrue(controller != null);
    }
    @Test
    public void testeOk() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/livro"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}