package br.com.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Date;

@Data
public class LivroDto {
    private Long id;
    @NotBlank(message = "'titulo' do livro é obrigatório")
    private String titulo;
    @NotBlank(message = "'Resumo' do livro é obrigatório")
    @Size(max = 500, message = "Tamanho máximo do 'resumo' é de 500 caracteres")
    private String resumo;
    private String sumario;
    @NotNull(message = "'preço' do livro é obrigatório")
    @DecimalMin(value = "20.0", message = "'preço' mínimo do livro é de 20 reais")
    private Double preco;
    @NotNull(message = "número de páginas do livro é obrigatório")
    @Min(value = 100, message = "número mínimo de páginas é de 100")
    private Integer paginas;
    @NotBlank(message = "'isbn' do livro é obrigatório")
    private String isbn;
    @Future(message = "Data de publicação deve ser no futuro")
    private Date dataPublicacao;

}

