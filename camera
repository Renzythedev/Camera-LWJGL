package lwjgl.lwjgl3d.Camera;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private float x,y,z;
    private float pitch,yaw,roll;
    private float fov;
    private float left,right,top,bottom;
    private float aspectRatio;
    private float zNear,zFar;
    private int mode;
    public final int MODE_2D = 2;
    public final int MODE_3D = 3;

    public Camera(Vector3f positions, Vector3f rotates, float fov,float aspectRatio,float zNear,float zFar) {
        this.x=positions.x;
        this.y=positions.y;
        this.z=positions.z;
        this.pitch=rotates.x;
        this.yaw= rotates.y;
        this.roll=rotates.z;
        this.fov=fov;
        this.aspectRatio=aspectRatio;
        this.zNear=zNear;
        this.zFar=zFar;
        this.mode=MODE_3D;
    }
    public Camera(float left,float right,float bottom,float top,float zNear,float zFar) {
        this.left=left;
        this.bottom=bottom;
        this.right=right;
        this.top=top;
        this.zNear=zNear;
        this.zFar=zFar;
        this.mode=MODE_2D;
    }

    public void set(String category) {
        if(category.equalsIgnoreCase("matrix") || category.equalsIgnoreCase("camera")) {
            if(mode==MODE_2D) {
                glOrtho(left,right,bottom,top,zNear,zFar);
            }else if(mode==MODE_3D) {
                GLU.gluPerspective(fov,aspectRatio,zNear,zFar);
            }
        }else if(category.equalsIgnoreCase("translations")) {
            if(mode==MODE_3D) {
                glRotatef(pitch,1,0,0);
                glRotatef(yaw,0,1,0);
                glRotatef(roll,0,0,1);
                glTranslatef(-x,-y,-z);
            }
        }

    }

    public void eventCamera(float sensitivity) {
        float maxLookUp = 85;
        float maxLookDown = -85;
        float mouseDX=Mouse.getDX();
        float mouseDY=Mouse.getDY();
        while(Mouse.isGrabbed()) {
            if(yaw + mouseDX >= 360) {
                yaw=yaw + mouseDX - 360;
            }else if(yaw + mouseDX < 0 ) {
                yaw=360-yaw + mouseDX;
            }else {
                yaw+=mouseDX;
            }
            if(pitch - mouseDY >=maxLookDown && pitch - mouseDY <= maxLookUp){
                pitch+= -mouseDY;
            }else if(pitch - mouseDY < maxLookDown) {
                pitch=maxLookDown;
            }else if (pitch- mouseDY >maxLookUp) {
                pitch=maxLookUp;
            }
        }
    }

    public void eventCamera(float sensitivity, float maxLookUp,float maxLookDown) {
        float mouseDX=Mouse.getDX();
        float mouseDY=Mouse.getDY();
        while(Mouse.isGrabbed()) {
            if(yaw + mouseDX >= 360) {
                yaw=yaw + mouseDX - 360;
            }else if(yaw + mouseDX < 0 ) {
                yaw=360-yaw + mouseDX;
            }else {
                yaw+=mouseDX;
            }
            if(pitch - mouseDY >=maxLookDown && pitch - mouseDY <= maxLookUp){
                pitch+= -mouseDY;
            }else if(pitch - mouseDY < maxLookDown) {
                pitch=maxLookDown;
            }else if (pitch- mouseDY >maxLookUp) {
                pitch=maxLookUp;
            }
        }
    }

    public void eventMove(int delta) {
        float i = 0.003f;
        float speedx = 1;
        float speedy = 1;
        float speedz = 1;
        boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W);
        boolean backward = Keyboard.isKeyDown(Keyboard.KEY_S);
        boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);
        boolean left = Keyboard.isKeyDown(Keyboard.KEY_A);
        boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        boolean flyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

        if(forward && !backward && !right && !left) {
            move(0,0,-speedz * delta * i);
        }
        if(backward && !forward && !right && !left) {
            move(0,0,speedz * delta * i);
        }
        if(right && !forward && !backward && !left) {
            move(speedx * delta * i,0,0);
        }
        if(left && !forward && !backward && !right) {
            move(-speedx * delta * i,0,0);
        }
        if(right && forward && !backward && !left) {
            move(speedx * delta * i,0,-speedz * delta * i);
        }
        if(left && forward && !backward && !left) {
            move(-speedx * delta * i,0,-speedz * delta * i);
        }
        if(right && backward && !forward && !left) {
            move(speedx * delta * i,0,speedz * delta * i);
        }
        if(left && backward && !forward && !left) {
            move(-speedx * delta * i,0,speedz * delta * i);
        }
        if(flyUp && !flyDown) {
            y += speedy * delta * i;
        }
        if(flyDown && !flyUp) {
            y -= speedy * delta * i;
        }

    }

    public void eventMove(int delta, float speedx,float speedy,float speedz) {
        float i = 0.003f;
        boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W);
        boolean backward = Keyboard.isKeyDown(Keyboard.KEY_S);
        boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);
        boolean left = Keyboard.isKeyDown(Keyboard.KEY_A);
        boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        boolean flyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

        if(forward && !backward && !right && !left) {
            move(0,0,-speedz * delta * i);
        }
        if(backward && !forward && !right && !left) {
            move(0,0,speedz * delta * i);
        }
        if(right && !forward && !backward && !left) {
            move(speedx * delta * i,0,0);
        }
        if(left && !forward && !backward && !right) {
            move(-speedx * delta * i,0,0);
        }
        if(right && forward && !backward && !left) {
            move(speedx * delta * i,0,-speedz * delta * i);
        }
        if(left && forward && !backward && !left) {
            move(-speedx * delta * i,0,-speedz * delta * i);
        }
        if(right && backward && !forward && !left) {
            move(speedx * delta * i,0,speedz * delta * i);
        }
        if(left && backward && !forward && !left) {
            move(-speedx * delta * i,0,speedz * delta * i);
        }
        if(flyUp && !flyDown) {
            y += speedy * delta * i;
        }
        if(flyDown && !flyUp) {
            y -= speedy * delta * i;
        }

    }

    private void move(float dx,float dy,float dz) {
        float usX = dx;
        float adX = usX * (float)Math.cos(Math.toRadians(yaw - 90));
        float opX = usX * (float)Math.sin(Math.toRadians(yaw - 90));
        this.x -= opX;
        this.z += adX;

        y+=dy;

        float usZ = dz;
        float adZ= usZ * (float)Math.cos(Math.toRadians(yaw));
        float opZ  = usZ * (float)Math.sin(Math.toRadians(yaw));
        this.x -= opZ;
        this.z += adZ;

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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public float getFov() {
        return fov;
    }

    public void setFov(float fov) {
        this.fov = fov;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public float getzNear() {
        return zNear;
    }

    public void setzNear(float zNear) {
        this.zNear = zNear;
    }

    public float getzFar() {
        return zFar;
    }

    public void setzFar(float zFar) {
        this.zFar = zFar;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }
}
