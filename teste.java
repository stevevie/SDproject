package proj;
import java.util.*;
import java.net.*;
import java.io.*;

public class teste { // SISTEMA DE CADASTRO COM FICHEIRO
	
	static String username;
	static String password;
	
	static void pedir_dados(){ // PEDIR DADOS AO UTILIZADOR
        Scanner in = new Scanner(System.in);
		System.out.print("username: ");
		username = in.next();
		System.out.print("password: ");
		password = in.next();
		//System.out.println(username);
		//System.out.println(password);
	}
	
	static void guardar_dados(){ // GUARDAR DADOS NO FICHEIRO
		
		
	}
	
	static void verificar_existencia(){
		
		
	}
	
	public static void main(String args[]){
		
		pedir_dados();
		
	}

}
