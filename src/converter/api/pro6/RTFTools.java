package converter.api.pro6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

public class RTFTools {
	
	public static String convertRtfToPlainText(String rtf) throws IOException, BadLocationException {
		RTFEditorKit rtfParser = new RTFEditorKit();
		Document document = rtfParser.createDefaultDocument();
		rtfParser.read(new ByteArrayInputStream(rtf.getBytes()), document, 0);
		return document.getText(0, document.getLength());
	}
	
	public static String convertPlainTextToRtf(String plainText) throws IOException, BadLocationException {
		return convertPlainTextToRtf(plainText, (AttributeSet) null);
	}
	
	public static String convertPlainTextToRtf(String plainText, Document document) throws IOException, BadLocationException {
		return convertPlainTextToRtf(plainText, document, null);
	}
	
	public static String convertPlainTextToRtf(String plainText, AttributeSet attributeSet) throws IOException, BadLocationException {
		RTFEditorKit editorKit = new RTFEditorKit();
		return convertPlainTextToRtf(plainText, editorKit, editorKit.createDefaultDocument(), null);
	}
	
	public static String convertPlainTextToRtf(String plainText, Document document, AttributeSet attributeSet) throws IOException, BadLocationException {
		return convertPlainTextToRtf(plainText, new RTFEditorKit(), document, attributeSet);
	}
	
	public static String convertPlainTextToRtf(String plainText, RTFEditorKit editorKit, Document document, AttributeSet attributeSet) throws IOException, BadLocationException {
		document.insertString(0, plainText, attributeSet);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		editorKit.write(os, document, 0, document.getLength());
		return new String(os.toByteArray());
	}
}
