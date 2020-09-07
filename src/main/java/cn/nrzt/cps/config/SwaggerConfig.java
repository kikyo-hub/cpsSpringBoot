package cn.nrzt.cps.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private static final String VERSION = "1.0.0";
	 @Value("${swagger.enable}")
	 private boolean enableSwagger;
	/**
	 * swagger接口文档配置
	 * **/
 	@Bean
    public Docket swaggerSpringMvcPlugin() { 		
// 		System.out.println("enableSwagger ="+ enableSwagger);
        return new Docket(DocumentationType.SWAGGER_2)
        		.enable(enableSwagger)
        		.apiInfo(apiInfo())
        		.select()        		
        		.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        		.build();
    }
 	
 	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CPS诊断后台服务接口")
                .description("Restful 风格接口")
                //服务条款网址
                //.termsOfServiceUrl("http://xxxx")
                .version(VERSION)
                //.contact(new Contact("wesker", "url", "email"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}
