package maven.demo;

import java.util.*;
import java.io.*;
import java.nio.charset.*;

public class Principal {
	
	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
	private static String charset = "ISO-8859-1";
	
    public static String readLine(){
        String s = "";
        try{
            char tmp = (char) in.read();
            while (tmp != '\n' && tmp != (char) -1) {
                if (tmp != '\r') s += tmp;
                tmp = (char) in.read();
            }
        }catch(IOException ioe){
            System.out.println("MyIO.readLine: " + ioe.getMessage());
        }
        return s;
    }
	
    public static int readInt(){
        int i = -1;
        try{
            i = Integer.parseInt(readString().trim());
        }catch(Exception e){}
        return i;
    }

    
    public static String readString(){
        String s = "";
        try{
            char tmp = (char) in.read();
            while (tmp != '\n' && tmp != ' ' && tmp != '\t' && tmp != (char) -1) {
                if (tmp != '\r') s += tmp;
                tmp = (char) in.read();
            }
        }catch(IOException ioe){
            System.out.println("MyIO.readString: " + ioe.getMessage());
        }
        return s;
    }
    
	public static void main(String[] args) {
			
			DAO dao = new DAO();
			
			dao.conectar();
			
			int menu,id;
			String nome = "";
			String email = "";
			String senha = "";
			
			do{
				System.out.println("=========MENU DE X=========");
				System.out.println("1: inserir usuario | 2: deleta usuario | 3:alterar usuario | 4:mostrar usuarios X | 0:sair");
				menu = readInt();
				
				if(menu == 1){		
					//Inserir um elemento na tabela
					
					System.out.println("Digite ID,Nome,Email,Senha");
					System.out.println("Id : ");
					id = readInt();
					System.out.println("Nome : ");
					nome = readLine();
					
					System.out.println("Email : ");
					email = readString();
					
					System.out.println("Senha: ");
					senha = readString();
					
					
					X usuario = new X(id, nome, email,senha);
					if(dao.inserirUsuario(usuario) == true) {
						System.out.println("Inserção com sucesso -> " + usuario.toString());
					
					}
				
				}
				else if(menu == 2) {
					//Excluir usuário
					
					System.out.println("Digite ID de usuário a ser excluido : ");
					id = readInt();
					dao.excluirUsuario(id);
				}
				else if(menu == 3){
					//Atualizar usuário
					X[] usuarios = dao.getUsuarios();
					int alterado = 0;
					System.out.println("Digite id de usuario a ser alterado : ");
					id = readInt();
					for(int i = 0; i < usuarios.length; i++) {
						if(usuarios[i].getId() == id) {
							alterado = i;
						}
					}
					
					
					System.out.println("Digite novo nome : ");
					nome = readLine();
					usuarios[alterado].setNome(nome);

					System.out.println("Digite novo email : ");
					email = readString();
					usuarios[alterado].setEmail(email);

					System.out.println("Digite nova senha : ");
					senha = readString();
					usuarios[alterado].setSenha(senha);
					
					
					dao.atualizarUsuario(usuarios[alterado]);
				}else if(menu == 4) {
					//Mostrar usuários
					
					X[] usuarios = dao.getUsuarios();
					System.out.println("==== Mostrar usuários === ");		
					for(int i = 0; i < usuarios.length; i++) {
						System.out.println(usuarios[i].toString());
					}
					
				}
				
			}while(menu != 0);
			
			System.out.println("Obrigado por Utilizar !!");
			
			dao.close();
		}
}
