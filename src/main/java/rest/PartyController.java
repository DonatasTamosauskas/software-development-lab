package rest;

import entities.Party;
import lombok.Getter;
import lombok.Setter;
import persistence.PartyDAO;
import rest.contracts.PartyDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.persistence.PessimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/party")
public class PartyController {

    @Inject
    @Getter
    @Setter
    private PartyDAO partyDAO;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Party> allParties = partyDAO.loadAll();
        if (allParties.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<PartyDTO> partiesDTOs = allParties.stream()
                .map((PartyDTO::new))
                .collect(Collectors.toList());

        return Response.ok(partiesDTOs).build();
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNew(PartyDTO partyDTO) {
        try {
            Party party = new Party();
            party.setName(partyDTO.getName());
            party.setGovernmentId(partyDTO.getGovernmentId());
            party.setBirthDate(partyDTO.getBirthDate());
            party.setPrivate(partyDTO.getPrivate());

            partyDAO.create(party);
            return Response.ok().build();
        } catch (OptimisticLockException | PessimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
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

