package cc.superliar.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Custom repository.
 * Extends the {@link Repository}
 * <p>
 * <p>
 * Hide the delete interface.
 * </p>
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 10/29/15
 * @since JDK1.8
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable>
    extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {

  /**
   * Saves a given po. Use the returned instance for further operations as the save operation might have changed the
   * po instance completely.
   *
   * @param entity PO
   * @return the saved po
   */
  <S extends T> S save(S entity);

  /**
   * Saves all given entities.
   *
   * @param entities POs
   * @return the saved entities
   * @throws IllegalArgumentException in case the given po is {@literal null}.
   */
  <S extends T> Iterable<S> save(Iterable<S> entities);

  /**
   * Retrieves an po by its id.
   *
   * @param id must not be {@literal null}.
   * @return the po with the given id or {@literal null} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}
   */
  Optional<T> findById(ID id);

  /**
   * Returns whether an po with the given id exists.
   *
   * @param id must not be {@literal null}.
   * @return true if an po with the given id exists, {@literal false} otherwise
   * @throws IllegalArgumentException if {@code id} is {@literal null}
   */
  boolean exists(ID id);

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  List<T> findAll();

  /**
   * Returns all instances of the type with the given IDs.
   *
   * @param ids PO's ids
   * @return POs
   */
  List<T> findAll(Iterable<ID> ids);

  /**
   * Returns all instances of the type with the given pageable.
   *
   * @param pageable pageable
   * @return page
   */
  Page<T> findAll(Pageable pageable);

  /**
   * Returns the number of entities available.
   *
   * @return the number of entities
   */
  long count();

}
