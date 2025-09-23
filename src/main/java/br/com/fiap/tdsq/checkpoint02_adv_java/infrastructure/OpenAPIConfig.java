package br.com.fiap.tdsq.checkpoint02_adv_java.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title("API de Gestão de Drones e Missões")
                        .version("1.0.0")
                        .description("""
                                API desenvolvida para o Checkpoint 2 da disciplina de Java Advanced.
                                Permite o cadastro e gerenciamento de drones e suas missões em uma relação 1:N,
                                incluindo operações CRUD e endpoints adicionais, como listagem de missões futuras
                                e relatórios de uso de bateria.
                                """));
    }

}