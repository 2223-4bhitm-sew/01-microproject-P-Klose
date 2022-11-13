package at.htl.boundary;

import at.htl.control.BussRepository;
import at.htl.entity.Buss;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Path("/buss")
public class BussResource {

    @Inject
    Logger logger;

    @Inject
    BussRepository bussRepository;

    private List<Buss> busses = new LinkedList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Buss> findAll() {
        return bussRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Buss findById(
            @PathParam("id") long id
    ) {
        logger.info(id);
        return bussRepository.findById(id);
    }

    @GET
    @Path("first")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Buss> findById(
            @QueryParam("first") String fuelType
    ) {
        //logger.info(fuelType);
        return bussRepository.findByFuelType(
                fuelType
        );
    }


    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(
            Buss buss,
            @Context UriInfo uriInfo
    ) throws Exception {
        //persons.add(person);
        Buss saved = bussRepository.save(buss);
        logger.info(buss.getNumberPlate() + " wird gespeichert");
        URI location = uriInfo
                .getAbsolutePathBuilder()
                .path(saved.getId().toString())
                .build();
        return Response.created(location).build();
    }

    @PATCH
    @Transactional
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("id") long id,
            Buss buss,
            @Context UriInfo uriInfo
    ) throws Exception {
        Buss oldBuss = findById(id);
        String numberPlate = buss.getNumberPlate() != null ? buss.getNumberPlate() : oldBuss.getNumberPlate();
        int seatingCapacity = buss.getSeatingCapacity() != 0 ? buss.getSeatingCapacity() : oldBuss.getSeatingCapacity();
        int standingCapacity = buss.getStandingCapacity() != 0 ? buss.getStandingCapacity() : oldBuss.getStandingCapacity();
        String companyName = buss.getCompanyName() != null ? buss.getCompanyName() : oldBuss.getCompanyName();
        String fuelType = buss.getFuelType() != null ? buss.getFuelType() : oldBuss.getFuelType();

        buss.setId(id);
        buss.setNumberPlate(numberPlate);
        buss.setCompanyName(companyName);
        buss.setFuelType(fuelType);
        buss.setSeatingCapacity(seatingCapacity);
        buss.setStandingCapacity(standingCapacity);

        bussRepository.save(buss);
        logger.info("Wird geändert");
        logger.debug(uriInfo);
        URI location = uriInfo
                .getAbsolutePathBuilder()
                .build();
        logger.debug(uriInfo);
        return Response.created(location).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(
            @PathParam("id") long id
    ) {
        //logger.info(id);
        bussRepository.delete(findById(id));
        return Response.noContent().build();
    }


}