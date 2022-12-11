package warehouse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import warehouse.model.User;
import warehouse.repository.RepositoryUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class Autorization_controller {
    private String trigger="";


    private void first_registration(){
        List<User> users=repositoryUser.findAll();
        if(users.size()==0){
            Date date=new Date();
            SimpleDateFormat simpl=new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String dat=simpl.format(date);
            User user=new User();
            user.setName("Константин");
            user.setSur_name("Анкуда");
            user.setPatronymic("Викторович");
            user.setName_photo_file("Men.png");
            user.setPhone("298456791");
            user.setType("Admin");
            user.setStatus("Активно");
            user.setLogin("admin");
            user.setPassword("admin");
            user.setData_registration(dat);
            repositoryUser.save(user);
        }
    }

    @Autowired
    RepositoryUser repositoryUser;

    @GetMapping("/warehouse")
    private String warehouse(Model model){
        first_registration();
        String tr=trigger;
        model.addAttribute("trigger",tr);
        trigger="";
        return "Autorization";
    }

    @GetMapping("/registration_user")
    private String registration_user(@RequestParam String name, @RequestParam String sur_name, @RequestParam String patronymic,
                                     @RequestParam String phone, @RequestParam String login, @RequestParam String password
    ){
        Date date=new Date();
        SimpleDateFormat simpl=new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dat=simpl.format(date);
        User user=new User();
        user.setName(name);
        user.setSur_name(sur_name);
        user.setPatronymic(patronymic);
        user.setPhone(phone);
        user.setLogin(login);
        user.setPassword(password);
        user.setName_photo_file("no_name");
        user.setStatus("Активно");
        user.setType("User");
        user.setData_registration(dat);
        trigger="registration";
        repositoryUser.save(user);
        return "redirect:/warehouse";
    }

    @PostMapping("/autorization_user")
    private String autorization_user(@RequestParam String login, @RequestParam String password){
        List<User> list_employee=repositoryUser.findAll();
        Boolean check=false;
        for(int i=0;i<list_employee.size();i++){
            if(list_employee.get(i).getLogin().equals(login)&&list_employee.get(i).getPassword().equals(password)){
                check=true;
                if(list_employee.get(i).getStatus().equals("Заблокировано")){
                    trigger="ban";
                    return ("redirect:/warehouse");
                }
                else {
                    if(list_employee.get(i).getType().equals("Admin")){
                        return "redirect:/admin_room:"+list_employee.get(i).getId();
                    }
                    else {
                        return "redirect:/user_room:"+list_employee.get(i).getId();
                    }
                }
            }
        }
        if(check!=true){
            trigger="no found";
        }
        return ("redirect:/warehouse");
    }

}
