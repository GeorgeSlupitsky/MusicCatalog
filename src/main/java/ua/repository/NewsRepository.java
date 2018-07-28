package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News>{

	News findByName(String name);
}
