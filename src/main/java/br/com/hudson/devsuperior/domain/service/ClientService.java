package br.com.hudson.devsuperior.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hudson.devsuperior.domain.dto.ClientDTO;
import br.com.hudson.devsuperior.domain.exception.ResourceNotFoundException;
import br.com.hudson.devsuperior.domain.model.Client;
import br.com.hudson.devsuperior.domain.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@Transactional(readOnly = true )
	public Page<ClientDTO> findAll(PageRequest pageRequest) {
		
		Page<Client> clients = clientRepository.findAll(pageRequest);
		
		if (clients.isEmpty()) {
			throw new ResourceNotFoundException("Clientes não encontrados");			
		}
		
		return clients.map(client -> new ClientDTO(client));		
	}


	public ClientDTO findById(Long id) {
		Client client = clientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cliente não encontrado id:"+ id));
		return new ClientDTO(client);

	}
	
	
	

}
