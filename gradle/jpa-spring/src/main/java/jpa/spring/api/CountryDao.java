package jpa.spring.api;

import java.util.List;

import jpa.domain.Country;

public interface CountryDao {
	List<Country> findAll();
	
	Country findByCountryCode(String code);
}
