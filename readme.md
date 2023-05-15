# Projeto de cadastro de Livro com cobertura de testes

1. [Enunciado](#enunciado)
2. [Requisitos](#requisitos)
3. [Restrições](#restricoes)
4. [Resultado Esperado](#resultadoEsperado)
5. [Requisitos Obrigatórios](#requisitosObrigatorios)

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
* Requisitos opcionais.
