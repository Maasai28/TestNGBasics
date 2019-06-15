package com.class02;

import org.testng.annotations.Test;

public class Priority {
	
	@Test(priority =0)
	public void first() {
		System.out.println("first test annotation");
	}
	
	@Test(priority =1)
	public void second() {
		System.out.println("second test annotation");
	}
	
	@Test(priority =2)
	public void third() {
		System.out.println("third test annotation");
	}
	
	@Test(priority =3)
	public void fourth() {
		System.out.println("fourth test annotation");
	}
}
