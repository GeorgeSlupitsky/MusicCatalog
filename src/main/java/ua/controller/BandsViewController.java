package ua.controller;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Album;
import ua.entity.Band;
import ua.entity.Collection;
import ua.entity.MyUser;
import ua.form.BandsViewFilter;
import ua.service.AlbumService;
import ua.service.BandService;
import ua.service.CollectionService;
import ua.service.MyUserService;
import ua.service.implementation.editor.AlbumEditor;
import ua.service.implementation.editor.BandEditor;
import ua.service.implementation.editor.CollectionEditor;
import ua.service.implementation.editor.MyUserEditor;

@Controller
public class BandsViewController {
	
	@Autowired
	private MyUserService service;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private BandService bandService;
	
	@Autowired
	private CollectionService collectionService;
	
	@InitBinder("band")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(MyUser.class, new MyUserEditor(service));
		binder.registerCustomEditor(Band.class, new BandEditor(bandService));
		binder.registerCustomEditor(Album.class, new AlbumEditor(albumService));
		binder.registerCustomEditor(Collection.class, new CollectionEditor(collectionService));
	}
	
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/bands")
	public String showBands(Locale locale, Model model, Principal principal, @PageableDefault(size=18, sort="name") Pageable pageable, @ModelAttribute("filter") BandsViewFilter bandsViewFilter){
		int idPrincipal= Integer.parseInt(principal.getName());
		model.addAttribute("user", service.findOneCollectionInited(idPrincipal));
		model.addAttribute("bands", bandService.findAll(pageable, bandsViewFilter));
		model.addAttribute("albums", albumService.findAll());
		model.addAttribute("collections", collectionService.findAll());
		model.addAttribute("filter", bandsViewFilter);
		return "bands";
	}
	
	
	@RequestMapping("/bands/add/{colId}/{id}")
	public String addToCollection(@PathVariable int colId, @PathVariable int id){
		if(albumService.addAlbumToCollection(colId, id) == true){
			return "redirect:/bands";
		}else {
			return "redirect:/myCollection";
		}
	}
}
