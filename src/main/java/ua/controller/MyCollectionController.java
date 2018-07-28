package ua.controller;

import java.security.Principal;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ua.entity.Album;
import ua.entity.MyUser;
import ua.form.CollectionForm;
import ua.service.AlbumService;
import ua.service.CollectionService;
import ua.service.MyUserService;
import ua.service.implementation.editor.AlbumEditor;
import ua.service.implementation.editor.MyUserEditor;
import ua.service.implementation.validator.CollectionValidator;

@Controller
public class MyCollectionController {

	@Autowired
	private MyUserService service;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private CollectionService collectionService;
	
	@InitBinder("collection")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(MyUser.class, new MyUserEditor(service));
		binder.registerCustomEditor(Album.class, new AlbumEditor(albumService));
		binder.setValidator(new CollectionValidator());
	}
	
	@ModelAttribute("collection")
	public CollectionForm getForm(){
		return new CollectionForm();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("myCollection")
	public String showMyCollection(Locale locale, Principal principal, Model model){
		int idPrincipal= Integer.parseInt(principal.getName());
		model.addAttribute("user", service.findOneCollectionInited(idPrincipal));
		model.addAttribute("collections", collectionService.findAll());
		model.addAttribute("albums", albumService.findAll());
		return "myCollection";
	}
	
	@RequestMapping("/myCollection/delete/{id}")
	public String deleteCollection(@PathVariable int id){
		collectionService.delete(id);
		return "redirect:/myCollection";
	}
	
	@RequestMapping(value="myCollection", method=RequestMethod.POST)
	public String saveMyCollection(@ModelAttribute ("collection") @Valid CollectionForm form, BindingResult br, Model model, MyUser user, Principal principal){
		int idPrincipal = Integer.parseInt(principal.getName());
		if (br.hasErrors()){
			model.addAttribute("user", service.findOneCollectionInited(idPrincipal));
			model.addAttribute("collections", collectionService.findAll());
			model.addAttribute("albums", albumService.findAll());
			return "myCollection";
		}
		user.setId(idPrincipal);
		form.setUser(user);
		collectionService.save(form);
		return "redirect:/myCollection";
	}
	
	@RequestMapping("/myCollection/delete/{id}/{colId}")
	public String removeAlbumFromMyCollection(@PathVariable int id, @PathVariable int colId){
		albumService.removeAlbumFromCollection(colId, id);
		return "redirect:/myCollection";
	}
}
