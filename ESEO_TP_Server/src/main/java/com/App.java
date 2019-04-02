package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@RequestMapping("/path")
public class App 
{
  public static void main( String[] args )
  {
  	try {
			SpringApplication.run(App.class,args);
			System.out.println("Application démarrée ");
		}catch (Exception e) {
			System.out.println("Application pas démarrée " + e);
		}
  }
}
