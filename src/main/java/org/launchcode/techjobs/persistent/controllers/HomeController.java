package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	    model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        if (optionalEmployer.isPresent()) {
            Employer employer = (Employer) optionalEmployer.get();
            model.addAttribute("employer", employer);
            newJob.setEmployer(employer);
        }

        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);

        if (optionalJob.isPresent()) {
            Job job = (Job) optionalJob.get();
            model.addAttribute("job", job);
        }
        return "view";
    }

    @GetMapping("delete")
    public String displayDeleteJobsForm(Model model) {
        model.addAttribute("title", "Delete Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "delete";
    }

    @PostMapping("delete")
    public String processDeleteJobsForm(@RequestParam(required = false) int[] jobIds) {

        if (jobIds != null) {
            for (int id : jobIds) {
                jobRepository.deleteById(id);
            }
        }
        return "redirect:";
    }
//
//    @GetMapping("edit/{jobId}")
//    public String processAndDisplayJobsToEditForm(Model model, @PathVariable int jobId) {
//        Job job = jobRepository.findById(jobId).get();
//        model.addAttribute("job", job);
//        String title = "Edit Job " + job.getName() + " (id=" + job.getId() + ")";
//        model.addAttribute("title", title);
//        model.addAttribute("employers", employerRepository.findAll());
//        model.addAttribute("skills", skillRepository.findAll());
//        return "edit";
//    }
//
//    @RequestMapping(value="edit", method={ RequestMethod.GET, RequestMethod.POST})
//    public String processEditForm(Model model, int jobId, String name, Employer employer, List<Skill> skills) {
//        Job job = jobRepository.findById(jobId).get();
//        job.setName(name);
//        job.setEmployer(employer);
//        job.setSkills(skills);
//        jobRepository.save(job);
//        return "redirect:/index";
//    }

}
