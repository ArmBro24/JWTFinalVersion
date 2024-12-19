package com.example.demo.repository;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsById(Long id);


    @Query("SELECT new com.example.demo.DTO.UserDTO(u.id, u.name, u.email, u.password, u.role) FROM User u WHERE u.name = :name")
    UserDTO findByName(String name);

    @Query("SELECT new com.example.demo.DTO.UserDTO(u.id, u.name, u.email, u.password, u.role) FROM User u WHERE u.id = :id")
    UserDTO findDTOById(Long id);

    void deleteById(Long id);
}
