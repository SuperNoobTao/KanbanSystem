package cc.superliar.domain;


import cc.superliar.component.LogHelper;
import cc.superliar.component.Transformer;
import cc.superliar.constant.CommonsConstant;
import cc.superliar.enums.ErrorType;
import cc.superliar.enums.OperationType;
import cc.superliar.enums.ValidFlag;
import cc.superliar.exception.CommonsException;
import cc.superliar.po.User;
import cc.superliar.repo.CustomRepository;
import cc.superliar.util.BeanUtils;
import cc.superliar.util.ErrorMsgHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Abstract base service implement.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 7/21/15
 * @since JDK1.8
 */
public abstract class BaseDomain<T, ID extends Serializable> {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Create <T> by param. 先通过参数组成一个实体类然后进行操作
   *
   * @param voType      VO of some class
   * @param inputParam  input param
   * @param currentUser current user
   * @param <VO>        VO extends to ResultVO
   * @return VO
   * @throws Exception
   */
  @Transactional
  public <VO> VO create(Class<VO> voType, Object inputParam, User currentUser) throws Exception {
    T po = transformer.param2PO(getClassT(), inputParam, getClassT().newInstance(), currentUser);
    return createByPO(voType, po, currentUser);
  }

  /**
   * Create <T> by PO.
   *
   * @param voType      VO of some class
   * @param inputPO     input PO
   * @param currentUser current user
   * @param <VO>        VO extends to ResultVO
   * @return VO
   * @throws Exception
   */
  @Transactional
  public <VO> VO createByPO(Class<VO> voType, T inputPO, User currentUser) throws Exception {
    return transformer.po2VO(voType, createByPO(inputPO, currentUser));
  }

  @Transactional
  public T createByPO(T inputPO, User currentUser) throws Exception {
    logHelper.logUsersOperations(OperationType.CREATE, getClassT().getSimpleName(), currentUser);
    return repository.save(inputPO);
  }

  /**
   * Get all <T>.
   *
   * @return <T>s
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public <VO> List<VO> getAll(Specification<T> specification, Sort sort, Class<VO> voType) throws Exception {
    List pos = repository.findAll(specification, sort);
    if (pos.isEmpty()) {
      // Throw po cannot find exception.
      throw new CommonsException(ErrorType.SYS0121, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0121, getClassT().getSimpleName(), getClassT().getSimpleName()));
    }
    return transformer.pos2VOs(voType, pos);
  }

  /**
   * Get page of <T>.
   *
   * @param specification {@link Specification}
   * @param pageable      pageable
   * @param voType        VO of some class
   * @return page of <T>
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public Page getPage(Specification<T> specification, Pageable pageable, Class voType) throws Exception {
    Page<T> poPage = repository.findAll(specification, pageable);
    if (poPage.getSize() == 0) {
      // Throw po cannot find exception.
      throw new CommonsException(ErrorType.SYS0121, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0121, getClassT().getSimpleName(), getClassT().getSimpleName()));
    }
    return transformer.poPage2VO(transformer.pos2VOs(voType, poPage.getContent()),
        pageable, poPage.getTotalElements());
  }

  /**
   * Get page of <T>.
   *
   * @param ids ids
   * @return page of <T>
   * @throws Exception
   */
  public List<T> getAllByIds(List<ID> ids) throws Exception {
    return repository.findAll(ids);
  }

  /**
   * Get <T> by id.
   *
   * @param id     id
   * @param voType VO of some class
   * @param <VO>   VO extends to ResultVO
   * @return <T>
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public <VO> VO getById(Long id, Class<VO> voType) throws Exception {
    return transformer.po2VO(voType, findById(id));
  }

  /**
   * Update <T> by param.
   *
   * @param voType      VO of some class
   * @param inputParam  input param
   * @param currentUser current user
   * @param <VO>        VO extends to ResultVO
   * @return VO
   * @throws Exception
   */
  @Transactional
  public <VO> VO update(Class<VO> voType, Object inputParam, User currentUser) throws Exception {
    T po = findByIdParam(inputParam);
    BeanUtils.copyPropertiesIgnoreNull(inputParam, po);
    return updateByPO(voType, po, currentUser);
  }

  /**
   * Update <T> by param.
   *
   * @param voType      VO of some class
   * @param inputPO     input PO
   * @param currentUser current user
   * @param <VO>        VO extends to ResultVO
   * @return VO
   * @throws Exception
   */
  @Transactional
  public <VO> VO updateByPO(Class<VO> voType, T inputPO, User currentUser) throws Exception {
    return transformer.po2VO(voType, updateByPO(inputPO, currentUser));
  }


  /**
   * Update <T> by param.
   *
   * @param po     input PO
   * @param currentUser current user
   * @return VO
   * @throws Exception
   */
  @Transactional
  public T updateByPO(T po, User currentUser) throws Exception {
    logHelper.logUsersOperations(OperationType.UPDATE, getClassT().getSimpleName(), currentUser);
//    Field lastModifiedByField = po.getClass().getDeclaredField(CommonsConstant.LAST_MODIFIED_BY);
//    lastModifiedByField.setAccessible(true);
//    lastModifiedByField.set(po, currentUser.getId());
    Field lastModifiedDateField = po.getClass().getDeclaredField(CommonsConstant.LAST_MODIFIED_DATE);
    lastModifiedDateField.setAccessible(true);
    lastModifiedDateField.set(po, new Date());
    return repository.save(po);
  }


  /**
   * Delete <T>, update valid flag to invalid.
   *
   * @param inputParam  input param
   * @param currentUser current user
   * @throws Exception
   */
  @Transactional
  public void delete(Object inputParam, User currentUser) throws Exception {
    T po = findByIdParam(inputParam);
    BeanUtils.copyPropertiesIgnoreNull(inputParam, po);
//    Field lastModifiedByField = po.getClass().getDeclaredField(CommonsConstant.LAST_MODIFIED_BY);
//    lastModifiedByField.setAccessible(true);
//    lastModifiedByField.set(po, currentUser.getId());
    Field lastModifiedDateField = po.getClass().getDeclaredField(CommonsConstant.LAST_MODIFIED_DATE);
    lastModifiedDateField.setAccessible(true);
    lastModifiedDateField.set(po, new Date());
    logHelper.logUsersOperations(OperationType.DELETE, getClassT().getName(), currentUser);
    repository.save(setInvalid(po));
  }

  /**
   * update valid flag to invalid.by id
   * @param id
   * @param currentUser
   * @throws Exception
   */
  @Transactional
  public void deleteById(String id, User currentUser) throws Exception {
    logHelper.logUsersOperations(OperationType.DELETE, getClassT().getName(), currentUser);
    T po = findById(Long.valueOf(id));
//    Field lastModifiedByField = po.getClass().getDeclaredField(CommonsConstant.LAST_MODIFIED_BY);
//    lastModifiedByField.setAccessible(true);
//    lastModifiedByField.set(po, currentUser);
    Field lastModifiedDateField = po.getClass().getDeclaredField(CommonsConstant.LAST_MODIFIED_DATE);
    lastModifiedDateField.setAccessible(true);
    lastModifiedDateField.set(po, new Date());
    repository.save(setInvalid(po));
  }

  /**
   * update valid flag to invalid.by ids
   * @param ids
   * @param currentUser
   * @throws Exception
   */
  @Transactional
  public void deleteByIds(String ids, User currentUser) throws Exception  {
    transformer.idsStr2List(ids).forEach(id -> {
      try {
        this.deleteById(id.toString(), currentUser);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Find class by id.
   *
   * @param inputParam id
   * @return class
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public T findByIdParam(Object inputParam) throws Exception {
    Field idField = inputParam.getClass().getDeclaredField(CommonsConstant.ID);
    idField.setAccessible(true);
    String className = getClassT().getSimpleName();
    return repository.findById((ID) idField.get(inputParam)).orElseThrow(
        () -> new CommonsException(ErrorType.SYS0122, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0122, className, CommonsConstant.ID)));
  }

  @SuppressWarnings("unchecked")
  public T findById(Long id) throws Exception {
    String className = getClassT().getSimpleName();
    return repository.findById((ID) id).orElseThrow(
        () -> new CommonsException(ErrorType.SYS0122, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0122, className, CommonsConstant.ID)));
  }

  // --------------------------
  // PRIVATE FIELDS AND METHODS
  // --------------------------

  @Autowired
  private CustomRepository<T, ID> repository;

  @Autowired
  protected LogHelper logHelper;

  @Autowired
  private Transformer transformer;


  /**
   * Get class of <T>
   *
   * @return class of <T>
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  protected Class<T> getClassT() throws Exception {
    Type type = getClass().getGenericSuperclass();
    return (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
  }

  /**
   * Set invalid flag
   *
   * @param po po
   * @return po with invalid flag
   * @throws Exception
   */
  private T setInvalid(T po) throws Exception {
    Field validFlagField = po.getClass().getDeclaredField(CommonsConstant.VALID_FLAG);
    validFlagField.setAccessible(true);
    validFlagField.set(po, ValidFlag.INVALID);
    return po;
  }
}
