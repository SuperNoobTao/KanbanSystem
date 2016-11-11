package cc.superliar.repo;

import cc.superliar.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by shentao on 2016/11/8.
 */
@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer>,JpaSpecificationExecutor<Admin> {





    /**
     *
     * @param name
     * @return
     */
    @Query("select a from Admin a where a.name = ?1")
    Admin findByAccount(String name);


}
