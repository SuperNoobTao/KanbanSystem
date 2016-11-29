package cc.superliar.domain;


import cc.superliar.component.Transformer;
import cc.superliar.enums.ErrorType;
import cc.superliar.exception.CommonsException;
import cc.superliar.param.LogParam;
import cc.superliar.po.Log;
import cc.superliar.po.User;
import cc.superliar.repo.LogRepository;
import cc.superliar.util.ErrorMsgHelper;
import cc.superliar.vo.LogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Domain of {@link Log}
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 10/28/15
 * @since JDK1.8
 */
@Service
@Transactional(readOnly = true)
public class LogDomain {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Create new {@link Log}.
   *
   * @param currentUser current user
   * @param param       {@link LogParam}
   * @return {@link LogVO}
   */
  @Transactional
  public LogVO create(LogParam param, User currentUser) throws Exception {
    return transformer.po2VO(LogVO.class, logRepository.save(logParam2PO(param, currentUser)));
  }

  /**
   * Show all {@link LogVO}.
   *
   * @return {@link List<LogVO>}
   * @throws CommonsException {@link ErrorType#SYS0121} No group exists.
   */
  @SuppressWarnings("unchecked")
  public List<LogVO> getAllLogs() throws Exception {
    List<Log> logs = logRepository.findAll();
    if (logs.isEmpty()) {
      // Throw no log exist exception.
      throw new CommonsException(ErrorType.SYS0121, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0121, LOG, LOG));
    }
    return transformer.pos2VOs(LogVO.class, logs);
  }

  /**
   * Show {@link LogVO} of {@link Page}.
   *
   * @param pageable {@link Pageable}
   * @return {@link Page}, {@link LogVO}
   * @throws CommonsException {@link ErrorType#SYS0121} No group exists.
   */
  public Page getPage(Pageable pageable) throws Exception {
    Page<Log> logPage = logRepository.findAll(pageable);
    if (!logPage.hasContent()) {
      // Throw no log exist exception.
      throw new CommonsException(ErrorType.SYS0121, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0121, LOG, LOG));
    }
    return transformer.poPage2VO(transformer.pos2VOs(LogVO.class, logPage.getContent()), pageable, logPage.getTotalElements());
  }

  // --------------------------
  // PRIVATE FIELDS AND METHODS
  // --------------------------

  @Autowired
  private Transformer transformer;

  @Autowired
  private LogRepository logRepository;

  private final static String LOG = "log";

  /**
   * Transform {@link LogParam} to {@link Log}.
   *
   * @param param {@link LogParam}
   * @return {@link Log}
   */
  private Log logParam2PO(LogParam param, User currentUser) {
    Log log = new Log();
    BeanUtils.copyProperties(param, log);
    log.setUserId((long) currentUser.getId());
    log.setUsername(currentUser.getAccount());
    return log;
  }
}
