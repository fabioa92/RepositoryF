package twitterPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.PathParam;


public class DAOtwitter {
	
	ConnessioneJDBC con;
	
	public DAOtwitter() {
		
		con = new ConnessioneJDBC();
		
	}
	
	public int RegistraUtente(Utenti u) {
		
		String query = "INSERT INTO utenti(nome, cognome, email, password) VALUES (?, ?, ?, ?)";
		
		try (Connection connessione = con.getConnection();
				PreparedStatement statement = connessione.prepareStatement(query);) {

				statement.setString(1, u.getNome());
				statement.setString(2, u.getCognome());
				statement.setString(3, u.getEmail());
				statement.setString(4, u.getPassword());
				statement.executeUpdate();
				
				return 1;

			} catch (SQLException e) {
				e.printStackTrace();
				
				return 0;
			
		}
				
	}
	
	public int AccediUtente(String em, String p) {
		
		String query = "SELECT id FROM utenti where email = ? and password = ?";
		
		try (Connection connessione = con.getConnection();
				PreparedStatement statement = connessione.prepareStatement(query);) {
			
			statement.setString(1, em);
			statement.setString(2, p);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) 
				return 1;
			else 
				return 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
	
		}
	
	
	}
	
	public ArrayList<Utenti> VisualizzaUtenti() {
		ArrayList<Utenti> utentiTwitter = new ArrayList();
		String query = "SELECT * FROM utenti";
		try (Connection connessione = con.getConnection();
				PreparedStatement statement = connessione.prepareStatement(query);) {
			
				ResultSet resultSet = statement.executeQuery();
				
				while( resultSet.next() ) {
					Utenti utente= new Utenti();
					utente.setId(resultSet.getInt("id"));
					utente.setNome( resultSet.getString( "Nome" ) );
					utente.setCognome( resultSet.getString( "Cognome" ) );
					utente.setEmail( resultSet.getString( "Email" ) );
					utente.setPassword( resultSet.getString( "Password" ) );
					utentiTwitter.add(utente);
				}
				resultSet.close();
				statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
		}	
		return utentiTwitter;
	
		
	}
	
	public ArrayList<Post> VisualizzaPost(int id) {
		ArrayList<Post> variPost = new ArrayList();
	String query = "select post.id, descrizione, id_utente from post inner join segue where segue.id_seguito = post.id_utente and segue.id_segue = ?";
		
		try (Connection connessione = con.getConnection();
				PreparedStatement statement = connessione.prepareStatement(query);) {
			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next() ) {
				Post p= new Post();
				p.setId( resultSet.getInt( "id" ) );
				p.setDescrizione( resultSet.getString( "descrizione" ) );
				p.setId_utente( resultSet.getInt( "id_utente" ) );
				variPost.add(p);
			}
			
			resultSet.close();
			statement.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			
	
		}
		
			return variPost;
	}
	
	public int InserimentoPost(Post p) {
		
		String query = "INSERT INTO post(id, descrizione, id_utente) VALUES (?, ?, ?)";
		
		try (Connection connessione = con.getConnection();
				PreparedStatement statement = connessione.prepareStatement(query);) {

				statement.setInt(1, p.getId());
				statement.setString(2, p.getDescrizione());
				statement.setInt(3, p.getId_utente());
				statement.executeUpdate();
				
				return 1;

			} catch (SQLException e) {
				e.printStackTrace();
				
				return 0;
			
		}
	}
	
	
	public int RimozionePost(int id) {
		
		String query = "DELETE FROM post WHERE ID = ?";
		try (Connection connessione = con.getConnection();
				PreparedStatement statement = connessione.prepareStatement(query);) {

				statement.setInt(1, id);
				statement.executeUpdate();
				
				return 1;

			} catch (SQLException e) {
				e.printStackTrace();
				
			return 0;		
	
		}
		
		
	}
	
	public int SegueUtente(int id1, int id2) {
		
		String query = "INSERT INTO segue(id_segue, id_seguito) VALUES (?, ?)";
		
		try (Connection connessione = con.getConnection();
				PreparedStatement statement = connessione.prepareStatement(query);) {

				statement.setInt(1, id1);
				statement.setInt(2, id2);
				statement.executeUpdate();
				
				return 1;

			} catch (SQLException e) {
				e.printStackTrace();
				
				return 0;
			
		}
		
	}
	

}
