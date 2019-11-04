package converter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;

import converter.api.ConverterAPI;

public class Converter {
	private JFrame converterFrame = null;
	private JTextField txtSngToPro6Sng = null;
	private JTextField txtSngToPro6Pro6 = null;
	private JTextField txtPro6ToSngSng = null;
	private JTextField txtPro6ToSngPro6 = null;
	
	public static void main(String[] args) throws IOException, BadLocationException {
		if (args.length == 0) {
			SwingUtilities.invokeLater(() -> {
				new Converter().converterFrame.setVisible(true);
			});
		} else if (args.length == 3) {
			switch (args[0].toLowerCase()) {
				case "pro6":
					ConverterAPI.writeFile(new File(args[2]), ConverterAPI.convertSngToPro6(new File(args[1])));
					break;
				case "sng":
					ConverterAPI.writeFile(new File(args[2]), ConverterAPI.convertPro6ToSng(new File(args[1])));
					break;
				default:
					System.err.println("Please only use pro6 or sng as targetformat!");
					break;
			}
		} else {
			System.err.println("Usage: java -jar SNGConverter.jar targetformat(pro6/sng) infile outfile!");
		}
	}
	
	public Converter() {
		this.converterFrame = new JFrame("SNGConverter");
		this.converterFrame.setBounds(100, 100, 700, 500);
		this.converterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.converterFrame.setIconImage(image);
		this.converterFrame.setLayout(null);
		this.converterFrame.setLocationRelativeTo(null);
		this.converterFrame.setResizable(false);
		
		JLabel lblSngToPro6Sng = new JLabel("<html>SongBeamer -> ProPresenter:<br>SongBeamer-Datei:</html>");
		lblSngToPro6Sng.setBounds(10, 5, 500, 35);
		this.converterFrame.add(lblSngToPro6Sng);
		
		this.txtSngToPro6Sng = new JTextField();
		this.txtSngToPro6Sng.setBounds(10, 40, 640, 25);
		this.txtSngToPro6Sng.setEditable(false);
		this.converterFrame.add(this.txtSngToPro6Sng);
		
		JButton btnSngToPro6 = new JButton("...");
		btnSngToPro6.addActionListener((e) -> {
			JFileChooser jfc = new JFileChooser();
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "SongBeamer-Datei";
				}
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().endsWith(".sng");
				}
			});
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "Text-Dateien";
				}
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().endsWith(".txt");
				}
			});
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "Alle Dateien";
				}
				@Override
				public boolean accept(File f) {
					return true;
				}
			});
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setDialogTitle("SongBeamer-Datei öffnen");
			jfc.setMultiSelectionEnabled(false);
			if (jfc.showOpenDialog(this.converterFrame) == JFileChooser.APPROVE_OPTION) {
				this.txtSngToPro6Sng.setText(jfc.getSelectedFile().getAbsolutePath());
			}
		});
		btnSngToPro6.setBounds(660, 40, 30, 25);
		this.converterFrame.add(btnSngToPro6);
		
		JLabel lblSngToPro6Pro6 = new JLabel("ProPresenter-Datei:");
		lblSngToPro6Pro6.setBounds(10, 60, 500, 35);
		this.converterFrame.add(lblSngToPro6Pro6);
		
		this.txtSngToPro6Pro6 = new JTextField();
		this.txtSngToPro6Pro6.setBounds(10, 90, 640, 25);
		this.txtSngToPro6Pro6.setEditable(false);
		this.converterFrame.add(this.txtSngToPro6Pro6);
		
		JButton btnSngToPro6Pro6 = new JButton("...");
		btnSngToPro6Pro6.addActionListener((e) -> {
			JFileChooser jfc = new JFileChooser();
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "ProPresenter-Datei";
				}
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().endsWith(".pro6");
				}
			});
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "Text-Dateien";
				}
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().endsWith(".txt");
				}
			});
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "Alle Dateien";
				}
				@Override
				public boolean accept(File f) {
					return true;
				}
			});
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setDialogTitle("ProPresenter-Datei öffnen");
			jfc.setMultiSelectionEnabled(false);
			if (jfc.showOpenDialog(this.converterFrame) == JFileChooser.APPROVE_OPTION) {
				this.txtSngToPro6Pro6.setText(jfc.getSelectedFile().getAbsolutePath());
			}
		});
		btnSngToPro6Pro6.setBounds(660, 90, 30, 25);
		this.converterFrame.add(btnSngToPro6Pro6);
		
		JButton convertSngToPro6 = new JButton("SongBeamer zu ProPresenter konvertieren");
		convertSngToPro6.addActionListener((e) -> {
			File destinationFile = new File(this.txtSngToPro6Pro6.getText());
			File sourceFile = new File(this.txtSngToPro6Sng.getText());
			try {
				ConverterAPI.writeFile(destinationFile, ConverterAPI.convertSngToPro6(sourceFile));
			} catch (IOException | BadLocationException ex) {
				StringWriter sw = new StringWriter();
				ex.printStackTrace(new PrintWriter(sw));
				JOptionPane.showMessageDialog(this.converterFrame, sw.toString(), "Fehler bei der Konvertierung", JOptionPane.ERROR_MESSAGE);
			}
		});
		convertSngToPro6.setBounds(10, 130, 680, 25);
		this.converterFrame.add(convertSngToPro6);
		
		JLabel lblPro6ToSngPro6 = new JLabel("<html>ProPresenter -> SongBeamer:<br>ProPresenter-Datei:</html>");
		lblPro6ToSngPro6.setBounds(10, 250, 500, 35);
		this.converterFrame.add(lblPro6ToSngPro6);
		
		this.txtPro6ToSngPro6 = new JTextField();
		this.txtPro6ToSngPro6.setBounds(10, 290, 640, 25);
		this.txtPro6ToSngPro6.setEditable(false);
		this.converterFrame.add(this.txtPro6ToSngPro6);
		
		JButton btnPro6ToSngPro6 = new JButton("...");
		btnPro6ToSngPro6.addActionListener((e) -> {
			JFileChooser jfc = new JFileChooser();
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "ProPresenter-Datei";
				}
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().endsWith(".pro6");
				}
			});
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "Text-Dateien";
				}
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().endsWith(".txt");
				}
			});
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "Alle Dateien";
				}
				@Override
				public boolean accept(File f) {
					return true;
				}
			});
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setDialogTitle("ProPresenter-Datei öffnen");
			jfc.setMultiSelectionEnabled(false);
			if (jfc.showOpenDialog(this.converterFrame) == JFileChooser.APPROVE_OPTION) {
				this.txtPro6ToSngPro6.setText(jfc.getSelectedFile().getAbsolutePath());
			}
		});
		btnPro6ToSngPro6.setBounds(660, 290, 30, 25);
		this.converterFrame.add(btnPro6ToSngPro6);
		
		JLabel lblPro6ToSngSng = new JLabel("SongBeamer-Datei:");
		lblPro6ToSngSng.setBounds(10, 310, 500, 35);
		this.converterFrame.add(lblPro6ToSngSng);
		
		this.txtPro6ToSngSng = new JTextField();
		this.txtPro6ToSngSng.setBounds(10, 340, 640, 25);
		this.txtPro6ToSngSng.setEditable(false);
		this.converterFrame.add(this.txtPro6ToSngSng);
		
		JButton btnPro6ToSngSng = new JButton("...");
		btnPro6ToSngSng.addActionListener((e) -> {
			JFileChooser jfc = new JFileChooser();
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "SongBeamer-Datei";
				}
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().endsWith(".sng");
				}
			});
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "Text-Dateien";
				}
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().endsWith(".txt");
				}
			});
			jfc.addChoosableFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "Alle Dateien";
				}
				@Override
				public boolean accept(File f) {
					return true;
				}
			});
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setDialogTitle("ProPresenter-Datei öffnen");
			jfc.setMultiSelectionEnabled(false);
			if (jfc.showOpenDialog(this.converterFrame) == JFileChooser.APPROVE_OPTION) {
				this.txtPro6ToSngSng.setText(jfc.getSelectedFile().getAbsolutePath());
			}
		});
		btnPro6ToSngSng.setBounds(660, 340, 30, 25);
		this.converterFrame.add(btnPro6ToSngSng);
		
		JButton convertPro6ToSng = new JButton("ProPresenter zu SongBeamer konvertieren");
		convertPro6ToSng.addActionListener((e) -> {
			File sourceFile = new File(this.txtPro6ToSngPro6.getText());
			File destinationFile = new File(this.txtPro6ToSngSng.getText());
			try {
				ConverterAPI.writeFile(destinationFile, ConverterAPI.convertPro6ToSng(sourceFile));
			} catch (IOException | BadLocationException ex) {
				StringWriter sw = new StringWriter();
				ex.printStackTrace(new PrintWriter(sw));
				JOptionPane.showMessageDialog(this.converterFrame, sw.toString(), "Fehler bei der Konvertierung", JOptionPane.ERROR_MESSAGE);
			}
		});
		convertPro6ToSng.setBounds(10, 380, 680, 25);
		this.converterFrame.add(convertPro6ToSng);
	}
}
