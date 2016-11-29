package cc.superliar.domain;

import cc.superliar.component.Transformer;
import cc.superliar.constant.CommonsConstant;
import cc.superliar.constant.ResourceConstant;
import cc.superliar.enums.ErrorType;
import cc.superliar.enums.ValidFlag;
import cc.superliar.exception.CommonsException;
import cc.superliar.param.ResourceParam;
import cc.superliar.po.Resource;
import cc.superliar.po.Role;
import cc.superliar.po.User;
import cc.superliar.repo.ResourceRepo;
import cc.superliar.util.ErrorMsgHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shentao on 2016/11/29.
 */
@Service
@Transactional(readOnly = true)
public class ResourceDomain extends BaseDomain<Resource,Long> {



    // --------------------------
    // PRIVATE FIELDS AND METHODS
    // --------------------------

    @Autowired
    private RoleDomain roleDomain;

    @Autowired private ResourceRepo resourceRepository;

    @Autowired private Transformer transformer;

    private static final String PATH = "path";

    /**
     * Transform {@link ResourceParam} to {@link Resource}.
     *
     * @param param       {@link ResourceParam}
     * @param resource    {@link Resource
     * @param currentUser currentUser
     * @return {@link Resource
     */
    private Resource resourceParam2PO(ResourceParam param, Resource resource, User currentUser) throws Exception {
        transformer.param2PO(getClassT(), param, resource, currentUser);
        if (!StringUtils.isBlank(param.getRoleIds())) {
            List<Role> roles = roleDomain.getAllByIds(transformer.idsStr2List(param.getRoleIds()));
            resource.setRoles(transformer.list2Set(roles));
        }
        return resource;
    }

    private Resource findByPath(String path) throws Exception {
        return resourceRepository.findByNameAndValidFlag(path, ValidFlag.VALID).orElseThrow(
                () -> new CommonsException(ErrorType.SYS0122,
                        ErrorMsgHelper.getReturnMsg(ErrorType.SYS0122, ResourceConstant.RESOURCES, PATH)));
    }

    private void nameExists(String name) throws Exception {
        if (resourceRepository.findByNameAndValidFlag(name, ValidFlag.VALID).isPresent()) {
            // Throw group already existing exception, name taken.
            throw new CommonsException(ErrorType.SYS0111, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0111, getClassT().getSimpleName(), CommonsConstant.NAME));
        }
    }

}
