package com.esprit.microservice.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobRestAPI {

    @Autowired
    private JobService jobService;

    private String title = "Hello, I'm the Job Micro-Service";

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }

    // Afficher tous les jobs
    @GetMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Afficher un job par son ID
    @GetMapping(value = "/jobs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Job> getJobById(@PathVariable("id") int id) {
        Optional<Job> job = jobService.getJobById(id);
        return job.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Afficher un job par son nom (service)
    @GetMapping(value = "/jobs/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Job>> getJobByName(@RequestParam("service") String service) {
        List<Job> jobs = jobService.getJobByName(service);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Modifier l'état d'un poste (oui = disponible, non = occupé)
    @PutMapping(value = "/jobs/{id}/etat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Job> updateJobState(@PathVariable("id") int id, @RequestParam("etat") boolean etat) {
        Job updatedJob = jobService.updateJobState(id, etat);
        return updatedJob != null ? new ResponseEntity<>(updatedJob, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return new ResponseEntity<>(jobService.addJob(job), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Job> updateJob(@PathVariable(value = "id") int id, @RequestBody Job job) {
        return new ResponseEntity<>(jobService.updateJob(id, job), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteJob(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(jobService.deleteJob(id), HttpStatus.OK);
    }
}
