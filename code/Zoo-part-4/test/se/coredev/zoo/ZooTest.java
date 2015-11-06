package se.coredev.zoo;

import static org.junit.Assert.*;

import org.hamcrest.core.IsSame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import se.coredev.zoo.model.Animal;
import se.coredev.zoo.model.Dog;
import se.coredev.zoo.model.Spider;

public class ZooTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private Zoo zoo;

	@Before
	public void setup() {
		zoo = new Zoo();
	}
	
	@Test
	public void shouldNotAllowDangerousAnimal() {
		
		thrown.expect(ZooException.class);
		thrown.expectMessage("Can not add a dangerous animal");
		
		Animal spider = new Spider("1001", "Fido");
		zoo.addAnimal(spider);
	}

	//	@Test(expected = ZooException.class)
//	public void shouldNotAllowDangerousAnimal() {
//		Animal spider = new Spider("1001", "Fido");
//		zoo.addAnimal(spider);
//	}
	
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
