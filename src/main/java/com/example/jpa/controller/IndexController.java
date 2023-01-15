package com.example.jpa.controller;

import com.example.jpa.model.Category;
import com.example.jpa.model.Task;
import com.example.jpa.repository.CategoryRepository;
import com.example.jpa.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path="/tasks")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }

    @GetMapping(path="/")
    public String getTasks(Model model) {
        model.addAttribute("rows", taskRepository.findAll());
        return "index";
    }

    @GetMapping(path="/tasks/new")
    public String nouveau(@ModelAttribute("note") Task task) {

        return "task_form";
    }

    @PostMapping(path="/tasks")
    public String postNew(Task task) {
        //Y a pas de propagation donc:

        //findbyname de la category
        Category cat = categoryRepository.findByName(task.getCategory().getName());
        //remplace la cate de task par celle ci
        task.setCategory(cat);
        //save de la task
        taskRepository.save(task);

        //redirige vers GET /tasks
        return "redirect:/tasks";
    }


    @GetMapping(value = "/tasks/{id}")
    public String getTask(@PathVariable("id") long id, Model model) {
        model.addAttribute("task", taskRepository.findById(id).get());
        return "task";
    }

    @GetMapping(value = "/tasks/{id}/edit")
    public String editTask(@PathVariable("id") Long id, @ModelAttribute("note") Task task, Model model){
        model.addAttribute("id", id);
        return "editTask";
    }

    @PostMapping(value = "/tasks/{id}")
    public String postTask(@PathVariable("id") Long id, Task editedTask){
        Task oldTask = taskRepository.findById(id).get();
        System.out.println(oldTask);
        //marche pas car problèeme avant
        if(editedTask.getCategory() != null){
            //Y a pas de propagation donc:

            //findbyname de la category
            Category cat = categoryRepository.findByName(editedTask.getCategory().getName());
            //remplace la category de task par celle ci
            oldTask.setCategory(cat);
        }
        if(editedTask.getContent() != ""){
            oldTask.setContent(editedTask.getContent());
        }
        System.out.println(oldTask);

        //save de la task
        taskRepository.save(oldTask);

        //redirige vers GET /tasks/{id}
        return "redirect:/tasks/{id}";
    }

    @DeleteMapping(value = "/tasks/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        System.out.println("suppression");
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}
