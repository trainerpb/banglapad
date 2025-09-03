package com.soham.tools;

import java.lang.reflect.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BengaliKeyMapUtil
{

	/* The matras in Bengali*/

	public final static char MATRA_AA='\u09BE';	
	public final static char MATRA_i='\u09BF';
	public final static char MATRA_I='\u09C0';
	public final static char MATRA_u='\u09C1';
	public final static char MATRA_U='\u09C2';
	public final static char MATRA_R='\u09C3';
	public final static char MATRA_e='\u09C7';
	public final static char MATRA_E='\u09C8';
	public final static char MATRA_o='\u09CB';
	public final static char MATRA_O='\u09CC';

	
	/*The vowels in Bengali */

	public final static char VOWEL_A='\u0985';
	public final static char VOWEL_AA='\u0986';	
	public final static char VOWEL_i='\u0987';
	public final static char VOWEL_I='\u0988';
	public final static char VOWEL_u='\u0989';
	public final static char VOWEL_U='\u098A';
	public final static char VOWEL_R='\u098B';
	public final static char VOWEL_e='\u098F';
	public final static char VOWEL_E='\u0990';
	public final static char VOWEL_o='\u0993';
	public final static char VOWEL_O='\u0994';



	
	/* Specials */
	
	public final static char HASANT='\u09CD';
	

	/* The consonents in Bengali*/

	

	public final static char K='\u0995';
	public final static char KH='\u0996';

	public final static char G='\u0997';
	public final static char GH='\u0998';

	public final static char CH='\u099A';
	public final static char CHH='\u099B';

	public final static char J='\u099C';
	public final static char JH='\u099D';

	public final static char T='\u099F';
	public final static char TH='\u09A0';

	public final static char D='\u09A1';
	public final static char DH='\u09A2';

	public final static char t='\u09A4';
	public final static char tH='\u09A5';

	public final static char d='\u09A6';
	public final static char dH='\u09A7';

	public final static char P='\u09AA';
	public final static char PH='\u09AB';

	public final static char B='\u09AC';
	public final static char BH='\u09AD';

	public final static char N='\u09A8';
	public final static char NH='\u09A3';
		
	public final static char M='\u09AE';

	public final static char YA='\u09AF';

	public final static char R='\u09B0';
	public static final char RR='\u09DC';
	public static final char RRH='\u09DD';
	public final static char L='\u09B2';
	public final static char SH='\u09B6';
	public final static char sH='\u09B7';
	public final static char S='\u09B8';
	public final static char H='\u09B9';
    public final static char CHANDRABINDU='\u09FA';
    public final static char ANUSHAR='\u0982';
    public final static char BISARGA='\u0983';
    public final static char YYA='\u09DF';
    public final static char UMA='\u0999';

    /* Numbers*/
    public final static char DGT_0='\u09E6';
    public final static char DGT_1='\u09E7';
	public final static char DGT_2='\u09E8';
    public final static char DGT_3='\u09E9';
    public final static char DGT_4='\u09EA';
    public final static char DGT_5='\u09EB';
    public final static char DGT_6='\u09EC';
    public final static char DGT_7='\u09ED';
    public final static char DGT_8='\u09EE';
    public final static char DGT_9='\u09EF';

    /* puncuation*/
    public final static char FULL_STOP='\u0964';

    public static boolean endsH=false;

	/* A method that parses a String */
	
	public final static String parseString(String str,char ch){
		int len=str.length();
		
		char charLast=len==0?' ':str.charAt(len-1);
		String x="";;
		endsH=false;
		switch(ch){
		
			case ' ':
			x=""+' ';
			break;
		
            case '\n':
            case '\r':
                x+='\r';
            case 'a':
				x+=VOWEL_A;
			break;
			case 'A':

					
				if("aeiou".indexOf((""+charLast).toLowerCase())<0) x+=MATRA_AA;
				if(charLast==' '||charLast=='\0') x=""+VOWEL_AA;
					
					
				break;		
			case 'i':
				//if("aeiou".indexOf((""+charLast).toLowerCase())<0) x+=MATRA_i;
				if(charLast==' ') x+=VOWEL_i;		
				else x+=MATRA_i;	
				break;


			case 'I':
			//	if("aeiou".indexOf((""+charLast).toLowerCase())<0) x+=MATRA_I;
				if(charLast==' ') x+=VOWEL_I;		
				else x+=MATRA_I;		
				break;



			case 'u':
				
				if(charLast==' ') x+=VOWEL_u;		
				else x+=MATRA_u;	
				break;

			case 'U':
				
				if(charLast==' ') x+=VOWEL_U;		
				else x+=MATRA_U;	
				break;

			case 'R':
				
				if(charLast==' ') x+=VOWEL_R;		
				else x+=MATRA_R;	
				break;

			case 'e':
				
				if(charLast==' ') x+=VOWEL_e;		
				else x+=MATRA_e;	
				break;

			case 'E':
				
				if(charLast==' ') x+=VOWEL_E;		
				else x+=MATRA_E	;
				break;

			case 'o':
				//if("aeiou".indexOf((""+charLast).toLowerCase())<0) x+=MATRA_o;
				if(charLast==' ') x+=VOWEL_o;		
				else x+=MATRA_o;	
				break;
			case 'O':
			//	if("aeiou".indexOf((""+charLast).toLowerCase())<0) x+=MATRA_O;
				if(charLast==' ') x+=VOWEL_O;		
				else x+=MATRA_O;			
				break;
			case '`':
			 x+=HASANT;
				break;

			case 'h':
				endsH=true;				
				if(charLast==K) x+=KH;
				else if(charLast==G) x+=GH;			
				else if(charLast==CH) x+=CHH;
				else if(charLast==J) x+=JH;
				else if(charLast==T) x+=TH;
				else if(charLast==D) x+=DH;
				else if(charLast==t) x+=tH;
				else if(charLast==d) x+=dH;
				else if(charLast==P) x+=PH;
				else if(charLast==B) x+=BH;
				else if(charLast==N) x+=NH;
				//else if(charLast==R) x+=RR;
				else if(charLast==R) x+=RRH;
				else if(charLast==S) x+=sH;
				//else if(charLast==S) x+=SH;	
			        else x+=H;		
			break;
			
			case 'K':
			case 'k':
			x+=K;
			break;

			case 'G':
			case 'g':
			x+=G;
			break;

			case 'c':
			case 'C':
			x+=CH;
			break;			
			

			case 'j':
			case 'J':
			x+=J;
			break;

			//case 'c':
			case 'T':
			x+=T;
			break;

			//case 'c':
			case 'D':
			x+=D;
			break;

			//case 'c':
			case 't':
			x+=t;
			break;
	//		case 'c':
			case 'd':
			x+=d;
			break;			
			
			case 'n':
			x+=N;
			break;

			case 'P':
			case 'p':
			x+=P;
			break;
					
			case 'B':
			case 'b':
			x+=B;
			break;
			
			case 'S':
			case 's':
			x+=S;
			break;		
								
			case 'l':
			case 'L':
			x+=L;					
			break;

			case 'r':
			x+=R;
			break;
            case 'Z':
                x+=RR;
                break;
            case 'z':
                x+=RRH;
                break;
			case 'M':
                x+=UMA;
                break;
			case 'm':
			x+=M;
			break;
			
			case 'Y':
				x+=YA;
				break;

            case 'y':
                x+=YYA;
                break;
			case 'H':
			x+=H;
			break;

            case 'N':
                x+=ANUSHAR;
                break;
            case 'w':
                x+=BISARGA;
                break;
            case 'W':
                x+=CHANDRABINDU;
                break;

                case 'V':
                x+=sH;
                break;
            case 'v':
                x+=SH;
                break;
            case '0':
                x+=DGT_0;
                break;
            case '1':
                x+=DGT_1;
                break;
            case '2':
                x+=DGT_2;
                break;
           case '3':
                x+=DGT_3;
                break;
           case '4':
                x+=DGT_4;
                break;

           case '5':
                x+=DGT_5;
                break;

                case '6':
                x+=DGT_6;
                break;
                case '7':
                x+=DGT_7;
                break;

                case '8':
                x+=DGT_8;
                break;

            case '9':
                x+=DGT_9;
                break;

            case '|':
                x+=FULL_STOP;
                break;
            default:
                x+=ch;
		}


		
		return x;




	}



     


	public static void main(String[] ar) throws Exception{
/*		Class cls=Class.forName("BengaliKeyMapUtil");
		BengaliKeyMapUtil u=new BengaliKeyMapUtil();
		Field[] f=cls.getDeclaredFields();
		for(int i=0;i<f.length;i++){
			char ch=(char)f[i].getInt(u);		
			System.out.print(" "+ch);
		}	*/

		System.out.println(parseString("d",'h'));
	} 


}


