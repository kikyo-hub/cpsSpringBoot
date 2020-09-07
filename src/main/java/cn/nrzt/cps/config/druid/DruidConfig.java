package cn.nrzt.cps.config.druid;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DruidConfig {
	
//	@Primary
//  @Bean(name = "twoDataSource")
//  @ConfigurationProperties("spring.datasource.druid.two")
//  public DataSource dataSourceTwo(){
//      return DruidDataSourceBuilder.create().build();
//  }
	
    @Bean(name = "druidDataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();//DruidDataSourceBuilder.create().build();
    }

    /*
    
  //配置druid的监控
    
    //1、配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        //控制台管理员用户
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        //IP白名单
        initParams.put("allow","");//允许谁登录
        bean.setInitParameters(initParams);
        return  bean;
    }
 
 
    //2、配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        //忽略拦截规则
        initParams.put("exclusions","*.js,*.css,/druid/*");//不拦截谁
        bean.setInitParameters(initParams);
        //过滤规则
        bean.setUrlPatterns(Arrays.asList("/*"));//拦截所有请求
        return  bean;
    }
    
    */
}