package com.esprit.microservice.candidat;
import com.esprit.microservice.candidat.Candidat;
import com.esprit.microservice.candidat.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidateRepository;

    // Ajouter un candidat
    public Candidat addCandidat(Candidat candidate) {
        return candidateRepository.save(candidate);
    }


    // Modifier un candidat
    public Candidat updateCandidat(int id, Candidat newCandidat) {
        if (candidateRepository.findById(id).isPresent()) {

        Candidat existingCandidat = candidateRepository.findById(id).get();
        existingCandidat.setNom(newCandidat.getNom());
        existingCandidat.setPrenom(newCandidat.getPrenom());
        existingCandidat.setEmail(newCandidat.getEmail());

        return candidateRepository.save(existingCandidat);
    } else
        return null;
    }


    // Supprimer un candidat
    public String deleteCandidat(int id) {
        if (candidateRepository.findById(id).isPresent()) {
            candidateRepository.deleteById(id);
            return "candidat supprimé";
        } else
            return "candidat non supprimé";
    }

}
