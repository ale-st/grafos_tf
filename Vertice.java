import java.awt.Color;
import java.awt.Graphics;

public class Vertice {

  private final int SIZE = 25;
  private final Color GRAY = new Color(56, 66, 58);

  private int name;
  private int value;
  private int deg, x, y, size;
  private Color color;
  private int group;
  private int[] pos;


  public Vertice(int name, int value, int line, int col, int posx, int posy) {
    this.name = name;
    deg = 0;
    this.x = posx;
    this.y = posy;
    this.size = this.SIZE;
    color = GRAY;
    this.value = value;
    pos = new int[2];
    pos[0] = line; pos[1] = col;
  }

  public int getName() {
    return name;
  }

  public int getLine() {
    return pos[0];
  }

  public int getCol() {
    return pos[1];
  }

  public int[] getPos() {
    return pos;
  }

  public int getDeg() {
    return deg;
  }

  public int getValue() {
    return value;
  }

  public void incDegree() {
    deg += 1;
  }

  public void decDegree() {
    deg -= 1;
  }

  public void setGroup(int n) {
    this.group = n;
  }

  public int getGroup() {
    return group;
  }

  public String toString() {
    return name+"";
  }

  public void setValue(int n) {
    this.value = n;
  }

  //para GUI

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getSize() {
    return size;
  }

  public void setColor(Color c) {
    this.color = c;
  }

  public Color getColor() {
    return color;
  }

  public int getCenterX() {
    return x + size / 2;
  }

  public int getCenterY() {
    return y + size / 2;
  }

  public void paintVertice(Graphics g) {
    g.setColor(color);
    g.fillOval(x, y, size, size);
    g.setColor(Color.WHITE);
    String title = (value==0)? "": ""+value;
    g.drawString(title, x + size / 2-4, y + size / 2+4);
  }

  public void paintEdge(Graphics g, Vertice v) {
    g.setColor(Color.black);
    g.drawLine(
      this.getCenterX(),
      this.getCenterY(),
      v.getCenterX(),
      v.getCenterY()
    );
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Vertice)) {
      return false;
    }

    Vertice v = (Vertice) o;

    if (
      v.name == this.name &&
      v.deg == this.deg &&
      this.x == v.x &&
      this.y == v.y &&
      this.pos[0] == v.pos[0] &&
      this.pos[1] == v.pos[1]
    ) {
      return true;
    }

    return this.name == v.name || this.pos[0] == v.pos[0] &&
      this.pos[1] == v.pos[1];
  }
}
