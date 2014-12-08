package principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Disk;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import edu.ufl.digitalworlds.gui.DWApp;

public class paintEng {
	
	//Tamanho total do Monitor
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
	//Tamanho do Display
	float dispH = 0f;
	float dispW = 0f;
	
	//Posições do cursor
	float xX = 0f;
	float yY = 0f;
	float zZ = 0f;
	
	//Cor do giz vigente
	float r = 1f;
	float g = 0f;
	float b = 0f;
	//Cor corpo do giz vigente
	float rC = 1f;
	float gC = 0.38f;
	float bC = 0.27f;
	
	//Fator de textura do giz principal
	int fatorTextura = 350;
	
	//Angulo Z do giz principal
	float zAngle = 0f;
	
	//Proporção
	float proporcao = 0f;
	//Tamanho da perspectiva
	float perspectiva = 60f;
	
	//Fator de Proporção do kinect
	float propKinectX = 0.2f;
	float propKinectY = 0.2f;
	float propKinectZ = 0.2f;
	
	//Limite de aproximação
	float limiteAproximacao= 180;
	
	
	//Intancia  de Cilindro, Esfera e Disco
	Cylinder cylinder = new Cylinder();
	Sphere sphere = new Sphere();
	Disk disk = new Disk();
	
	//Mapeamento de desenho
	HashMap<String, Draw> mapDraw = new HashMap<String, Draw>(); 
	
	Kinect myKinect;
	
	public void textureMap(float x, float y){
		//Faz um random de até 300 pontos por cada ponto do cursor dentro de um raio de 10 pixels, sendo que se aumentar o fator maior a textura do giz
		for (int cont = 0; cont < fatorTextura; cont++){
	    	 
			 //Estrutura de dados de desenho
			 Draw draw;
			 
			 //Variaveis ramdomicas
	    	 double random = new Random().nextFloat();
	    	 double rnd = (x -5) + (random * ((x+5) - (x -5))); //-5 e + 5 é o raio da area a ser pintada
	     	 //double rnd = (xX -5) + (random * ((xX+5) - (xX -5))); //-5 e + 5 é o raio da area a ser pintada
	     	 
	     	 //Angulo aleatorio para fazer pontos num circulo
	     	 float angle = (float) ((0 + (random * (360 - 0))));
	     	 
	     	 //São os limites circulares em X e em Y
	     	 float limX = (float) (5f * Math.cos(angle));
	     	 float limY = (float) (5f * Math.sin(angle));
	     	 
	     	 //Variaveis com X e Y randomizados
	     	 float newX = 0;
	     	 float newY = 0;
	     	 
	     	 //Formato decimal com 2 casas num float
	     	 DecimalFormat dc = new DecimalFormat("#.0");  
	    	 
	     	 //ramdomizo um valor em X, subtraio seu limite angular, multiplico pelo fator de perspectiva e coloco no formato decimal
	    	 newX = Float.parseFloat(dc.format(((((rnd-limX)-(dispW/2)) * perspectiva * proporcao)/(dispW/2))).replaceAll(",", "."));
	    	 
	    	 random = new Random().nextFloat();
	    	 //rnd = (yY -5) + (random * ((yY+5) - (yY -5)));
	    	 rnd = (y -5) + (random * ((y+5) - (y -5)));
	    	 //ramdomizo um valor em Y, subtraio seu limite angular, multiplico pelo fator de perspectiva e coloco no formato decimal
	    	 newY = Float.parseFloat(dc.format(((((rnd -limY)- (dispH/2))*perspectiva)/(dispH/2))).replaceAll(",", "."));
	    	 
	    	 //Adiciono todos os valores na estrutura
	    	 draw = new Draw(newX, newY, this.r, this.g, this.b);
	    	 
	    	 //HashMap do desenho
	    	 mapDraw.put("x:"+newX+",y:"+newY, draw);
	     }
	}
	 
	public void mainCrayon(float r, float g, float b, float rC, float gC, float bC){
		
		GL11.glPushMatrix();
		
		GL11.glRotatef(zZ, 0, 0, 1);	
		GL11.glRotatef(zZ, 0, 1, 0);

			//Esfera da ponta
		    GL11.glPushMatrix();
		    	GL11.glColor3f(r, g, b);
				GL11.glTranslatef( 0, 0, 0);
				sphere.draw(1.0f, 50, 50);
			GL11.glPopMatrix();
		
			//cone depois da ponta
			GL11.glPushMatrix();
				GL11.glColor3f(r, g, b);
				GL11.glTranslatef( 0f, 0.0f, 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				cylinder.draw(1.0f, 1.4f, 2, 50, 50);
			GL11.glPopMatrix();
			
			//dico fecha cone
			GL11.glPushMatrix();
				GL11.glColor3f(r, g, b);
				GL11.glTranslatef(0f, -2f, 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				disk.draw(0f, 1.5f, 50, 50);
			GL11.glPopMatrix();
			
			
			//cilindro giz
			GL11.glPushMatrix();
				GL11.glColor3f(r, g, b);
				GL11.glTranslatef(0f, - 2f, 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				cylinder.draw(1.5f, 1.5f, 0.8f, 50, 50);
			GL11.glPopMatrix();
			
			
			//corpo giz 
			GL11.glPushMatrix();
				GL11.glColor3f(rC, gC , bC);
				GL11.glTranslatef(0f, -2.8f, 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				cylinder.draw(1.5f, 1.5f, 8, 50, 50);
			GL11.glPopMatrix();		
			
			//giz fim
			GL11.glPushMatrix();
			    GL11.glColor3f(r, g, b);
				GL11.glTranslatef( 0f, -10.8f, 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				cylinder.draw(1.5f, 1.5f, 0.5f, 50, 50);
			GL11.glPopMatrix();
			
			//disco fecha giz
			GL11.glPushMatrix();
			    GL11.glColor3f(r, g, b);
				GL11.glTranslatef(0f, -11.3f, 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				disk.draw(0f, 1.5f, 50, 50);
			GL11.glPopMatrix();
			
		GL11.glPopMatrix();
	}
	
	
	
	public void drawCrayons(float x, float y, float z, float r, float g, float b, float rC, float gC, float bC){
		
		//Verifico se o cursor está dentro da area do giz e mudo a cor do giz principal
		//Esta conta se deve pelo fator angular da perspectiva,
		//Tenho uma áerea total que está diretamente proporcional a "variavel perspectiva" de dimensão
		if(  x > (((xX- (dispW/2))*perspectiva*proporcao)/(dispW/2) - 2.5f) && x < (((xX- (dispW/2))*perspectiva*proporcao)/(dispW/2) + 2.5f)  &&  
			 y < ((yY-(dispH/2))*perspectiva)/(dispH/2) && y > (((yY-(dispH/2))*perspectiva)/(dispH/2) - 20)){

			y += 5f;
			
			this.r = r;
			this.g = g;
			this.b = b;
			
			this.rC = rC;
			this.gC = gC;
			this.bC = bC;
		}
		
		GL11.glPushMatrix();			
			//Esfera da ponta
		    GL11.glPushMatrix();
		    	GL11.glColor3f(r, g, b);
				GL11.glTranslatef( x + 0f, y + 18.8f, z + 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				sphere.draw(1.5f, 50, 50);
			GL11.glPopMatrix();
		
			//cone depois da ponta
			GL11.glPushMatrix();
				GL11.glColor3f(r, g, b);
				GL11.glTranslatef(x + 0f, y + 19f, z + 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				cylinder.draw(1.5f, 1.9f, 4, 50, 50);
			GL11.glPopMatrix();
			
			//dico fecha cone
			GL11.glPushMatrix();
				GL11.glColor3f(r, g, b);
				GL11.glTranslatef(x + 0f, y + 15f, z + 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				disk.draw(0f, 2f, 50, 50);
			GL11.glPopMatrix();
			
			
			//cilindro giz
			GL11.glPushMatrix();
				GL11.glColor3f(r, g,b);
				GL11.glTranslatef(x + 0f, y + 15f, z + 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				cylinder.draw(2f, 2f, 1, 50, 50);
			GL11.glPopMatrix();
			
			
			//corpo giz 
			GL11.glPushMatrix();
				GL11.glColor3f(rC, gC , bC);
				GL11.glTranslatef( x + 0f, y + 14f, z + 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				cylinder.draw(2f, 2f, 14, 50, 50);
			GL11.glPopMatrix();		
			
			//giz fim
			GL11.glPushMatrix();
			    GL11.glColor3f(r, g, b);
				GL11.glTranslatef( x + 0f, y + 0f, z + 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				cylinder.draw(2f, 2f, 1, 50, 50);
			GL11.glPopMatrix();
			
			//disco fecha giz
			GL11.glPushMatrix();
			    GL11.glColor3f(r, g, b);
				GL11.glTranslatef( x + 0f, y + 0f, z + 0f);
				GL11.glRotatef(90, 1f, 0, 0);
				disk.draw(0f, 2f, 50, 50);
			GL11.glPopMatrix();
			
		GL11.glPopMatrix();
	}
	

	public void drawLine(){
				
		Iterator<Entry<String, Draw>> iterator = mapDraw.entrySet().iterator();

		GL11.glPushMatrix();
	    	
	    	GL11.glBegin(GL11.GL_LINES);

	    	while(iterator.hasNext()){
	 		    Entry<String, Draw> entry = iterator.next();
	 		    Draw draw = new Draw();
	 		    draw = entry.getValue();

	 		    GL11.glColor3f(draw.getR(), draw.getG(), draw.getB());
	    	    GL11.glVertex2f(draw.getX(), draw.getY());
	  	    	GL11.glVertex2f(draw.getX(), draw.getY() + 0.1f); 
	 		}
	    	GL11.glEnd();
    	GL11.glPopMatrix();
	}
	

	public void start() {
    	
		try {
			
			//START KINECT
		    myKinect = new Kinect();
		    if(myKinect.start(true,Kinect.NUI_IMAGE_RESOLUTION_320x240,Kinect.NUI_IMAGE_RESOLUTION_640x480)==0)
			{
				DWApp.showErrorDialog("ERROR", "<html><center><br>ERROR: The Kinect device could not be initialized.<br><br>1. Check if the Microsoft's Kinect SDK was succesfully installed on this computer.<br> 2. Check if the Kinect is plugged into a power outlet.<br>3. Check if the Kinect is connected to a USB port of this computer.</center>");
			}
		    
		    //Inicia o tracking do kinect
		    myKinect.startSkeletonTracking(true);
		    //Inicia track de esqueleto por perto
		    myKinect.setNearMode(true);

			 //Display.setDisplayMode(new DisplayMode(800, 800));
		     Display.setDisplayMode(new DisplayMode((int)width, (int)height));
			 Display.setFullscreen(true);
			 Display.setTitle("Crayon Kinect Paint");
		     Display.create();

		     dispW = Display.getWidth();
		     dispH = Display.getHeight();
		    
		     proporcao = dispW / dispH;
		 
		 } catch (LWJGLException e) {
			 e.printStackTrace();
			 myKinect.stop();
			 System.exit(0);
		 }
        
	     //init OpenGL
	        
	     //Setando a luz   
	     GL11.glShadeModel(GL11.GL_SMOOTH);   	 
	   	 GL11.glEnable(GL11.GL_LIGHTING);
	   	 GL11.glEnable(GL11.GL_LIGHT0);
	   	 GL11.glEnable(GL11.GL_DEPTH_TEST);
	   	 GL11.glEnable(GL11.GL_COLOR_MATERIAL); 
	   	 GL11.glEnable(GL11.GL_BLEND);
	   	 GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	   	 
	   	 //cor de fundo
	     GL11.glClearColor(0.99f, 0.99f, 0.99f, 0);
		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glLoadIdentity(); 
		 
		 //perspectiva
		 //GLU.gluPerspective(90f, 1f, 1f, 60f); 
		 
		 //Se NÃO for um quadrado a proporção irá mudar
		 if(proporcao >= 1)
			 GL11.glOrtho (-perspectiva * proporcao, perspectiva * proporcao, -perspectiva, perspectiva, -perspectiva, perspectiva);
		 else
			 GL11.glOrtho (-perspectiva , perspectiva , -perspectiva * proporcao, perspectiva * proporcao, -perspectiva, perspectiva);
		 
		 GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 GL11.glLoadIdentity(); 
		 
		 //camera de visão
		 GLU.gluLookAt( 0f, 0f, 10.0f,
		           		0.0f, 0.0f, 0.0f,
		           		0.0f, 1f, 0.0f );
	
		 //init OpenGL here
		 	
		 while (!Display.isCloseRequested()) {
	
	         // Limpa a tela e aprofunda o buffer
			 GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			 
			 xX = ( (myKinect.getRightHandX() * dispW) / propKinectX);
			 yY = ( (myKinect.getRightHandY() * dispH) / propKinectY);
			 zZ = ( (-myKinect.getRightHandZ() * limiteAproximacao) / propKinectZ);
			 
			 //Limitador de profundidade e angulo do giz
			 if(zZ > (-936)){
				textureMap(xX, yY);
				if(zZ > (-940)){
					textureMap(xX, yY);
					zZ = -940;
				}
			 }
			 else if (zZ < (-1020)){
				 zZ = -1020;
			 }
	    	    
			 //Chamo o giz principal
	    	 GL11.glPushMatrix();
	    	 	//posição em relação ao cursor levando em conta a proporcionalidade a perspectiva
	 	    	GL11.glTranslatef(((xX- (dispW/2)) * perspectiva * proporcao)/(dispW/2), ((yY-(dispH/2)) * perspectiva )/(dispH/2), 0);
		    	mainCrayon( r, g, b, 
							rC, bC, bC);
		     GL11.glPopMatrix();
	    	    
		     //função que pinta a tela
		     drawLine();	
		    	
		    //Inicio a paleta de cores
	    	GL11.glPushMatrix();
	    	    
	    		int x = -30;
	    	    int y = -55;
	    	    	
	    	    //Vermelho
		    	drawCrayons(x, y, 0, 
		    				1f, 0f, 0f, 
		    				1f, 0.38f, 0.27f);
		    	//Laranja
		    	drawCrayons(x+8, y, 0, 
	    					1f, 0.54f, 0f, 
	    					1f, 1f, 0f);
		    	//Amarelo
		    	drawCrayons(x+16, y, 0, 
		    				1f, 1f, 0f, 
	    					1f, 0.84f, 0.0f);
		    	//verde
		    	drawCrayons(x+24, y, 0, 
	    					0f, 1f, 0f, 
	    					0.18f, 0.54f, 0.34f);
		    	//Azul
		    	drawCrayons(x+32, y, 0, 
		    				0.11f, 0.56f, 1f, 
	    					0.25f, 0.41f, 1f);
		    	//Anil
		    	drawCrayons(x+40, y, 0, 
	    					0.29f, 0.0f, 0.50f, 
	    					0.58f, 0.0f, 0.82f);
		    	//Violeta
		    	drawCrayons(x+48, y, 0, 
	    					0.93f, 0.50f, 0.93f, 
	    					0.85f, 0.43f, 0.83f);
		    		
		    	//Branco
		    	drawCrayons(x+56, y, 0, 
	    					0.99f, 0.99f, 0.99f, 
	    					0.8f, 0.80f, 0.80f);
		    		
		    	//Preto
		    	drawCrayons(x+ 64, y, 0, 
	    					0f, 0.0f, 0.0f, 
	    					0.2f, 0.2f, 0.2f);
		    		
		    GL11.glPopMatrix();
		    	
	
		    pollInput();
		    Display.update();
		 }

		 Display.destroy();
    }
	
	
	public void resetKinect()
	{
		myKinect.stop();
		
		myKinect.startSkeletonTracking(true);
		myKinect.setNearMode(true);
	}

    public void pollInput() {
		
    	if(Mouse.isButtonDown(1)){
    		System.out.println("Mudar o angulo do Kinect");
    	}
		
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_F1) {
				r = 1f;
				g = 0f;
			    b = 0f;
				rC = 1f;
				gC = 0.38f;
				bC = 0.27f;
				
				mapDraw.clear();
				
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				System.exit(0);
				myKinect.stop();
			}
		    
		}
    }

    public static void main(String[] argv) {
        paintEng inputExample = new paintEng();
        inputExample.start();
    }
}