package se.coredev.zoo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.coredev.zoo.model.Animal;
import se.coredev.zoo.model.Dog;
import se.coredev.zoo.model.Spider;

public class ZooTest {

	private Zoo zoo;

	@Before
	public void setup() {
		zoo = new Zoo();
	}

	@Test(expected = ZooException.class)
	public void shouldNotAllowDangerousAnimal() {
		Animal spider = new Spider("1001", "Fido");
		zoo.addAnimal(spider);
	}
	
//	@Test
//	public void shouldNotAllowDangerousAnimal() {
//		Animal spider = new Spider("1001", "Fido");
//		
//		try {
//			zoo.addAnimal(spider);
//			fail();
//		}
//		catch (ZooException e) {}
//	}

	@Test
	public void animalShouldBeInZooAfterAdd() {
		Animal dog = new Dog("1001", "Fido");

		boolean added = zoo.addAnimal(dog);
		assertTrue(added);
	}

	@Test
	public void shouldNotAllowSameAnimal() {
		Animal dog1 = new Dog("1001", "Fido");
		Animal dog2 = new Dog("1001", "Fido");

		boolean added = zoo.addAnimal(dog1);
		assertTrue(added);

		added = zoo.addAnimal(dog2);
		assertFalse(added);
	}

}
