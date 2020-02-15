package tp.vsphere;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VmDetail {
    private String name;
    private String ipAddress;
    private String status;
    private LocalDateTime lastBackupDate;
}
