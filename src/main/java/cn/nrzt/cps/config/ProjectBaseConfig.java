package cn.nrzt.cps.config;
import org.springframework.beans.factory.annotation.Value;
/**
 * 类说明：项目基本配置类(包含跨域配置以及静态资源映射网络位置)
 * 实现对WebMvcConfigurer接口的实现
 * **/
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectBaseConfig implements WebMvcConfigurer {
	
	 @Value("${swagger.enable}")
	 private boolean enableSwagger;
	
	/**
	 * CORS跨域配置
	 * **/
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("*")//跨域允许的方法
				.allowedOrigins("*")//允许的地址或域名
				.allowedHeaders("*")//允许的请求头
				.allowCredentials(true);
		
	}
	/**
	 * 静态资源的映射
	 * **/
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 if (enableSwagger)
		 {
	        registry.addResourceHandler("/party/**")
	        .addResourceLocations("file:/usr/local/**");//附件位置映射
	        registry.addResourceHandler("swagger-ui.html")
	        .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
	        registry.addResourceHandler("/webjars/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/");
			 registry.addResourceHandler("/static/**")
					 .addResourceLocations("classpath:static/");
		 }
	}

}
