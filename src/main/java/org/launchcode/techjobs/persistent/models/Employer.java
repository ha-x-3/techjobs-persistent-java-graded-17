package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Employer extends AbstractEntity {

    @NotNull(message = "Location is required.")
    @Size(min = 2, max = 50, message = "Location must be between 2 and 50 characters.")
    private String location;

    @OneToMany()
    @JoinColumn(name = "employer_id")
    private List<Job> jobs =  new ArrayList<>();

    public Employer() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "location='" + location + '\'' +
                ", jobs=" + jobs +
                '}';
    }
}
