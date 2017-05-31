package org.nitin.java.practice.console;

public class NonStaticField {
	static String name= "nitin";
	static NonStaticField obj;
	
	public static NonStaticField getName() {
		System.out.println("==> " + name);
		obj = new NonStaticField();
		return obj;
	}
}
