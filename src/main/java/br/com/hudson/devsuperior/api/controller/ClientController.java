package br.com.hudson.devsuperior.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hudson.devsuperior.domain.dto.ClientDTO;
import br.com.hudson.devsuperior.domain.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	ClientService clientService;

	@GetMapping
	public Page<ClientDTO> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return clientService.findAll(pageRequest);
	}
	
	@GetMapping("/{id}")
	public ClientDTO findById(@PathVariable Long id) {
		return clientService.findById(id);
	}

}
