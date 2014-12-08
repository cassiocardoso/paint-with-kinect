package principal;

public class Draw {
	private float x = 0;
	private float y = 0;
	private float r = 0;
	private float g = 0;
	private float b = 0;
	
	
	public Draw(){
		
	}
	public Draw(float x, float y, float r, float g, float b){
		this.x = x;
		this.y = y;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getR() {
		return r;
	}
	public void setR(float r) {
		this.r = r;
	}
	public float getG() {
		return g;
	}
	public void setG(float g) {
		this.g = g;
	}
	public float getB() {
		return b;
	}
	public void setB(float b) {
		this.b = b;
	}
	
	public String toString(){
		return x + "," + y + "," + r + "," + g + "," + b;
	}
}
