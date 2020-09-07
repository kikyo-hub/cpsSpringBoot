package cn.nrzt.cps.application;

import javax.servlet.Servlet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.bstek.ureport.console.UReportServlet;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("cn.nrzt.cps.*.mapper")
@ComponentScan(basePackages = "cn.nrzt.cps.*")//开启全局扫描组件注解，并将其注册成组件
@EnableSwagger2 //开启swagger文档
@SpringBootApplication()
@ImportResource("classpath:ureport-console-context.xml") // 加载ureport对应的xml配置文件
public class CpsApplication extends SpringBootServletInitializer {
	/**
	 * 项目启动入口
	 * **/
	public static void main(String[] args) {
        SpringApplication.run(CpsApplication.class, args);
    }
	//为了打包springboot项目
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
	/**
	 * ureport2报表Servlet配置
	 */
	@Bean
	public ServletRegistrationBean ureport2Servlet(){
	    return new ServletRegistrationBean (new UReportServlet(), "/ureport/*");
	}

}