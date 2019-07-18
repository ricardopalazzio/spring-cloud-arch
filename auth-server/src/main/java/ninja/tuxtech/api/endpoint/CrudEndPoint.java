package ninja.tuxtech.api.endpoint;

import org.hibernate.dialect.lock.PessimisticEntityLockException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

public abstract class CrudEndPoint<T , ID extends Serializable> {


    PagingAndSortingRepository<T,ID> repository;

    public CrudEndPoint(PagingAndSortingRepository<T, ID> repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> all(Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") ID id){
        return ResponseEntity.ok(repository.findById(id));
    }


    @PostMapping @Transactional ResponseEntity<?> save(@RequestBody  @Valid  T t) {
        try {
            return ResponseEntity.status( HttpStatus.CREATED).body( repository.save(t));
        }catch (DuplicateKeyException duplicateKeyException) {
            throw duplicateKeyException;
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw dataIntegrityViolationException;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping @Transactional ResponseEntity<?> update(@RequestBody  @Valid  T t) {
        try {
            return ResponseEntity.status( HttpStatus.OK).body(  repository.save(t));
        }catch (PessimisticEntityLockException p){
            throw p;
        }catch (DuplicateKeyException duplicateKeyException) {
            throw duplicateKeyException;
        }catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw dataIntegrityViolationException;
        }catch (ObjectOptimisticLockingFailureException objectOptimisticLockingFailureException){
            throw objectOptimisticLockingFailureException;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}") @Transactional ResponseEntity<?> delete(@PathVariable("id") ID id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (PessimisticEntityLockException p){
            throw p;
        }catch (DuplicateKeyException duplicateKeyException) {
            throw duplicateKeyException;
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw dataIntegrityViolationException;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
