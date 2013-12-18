import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Test {
	public static void fileChooser() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "csv", "jpg", "gif");
	    //设置文件类型
	    chooser.setFileFilter(filter);
	    //打开选择器面板
	    int returnVal = chooser.showSaveDialog(new JPanel());  
                      //保存文件从这里入手，输出的是文件名
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("你打开的文件夹是: " +
	            chooser.getSelectedFile().getPath());
	       String path = chooser.getSelectedFile().getPath();
	       try {
	    	   File f = new File(path+".csv");
	    	   System.out.println(f.getAbsolutePath());
	    	   f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);
			
			out.write("aaaaaaaaaaaaaaaaa".getBytes());
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	}
	public static void main(String[] args) {
		fileChooser();
//	 File f = new File("d:/Users//MMY/Documents/asdfasfda/asdfasfda.txt");
//	   try {
//		f.createNewFile();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	}
}