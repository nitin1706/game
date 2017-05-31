package org.nitin.java.practice.console;

public class TestGetSystemPath {

	public static void main(String[] args) {
		System.out.println("Hello");
		System.out.println(System.getProperty("user.home"));
		NonStaticField obj = new NonStaticField();
		System.out.println("== > " + obj.getName());
		
		System.out.println();
		
		System.out.println(" _                 __");
		System.out.println("| )    \\/ . |\\ | (");
		System.out.println(" _");
		System.out.println("|_) () /\\ | | \\| (__-|");
	}

}
