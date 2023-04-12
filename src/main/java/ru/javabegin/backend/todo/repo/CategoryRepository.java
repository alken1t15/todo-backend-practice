package ru.javabegin.backend.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.entity.Category;

import java.util.List;


// Вы можете уже сразу использовать все методы CRUD (Create, Read, Update, Delete)
// принцип ООП: абстракция-реализация - здесь описываем все доступные способы доступа к данным
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUserEmailOrderByTitleAsc(String email);


    @Query("SELECT C FROM  Category  C WHERE (:title is null or :title='' or lower(C.title) like  lower(concat('%', :title,'%'))) and C.user.email=:email " +
            "order by C.title asc ")
    List<Category> findByTitle(@Param("title") String title, @Param("email") String email);


}