/*우리가 등록한 jar파일에 들어있는 클래스를 사용해보자.*/

package com.ss.test;
import com.ss.driver.MiracleDriver;

public class UseDriver{
	public static void main(String[] args){
		MiracleDriver md=new MiracleDriver();
		System.out.println(md.getName());
	}
}
