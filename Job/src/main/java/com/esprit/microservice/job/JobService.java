package com.esprit.microservice.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // Ajouter un job
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    // Modifier un job
    public Job updateJob(int id, Job newJob) {
        return jobRepository.findById(id).map(existingJob -> {
            existingJob.setService(newJob.getService());
            existingJob.setEtat(newJob.getEtat());
            return jobRepository.save(existingJob);
        }).orElse(null);
    }

    // Supprimer un job
    public String deleteJob(int id) {
        return jobRepository.findById(id).map(job -> {
            jobRepository.deleteById(id);
            return "Job supprimé";
        }).orElse("Job non trouvé");
    }

    // Afficher tous les jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Afficher un job par son ID
    public Optional<Job> getJobById(int id) {
        return jobRepository.findById(id);
    }

    // Afficher un job par son nom (service)
    public List<Job> getJobByName(String service) {
        return jobRepository.findByServiceContaining(service);
    }

    // Modifier l'état d'un job (oui = disponible, non = occupé)
    public Job updateJobState(int id, boolean etat) {
        return jobRepository.findById(id).map(existingJob -> {
            existingJob.setEtat(etat);
            return jobRepository.save(existingJob);
        }).orElse(null);
    }
}
