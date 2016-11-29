//package cc.superliar.domain;
//
//import cc.superliar.component.Transformer;
//import cc.superliar.param.DeviceParam;
//import cc.superliar.po.Device;
//import cc.superliar.po.Url;
//import cc.superliar.po.User;
//import cc.superliar.repo.DeviceRepo;
//import cc.superliar.vo.DeviceVO;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * Created by shentao on 2016/11/29.
// */
//@Service
//@Transactional(readOnly = true)
//public class DeviceDomain  extends BaseDomain<Device, String> {
//
//    // ------------------------
//    // PUBLIC METHODS
//    // ------------------------
//
//    /**
//     * Create new {@link User}.
//     *
//     * @param currentUser current user
//     * @param param       {@link DeviceParam}
//     * @return {@link DeviceVO}
//     * @throws CommonsException {@link ErrorType#SYS0111} user already existing, usr taken.
//     */
//    @Transactional
//    public DeviceVO create(DeviceParam param, User currentUser) throws Exception {
//        usrExists(param.getUsr());
//        return super.createByPO(UserVO.class, userParam2PO(param, new User(), currentUser), currentUser);
//    }
//
//
//    // --------------------------
//    // PRIVATE FIELDS AND METHODS
//    // --------------------------
//
//    @Autowired private DeviceRepo deviceRepository;
//
//    @Autowired private Transformer transformer;
//
//    @Autowired
//    public DeviceDomain(DeviceRepo deviceRepository) {
//        this.deviceRepository = deviceRepository;
//    }
//
//    private static final String DEVICE = "device_id";
//
//    /**
//     * Transform {@link UserParam} to {@link User}.
//     *
//     * @param param       {@link UserParam}
//     * @param user        {@link User}
//     * @param currentUser currentUser
//     * @return {@link User}
//     */
//    private User deviceParam2PO(DeviceParam param, Device device, User currentUser) throws Exception {
//        transformer.param2PO(getClassT(), param, device, currentUser);
//        if (!StringUtils.isBlank(param.getUrlIds())) {
//            List<Url> urls = roleDomain.getAllByIds(transformer.idsStr2List(param.getRoleIds()));
//            user.setRoles(transformer.list2Set(roles));
//        }
//        if (!StringUtils.isBlank(param.getPwd())) {
//            user.setPwd(passwordEncoder.encode(param.getPwd()));
//        }
//        return user;
//    }
//
//    private void usrExists(String usr) throws Exception {
//        if (userRepository.findByUsrAndValidFlag(usr, ValidFlag.VALID).isPresent()) {
//            // Throw user already exists error, usr taken.
//            throw new CommonsException(ErrorType.SYS0111, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0111, ResourceConstant.USERS, USR));
//        }
//    }
//
//}
