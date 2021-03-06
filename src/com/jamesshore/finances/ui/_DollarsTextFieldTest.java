package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _DollarsTextFieldTest {

	private DollarsTextField field;

	@Before
	public void setup() {
		field = new DollarsTextField(ValidDollars.create(42));
	}

	@Test
	public void canRetrieveAmount() {
		assertEquals(ValidDollars.create(42), field.getDollars());
	}

	@Test
	public void textReflectsDollarAmountUponConstruction() {
		assertEquals("$42", field.getText());
	}

	@Test
	public void changingTextChangesDollarAmount() {
		field.setText("1024");
		assertEquals(ValidDollars.create(1024), field.getDollars());
	}

	@Test
	public void fieldIsReformattedWhenItLosesFocus() throws Exception {
		field.dispatchEvent(new FocusEvent(field, FocusEvent.FOCUS_GAINED));
		field.setText("10");
		field.dispatchEvent(new FocusEvent(field, FocusEvent.FOCUS_LOST));

		final String[] testResult = { null };
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				testResult[0] = (field.getText());
			}
		});
		assertEquals("$10", testResult[0]);
	}

	@Test
	public void negativeValuesRenderedByDomainClass() throws Exception {
		field.dispatchEvent(new FocusEvent(field, FocusEvent.FOCUS_GAINED));
		field.setText("-10");
		field.dispatchEvent(new FocusEvent(field, FocusEvent.FOCUS_LOST));

		final String[] testResult = { null };
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				testResult[0] = (field.getText());
			}
		});
		assertEquals("($10)", testResult[0]);
		assertEquals(Color.RED, field.getForeground());
	}

	@Test
	public void fieldIsNotReformattedWhenTheValueIsInvalid() throws Exception {
		field.dispatchEvent(new FocusEvent(field, FocusEvent.FOCUS_GAINED));
		field.setText("xxx");
		field.dispatchEvent(new FocusEvent(field, FocusEvent.FOCUS_LOST));

		final String[] testResult = { null };
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				testResult[0] = (field.getText());
			}
		});
		assertEquals("xxx", testResult[0]);
	}

}
