package com.joo.abysshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String resourcePath = "/static/**";
    private final String classpath = "classpath:/static/";

    @Value("${IMAGE_DIR}")
    private String imageDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
            .addResourceLocations(classpath);

        String location = "file:" + (imageDir.endsWith("/") ? imageDir : imageDir + "/");
        registry.addResourceHandler("/upload/**")
            .addResourceLocations(location);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        registry.viewResolver(resolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/api/**")
            //.allowedOrigins("http://localhost:3000") 로컬 배포 테스트용
            .allowedOrigins("https://abysshoprestfrontend-production.up.railway.app")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
