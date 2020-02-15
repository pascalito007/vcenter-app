package tp.vsphere;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tp.vsphere.vcenter.vm.create.basicvm.CreateBasicVM;
import tp.vsphere.vcenter.vm.list.ListVMs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank P. Moley III.
 */
@Controller
@RequestMapping("/")
public class AppController {

    List<MachinVirtuel> machines ;
    MachinVirtuel vm;
    String args[] ={
            "--server", "192.168.1.100",
            "--username", "mrtest",
            "--password", "Bonjour01",
            "--skip-server-verification",
            "--datacenter", "DC01"};

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) throws Exception {
        machines = new ArrayList<>();

        ListVMs.main(args, machines);
        machines.forEach(System.out::println);
        model.addAttribute("machines", machines);
        model.addAttribute("vm", new MachinVirtuel());
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping(value = "/delete/{name}")
    public String delete(Model model, @PathVariable String name) throws Exception {
        String args[] ={
                "--server", "192.168.1.100",
                "--username", "mrtest",
                "--password", "Bonjour01",
                "--vmfolder", "SUPINFO",
                "--vmname", name,
                "--cleardata", "true",
                "--datastore", "VMDatastore02",
                "--host", "esxi01.cloudis305417.lan",
                "--cluster", "Cluster",
                "--standardportgroup", "Public VM Network",
                "--skip-server-verification",
                "--datacenter", "DC01"};
        CreateBasicVM.main(args);
        return "redirect:index";
    }

    @GetMapping(value = "/backup/{name}")
    public String backup(Model model, @PathVariable String name) throws Exception {

        return "redirect:index";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute MachinVirtuel vm) throws Exception {
        if(null != vm.getName() && null != vm.getIpAddress()){
            String args[] ={
                    "--server", "192.168.1.100",
                    "--username", "mrtest",
                    "--password", "Bonjour01",
                   "--vmfolder", "SUPINFO",
                    "--vmname", vm.getName(),
                    "--datastore", "VMDatastore02",
                    "--host", "esxi01.cloudis305417.lan",
                    "--cluster", "CLU01",
                    "--standardportgroup", "Public VM Network",
                    "--skip-server-verification",
                    "--datacenter", "DC01"};
            CreateBasicVM.main(args);
        }
        return "redirect:index";
    }

    @GetMapping(value = "/logout-success")
    public String getLogoutPage(Model model) {
        return "logout";
    }

    public List<MachinVirtuel> getMachines() {
        return machines;
    }

    public void setMachines(List<MachinVirtuel> machines) {
        this.machines = machines;
    }

    public MachinVirtuel getVm() {
        return vm;
    }

    public void setVm(MachinVirtuel vm) {
        this.vm = vm;
    }
}
