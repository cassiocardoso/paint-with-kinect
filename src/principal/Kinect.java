package principal;

import edu.ufl.digitalworlds.j4k.J4KSDK; 
import edu.ufl.digitalworlds.j4k.DepthMap; 
import edu.ufl.digitalworlds.j4k.Skeleton; 
import edu.ufl.digitalworlds.j4k.VideoFrame; 

/*This class is an implementation of the abstract class J4KSDK. 
  It is a simple example of source code that shows how to read  
  depth, video, and skeleton frames from a Kinect sensor.*/ 
public class Kinect extends J4KSDK{
	
	private float rightHandX = 0;
	private float rightHandY = 0;
	private float rightHandZ = 0;
	
	
	
	/*This object will hold the current video frame received from  
	the Kinect video camera.*/  
	VideoFrame videoTexture; 
	
	  /*The constructor of the class initializes the native Kinect 
	  SKD libraries and creates a new VideoFrame object.*/ 
	  public Kinect() { 
	      super(); 
	      videoTexture=new VideoFrame(); 
	  }
	  
	  
	  /*The following method will run every time a new depth frame is 
      received from the Kinect sensor. The packed data frame is 
      converted into a DepthMap object, with U,V texture mapping if 
      available.*/ 
    @Override 
    public void onDepthFrameEvent(short[] packed_depth, int[] U, int V[]) { 
         
        DepthMap map=new DepthMap(depthWidth(),depthHeight(),packed_depth); 
        if(U!=null && V!=null) map.setUV(U,V,videoWidth(),videoHeight()); 
    } 

    /*The following method will run every time a new skeleton frame 
      is received from the Kinect sensor. The skeletons are converted 
      into Skeleton objects.*/  
    @Override 
    public void onSkeletonFrameEvent(float[] data, boolean[] flags) { 
    	
    	
        Skeleton skeletons[]=new Skeleton[Kinect.NUI_SKELETON_COUNT];
        for(int i=0; i < Kinect.NUI_SKELETON_COUNT; i++) {
          skeletons[i]=Skeleton.getSkeleton(i, data, flags);     
          
          //Se um esqueleto for valido eu pego a posição da mão!!!
          if(flags[i] == true){
          	//System.out.println(i + " " + data[i] + " " + flags[i] + " " +skeletons[i].get3DJointX(NUI_SKELETON_POSITION_HAND_RIGHT));
          	setRightHandX(skeletons[i].get3DJointX(NUI_SKELETON_POSITION_HAND_RIGHT));
          	setRightHandY(skeletons[i].get3DJointY(NUI_SKELETON_POSITION_HAND_RIGHT));
          	setRightHandZ(skeletons[i].get3DJointZ(NUI_SKELETON_POSITION_HAND_RIGHT));
          }
          //System.out.println(skeletons[i].get3DJointX(11));
          //System.out.println(getSkeletonData().toString());
          //System.out.println(Skeleton.getSkeleton(i, data, flags).get3DJointX(11));
          //System.out.println("i:" + i + " " + skeletons[i].get3DJointX(NUI_SKELETON_POSITION_HAND_RIGHT) + " " + skeletons[i].get3DJointY(NUI_SKELETON_POSITION_HAND_RIGHT) + " " + + skeletons[i].get3DJointZ(NUI_SKELETON_POSITION_HAND_RIGHT)); 
          
        }
    } 

    /*The following method will run every time a new video frame 
      is received from the Kinect video camera. The incoming frame 
      is passed to the videoTexture object to update its content.*/     
    @Override 
    public void onVideoFrameEvent(byte[] data) {     

        videoTexture.update(videoWidth(), videoHeight(), data); 
    }

    
    
    
    //Getters and Setters
	public float getRightHandX() {
		return rightHandX;
	}


	public void setRightHandX(float rightHandX) {
		this.rightHandX = rightHandX;
	}


	public float getRightHandY() {
		return rightHandY;
	}


	public void setRightHandY(float rightHandY) {
		this.rightHandY = rightHandY;
	}


	public float getRightHandZ() {
		return rightHandZ;
	}


	public void setRightHandZ(float rightHandZ) {
		this.rightHandZ = rightHandZ;
	} 
	
	
	
	
}