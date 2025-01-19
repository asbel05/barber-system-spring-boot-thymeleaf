package com.asbel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asbel.model.entity.Local;
import com.asbel.service.impl.LocalServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/local")
public class LocalController {
	@Autowired
	private LocalServiceImpl localService;

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("locales", localService.listarTodos());
		model.addAttribute("titulo", "Listado de Locales");
		return "local/lista";
	}

	@GetMapping("/nuevo")
	public String formularioNuevo(Model model) {
		model.addAttribute("local", new Local());
		model.addAttribute("titulo", "Nuevo Local");
		return "local/formulario";
	}

	@GetMapping("/editar/{id}")
	public String formularioEditar(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Local buscado = localService.obtenerPorId(id);
			model.addAttribute("local", buscado);
			model.addAttribute("titulo", "Editar Local");
			return "local/formulario";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Local no encontrado");
			return "redirect:/local";
		}
	}

	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("local") Local local, RedirectAttributes redirectAttributes) {
		try {
			boolean esNuevo = local.getId() == null;
			localService.guardar(local);

			if (esNuevo) {
				redirectAttributes.addFlashAttribute("mensaje", "Local creado correctamente");
			} else {
				redirectAttributes.addFlashAttribute("mensaje", "Local editado correctamente");
			}

			return "redirect:/local";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al guardar el local: " + e.getMessage());
			return "redirect:/local/nuevo";
		}
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			localService.eliminar(id);
			redirectAttributes.addFlashAttribute("mensaje", "Local eliminado correctamente");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al eliminar el local: " + e.getMessage());
		}
		return "redirect:/local";
	}
}
