package filework;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ProgressBar;

public class main {

	protected Shell shell;
	private Text text;
	private Text text_1;


	static void copy(String srcPathStr,String desPathStr) {
		String newFileName =srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1);
		System.out.println("源文件："+newFileName);
		desPathStr=desPathStr+File.separator+newFileName;
		System.out.println("目标文件地址："+desPathStr);
		try {
			FileInputStream fis=new FileInputStream(srcPathStr);
			FileOutputStream fos=new FileOutputStream(desPathStr);
			byte datas[]=new byte[1024*8];
			int len=0;
			while ((len=fis.read(datas))!=-1) {
				fos.write(datas,0,len);
			}
			fis.close();
			fis.close();
			System.out.println("文件复制成功");
		} catch (Exception e) {
			
		}
	}
	
	static void move(String srcPathStr,String desPathStr){
			try{
				File file=new File(srcPathStr); //源文件
				if (file.renameTo(new File(desPathStr+file.getName())))
				{
					System.out.println("文件移动成功");
				}
				else
				{
					System.out.println("文件移动失败");
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	
	public static void main(String[] args) {
		try {
			main window = new main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shell = new Shell();
		shell.setSize(960, 720);
		shell.setText("SWT Application");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(39, 28, 76, 20);
		label.setText("\u6E90\u6587\u4EF6\u5730\u5740");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(39, 81, 76, 20);
		label_1.setText("\u76EE\u6807\u6587\u4EF6\u5730\u5740");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(156, 25, 465, 26);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(156, 78, 465, 26);
		
		Button MoveFile = new Button(shell, SWT.NONE);
		MoveFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) { 
				move(text.toString(), text_1.toString());
				
			}
		});
		MoveFile.setBounds(158, 129, 98, 30);
		MoveFile.setText("\u79FB\u52A8\u6587\u4EF6");
		
		Button CopyFlie = new Button(shell, SWT.NONE);
		CopyFlie.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				copy(text.toString(), text_1.toString());
			}
		});
		CopyFlie.setBounds(308, 129, 98, 30);
		CopyFlie.setText("\u590D\u5236\u6587\u4EF6");
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 FileDialog fd=new FileDialog(shell,SWT.OPEN);
				  fd.setFilterPath(System.getProperty("JAVA.HOME"));
				  fd.setFilterExtensions(new String[]{"*.txt","*.*"});
				  fd.setFilterNames(new String[]{"Text Files(*.txt)","All Files(*.*)"});
				  String file=fd.open();
				  if(file!=null){
				  File path=new File(file);
				  System.out.println(path.getPath());
				  }
			}
		});
		button.setBounds(640, 25, 98, 30);
		button.setText("\u9009\u62E9\u6587\u4EF6");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd=new DirectoryDialog(shell);
				 dd.setMessage("setMessage");
				 dd.setText("setText");
				 dd.setFilterPath("C://");
				 String saveFile=dd.open();
				 if(saveFile!=null){
				  File directiory=new File(saveFile);
				  System.out.println(directiory.getPath());     
				  text_1.setText(directiory.getPath());
				 }
			}
		});
		button_1.setBounds(640, 71, 98, 30);
		button_1.setText("\u9009\u62E9\u5730\u5740");

	}
}
