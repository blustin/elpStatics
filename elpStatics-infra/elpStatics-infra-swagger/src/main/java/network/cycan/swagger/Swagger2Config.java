package network.cycan.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import network.cycan.core.util.GeneralProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * Swagger2Config 类型:
 * </p>
 *
 * @author linjd
 * @since 2020/6/28 10:19
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        String basePackage=GeneralProperties.getProperty("hyt.api.doc.base-package","com.hyt.it.ogt");
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        String title=GeneralProperties.getProperty("hyt.api.doc.title","海云天云考试");
        String description=GeneralProperties.getProperty("hyt.api.doc.description","海云天云考试系统对外接口");
        String termsOfServiceUrl=GeneralProperties.getProperty("hyt.api.doc.termsOfServiceUrl","http://www.seaskylight.com");
        String contactName=GeneralProperties.getProperty("hyt.api.doc.contact.name","云考试-考试项目组");
        String contactUrl=GeneralProperties.getProperty("hyt.api.doc.contact.url","http://www.seaskylight.com");
        String contactEamil=GeneralProperties.getProperty("hyt.api.doc.contact.email","linjd@seaskylight.com");
        String version=GeneralProperties.getProperty("hyt.api.doc.version","1.0");
        return new ApiInfoBuilder().title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEamil))
                .version(version)
                .build();
    }


    public static void main(String[] args){
        System.out.println("Test==== ");
    }
}
