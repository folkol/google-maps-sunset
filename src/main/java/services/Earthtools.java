package services;

import static java.lang.String.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("")
public class Earthtools {

    private static final String BASE_URL = "http://www.earthtools.org/sun";

    @GET
    @Path("sunset/{latitude}/{longitude}/{day}/{month}/{timezone}/{dst}")
    public String sunset(@PathParam("latitude") String latitude,
                      @PathParam("longitude") String longitude,
                      @PathParam("day") String day,
                      @PathParam("month") String month,
                      @PathParam("timezone") String timezone,
                      @PathParam("dst") String dst)
    {
        Client client = Client.create();
        String url = format("%s/%s/%s/%s/%s/%s/%s", BASE_URL, latitude, longitude, day, month, timezone, dst);
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);

        return response.getEntity(String.class);
    }

}
