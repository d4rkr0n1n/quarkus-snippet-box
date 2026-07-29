package dev.you;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/snippet")
public class SnippetCount {
  
  @GET
  @Path("/count")
  @Produces(MediaType.TEXT_PLAIN)
  public String count(){
    return "69";
  }
}
