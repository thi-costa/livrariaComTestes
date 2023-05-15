# Projeto de cadastro de Livro com cobertura de testes

1. [Enunciado](#enunciado)
2. [Requisitos](#requisitos)
3. [Restrições](#restricoes)
4. [Resultado Esperado](#resultadoEsperado)
5. [Requisitos Obrigatórios](#requisitosObrigatorios)
6. [Testes integrados implementados](#testesIntegradosImplementados)

# Enunciado <a id="enunciado"></a>

Implementar uma API básica com as camadas de Repository, Service e Controller de um cadastro livros.

# Requisitos <a id="requisitos"></a>

É necessário cadastrar um novo livro no sistema.
Todo livro deve ter os seguintes atributos abaixo:

* Um título
* Um resumo do que vai ser encontrado no livro
* Um sumário de tamanho livre.
* Preço do livro
* Número de páginas
* Isbn(identificador do livro)
* Data que ele deve entrar no ar(de publicação)

# Restrições <a id="restricoes"></a>


* Título é obrigatório
* Resumo é obrigatório e tem no máximo 500 caracteres
* Sumário é de tamanho livre.
* Preço é obrigatório e o mínimo é de 20
* Número de páginas é obrigatória e o mínimo é de 100
* Isbn é obrigatório, formato livre
* Data que vai entrar no ar precisa ser no futu


# Resultado esperado <a id="resultadoEsperado"></a>


* Um novo livro precisa ser criado e status 200 retornado

# Requisitos obrigatórios <a id="requisitosObrigatorios"></a>

* A aplicação precisa ter cobertura mínima de 80% do código da API.
* Realizar pelo menos 2 testes de integração.

# Testes integrados implementados <a id="testesIntegradosImplementados"></a>
<details>
  <summary>1. Carregamento do contexto</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se contexto da aplicação foi carregado</li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void carregouContexto(){
        Assertions.assertTrue(controller != null);
    }
  ```
</details>

<details>
  <summary>2. Teste se LivroController está ok</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se o LivroController está ativo</li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void testeOk() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/livro"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
  ```
</details>

<details>
  <summary>3. Criação bem-sucedida do livro</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se é possível a criação bem-sucedida de 1 livro</li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void criarLivroComSucesso() throws Exception{
        LivroDto livroDto = new LivroDto();

        livroDto.setTitulo("Teste");
        livroDto.setResumo("Resumo do livro");
        livroDto.setSumario("Sumario");
        livroDto.setPreco(20.00);
        livroDto.setPaginas(100);
        livroDto.setIsbn("978-6559871121");
        livroDto.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        LivroEntity livro = repository.findByIsbn("978-6559871121");

        Assertions.assertEquals("Teste", livro.getTitulo());
        Assertions.assertEquals("Resumo do livro", livro.getResumo());
        Assertions.assertEquals("Sumario", livro.getSumario());
        Assertions.assertEquals(20.00, livro.getPreco());
        Assertions.assertEquals(100, livro.getPaginas());
        Assertions.assertEquals("978-6559871121", livro.getIsbn());
        Assertions.assertEquals(Date.valueOf(LocalDate.now().plusDays(1)), livro.getDataPublicacao());

    }
  ```
</details>

<details>
  <summary>4. Checar falha de criação com título inválido</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se a aplicação rejeita a criação de um livro com 'título' inválido</li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void criarLivroTituloInvalidoComFalha() throws Exception{
      LivroDto livro1 = new LivroDto();

      livro1.setTitulo("");
      livro1.setResumo("Resumo do livro");
      livro1.setSumario("Sumario");
      livro1.setPreco(20.00);
      livro1.setPaginas(100);
      livro1.setIsbn("978-6559871122");
      livro1.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(livro1);

      this.mockMvc
            .perform(MockMvcRequestBuilders.post("/livro")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
  ```
</details>

<details>
  <summary>5. Checar falha de criação com resumo inválido</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se aplicação rejeita criação de livro com resumo inválido</li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void criarLivroResumoInvalidoComFalha() throws Exception{
        LivroDto livro1 = new LivroDto();

        livro1.setTitulo("Titulo");
        livro1.setResumo("testetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetes" +
                "tetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetesteteste" +
                "testetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestete" +
                "stetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetes" +
                "tetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetesteteste" +
                "teste1");
        livro1.setSumario("Sumario");
        livro1.setPreco(20.00);
        livro1.setPaginas(100);
        livro1.setIsbn("978-6559871123");
        livro1.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livro1);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
  ```
</details>

<details>
  <summary>6. Checar falha de criação com preço inválido</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se aplicação rejeita livro com preço menor que o mínimo (20,00 reais) </li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void criarLivroPrecoInvalidoComFalha() throws Exception{
        LivroDto livro1 = new LivroDto();

        livro1.setTitulo("Titulo");
        livro1.setResumo("teste");
        livro1.setSumario("Sumario");
        livro1.setPreco(19.99);
        livro1.setPaginas(100);
        livro1.setIsbn("978-6559871123");
        livro1.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livro1);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
  ```
</details>

<details>
  <summary>7. Checar falha de criação com número de páginas inválido</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se aplicação rejeita livro com menos páginas que o mínimo (100 páginas) </li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void criarLivroPaginasInvalidoComFalha() throws Exception{
        LivroDto livro1 = new LivroDto();

        livro1.setTitulo("Titulo");
        livro1.setResumo("teste");
        livro1.setSumario("Sumario");
        livro1.setPreco(20.00);
        livro1.setPaginas(99);
        livro1.setIsbn("978-6559871123");
        livro1.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livro1);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
  ```
</details>

<details>
  <summary>8. Checar falha de criação com data de publicação inválida</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se aplicação rejeita criação de livro com data inválida (anterior ou igual à data de hoje)</li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void criarLivroDataInvalidaComFalha() throws Exception{
        LivroDto livro1 = new LivroDto();

        livro1.setTitulo("Titulo");
        livro1.setResumo("teste");
        livro1.setSumario("Sumario");
        livro1.setPreco(20.00);
        livro1.setPaginas(100);
        livro1.setIsbn("978-6559871123");
        livro1.setDataPublicacao(Date.valueOf(LocalDate.now()));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livro1);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
  ```
</details>

<details>
  <summary>9. Checar falha de criação com isbn inválido</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se aplicação rejeita criação de livro com ibsn inválido (nulo ou em branco)</li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void criarLivroIsbnInvalidaComFalha() throws Exception{
      LivroDto livro1 = new LivroDto();

      livro1.setTitulo("Titulo");
      livro1.setResumo("teste");
      livro1.setSumario("Sumario");
      livro1.setPreco(20.00);
      livro1.setPaginas(100);
      livro1.setIsbn("");
      livro1.setDataPublicacao(Date.valueOf(LocalDate.now()));

      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(livro1);

      this.mockMvc
      .perform(MockMvcRequestBuilders.post("/livro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
      )
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
  ```
</details>

<details>
  <summary>10. Checar falha de criação com isbn duplicado</summary>
  <h5>Objetivo</h5>
  <ul>
    <li>Checar se aplicação rejeita criação de livro com isbn já existente na base (isbn é um identificado único)</li>
  </ul>
  <h5>Código</h5>

  ```java
    @Test
    public void criarLivroIsbnRepetidoComFalha() throws Exception{
      LivroDto livro1 = new LivroDto();

      livro1.setTitulo("Titulo");
      livro1.setResumo("teste");
      livro1.setSumario("Sumario");
      livro1.setPreco(20.00);
      livro1.setPaginas(100);
      livro1.setIsbn("978-6559871125");
      livro1.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(livro1);

      repository.save(livroMapper.update(livro1));

      this.mockMvc
              .perform(MockMvcRequestBuilders.post("/livro")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json)
              )
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
  ```
</details>