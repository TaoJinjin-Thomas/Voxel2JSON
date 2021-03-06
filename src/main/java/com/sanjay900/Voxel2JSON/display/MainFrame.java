package com.sanjay900.Voxel2JSON.display;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lombok.Getter;
import org.json.JSONObject;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sanjay900.Voxel2JSON.Voxel2JSON;

import lombok.AllArgsConstructor;

public class MainFrame extends JFrame {
	@Getter
	private JPanel contentPane;
	@Getter
	private JSlider rx, ry,rz,tx,ty,tz,sx,sy,sz;
	private HashMap<ViewType,DisplayObject> displays = new HashMap<>();
	private ViewType type = ViewType.IN_HAND;
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		Arrays.stream(ViewType.values()).forEach(type->displays.put(type, new DisplayObject()));
		setTitle("Item Display Properties - In Hand");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 579);
		setPreferredSize(new Dimension(490,411));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnInHand = new JButton("In Hand");
		btnInHand.addActionListener(e -> {
            setTitle("Item Display Properties - "+((JButton)e.getSource()).getText());
            loadDisplay(ViewType.IN_HAND);
        });
		menuBar.add(btnInHand);
		
		JButton btnOnHead = new JButton("On Head");
		btnOnHead.addActionListener(e -> {
            setTitle("Item Display Properties - "+((JButton)e.getSource()).getText());
            loadDisplay(ViewType.ON_HEAD);
        });
		menuBar.add(btnOnHead);
		
		JButton btnOnFloor = new JButton("On Floor");
		menuBar.add(btnOnFloor);
		btnOnFloor.addActionListener(e -> {
            setTitle("Item Display Properties - "+((JButton)e.getSource()).getText());
            loadDisplay(ViewType.ON_FLOOR);
        });
		JButton btnOnWall = new JButton("On Wall");
		menuBar.add(btnOnWall);
		btnOnWall.addActionListener(e -> {
            setTitle("Item Display Properties - "+((JButton)e.getSource()).getText());
            loadDisplay(ViewType.ON_WALL);
        });
		JButton btnstPerson = new JButton("First Person");
		menuBar.add(btnstPerson);
		btnstPerson.addActionListener(e -> {
            setTitle("Item Display Properties - "+((JButton)e.getSource()).getText());
            loadDisplay(ViewType.FIRST_PERSON);
        });
		JButton btnGui = new JButton("GUI");
		menuBar.add(btnGui);
		btnGui.addActionListener(e -> {
            setTitle("Item Display Properties - "+((JButton)e.getSource()).getText());
            loadDisplay(ViewType.GUI);
        });
		contentPane = new JPanel();
		contentPane.setBounds(0,0, 489, 310);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));

		JPanel rotation = new JPanel();
		rotation.setBorder(BorderFactory.createTitledBorder("Rotation"));
		contentPane.add(rotation);
		rotation.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("400px"),
				ColumnSpec.decode("40px"),},
				new RowSpec[] {
						FormSpecs.PARAGRAPH_GAP_ROWSPEC,
						RowSpec.decode("26px"),
						RowSpec.decode("26px"),
						RowSpec.decode("26px"),}));
		JPanel translation = new JPanel();
		translation.setBorder(BorderFactory.createTitledBorder("Translation"));
		contentPane.add(translation);
		translation.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("400px"),
				ColumnSpec.decode("40px"),},
				new RowSpec[] {
						FormSpecs.PARAGRAPH_GAP_ROWSPEC,
						RowSpec.decode("26px"),
						RowSpec.decode("26px"),
						RowSpec.decode("26px"),}));
		JPanel scale = new JPanel();
		scale.setBorder(BorderFactory.createTitledBorder("Scale"));
		contentPane.add(scale);
		scale.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("400px"),
				ColumnSpec.decode("40px"),},
				new RowSpec[] {
						FormSpecs.PARAGRAPH_GAP_ROWSPEC,
						RowSpec.decode("26px"),
						RowSpec.decode("26px"),
						RowSpec.decode("26px"),}));

		JTextField rxl = new JTextField("0");
		JTextField ryl = new JTextField("0");
		JTextField rzl = new JTextField("0");
		JTextField txl = new JTextField("0");
		JTextField tyl = new JTextField("0");
		JTextField tzl = new JTextField("0");
		JTextField sxl = new JTextField("1");
		JTextField syl = new JTextField("1");
		JTextField szl = new JTextField("1");
		rx = new JSlider();
		rx.addChangeListener(e -> rxl.setText(rx.getValue()/100f+""));
		rx.setMinimum(-18000);
		rx.setMaximum(18000);
		rx.setValue(0);

		ry = new JSlider();
		ry.addChangeListener(e -> ryl.setText(ry.getValue()/100f+""));
		ry.setValue(0);
		ry.setMinimum(-18000);
		ry.setMaximum(18000);

		rz = new JSlider();
		rz.addChangeListener(e -> rzl.setText(rz.getValue()/100f+""));
		rz.setMinimum(-18000);
		rz.setMaximum(18000);
		rz.setValue(0);
		rotation.add(rx, "2, 2, fill, fill");
		rotation.add(rxl, "3, 2, default, fill");
		rotation.add(ry, "2, 3, fill, fill");
		rotation.add(ryl, "3, 3, fill, fill");
		rotation.add(rz, "2, 4, fill, fill");
		rotation.add(rzl, "3, 4, fill, fill");

		//Translation
		tx = new JSlider();
		tx.addChangeListener(e -> txl.setText(tx.getValue()/100f+""));
		tx.setMinimum(-1500);
		tx.setMaximum(1500);
		tx.setValue(0);

		ty = new JSlider();
		ty.addChangeListener(e -> tyl.setText(ty.getValue()/100f+""));
		ty.setValue(0);
		ty.setMinimum(-1500);
		ty.setMaximum(1500);

		tz = new JSlider();
		tz.addChangeListener(e -> tzl.setText(tz.getValue()/100f+""));
		tz.setMinimum(-1500);
		tz.setMaximum(1500);
		tz.setValue(0);
		translation.add(tx, "2, 2, fill, fill");
		translation.add(txl, "3, 2, default, fill");
		translation.add(ty, "2, 3, fill, fill");
		translation.add(tyl, "3, 3, fill, fill");
		translation.add(tz, "2, 4, fill, fill");
		translation.add(tzl, "3, 4, fill, fill");
		//scale
		sx = new JSlider();
		sx.addChangeListener(e -> sxl.setText(sx.getValue()/100f+""));
		sx.setMinimum(-400);
		sx.setMaximum(400);
		sx.setValue(100);

		sy = new JSlider();
		sy.addChangeListener(e -> syl.setText(sy.getValue()/100f+""));
		sy.setValue(100);
		sy.setMinimum(-400);
		sy.setMaximum(400);

		sz = new JSlider();
		sz.addChangeListener(e -> szl.setText(sz.getValue()/100f+""));
		sz.setMinimum(-400);
		sz.setMaximum(400);
		sz.setValue(100);
		scale.add(sx, "2, 2, fill, fill");
		scale.add(sxl, "3, 2, default, fill");
		scale.add(sy, "2, 3, fill, fill");
		scale.add(syl, "3, 3, fill, fill");
		scale.add(sz, "2, 4, fill, fill");
		scale.add(szl, "3, 4, fill, fill");

		rxl.getDocument().addDocumentListener(new SliderSync(rxl,rx));
		ryl.getDocument().addDocumentListener(new SliderSync(ryl,ry));
		rzl.getDocument().addDocumentListener(new SliderSync(rzl,rz));
		txl.getDocument().addDocumentListener(new SliderSync(txl,tx));
		tyl.getDocument().addDocumentListener(new SliderSync(tyl,ty));
		tzl.getDocument().addDocumentListener(new SliderSync(tzl,tz));
		sxl.getDocument().addDocumentListener(new SliderSync(sxl,sx));
		syl.getDocument().addDocumentListener(new SliderSync(syl,sy));
		szl.getDocument().addDocumentListener(new SliderSync(szl,sz));
		
		JButton btnReset = new JButton("Reset View");
		contentPane.add(btnReset);
	}
	protected void loadDisplay(ViewType newType) {
		DisplayObject old = displays.get(type);
		old.rx = rx.getValue()/100f;
		old.ry = ry.getValue()/100f;
		old.rz = rz.getValue()/100f;
		old.sx = sx.getValue()/100f;
		old.sy = sy.getValue()/100f;
		old.sz = sz.getValue()/100f;
		old.tx = tx.getValue()/100f;
		old.ty = ty.getValue()/100f;
		old.tz = tz.getValue()/100f;
		displays.put(type, old);
		DisplayObject newObj = displays.get(newType);
		rx.setValue((int) (newObj.rx*100));
		ry.setValue((int) (newObj.ry*100));
		rz.setValue((int) (newObj.rz*100));
		tx.setValue((int) (newObj.tx*100));
		ty.setValue((int) (newObj.ty*100));
		tz.setValue((int) (newObj.tz*100));
		sx.setValue((int) (newObj.sx*100));
		sy.setValue((int) (newObj.sy*100));
		sz.setValue((int) (newObj.sz*100));
		type = newType;
	}
	@AllArgsConstructor
	public class SliderSync implements DocumentListener {
		JTextField component;
		JSlider slider;
		public void actionPerformed(DocumentEvent e) {
			ChangeListener[] cl = Arrays.copyOf(slider.getChangeListeners(),slider.getChangeListeners().length);
			Arrays.stream(cl).forEach(slider::removeChangeListener);
			float f = 0;
			try {
				f = Float.parseFloat(component.getText());
				slider.setValue((int) (f*100));
			} catch (Exception ex) {
				slider.setValue(100);
			}
			Arrays.stream(cl).forEach(slider::addChangeListener);
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			actionPerformed(e);
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			actionPerformed(e);
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
			actionPerformed(e);
		}
		
	}
	public void fromDisplay(JSONObject display) {
		for (ViewType v: ViewType.values()) {
			if (display.has(v.getModelName())) {
				DisplayObject obj = new DisplayObject();
				obj.fromJSON(display.getJSONObject(v.getModelName()));
				displays.put(v, obj);
			}
		}
	}
	public JSONObject getDisplay() {
		JSONObject display = new JSONObject();
		displays.entrySet().forEach(entry -> {
			if (entry.getValue().toJSON().length() != 0)
				display.put(entry.getKey().getModelName(), entry.getValue().toJSON());
		});
		return display;
	}

}
