package ninja.tuxtech.api.endpoint;

import org.hibernate.dialect.lock.PessimisticEntityLockException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

public abstract class CrudEndPoint<T , ID extends Serializable> {


    JpaRepository<T,ID> repository;

    public CrudEndPoint(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") ID id){
        return ResponseEntity.ok(repository.getOne(id));
    }


    @PostMapping @Transactional ResponseEntity<?> save(@RequestBody  @Valid  T t) {
        try {
            return ResponseEntity.ok(repository.save(t));
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
            return ResponseEntity.ok(repository.save(t));
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
