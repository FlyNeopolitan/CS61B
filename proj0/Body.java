public class Body{

  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public static double G=6.67e-11;

  public Body(double xP,double yP,double xV,double yV,double m,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
        }

  public Body(Body b) {
        xxPos=b.xxPos;
        yyPos=b.yyPos;
        xxVel=b.xxVel;
        yyVel=b.yyVel;
        mass=b.mass;
        imgFileName=b.imgFileName;
        }
  public double calcDistance(Body b){
      double Distance=Math.sqrt((xxPos-b.xxPos)*(xxPos-b.xxPos)+(yyPos-b.yyPos)*(yyPos-b.yyPos));
      return Distance;
  }

  public double calcForceExertedBy(Body b){
      double Force=G*mass*(b.mass)/(calcDistance(b)*calcDistance(b));
      return Force;
  }

  public double calcForceExertedByX(Body b){
        double dx=b.xxPos-xxPos;
        double Force=calcForceExertedBy(b)*dx/calcDistance(b);
        return Force;
  }

  public double calcForceExertedByY(Body b){
        double dy=b.yyPos-yyPos;
        double Force=calcForceExertedBy(b)*dy/calcDistance(b);
        return Force;
  }

  public double calcNetForceExertedByX(Body[] allBodys){
       double NetForce=0;
       for(int i=0;i< allBodys.length;i++){
           if(this.equals(allBodys[i])){
               continue;
           }
           else {
               NetForce = NetForce + calcForceExertedByX(allBodys[i]);
           }
       }
       return NetForce;
  }

  public double calcNetForceExertedByY(Body[] allBodys){
        double NetForce=0;
        for(int i=0;i< allBodys.length;i++){
            if(this.equals(allBodys[i])){
                continue;
            }
            else {
                NetForce = NetForce + calcForceExertedByY(allBodys[i]);
            }
        }
        return NetForce;
  }

  public void update(double dt,double fX,double fY){
      double aX=fX/mass; double aY=fY/mass;
      xxVel=xxVel+dt*aX; yyVel=yyVel+dt*aY;
      xxPos=xxPos+dt*xxVel; yyPos=yyPos+dt*yyVel;
  }

  public void draw(){
      StdDraw.picture(xxPos,xxVel,"images/"+imgFileName);
  }






}