/*
 * *******************************************************
 * Copyright VMware, Inc. 2017.  All Rights Reserved.
 * SPDX-License-Identifier: MIT
 * *******************************************************
 *
 * DISCLAIMER. THIS PROGRAM IS PROVIDED TO YOU "AS IS" WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, WHETHER ORAL OR WRITTEN,
 * EXPRESS OR IMPLIED. THE AUTHOR SPECIFICALLY DISCLAIMS ANY IMPLIED
 * WARRANTIES OR CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY,
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package tp.vsphere.vcenter.vm.list;

import com.vmware.vcenter.VM;
import com.vmware.vcenter.VMTypes.FilterSpec.Builder;
import com.vmware.vcenter.VMTypes.Summary;
import org.apache.commons.cli.Option;
import tp.vsphere.MachinVirtuel;
import tp.vsphere.common.SamplesAbstractBase;
import tp.vsphere.vcenter.helpers.ClusterHelper;
import tp.vsphere.vcenter.helpers.DatacenterHelper;
import tp.vsphere.vcenter.helpers.FolderHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description: Demonstrates getting list of VMs present in vCenter
 *
 * Author: VMware, Inc.
 * Sample Prerequisites: vCenter 6.5+
 */
public class ListVMs extends SamplesAbstractBase {
    private VM vmService;
    private String vmFolderName;
    private String datacenterName;
    private String clusterName;
    private static List<MachinVirtuel> machines;

    /**
     * Define the options specific to this sample and configure the sample using
     * command-line arguments or a config file
     *
     * @param args command line arguments passed to the sample
     */
    protected void parseArgs(String[] args) {
        Option datacenterOption = Option.builder()
                .longOpt("datacenter")
                .desc("OPTIONAL: Specify the name of the Datacenter"
                        + " to list the Vms in it.")
                .argName("DATACENTER")
                .required(false)
                .hasArg()
                .build();
        Option vmFolderOption = Option.builder()
                .longOpt("vmfolder")
                .desc("OPTIONAL: Specify the name of the VM Folder to list the"
                        + " Vms in it.")
                .argName("VM FOLDER")
                .required(false)
                .hasArg()
                .build();
        Option clusterOption = Option.builder()
                .longOpt("cluster")
                .desc("OPTIONAL: Specify the name of the Cluster to list the"
                        + " Vms in it.")
                .argName("CLUSTER")
                .required(false)
                .hasArg()
                .build();
        List<Option> optionList = Arrays.asList(vmFolderOption,
                datacenterOption, clusterOption);

        super.parseArgs(optionList, args);
        this.vmFolderName = (String) parsedOptions.get("vmfolder");
        this.datacenterName = (String) parsedOptions.get("datacenter");
        this.clusterName = (String) parsedOptions.get("cluster");
    }

    protected void setup() throws Exception {
        this.vmService =
                vapiAuthHelper.getStubFactory()
                    .createStub(VM.class, sessionStubConfig);
    }

    protected void run() throws Exception {
        Builder bldr = new Builder();
        if(null != this.datacenterName && !this.datacenterName.isEmpty()){
            bldr.setDatacenters(Collections.singleton(DatacenterHelper.
                  getDatacenter(this.vapiAuthHelper.getStubFactory(),
                          this.sessionStubConfig, this.datacenterName)));
        }
        if(null != this.clusterName && !this.clusterName.isEmpty()) {
            bldr.setClusters(Collections.singleton(ClusterHelper.getCluster(
                  this.vapiAuthHelper.getStubFactory(), sessionStubConfig,
                  this.clusterName)));
        }
        if(null != this.vmFolderName && !this.vmFolderName.isEmpty())
        {
            bldr.setFolders(Collections.singleton(FolderHelper.getFolder(
                  this.vapiAuthHelper.getStubFactory(), sessionStubConfig,
                  this.vmFolderName)));
        }
        List<Summary> vmList = this.vmService.list(bldr.build());
        for (Summary vmSummary : vmList) {
            //System.out.println(vmSummary._getDynamicField("backup"));
            //System.out.println(vmSummary._getDataValue().getFields());
            System.out.println("**********");
            machines.add(new MachinVirtuel(vmSummary.getName(), null, vmSummary.getPowerState(), null));
        }
    }
    protected void cleanup() throws Exception {
    	// No cleanup required
    }

    public static void main(String[] args, List<MachinVirtuel> vms) throws Exception {
        /*
         * Execute the sample using the command line arguments or parameters
         * from the configuration file. This executes the following steps:
         * 1. Parse the arguments required by the sample
         * 2. Login to the server
         * 3. Setup any resources required by the sample run
         * 4. Run the sample
         * 5. Cleanup any data created by the sample run, if cleanup=true
         * 6. Logout of the server
         */
        machines = vms;
        new ListVMs().execute(args);
    }
}
