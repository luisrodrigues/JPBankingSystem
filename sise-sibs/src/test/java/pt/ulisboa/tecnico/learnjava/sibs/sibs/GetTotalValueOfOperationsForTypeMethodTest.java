package pt.ulisboa.tecnico.learnjava.sibs.sibs;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.sibs.domain.Operation;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class GetTotalValueOfOperationsForTypeMethodTest {
	private static final String SOURCE_IBAN = "SourceIban";
	private static final String TARGET_IBAN = "TargetIban";

	private Sibs sibs;

	@Before
	public void setUp() throws OperationException, SibsException {
		this.sibs = new Sibs(3);
		this.sibs.addOperation(Operation.OPERATION_PAYMENT, null, TARGET_IBAN, 100);
		this.sibs.addOperation(Operation.OPERATION_TRANSFER, SOURCE_IBAN, TARGET_IBAN, 200);
	}

	@Test
	public void successTwo() throws SibsException, OperationException {
		assertEquals(100, this.sibs.getTotalValueOfOperationsForType(Operation.OPERATION_PAYMENT));
	}

	@After
	public void tearDown() {
		this.sibs = null;
	}

}
