package com.vcdom.controllers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.vcdom.jparepository.EmployeeRepository;
import com.vcdom.model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        List<EmployeeEntity> list = repository.findAll();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping({"/addEmployee"})
    public String addEmployee(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        return "addEmployee";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("employee") EmployeeEntity employee, Map<String, Object> model) {
        repository.save(employee);
        return "redirect:index";
    }

    @RequestMapping(value = "/editEmployee/{id}")
    public String edit(@PathVariable long id, Model model) {
        EmployeeEntity employee = repository.getOne(id);
        model.addAttribute("employee", employee);
        return "addEmployee";
    }

    @RequestMapping(value = "/deleteEmployee/{id}")
    public String delete(@PathVariable long id) {
        repository.deleteById(id);
        return "redirect:../index";
    }

    @RequestMapping(value = "/upload-csv-file")
    public String uploadCsvFile(@RequestParam("file") MultipartFile file, Model model) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<EmployeeEntity> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(EmployeeEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<EmployeeEntity> employeeEntities = csvToBean.parse();

            repository.saveAll(employeeEntities);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:index";
    }
}
