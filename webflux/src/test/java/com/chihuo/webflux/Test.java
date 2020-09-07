package com.chihuo.webflux;

public class Test {

	public static void main(String[] args) {
		
	    String []f = "flux".split("\\s*");
	    String []m = "mono".split("\\s*");
	    
		for (int i = 0; i < f.length; i++) {
			System.out.println(f[i]);
		}
		
		for (int i = 0; i < m.length; i++) {
			System.out.println(m[i]);
		}
		
	}

}
