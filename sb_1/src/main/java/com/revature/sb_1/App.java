package com.revature.sb_1;
  
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	SpringApplication.run(App.class, args);
    }
}
