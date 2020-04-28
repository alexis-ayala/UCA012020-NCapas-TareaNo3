package com.uca.capas.modelo.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TareaNo3Controller {
	
	@RequestMapping("/registrar")
	public ModelAndView registrar(
			@RequestParam String nombre, 
			@RequestParam String apellido,
			@RequestParam String fecha,
			@RequestParam String lugar,
			@RequestParam String instituto,
			@RequestParam String telefono,
			@RequestParam String movil
			) {
		ModelAndView mav = new ModelAndView();
				
		mav.addObject("nombre", isValid(nombre,1,25));
		mav.addObject("apellido", isValid(apellido,1,25));
		mav.addObject("fecha", IsValidDate(fecha));
		mav.addObject("lugar", isValid(lugar,1,25));
		mav.addObject("instituto", isValid(instituto,1,100));
		mav.addObject("telefono", isValid(telefono,1,8));
		mav.addObject("movil", isValid(movil,1,8));
		
		mav.addObject("correcto", 
				!((isValid(nombre,1,25)=="Correcto.") &&
				(isValid(apellido,1,25)=="Correcto.") &&
				(isValid(lugar,1,25)=="Correcto.") &&
				(isValid(instituto,1,100)=="Correcto.") &&
				(isValid(telefono,1,8)=="Correcto.") &&
				(isValid(movil,1,8)=="Correcto.") && 
				(IsValidDate(fecha)=="Correcto."))
				);
		
		
		mav.setViewName("commons/resultado");
		return mav;
	}
	@RequestMapping("/ingresar")
	public String ingresar() {
		return "commons/ingresar";
	}
	public String isValid(String txt, Integer mins, Integer maxs) {
		String str = "";
		if(txt.trim().length()<mins || txt.trim().length()>maxs) {
			if(txt.trim().length()<mins) {
				str += " - Error: campo requiere " + mins + " caracter(es) como mínimo.";
			}
			if(txt.trim().length()>maxs) {
				str += " - Error: campo requiere " + maxs + " caracteres como máximo.";
			}
			return txt + " " + str;
		}
		return "Correcto.";
	}
	public String IsValidDate(String txt) {
		try {
			//2020/12/31
			//0123456789
			Integer anio = Integer.parseInt(txt.trim().substring(0,4));
			Integer mes = Integer.parseInt(txt.trim().substring(5,7));
			Integer dia = Integer.parseInt(txt.trim().substring(8));
			DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.of(anio, mes, dia));
			
			if(anio<2003) {
				return txt + " - Error: fecha no debe ser menor a 01/Ene/2003." ;
			}
		}catch(Exception ex) {
			return txt + " - Error: ingresar un valor con formato de fecha." ;
		}
		return "Correcto.";
	}
}
