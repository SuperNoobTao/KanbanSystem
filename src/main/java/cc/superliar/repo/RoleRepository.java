package cc.superliar.repo;

import cc.superliar.enums.ValidFlag;
import cc.superliar.po.Role;
import cc.superliar.po.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by shentao on 2016/11/29.
 */
@Repository
public interface RoleRepository extends CustomRepository<Role, Long> {
    Optional<Role> findByNameAndValidFlag(String name, ValidFlag validFlag);
}
