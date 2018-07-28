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
public class CollectionController {
	
	@Autowired
	private CollectionService collectionService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private MyUserService myUserService;
	
	@InitBinder("collection")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(MyUser.class, new MyUserEditor(myUserService));
		binder.registerCustomEditor(Album.class, new AlbumEditor(albumService));
		binder.setValidator(new CollectionValidator());
	}
	
	@ModelAttribute("collection")
	public CollectionForm getForm(){
		return new CollectionForm();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("admin/collection")
	public String showCollections(Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("collections", collectionService.findAll(pageable));
		model.addAttribute("albums", albumService.findAll());
		model.addAttribute("users", myUserService.findAll());
		return "adminCollection";
	}
	
	@RequestMapping(value="admin/collection", method=RequestMethod.POST)
	public String save(@ModelAttribute ("collection") @Valid CollectionForm form, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("collections", collectionService.findAll(pageable));
			model.addAttribute("albums", albumService.findAll());
			model.addAttribute("users", myUserService.findAll());
			return "adminCollection";
		}
		collectionService.save(form);
		return "redirect:/admin/collection";
	}
	
	@RequestMapping(value="admin/collection/delete/{id}")
	public String delete(@PathVariable int id){
		collectionService.delete(id);
		return "redirect:/admin/collection";
	}
	
	@RequestMapping(value="admin/collection/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("collection", collectionService.findOneForm(id));
		model.addAttribute("collections", collectionService.findAll(pageable));
		model.addAttribute("albums", albumService.findAll());
		model.addAttribute("users", myUserService.findAll());
		return "adminCollection";
	}
}
