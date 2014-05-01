package services;

import static java.lang.String.*;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("")
public class EarthtoolsProxy {

    @GET
    @Path("sunset/{latitude}/{longitude}")
    public String sun(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude)
    {
        WebResource resource = Client.create().resource("http://www.earthtools.org/sun-1.0");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = 1 + calendar.get(Calendar.MONTH);

        String path = format("%s/%s/%d/%d/%d/%d", latitude, longitude, day, month, 1, 0);
        ClientResponse response = resource.path(path).get(ClientResponse.class);

        return response.getEntity(String.class);
    }

}
