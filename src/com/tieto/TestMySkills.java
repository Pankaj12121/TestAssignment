package com.tieto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

public class TestMySkills {
	int x= 735;
	@Test
	public void mysum(){
	int[] myArray = new int[] {1, 2, 3, 4, 5};
	//List mylist=Arrays.asList(myArray);
	
	


	String intStr=Integer.toString(x);
	
	Pattern p =Pattern.compile("[]");
	
	Pattern pat= Pattern.compile("\\.");
	String [] s=pat.split("Amogh.pankaj.yogi");
	for(String p1:s){
	System.out.println(p1);	
	}
	Matcher mat=pat.matcher("ah b #Pan@Amo1Kajg h");
	while(mat.find()){
	System.out.println(mat.start()+" "+mat.group());
	}
	String intStr1= intStr.substring(0,1);
	String intStr2= intStr.substring(1,2);
	String intStr3= intStr.substring(2,3);
	
	String [] splinumber= new String [3];
	splinumber[0]=intStr1;
	splinumber[1]=intStr2;
	splinumber[2]=intStr3;
	int sum=0;
	for(String t:splinumber){
		
		int v= Integer.parseInt(t);
		
		sum =sum+v;
		
	}
	System.out.println("the sum is "+sum);
	}
	
	
}
