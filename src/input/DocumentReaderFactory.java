package input;

public class DocumentReaderFactory {
	
	public DocumentReaderFactory() {
		
	}
	private ReaderAtbashDecorator readerAtbashDecorator;
	private ReaderRot13Decorator readerRot13Decorator;

	public DocumentReader createReader(String filename,String type,String decoding){
		if(type.equals("excel")) {
			if(decoding.equals("Atbash")) {
				ExcelReader excelr = new ExcelReader(filename);
				readerAtbashDecorator = new ReaderAtbashDecorator(excelr);
				return readerAtbashDecorator;
			}
			if(decoding.equals("Rot13")) {
				ExcelReader excelr = new ExcelReader(filename);
				readerRot13Decorator = new ReaderRot13Decorator(excelr);
				return readerRot13Decorator;
			}
			else {
				ExcelReader excelr = new ExcelReader(filename);
				return excelr;
			}
		}
		if(type.equals("word")) {
			if(decoding.equals("Atbash")) {
				WordReader wordr = new WordReader(filename);
				readerAtbashDecorator = new ReaderAtbashDecorator(wordr);
				return readerAtbashDecorator;
			}
			if(decoding.equals("Rot13")) {
				WordReader wordr = new WordReader(filename);
				readerRot13Decorator = new ReaderRot13Decorator(wordr);
				return readerRot13Decorator;
			}
			else {
				WordReader wordr = new WordReader(filename);
				return wordr;
			}
		}
		if(!type.equals("word") && !type.equals("excel")) {
			System.out.println("Error:Expected 'word' or 'excel' as the type of the imported file.");
			System.exit(0);
		}
		System.out.println("Shouldn't be here.Starting debugging...");
		return readerAtbashDecorator;
	}
		
}
