package com.soham.tools;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JMenuItem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;

class BanglaPadUI extends JFrame implements ActionListener
{

    public void actionPerformed(ActionEvent ae) {
        String ac=ae.getActionCommand();
        if("EXIT".equalsIgnoreCase(ac)){
            System.exit(0);
        }
        if("Save".equalsIgnoreCase(ac)){
            String utfToSave=jta.getText();
            JFileChooser jfc=new JFileChooser();
            jfc.addChoosableFileFilter(new FileFilter() {


                public boolean accept(File f) {
                    if(f.isFile() && (f.exists()||f.getName().toUpperCase().endsWith(".bng")==false)){
                        return false;
                    }else{
                        return true;
                    }
                }


                public String getDescription() {
                    return "Only Bengali file (*.bng)";
                }
            });
            int option=jfc.showSaveDialog(this);
            if(JFileChooser.APPROVE_OPTION!=option){
                return;
            }
            File fileSelected=jfc.getSelectedFile();
            String path=fileSelected.getAbsolutePath();
            path=path.substring(0,path.lastIndexOf(File.separator));


            String nm=fileSelected.getName();
            nm=nm.toUpperCase().endsWith(".BNG")?nm:nm+".bng";
            String destFileNm=path+File.separator+nm;
            try{
            FileOutputStream fos=new FileOutputStream(destFileNm);
            fos.write(utfToSave.getBytes());
            fos.close();
            JOptionPane.showMessageDialog(this,"Saved "+destFileNm);
            }catch(Throwable t){
                JOptionPane.showMessageDialog(this, "Error: "+t.toString());
            }

            System.out.println(nm+"***"+path+"*");
        }
        if("open".equalsIgnoreCase(ac)){
            try{
                    JFileChooser jfc=new JFileChooser();
                    int option=jfc.showOpenDialog(this);
                    if(option!=jfc.APPROVE_OPTION) return;

                    File fs=jfc.getSelectedFile();
                    FileInputStream fis=new FileInputStream(fs);
                    byte[] data=new byte[fis.available()];
                    fis.read(data);
                    String str=new String(data);
                    jta.setText(str);

            }catch(Throwable t){
                JOptionPane.showMessageDialog(this, "Error: "+t.toString());
            }
        }

        if(ac.toUpperCase().indexOf("PDF")>=0){
            String utfToSave=jta.getText();
            JFileChooser jfc=new JFileChooser();
            jfc.addChoosableFileFilter(new FileFilter() {


                public boolean accept(File f) {
                    if(f.isFile() && (f.exists()||f.getName().toUpperCase().endsWith(".PDF")==false)){
                        return false;
                    }else{
                        return true;
                    }
                }


                public String getDescription() {
                    return "Only PDF (*.pdf)";
                }
            });
            int option=jfc.showSaveDialog(this);
            if(JFileChooser.APPROVE_OPTION!=option){
                return;
            }
            File fileSelected=jfc.getSelectedFile();
            String path=fileSelected.getAbsolutePath();
            path=path.substring(0,path.lastIndexOf(File.separator));


            String nm=fileSelected.getName();
            nm=nm.toUpperCase().endsWith(".PDF")?nm:nm+".PDF";
            String destFileNm=path+File.separator+nm;
            try{
            FileOutputStream fos=new FileOutputStream(destFileNm);
            Document doc=new Document();
            PdfWriter writer=PdfWriter.getInstance(doc, fos);
            doc.open();
            BaseFont bf=BaseFont.createFont("/fonts/LikhanNormal.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
            com.itextpdf.text.Font font=new com.itextpdf.text.Font(bf);
            //StringTokenizer tokenizer=new StringTokenizer(utfToSave,"\r\n");
            //while(tokenizer.hasMoreTokens()){
                Paragraph para=new Paragraph(utfToSave, font);
                doc.add(para);
            //}

            doc.close();
            writer.close();
            //fos.write(utfToSave.getBytes());
            fos.close();
            JOptionPane.showMessageDialog(this,"Saved "+destFileNm);
            }catch(Throwable t){
                JOptionPane.showMessageDialog(this, "Error: "+t.toString());
            }

        }
    }

	JTextArea jta=new JTextArea(20,40);
    JMenuItem[] items=new JMenuItem[]{new JMenuItem("Save"),new JMenuItem("Save As PDF"),new JMenuItem("Open"),new JMenuItem("EXIT")};

	public BanglaPadUI(){
		super("Change this text");
        JMenu menu=new JMenu("File");
        for(int i=0;i<items.length;i++){
            items[i].addActionListener(this);
            menu.add(items[i]);
        }
        JMenuBar mb=new JMenuBar();
        mb.add(menu);
        this.setJMenuBar(mb);
        jta.setFont(new Font("Likhan",Font.BOLD,20));
		jta.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke){
				char ch=ke.getKeyChar();
				int offset=jta.getCaretPosition();
				String str=jta.getText();

                    int endIndex=jta.getCaretPosition();
                    String offsetStr=str.substring(0,endIndex);
                    String restStr=str.substring(endIndex);
                    String _parsed= BengaliKeyMapUtil.parseString(offsetStr, ch);
                    if(BengaliKeyMapUtil.endsH){
                    //    ke.setKeyChar('\0');'
                        String modiOffsetStr=offsetStr.substring(0,offsetStr.length()-1);
                        jta.setText(modiOffsetStr);
                        ke.setKeyChar(_parsed.charAt(0));
                        jta.setText(jta.getText()+restStr);
                    }else{
                        ke.setKeyChar(_parsed.charAt(0));
                    }


                    System.out.println(offsetStr+"**********"+restStr);
                }

				//ke.setKeyChar(y);


		});
        JScrollPane jsp=new JScrollPane(jta,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );

		this.getContentPane().add(jsp,BorderLayout.CENTER);
		this.setSize(640,480);
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] ar){
		new BanglaPadUI();
	}

}
