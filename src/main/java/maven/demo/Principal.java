package maven.demo;

import java.util.*;

public class Principal {
	
	public static Scanner sc = new Scanner(System.in);
	
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
				menu = sc.nextInt();
				
				if(menu == 1){		
					//Inserir um elemento na tabela
					
					System.out.println("Digite ID,Nome,Email,Senha");
					System.out.println("Id : ");
					id = sc.nextInt();
					System.out.println("Nome : ");
					nome = sc.next();
					System.out.println("Email : ");
					email = sc.next();
					System.out.println("Senha: ");
					senha = sc.next();
					
					
					X usuario = new X(id, nome, email,senha);
					if(dao.inserirUsuario(usuario) == true) {
						System.out.println("Inserção com sucesso -> " + usuario.toString());
					
					}
				
				}
				else if(menu == 2) {
					//Excluir usuário
					
					System.out.println("Digite ID de usuário a ser excluido : ");
					id = sc.nextInt();
					dao.excluirUsuario(id);
				}
				else if(menu == 3){
					//Atualizar usuário
					X[] usuarios = dao.getUsuarios();
					int alterado = 0;
					System.out.println("Digite id de usuario a ser alterado : ");
					id = sc.nextInt();
					for(int i = 0; i < usuarios.length; i++) {
						if(usuarios[i].getId() == id) {
							alterado = i;
						}
					}
					
					
					System.out.println("Digite novo nome : ");
					nome = sc.nextLine();
					usuarios[alterado].setNome(nome);

					System.out.println("Digite novo email : ");
					email = sc.nextLine();
					usuarios[alterado].setEmail(email);

					System.out.println("Digite nova senha : ");
					senha = sc.nextLine();
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
