package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.News;
import ua.form.NewsFilter;
import ua.form.NewsForm;

public interface NewsService {

	News addNews (News name);
	
	void save (NewsForm form);
	
	News findByName (String name);
	
	List<News> findAll();
	
	void delete (int id);

	NewsForm findOneForm(int id);
	
	News findOne(int id);

	Page<News> findAll(Pageable pageable);

	Page<News> findAll(Pageable pageable, NewsFilter newsFilter);
}
