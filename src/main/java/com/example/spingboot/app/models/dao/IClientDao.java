package com.example.spingboot.app.models.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.spingboot.app.models.entity.Client;

public interface IClientDao  extends PagingAndSortingRepository<Client, Long>{

}
