package ru.javabegin.backend.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.entity.Priority;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    // поиск значений по названию для конкретного пользователя
    @Query("SELECT p FROM Priority p where " +
            "(:title is null or :title='' " + // если передадим параметр title пустым, то вы
            " or lower(p.title) like lower(concat('%', :title,'%'))) " + // если параметр tit
            " and p.user.email=:email " + // фильтрация для конкретного пользователя
            "order by p.title asc")
    // сортировка по названию
    List<Priority> findByTitle(@Param("title") String title, @Param("email") String email);

    List<Priority> findByUserEmailOrderByIdAsc(String email);

}