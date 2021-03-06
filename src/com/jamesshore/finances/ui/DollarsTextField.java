package com.jamesshore.finances.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jamesshore.finances.domain.*;

// If you want to subclass this class, it's okay to remove the 'final' designator, but be careful of race 
// conditions with the event handler in the constructor. It could execute before the subclass constructor.
public final class DollarsTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	class DollarsTextFieldRenderTargetAdapter implements RenderTarget {
		private DollarsTextField field;

		public DollarsTextFieldRenderTargetAdapter(DollarsTextField field) {
			this.field = field;
		}

		@Override
		public void setText(String text) {
			field.setText(text);
		}

		@Override
		public void setIcon(Icon icon) {
			// TODO: implement?
		}

		@Override
		public void setToolTipText(String text) {
			// TODO: implement?
		}

		@Override
		public void setForegroundColor(Color color) {
			field.setForeground(color);
		}
	}

	public DollarsTextField(Dollars initialValue) {
		this.setText(initialValue.toString());
		this.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				Dollars dollars = getDollars();
				if (dollars.isValid()) {
					dollars.render(new Resources(), new DollarsTextFieldRenderTargetAdapter(DollarsTextField.this));
				}
			}
		});
	}

	public Dollars getDollars() {
		return Dollars.parse(getText());
	}

}
