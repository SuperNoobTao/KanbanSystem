package cc.superliar.domain;

import cc.superliar.po.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shentao on 2016/11/29.
 */
@Service
@Transactional(readOnly = true)
public class RoleDomain extends BaseDomain<Role,Long>{



}
