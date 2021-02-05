import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OceanMapTest {

	@Test
	void testGetInstance() {
		OceanMap oceanMap1 = OceanMap.getInstance();
		OceanMap oceanMap2 = OceanMap.getInstance();
		if(oceanMap1 != oceanMap2)
			fail("getInstance() doesn't get same instance when used twice");
	}

	@Test
	void testGetMap() {
		OceanMap oceanMap = OceanMap.getInstance();
		String mapClass = oceanMap.getMap().getClass().toString();
		if(!mapClass.equals("class [[Z"))
			fail("Does not return 2D array"+mapClass);
	}

	@Test
	void testGetDimensionX() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.initiate(20, 50);
		if(oceanMap.getDimensionX() != 20)
			fail("Dimensions Aren't Equal");
	}

	@Test
	void testGetDimensionY() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.initiate(20, 50);
		if(oceanMap.getDimensionY() != 50)
			fail("Scales Aren't Equal");
	}
}
