package test.mx.uam.ayd.sadue.datos;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(DAOProductosTest.class);
		//$JUnit-END$
		return suite;
	}

}
