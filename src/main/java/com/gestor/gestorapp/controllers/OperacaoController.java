package com.gestor.gestorapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gestor.gestorapp.models.Operacao;
import com.gestor.gestorapp.respository.OperacaoRepository;

@Controller
public class OperacaoController {
	
	@Autowired
	private OperacaoRepository or;
	

	@RequestMapping(value="/cadastrarGanho", method = RequestMethod.GET)
	public String ganho() {
		return "operacao/formOperacao";
	}
	
	@RequestMapping(value="/cadastrarGasto", method = RequestMethod.GET)
	public String gasto() {
		return "operacao/formOperacao";
	}
	
	
	@RequestMapping(value="/cadastrarGanho", method = RequestMethod.POST)
	public String ganho(Operacao op) {
		or.save(op);
		return "redirect:/operacoes";
	}
	
	@RequestMapping(value="/cadastrarGasto", method = RequestMethod.POST)
	public String gasto(Operacao op) {
		op.setValor(op.getValor() * - 1);
		or.save(op);
		return "redirect:/operacoes";
	}
	
	@RequestMapping(value="/operacoes", method = RequestMethod.GET)
	public ModelAndView listaOperacoes() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Operacao> operacoes = or.findAll();
		for (Operacao op : operacoes) {
			if(op.getValor() < 0) {
				op.setValor(op.getValor() * -1);
				op.setCategoria("Gasto");
			}else {
				op.setCategoria("Ganho");
			}
		}
		mv.addObject("operacao", operacoes);
		return mv;
	}
	
	
	@ModelAttribute("saldo")
	public String saldo() {
		ModelAndView mv = new ModelAndView("index");
		Double saldo = 0.0;
		Iterable<Operacao> operacoes = or.findAll();
		for (Operacao operacao : operacoes) {
			saldo += operacao.getValor();
		}
		if(saldo < 0) {
			return "Saldo negativo de R$" + saldo.toString();
		}else if(saldo > 0) {
		return "Saldo positivo de R$" + saldo.toString();
		}
		return "Saldo de R$0,00";
	}

}
