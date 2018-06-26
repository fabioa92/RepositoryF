package twitterPack;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/twitter")
public class ServiziRest {
	DAOtwitter daot;
	
	public ServiziRest() {
		daot = new DAOtwitter();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/registra")
	public Response Regista(Utenti u) {
		
		
		int esito = daot.RegistraUtente(u);
	
		RispostaBoolean response = new RispostaBoolean();
		if (esito == 1) {
			response.setError(false);
			response.setMessage("REGISTRAZIONE OK");
			return Response.status(Status.OK).entity(response).build();
		} else {
			response.setError(true);
			response.setMessage("REGISTRAZIONE KO");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/accedi")
	public Response Accedi(Utenti u) {
		
		String e = u.getEmail();
		String p = u.getPassword();
		
		int esito = daot.AccediUtente(e, p);
		
		RispostaBoolean response = new RispostaBoolean();
		if (esito == 1) {
			response.setError(false);
			response.setMessage("ACCESSO OK");
			return Response.status(Status.OK).entity(response).build();
		} else {
			response.setError(true);
			response.setMessage("ACCESSO KO");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/visualizzaUtenti")
	public ArrayList<Utenti> VisualizzaU(){
		
		ArrayList<Utenti> listaU =  daot.VisualizzaUtenti();
		return listaU;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/visualizzaPost/{id}")
	public ArrayList<Post> VisualizzaP(@PathParam("id") int id) {
		ArrayList<Post> listaP = daot.VisualizzaPost(id);
		return listaP;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pubblica")
	public Response InserisciP(Post p) {
	
		
		int esito =	daot.InserimentoPost(p);
		
		RispostaBoolean response = new RispostaBoolean();
		if (esito == 1) {
			response.setError(false);
			response.setMessage("INSERIMENTO OK");
			return Response.status(Status.OK).entity(response).build();
		} else {
			response.setError(true);
			response.setMessage("INSERIMENTO KO");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cancella/{id}")
	public Response RimuoviP(@PathParam("id") int id) {
		
		int esito = daot.RimozionePost(id);
		
		RispostaBoolean response = new RispostaBoolean();
		if (esito == 1) {
			response.setError(false);
			response.setMessage("INSERIMENTO OK");
			return Response.status(Status.OK).entity(response).build();
		} else {
			response.setError(true);
			response.setMessage("INSERIMENTO KO");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
		
		
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/segui/{id}/{id2}")
	public Response Segue(@PathParam("id") int id, @PathParam("id2") int id2) {
		
		int id1 = id;
	
		int esito  = daot.SegueUtente(id1, id2);
	
		RispostaBoolean response = new RispostaBoolean();
		if (esito == 1) {
			response.setError(false);
			response.setMessage("SEGUI OK");
			return Response.status(Status.OK).entity(response).build();
		} else {
			response.setError(true);
			response.setMessage("SEGUI KO");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}


}
	
}
