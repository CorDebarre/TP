package com.controller;

import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.dao.VilleFranceDAO;
import com.dto.VilleFrance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class VilleController {
	
	ArrayList<VilleFrance> listeVille ;
	VilleFranceDAO villeDAO = new VilleFranceDAO();
	
	@RequestMapping(value="/ville",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<VilleFrance> get(@RequestParam(required = false,value = "filtre") String filtre, @RequestParam(required = false,value = "value") String value) {
		//log.info("Appel GET");
		
		if (filtre==null) {
			listeVille=villeDAO.listeVillesFrance();
		}else {
		
		
		
		listeVille = villeDAO.trouver(filtre,value);
		
		}
		
		return listeVille;
	}
	
	@RequestMapping(value="/insertVille",method=RequestMethod.POST)
	@ResponseBody
	public String insert (@RequestBody VilleFrance ville) {
		VilleFranceDAO villefrancedao = new VilleFranceDAO();
		
		villefrancedao.creationVilleFrance(ville);
		
		return "La ville a été créée";
	}
	
	@RequestMapping(value="/updateVille",method=RequestMethod.PUT)
	@ResponseBody
	public String update (@RequestBody VilleFrance ville, @RequestParam (required = true,value = "code_commune") String code_commune) {
		VilleFranceDAO villefrancedao = new VilleFranceDAO();
		
		villefrancedao.modifier(ville, code_commune);
		
		return "La ville a été modifiée";
	}
	
	@RequestMapping(value="/deleteVille",method=RequestMethod.DELETE)
	@ResponseBody
	public String delete (@RequestParam (required = true,value = "code_commune") String code_commune) {
		VilleFranceDAO villefrancedao = new VilleFranceDAO();
		
		villefrancedao.supprimer(code_commune);
		
		return "La ville a été supprimée";
	}
	
}
