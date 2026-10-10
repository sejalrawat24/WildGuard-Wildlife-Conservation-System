package com.wildlife.wildlife_service.service;
import org.springframework.stereotype.Service;
import java.util.*;
import com.wildlife.wildlife_service.model.Animal;
import com.wildlife.wildlife_service.repository.*;
@Service
public class AnimalService 
{
	private final AnimalRepo animalRepo;

	public AnimalService(AnimalRepo animalRepo) 
	{
	    this.animalRepo = animalRepo;
	}
	
	public List<Animal> getAllAnimalsSer() 
	{
		return animalRepo.getAllAnimals();
	}
	
	public Animal getAnimalByIDSer(int id)
	{
		return animalRepo.getAnimalById(id);
	}
	
	public int addAnimalSer(Animal ani)
	{
		return animalRepo.addAnimal(ani);
	}
	
	public int updateAnimalSer(int id, Animal ani)
	{
		return animalRepo.updateAnimal(id, ani);
	}
	
	public int updateHealthSer(int id, String status)
	{
		return animalRepo.updateHealthStatus(id,status);
	}
	
	public int updateLocSer(int id, String loc)
	{
		return animalRepo.updateLocation(id, loc);
	}
	
	public int deleteAnimalSer(int id)
	{
		return animalRepo.deleteAnimal(id);
	}
}
