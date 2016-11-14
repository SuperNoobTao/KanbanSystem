package cc.superliar.repo;

import cc.superliar.po.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by shentao on 2016/11/8.
 */
@Repository
public interface UserRepo extends CustomRepository<User, Integer> {


    /**
     * return the row you impacted
     * @param ID
     * @return
     */
    @Modifying
    @Transactional
    @Query("delete from Admin a  where a.name = ?1")
    User deleteById(Integer ID);


}
