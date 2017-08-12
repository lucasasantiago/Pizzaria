package br.com.santiagodev.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.santiagodev.pizzaria.excecoes.IngredienteInvalidoException;
import br.com.santiagodev.pizzaria.modelo.entidades.Ingrediente;
import br.com.santiagodev.pizzaria.modelo.entidades.Pizza;
import br.com.santiagodev.pizzaria.modelo.enumeracoes.CategoriaPizza;
import br.com.santiagodev.pizzaria.modelo.repositorios.IngredienteRepositorio;
import br.com.santiagodev.pizzaria.modelo.repositorios.PizzaRepositorio;
import br.com.santiagodev.pizzaria.propertyeditors.IngredientePropertyEditor;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaRepositorio pizzaRepositorio;
	@Autowired
	private IngredienteRepositorio ingredienteRepositorio;
	@Autowired
	private IngredientePropertyEditor ingredientePropertyEditor;

	@RequestMapping(method = RequestMethod.GET)
	public String listarPizzas(Model model) {
		Iterable<Pizza> pizzas = pizzaRepositorio.findAll();

		model.addAttribute("titulo", "Listagem de Ingredientes");
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("categorias", CategoriaPizza.values());
		model.addAttribute("ingredientes", ingredienteRepositorio.findAll());
		return "pizza/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarPizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			throw new IngredienteInvalidoException();
		} else {
			pizzaRepositorio.save(pizza);

		}

		model.addAttribute("pizzas", pizzaRepositorio.findAll());

		return "pizza/tabela-pizzas";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarPizza(@PathVariable Long id) {
		try {
			pizzaRepositorio.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Pizza buscarPizza(@PathVariable Long id) {
		Pizza pizza = pizzaRepositorio.findOne(id);
		return pizza;
	}

	@InitBinder
	private void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Ingrediente.class, ingredientePropertyEditor);
	}

	@RequestMapping("/quantas")
	@ResponseBody
	public String quantasPizzas() {
		return "Atualmente há " + pizzaRepositorio.count() + " cadastradas.";
	}

}
