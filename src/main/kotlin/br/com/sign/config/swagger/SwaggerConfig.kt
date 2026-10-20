package br.com.sign.config.swagger

import br.com.sign.model.Client
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig {

    @Bean
    fun apiDocumentation() = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.sign"))
            .paths(PathSelectors.ant("/**"))
            .build()
            .ignoredParameterTypes(Client::class.java)
            .globalOperationParameters(
                    listOf(
                            ParameterBuilder()
                                    .name("Authorization")
                                    .description("Header para Bearer token JWT")
                                    .modelRef(ModelRef("string"))
                                    .parameterType("header")
                                    .required(false)
                                    .build()
                    )
            )
}