package br.com.hudson.devsuperior.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hudson.devsuperior.domain.dto.ClientDTO;
import br.com.hudson.devsuperior.domain.exception.DataBaseException;
import br.com.hudson.devsuperior.domain.exception.DomainException;
import br.com.hudson.devsuperior.domain.exception.ResourceNotFoundException;
import br.com.hudson.devsuperior.domain.model.Client;
import br.com.hudson.devsuperior.domain.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(Pageable pageable) {
		
		Page<Client> clients = clientRepository.findAll(pageable);
		
		if (clients.isEmpty()) {
			throw new ResourceNotFoundException("Clientes não encontrados");			
		}
		
		return clients.map(client -> new ClientDTO(client));		
	}


	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = clientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cliente não encontrado id:"+ id));
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {
		
		Client client = new Client();
		client.setName(clientDTO.getName());
		client.setIncome(clientDTO.getIncome());
		client.setBirthDate(clientDTO.getBirthDate());
		client.setChildren(clientDTO.getChildren());
		
		client = clientRepository.save(client);
		return new ClientDTO(client);
	}
	
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client client = clientRepository.getOne(id);
			copyDtoToModel(clientDTO, client);
			return clientDTO;			
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cliente não encontrado id: "+id);
		}
		catch (Exception e) {
			throw new DomainException("Erro - "+e.getMessage());
		}		
	}
	
	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Cliente não encontrado. ID:" + id);

		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade");
		}
	}
	
	private void copyDtoToModel(ClientDTO clientDTO, Client client) {

		client.setName(clientDTO.getName());
		client.setBirthDate(clientDTO.getBirthDate());
		client.setChildren(clientDTO.getChildren());
		client.setIncome(clientDTO.getIncome());
	}
	

}
