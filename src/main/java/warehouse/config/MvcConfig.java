package warehouse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path_user}")
    private String load_user;
    @Value("${upload.path_product}")
    private String load_product;
    @Value("${upload.path_supplier}")
    private  String load_supplier;

    @Value("${upload.path_reception}")
    private  String load_reception;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img_user/**")
                .addResourceLocations("file://"+load_user+"/");
        registry.addResourceHandler("/img_product/**")
                .addResourceLocations("file://"+load_product+"/");
        registry.addResourceHandler("/img_supplier/**")
                .addResourceLocations("file://"+load_supplier+"/");
        registry.addResourceHandler("/img_reception/**")
                .addResourceLocations("file://"+load_reception+"/");
    }
}
