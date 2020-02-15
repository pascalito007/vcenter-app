package tp.vsphere;

import com.vmware.vcenter.vm.PowerTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MachinVirtuel {
    private String name;
    private String ipAddress;
    private PowerTypes.State status;
    private String lastBackup;

}
