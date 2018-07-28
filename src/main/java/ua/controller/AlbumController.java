package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Band;
import ua.entity.Collection;
import ua.entity.Country;
import ua.entity.Genre;
import ua.entity.Label;
import ua.entity.TypeOfAlbum;
import ua.entity.TypeOfRecord;
import ua.form.AlbumFilter;
import ua.form.AlbumForm;
import ua.service.AlbumService;
import ua.service.BandService;
import ua.service.CollectionService;
import ua.service.CountryService;
import ua.service.GenreService;
import ua.service.LabelService;
import ua.service.TypeOfAlbumService;
import ua.service.TypeOfRecordService;
import ua.service.implementation.editor.BandEditor;
import ua.service.implementation.editor.CollectionEditor;
import ua.service.implementation.editor.CountryEditor;
import ua.service.implementation.editor.GenreEditor;
import ua.service.implementation.editor.LabelEditor;
import ua.service.implementation.editor.TypeOfAlbumEditor;
import ua.service.implementation.editor.TypeOfRecordEditor;
import ua.service.implementation.validator.AlbumValidator;

@Controller
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private TypeOfAlbumService typeOfAlbumService;
	
	@Autowired
	private TypeOfRecordService typeOfRecordService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private LabelService labelService;
	
	@Autowired
	private BandService bandService;
	
	@Autowired
	private CollectionService collectionService;
	
	@InitBinder("album")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Genre.class, new GenreEditor(genreService));
		binder.registerCustomEditor(TypeOfAlbum.class, new TypeOfAlbumEditor(typeOfAlbumService));
		binder.registerCustomEditor(TypeOfRecord.class, new TypeOfRecordEditor(typeOfRecordService));
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.registerCustomEditor(Label.class, new LabelEditor(labelService));
		binder.registerCustomEditor(Band.class, new BandEditor(bandService));
		binder.registerCustomEditor(Collection.class, new CollectionEditor(collectionService));
		binder.setValidator(new AlbumValidator());
	}
	
	@ModelAttribute("album")
	public AlbumForm getForm(){
		return new AlbumForm();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("admin/album")
	public String showAlbums(Model model, @PageableDefault(size=5) Pageable pageable, @ModelAttribute("filter") AlbumFilter albumFilter){
		model.addAttribute("albums", albumService.findAll(pageable, albumFilter));
		model.addAttribute("genres", genreService.findAll());
		model.addAttribute("typeOfAlbums", typeOfAlbumService.findAll());
		model.addAttribute("typeOfRecords", typeOfRecordService.findAll());
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("labels", labelService.findAll());
		model.addAttribute("bands", bandService.findAll());
		model.addAttribute("collections", collectionService.findAll());
		model.addAttribute("filter", albumFilter);
		return "adminAlbum";
	}
	
	@RequestMapping(value="admin/album", method=RequestMethod.POST)
	public String save(@ModelAttribute ("album") @Valid AlbumForm form, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("albums", albumService.findAll(pageable));
			model.addAttribute("genres", genreService.findAll());
			model.addAttribute("typeOfAlbums", typeOfAlbumService.findAll());
			model.addAttribute("typeOfRecords", typeOfRecordService.findAll());
			model.addAttribute("countries", countryService.findAll());
			model.addAttribute("labels", labelService.findAll());
			model.addAttribute("bands", bandService.findAll());
			model.addAttribute("collections", collectionService.findAll());
			return "adminAlbum";
		}
		albumService.save(form);
		return "redirect:/admin/album";
	}
	
	@RequestMapping(value="admin/album/delete/{id}")
	public String delete(@PathVariable int id){
		albumService.delete(id);
		return "redirect:/admin/album";
	}
	
	@RequestMapping(value="admin/album/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("album", albumService.findOneForm(id));
		model.addAttribute("albums", albumService.findAll(pageable));
		model.addAttribute("genres", genreService.findAll());
		model.addAttribute("typeOfAlbums", typeOfAlbumService.findAll());
		model.addAttribute("typeOfRecords", typeOfRecordService.findAll());
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("labels", labelService.findAll());
		model.addAttribute("bands", bandService.findAll());
		model.addAttribute("collections", collectionService.findAll());
		return "adminAlbum";
	}
}
