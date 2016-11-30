//package cc.superliar.param;
//
//import cc.superliar.annotation.NotNullField;
//import cc.superliar.enums.OperationType;
//
//import javax.validation.constraints.Size;
//
///**
// * Created by shentao on 2016/11/28.
// */
//public class DeviceParam extends BaseParam{
//
//    private static final long serialVersionUID = -9153801716112918626L;
//
//    @NotNullField(value = {OperationType.CREATE,OperationType.UPDATE, OperationType.DELETE}, message = "udid cannot be null.")
//    private String udid; // device's udid
//
//    @NotNullField(value = OperationType.CREATE, message = "name cannot be null.")
//    @Size(min = 4, max = 50)
//    private String name; // name
//
//    @NotNullField(value = OperationType.CREATE, message = "location cannot be null.")
//    @Size(min = 4, max = 50)
//    private String location; // location
//
//    @NotNullField(value = OperationType.CREATE, message = "size cannot be null.")
//    @Size(min = 4, max = 50)
//    private String size; // size
//
//    @NotNullField(value = OperationType.CREATE, message = "num cannot be null.")
//    @Size(min = 4, max = 50)
//    private String num; // num
//
//    private String urlIds; // url ids string
//
//    private String description;
//
//    public DeviceParam() {}
//
//    public DeviceParam(String udid) {
//        this.udid = udid;
//    }
//
//    public String getUdid() {
//        return udid;
//    }
//
//    public void setUdid(String udid) {
//        this.udid = udid;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }
//
//    public String getNum() {
//        return num;
//    }
//
//    public void setNum(String num) {
//        this.num = num;
//    }
//
//    public String getUrlIds() {
//        return urlIds;
//    }
//
//    public void setUrlIds(String urlIds) {
//        this.urlIds = urlIds;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}
