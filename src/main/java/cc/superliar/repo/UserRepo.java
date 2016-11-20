//package cc.superliar.repo;
//
//import cc.superliar.enums.ValidFlag;
//import cc.superliar.po.User;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//import java.util.Optional;
//
///**
// * Created by shentao on 2016/11/8.
// */
//@Repository
//public interface UserRepo extends CustomRepository<User, Integer> {
//
//
//    Optional<User> findByAccountAndValidFlag(String usr, ValidFlag validFlag);
//}
