package com.example.spingboot.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spingboot.app.models.dao.IClientDao;
import com.example.spingboot.app.models.entity.Client;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clienteDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Client> findAll() {
		return (List<Client>) clienteDao.findAll();
	}

	@Transactional
	@Override
	public void save(Client client) {
		clienteDao.save(client);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Client findOne(Long id) {
		return clienteDao.findOne(id);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		clienteDao.delete(id);		
	}

	@Override
	public Page<Client> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

}
