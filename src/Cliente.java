
import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.*;


public class Cliente {
	private int cliente_id;
	
    
    public static void cadastro(){
        //System.out.println("entrou no metodo cadastro.");
    }
    
    public static void login(){
        //System.out.println("entrou no metodo login.");
    	int registado = 1;
        Scanner in = new Scanner(System.in);
        String username = "";
        String password = "";
        System.out.print("Username: ");
        username = in.next();
        System.out.print("Senha: ");
        password = in.next();
        
        // verificar se o utilizador ja esta registado
        if(registado == 0){
        	System.out.println("DADOS DE ACESSO INVALIDOS!");
        	// volta ao menu inicial automaticamente
        	menu_principal();
        }else{
        	//System.out.println("acesso conseguido!");
        	menu_acesso();
        }

    }
    ////////////////////////////////////////////
    // TRABALHOS AO NIVEL DO SISTEMA DE FICHEIROS
    
    public static void cadastrar(){
    	
    	// cria o ficheiro de cadastros apenas se nao existir
    	// verifica se o par (user+pass) ja estao em uso
    	// se ainda nao estiverem em uso entao adiciona
    	// no final do ficheiro esse par
    	
    }
    ///////////////////////////////////////////////
    
    public static void menu_principal(){
    	int opcao;
        Scanner in = new Scanner(System.in);
        
        //do{
        System.out.println("1. Fazer login");
        System.out.println("2. Criar conta");
        System.out.print("Opcao:");
        
            opcao = in.nextInt();
            
            switch(opcao){
            case 1:
                login();
                break;
   
            case 2:
                cadastro();
                break;
            
            default:
                System.out.println("OPCAO IVALIDA!");
                menu_principal();
            }
        //} while(opcao != 0);
    }
    
    public static void menu_acesso(){
    	int opcao;
        Scanner entrada = new Scanner(System.in);
        
        int mgs = 0;
        
        //do{
        System.out.println("ACESSO AUTORIZADO!");
        System.out.println("1. Criar novo leilao");
        System.out.println("2. Pesquisar leilao por codigo");
        System.out.println("3. Consultar detalhes de um leilao");
        System.out.println("4. Listar todos os leiloes");
        System.out.println("5. Efetuar licitao num leilao");
        System.out.println("6. Editar propriedades de um leilao");
        System.out.println("7. Escrever mensagem no mural de um leilao");
        System.out.println("8. Ler mensagens recebidas ("+ mgs +")");
        System.out.println("9. Listar utilizador ligados atualmente");
        System.out.println("10. Pesquisar leilao por codigo");

        System.out.print("Opcao:");
            opcao = entrada.nextInt();
            
            switch(opcao){
            case 1:
                login();
                break;
                
            case 2:
                cadastro();
                break;
            
            default:
                System.out.println("Opcao invalida.");
                menu_principal();
            }
        //} while(opcao != 0);
    }
    	
    public int getId(){
    	return cliente_id;
    }
    
    public static void main(String[] args) {
        System.out.println("\tBem vindo ao iBei!");
        menu_principal();
    }
}