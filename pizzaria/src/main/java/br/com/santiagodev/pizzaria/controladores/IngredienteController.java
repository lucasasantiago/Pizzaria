package br.com.santiagodev.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.santiagodev.pizzaria.excecoes.IngredienteInvalidoException;
import br.com.santiagodev.pizzaria.modelo.entidades.Ingrediente;
import br.com.santiagodev.pizzaria.modelo.enumeracoes.CategoriaIngrediente;
import br.com.santiagodev.pizzaria.modelo.repositorios.IngredienteRepositorio;

//	app/ingredientes (metodo GET) -> listarIngredientes
//	app/ingredientes (metodo POST) -> salvarIngredientes

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired private IngredienteRepositorio ingredienteRepositorio;

	@RequestMapping(method = RequestMethod.GET)
	public String listarIngredientes(Model model) {
		Iterable<Ingrediente> ingredientes = ingredienteRepositorio.findAll();

		model.addAttribute("titulo", "Listagem de Ingredientes");
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("categorias", CategoriaIngrediente.values());
		return "ingrediente/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarIngrediente(@Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			throw new IngredienteInvalidoException();
		} else {
			ingredienteRepositorio.save(ingrediente);

		}

		model.addAttribute("ingredientes", ingredienteRepositorio.findAll());
		model.addAttribute("categorias", CategoriaIngrediente.values());

		return "ingrediente/tabela-ingrediente";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarIngrediente(@PathVariable Long id) {
		try {
			ingredienteRepositorio.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody // O Spring espera um retorno de uma página, como não encontra tem que colocar essa annotation para os métodos que irão retornar JSon ou XML.
	public Ingrediente buscarIngrediente(@PathVariable Long id) {
		Ingrediente ingrediente = ingredienteRepositorio.findOne(id);
		return ingrediente;
	}
	
	
}
