package warehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import warehouse.model.*;
import warehouse.repository.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class User_controller {
    private String trigger="";
    private int pers_id;
    private List<History> sort_History(List<History>list){
        for (int i=list.size()-1;i>=0;i--){
            if(list.get(i).getId_user()!=pers_id){
                list.remove(i);
            }
        }
        return list;
    }
    private List<Orders> sort_record(List<Orders>list){
        for (int i=list.size()-1;i>=0;i--){
            if(list.get(i).getId_user()!=pers_id){
                list.remove(i);
            }
            else{
                if(list.get(i).getStatus().equals("Завершено")){
                    list.remove(i);
                }
            }
        }

        return list;
    }
    private void close_order() throws ParseException {
        List<Orders>list=repositoryOrders.findAll();
        for (int i=0;i<list.size();i++){
            Date date1=new Date();
            SimpleDateFormat smpl= new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date date=smpl.parse(list.get(i).getDate());
            if(date.before(date1)&&list.get(i).getStatus().equals("Ожидается")){
                History history=new History();
                history.setId_user(list.get(i).getId_user());
                history.setDate(list.get(i).getDate());
                history.setType(list.get(i).getType());
                history.setFIO_supplier(list.get(i).getFIO_supplier());
                history.setPrice(list.get(i).getPrice());
                list.get(i).setStatus("Завершено");
                repositoryHistory.save(history);
            }
        }
        repositoryOrders.saveAll(list);
    }

    @Value("${upload.path_user}")
    private String load_user;
    @Autowired
    RepositorySupplier repositorySupplier;
    @Autowired
    RepositoryOrders repositoryOrders;
    @Autowired
    RepositoryUser repositoryUser;
    @Autowired
    RepositoryHistory repositoryHistory;
    @Autowired
    RepositoryProduct repositoryProduct;

    @PostMapping("/update_settins_user")
    private String update_settins(@RequestParam String name_set, @RequestParam String sur_set, @RequestParam String patr_set,
                                  @RequestParam String phone_set, @RequestParam String login_set, @RequestParam String password_set,
                                  @RequestParam(required = false) MultipartFile pers_photo
    ) throws IOException {
        User employee=repositoryUser.findById(pers_id).get();
        employee.setName(name_set);
        employee.setSur_name(sur_set);
        employee.setPatronymic(patr_set);
        employee.setPhone(phone_set);
        employee.setLogin(login_set);
        employee.setPassword(password_set);
        if(!pers_photo.getOriginalFilename().isEmpty()&&!pers_photo.getOriginalFilename().equals(employee.getName_photo_file())){
            File file=new File(load_user);
            if(!file.exists()) {
                file.mkdir();
            }
            if(!pers_photo.isEmpty()){
                if(!employee.getName_photo_file().equals("Meni.png")&&!employee.getName_photo_file().equals("Women.png")){
                    File file1=new File(file.getAbsolutePath()+"/"+employee.getName_photo_file());
                    file1.delete();
                }
                String uuid_file= UUID.randomUUID().toString();
                String Or_file_name=pers_photo.getOriginalFilename().substring(pers_photo.getOriginalFilename().indexOf("."),pers_photo.getOriginalFilename().length());
                String file_name=uuid_file+"_"+employee.getId()+Or_file_name;
                pers_photo.transferTo(new File(file.getAbsolutePath()+"/"+file_name));
                employee.setName_photo_file(file_name);
            }
        }
        repositoryUser.save(employee);
        trigger="settins";
        return "redirect:/user_room:"+pers_id;
    }

    @GetMapping("/user_room:{id}")
    private String user_menu(Model model, @PathVariable(value = "id")Integer id_user) throws ParseException {
        close_order();
        pers_id=id_user;
        User user=repositoryUser.findById(id_user).get();
        List<Supplier> supplierList=repositorySupplier.findAll();
        List<History> listHistory=repositoryHistory.findAll();
        List<Orders>listOrders=repositoryOrders.findAll();
        List<Product>listProduct=repositoryProduct.findAll();
        String tr=trigger;
        model.addAttribute("trigger",tr);
        model.addAttribute("pers_info",user);
        model.addAttribute("all_supplier",supplierList);
        model.addAttribute("list_history",sort_History(listHistory));
        model.addAttribute("list_order",sort_record(listOrders));
        model.addAttribute("list_product",listProduct);
        trigger="";
        return "User_menu";
    }

    @PostMapping("/add_order")
    private String add_order(@RequestParam int id_product,@RequestParam int id_supplier,@RequestParam String fio_supplier,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                             LocalDateTime date
    )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dat = date.format(formatter);
        User user=repositoryUser.findById(pers_id).get();
        Orders orders=new Orders();
        orders.setStatus("Ожидается");
        orders.setId_user(pers_id);
        orders.setId_supplier(id_supplier);
        orders.setPrice(repositoryProduct.findById(id_product).get().getPrice());
     //   orders.setPrice(repositoryOrders.findById(id_product).get().getPrice());
        orders.setId_product(id_product);
        orders.setFIO_supplier(fio_supplier);
        orders.setFIO_user(user.getName()+" "+user.getSur_name()+" "+user.getPatronymic());
        orders.setDate(dat);
        orders.setType(repositoryProduct.findById(id_product).get().getType());
        repositoryOrders.save(orders);
        trigger="order";
        return "redirect:/user_room:"+pers_id;
    }
    @GetMapping("/cancel_order:{id}")
    private String cancel_record(@PathVariable(value = "id")Integer id_product){
        repositoryOrders.deleteById(id_product);
        trigger="history";
        return "redirect:/user_room:"+pers_id;
    }
}
