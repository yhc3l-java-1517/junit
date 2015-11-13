package se.coredev.zoo;

import se.coredev.zoo.model.Animal;

public interface ZooStorage {

	void addAnimal(Animal animal);
	
	Animal getById(String id);
}
