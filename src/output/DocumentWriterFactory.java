package output;
import java.util.ArrayList;

public class DocumentWriterFactory {

	public DocumentWriterFactory() {
		
	}
	private WriterAtbashDecorator writerAtbashDecorator;
	private WriterRot13Decorator writerRot13Decorator;
	public DocumentWriter createWriter(String filename,String type,String decoding,ArrayList<String> array){
		if(type.equals("excel")) {
			if(decoding.equals("Atbash")) {
				ExcelWriter excelr = new ExcelWriter(filename,array);
				writerAtbashDecorator = new WriterAtbashDecorator(excelr);
				return writerAtbashDecorator;
			}
			if(decoding.equals("Rot13")) {
				ExcelWriter excelr = new ExcelWriter(filename,array);
				writerRot13Decorator = new WriterRot13Decorator(excelr);
				return writerRot13Decorator;
			}
			else {
				ExcelWriter excelr = new ExcelWriter(filename,array);
				return excelr;
			}
		}
		if(type.equals("word")) {
			if(decoding.equals("Atbash")) {
				WordWriter wordr = new WordWriter(filename,array);
				writerAtbashDecorator = new WriterAtbashDecorator(wordr);
				return writerAtbashDecorator;
			}
			if(decoding.equals("Rot13")) {
				WordWriter wordr = new WordWriter(filename,array);
				writerRot13Decorator = new WriterRot13Decorator(wordr);
				return writerRot13Decorator;
			}
			else {
				WordWriter wordr = new WordWriter(filename,array);
				return wordr;
			}
		}
		if(!type.equals("word") && !type.equals("excel")) {
			System.out.println("Error:Expected 'word' or 'excel' as the type of the exported file.");
			System.exit(0);
		}
		System.out.println("Shouldn't be here.Starting debugging...");
		return writerAtbashDecorator;
	}
		
}