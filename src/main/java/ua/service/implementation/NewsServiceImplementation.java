package ua.service.implementation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.News;
import ua.form.NewsFilter;
import ua.form.NewsForm;
import ua.repository.NewsRepository;
import ua.service.NewsService;
import ua.service.implementation.specification.NewsFilterSpecification;

@Service
@Transactional
public class NewsServiceImplementation implements NewsService{

	@Autowired
	NewsRepository newsRepository;
	
	@Override
	public News addNews(News name) {
		return newsRepository.save(name);
	}

	@Override
	@Transactional
	public void save(NewsForm form) {
		News news = new News();
		news.setId(form.getId());
		news.setName(form.getName());
		news.setNameRU(form.getNameRU());
		news.setNameUA(form.getNameUA());
		news.setPath(form.getPath());
		news.setVersion(form.getVersion());
		news.setContent(form.getContent());
		news.setContentRU(form.getContentRU());
		news.setContentUA(form.getContentUA());
		newsRepository.saveAndFlush(news);
		if(form.getFile()!=null && !form.getFile().isEmpty()){
			int index = form.getFile().getOriginalFilename().lastIndexOf(".");
			String extention = form.getFile().getOriginalFilename().substring(index);
			String path = System.getProperty("catalina.home")+"/images/news/";
			File file = new File(path);
			if(!file.exists())file.mkdirs();
			file = new File(file, news.getId()+extention);
			try {
				form.getFile().transferTo(file);
			} catch (IllegalStateException | IOException e) {
			}
			news.setPath(extention);
			news.setVersion(form.getVersion()+1);
			newsRepository.save(news);
		}
	}

	@Override
	public News findByName(String name) {
		return newsRepository.findByName(name);
	}

	@Override
	public List<News> findAll() {
		return newsRepository.findAll();
	}

	@Override
	public void delete(int id) {
		newsRepository.delete(id);
	}

	@Override
	public NewsForm findOneForm(int id) {
		News news = newsRepository.findOne(id);
		NewsForm form = new NewsForm();
		form.setId(news.getId());
		form.setName(news.getName());
		form.setNameRU(news.getNameRU());
		form.setNameUA(news.getNameUA());
		form.setContent(news.getContent());
		form.setContentRU(news.getContentRU());
		form.setContentUA(news.getContentUA());
		form.setPath(news.getPath());
		form.setVersion(news.getVersion());
		return form;
	}

	@Override
	public News findOne(int id) {
		return newsRepository.findOne(id);
	}

	@Override
	public Page<News> findAll(Pageable pageable) {
		return newsRepository.findAll(pageable);
	}

	@Override
	public Page<News> findAll(Pageable pageable, NewsFilter newsFilter) {
		return newsRepository.findAll(new NewsFilterSpecification(newsFilter), pageable);
	}

}
