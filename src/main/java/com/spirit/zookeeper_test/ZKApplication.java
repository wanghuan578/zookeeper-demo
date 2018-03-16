package com.spirit.zookeeper_test;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ZKApplication 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	TestGetData test = new TestGetData();
    	try {
			test.getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Thread.sleep(10000000);
    	
    	SpringApplication.run(ZKApplication.class, args);
    }
}
