package warehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class Admin_controller {
    private String trigger="";
    private int personal_id;
    private void close_order() throws ParseException {
        List<Orders> list= repositoryOrders.findAll();
        for (int i=0;i<list.size();i++){
            Date date1=new Date();
            SimpleDateFormat smpl= new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date date=smpl.parse(list.get(i).getDate());
            if(date.before(date1)&&list.get(i).getStatus().equals("Ожидается")){
                History history=new History();
                history.setId_user(list.get(i).getId_user());
                history.setType(list.get(i).getType());
                history.setDate(list.get(i).getDate());
                history.setFIO_supplier(list.get(i).getFIO_supplier());
                history.setPrice(list.get(i).getPrice());
                list.get(i).setStatus("Завершено");
                repositoryHistory.save(history);
            }
        }
       repositoryOrders.saveAll(list);
    }


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
    @Autowired
    RepositoryBanList repositoryBanList;
    @Autowired
    RepositoryReception repositoryReception;

    @Value("${upload.path_user}")
    private String load_user;
    @Value("${upload.path_product}")
    private String load_product;
    @Value("${upload.path_supplier}")
    private  String load_supplier;

    @Value("${upload.path_reception}")
    private  String load_reception;

    @GetMapping("/admin_room:{id}")
    private String admin_menu(Model model, @PathVariable(value = "id")Integer id_admin) throws ParseException {
        close_order();
        User pers_info=repositoryUser.findById(id_admin).get();
        List<User> list_user=repositoryUser.findAll();
        List<Product>list_product=repositoryProduct.findAll();
        List<BanList>list_ban=repositoryBanList.findAll();
        List<Supplier>list_supplier=repositorySupplier.findAll();
        List<Reception> list_reception=repositoryReception.findAll();
        personal_id=id_admin;
        String tr=trigger;
        model.addAttribute("trigger",tr);
        model.addAttribute("pers_info",pers_info);
        model.addAttribute("all_user",list_user);
        model.addAttribute("product",list_product);
        model.addAttribute("ban",list_ban);
        model.addAttribute("supplier",list_supplier);
        model.addAttribute("reception",list_reception);
        trigger="";
        return "Admin_menu";
    }

    @PostMapping("/update_settins")
    private String update_settins(@RequestParam String name_set, @RequestParam String sur_set, @RequestParam String patr_set,
                                  @RequestParam String phone_set, @RequestParam String login_set, @RequestParam String password_set,
                                  @RequestParam(required = false) MultipartFile pers_photo
    ) throws IOException {
        User employee=repositoryUser.findById(personal_id).get();
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
        return "redirect:/admin_room:"+personal_id;
    }

    @PostMapping("/add_product")
    private String add_product(@RequestParam String type,@RequestParam String name_product,@RequestParam double weightProduct,@RequestParam double price,@RequestParam MultipartFile photo_product) throws IOException {
        Product product=new Product();
        product.setName_product(name_product);
        product.setType(type);
        product.setPrice(price);
        product.setWeight(weightProduct);
        File file=new File(load_product);
        if(!photo_product.isEmpty()){
            String uuid_file= UUID.randomUUID().toString();
            String Or_file_name=photo_product.getOriginalFilename().substring(photo_product.getOriginalFilename().indexOf("."),photo_product.getOriginalFilename().length());
            String file_name=uuid_file+"_"+Or_file_name;
            photo_product.transferTo(new File(file.getAbsolutePath()+"/"+file_name));
            product.setName_photo_file(file_name);
        }
        trigger="product";
        repositoryProduct.save(product);
        return "redirect:/admin_room:"+personal_id;
    }

    @GetMapping("/deleted_user:{id}")
    private String deleted_user(@PathVariable(value = "id")Integer user_id){
        if(!repositoryUser.findById(user_id).get().getName_photo_file().equals("no_name.png")){
            File file=new File(load_user);
            File file1=new File(file.getAbsolutePath()+"/"+repositoryUser.findById(user_id).get().getName_photo_file());
            file1.delete();
        }
        repositoryUser.deleteById(user_id);
        trigger="users";
        return "redirect:/admin_room:"+personal_id;
    }
    @GetMapping("/do_administration:{id}")
    private String do_administration(@PathVariable(value = "id")Integer user_id){
        User user=repositoryUser.findById(user_id).get();
        user.setType("Admin");
        repositoryUser.save(user);
        trigger="users";
        return "redirect:/admin_room:"+personal_id;
    }
    @GetMapping("/unblock_user:{id}")
    private String block_or_unblock_user(@PathVariable(value = "id")Integer user_id){
        User user=repositoryUser.findById(user_id).get();
        user.setStatus("Активно");
        repositoryBanList.deleteById(user_id);
        trigger="ban_list";
        repositoryUser.save(user);
        return "redirect:/admin_room:"+personal_id;
    }

    @PostMapping("/block_user")
    private String unblock_user(@RequestParam String comment,@RequestParam int id_user){
        User user=repositoryUser.findById(id_user).get();
        user.setStatus("Заблокировано");
        BanList ban_list=new BanList();
        ban_list.setFIO(user.getName()+" "+user.getSur_name()+" "+user.getPatronymic());
        ban_list.setReason(comment);
        ban_list.setName_photo(user.getName_photo_file());
        ban_list.setId_supplier(id_user);
        repositoryBanList.save(ban_list);
        trigger="users";
        repositoryUser.save(user);
        repositoryBanList.save(ban_list);
        return "redirect:/admin_room:"+personal_id;
    }

    @GetMapping("/deleted_supplier:{id}")
    private String deleted_supplier(@PathVariable(value = "id")Integer supplier_id){
        File file=new File(load_supplier);
        File file1=new File(file.getAbsolutePath()+"/"+repositorySupplier.findById(supplier_id).get().getName_photo_file());
        file1.delete();
        repositorySupplier.deleteById(supplier_id);
        trigger="supplier";
        return "redirect:/admin_room:"+personal_id;
    }

    @PostMapping("/add_supplier")
    private String add_supplier(@RequestParam String name,@RequestParam String sur_name,@RequestParam String patronymic
            ,@RequestParam String phone,@RequestParam String townSup,@RequestParam int id_product,
                                @RequestParam MultipartFile photo_supplier
    ) throws IOException {
        Date date=new Date();
        SimpleDateFormat simpl=new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dat=simpl.format(date);
        Product product=repositoryProduct.findById(id_product).get();
        Supplier supplier=new Supplier();
        supplier.setName(name);
        supplier.setSecond_name(sur_name);
        supplier.setPatronymic(patronymic);
        supplier.setPhone(phone);
        supplier.setCategory(product.getType());
        supplier.setDate_settled(dat);
        supplier.setId_product(id_product);
        supplier.setTownSupplier(townSup);
        File file=new File(load_supplier);
        if(!photo_supplier.isEmpty()){
            String uuid_file= UUID.randomUUID().toString();
            String Or_file_name=photo_supplier.getOriginalFilename().substring(photo_supplier.getOriginalFilename().indexOf("."),photo_supplier.getOriginalFilename().length());
            String file_name=uuid_file+"_"+Or_file_name;
            photo_supplier.transferTo(new File(file.getAbsolutePath()+"/"+file_name));
            supplier.setName_photo_file(file_name);
        }
        repositorySupplier.save(supplier);
        trigger="supplier";
        return "redirect:/admin_room:"+personal_id;
    }

    @PostMapping("/add_reception")
    private String add_reception(@RequestParam String address,@RequestParam String town, @RequestParam String timeWork
            , @RequestParam MultipartFile photo_reception
    ) throws IOException {
       Reception reception = new Reception();
       reception.setAddress(address);
       reception.setTown(town);
       reception.setTimeWork(timeWork);
        File file=new File(load_reception);
        if(!photo_reception.isEmpty()){
            String uuid_file= UUID.randomUUID().toString();
            String Or_file_name=photo_reception.getOriginalFilename().substring(photo_reception.getOriginalFilename().indexOf("."),photo_reception.getOriginalFilename().length());
            String file_name=uuid_file+"_"+Or_file_name;
            photo_reception.transferTo(new File(file.getAbsolutePath()+"/"+file_name));
            reception.setName_photo(file_name);
        }
        repositoryReception.save(reception);
        trigger="reception";
        return "redirect:/admin_room:"+personal_id;
    }
    @GetMapping("/deleted_reception:{id}")
    private String deleted_reception(@PathVariable(value = "id") Integer id){
        File file=new File(load_reception);
        File file1=new File(file.getAbsolutePath()+"/"+repositoryReception.findById(id).get().getName_photo());
        file1.delete();
        repositoryReception.deleteById(id);
        trigger="reception";
        return "redirect:/admin_room:"+personal_id;
    }
    @GetMapping("/deleted_product:{id}")
    private String deleted_product(@PathVariable(value = "id") Integer id_product){
        File file=new File(load_product);
        File file1=new File(file.getAbsolutePath()+"/"+repositoryProduct.findById(id_product).get().getName_photo_file());
        file1.delete();
        List<Supplier>list=repositorySupplier.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId_product()==id_product){
                list.get(i).setId_product(-1);
            }
        }
        repositorySupplier.saveAll(list);
        repositoryProduct.deleteById(id_product);
        trigger="product";
        return "redirect:/admin_room:"+personal_id;
    }
}
