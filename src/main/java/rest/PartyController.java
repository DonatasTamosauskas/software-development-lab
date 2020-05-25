package rest;

import entities.Party;
import lombok.Getter;
import lombok.Setter;
import persistence.PartyDAO;
import rest.contracts.PartyDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/party")
public class PartyController {

    @Inject
    @Getter
    @Setter
    private PartyDAO partyDAO;

    // TODO: Needs to be implemented
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Party party = partyDAO.findOne(id);
        if (party == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PartyDTO partyDTO = new PartyDTO();
        partyDTO.setName(party.getName());
        partyDTO.setGovernmentId(party.getGovernmentId());
        partyDTO.setBirthDate(party.getBirthDate());
        partyDTO.setPrivate(party.getPrivate());

        return Response.ok(partyDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer partyId, PartyDTO partyData) {
        try {
            Party existingParty = partyDAO.findOne(partyId);
            if (existingParty == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            existingParty.setName(partyData.getName());
            existingParty.setGovernmentId(partyData.getGovernmentId());
            existingParty.setBirthDate(partyData.getBirthDate());
            existingParty.setPrivate(partyData.getPrivate());
            partyDAO.update(existingParty);

            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}

